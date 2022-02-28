package by.itacademy.zuevvlad.bankaccountproject.dao.database.file;

import by.itacademy.zuevvlad.bankaccountproject.dao.database.DataBase;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.exception.*;
import by.itacademy.zuevvlad.bankaccountproject.entity.Entity;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.file.daemonthreadfactory.DaemonThreadFactory;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.file.objectstreamwithoutheader.ObjectInputStreamNotReadingHeader;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.file.objectstreamwithoutheader.ObjectOutputStreamNotWritingHeader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class FileDataBase<TypeOfStoredEntity extends Entity> implements DataBase<TypeOfStoredEntity>
{
    private final File file;
    private final Lock lock;
    private final Condition conditionOfReloading;
    private boolean reloading;
    private final DaemonThreadFactory daemonThreadFactory;

    public static <TypeOfStoredEntity extends Entity> FileDataBase<TypeOfStoredEntity> createFileDataBase(
            final File file)
            throws DataBaseCreatingException
    {
        if(!(file.exists() && file.isFile()))
        {
            throw new DataBaseCreatingException(file.getAbsolutePath() + " doesn't exist or it is not file.");
        }
        final Lock lock = new ReentrantLock();
        final Condition conditionOfReloading = lock.newCondition();
        final boolean reloading = false;
        final DaemonThreadFactory daemonThreadFactory = DaemonThreadFactory.createDaemonThreadFactory();
        return new FileDataBase<TypeOfStoredEntity>(file, lock, conditionOfReloading, reloading, daemonThreadFactory);
    }

    private FileDataBase(final File file, final Lock lock, final Condition conditionOfReloading,
                         final boolean reloading, final DaemonThreadFactory daemonThreadFactory)
    {
        super();

        this.file = file;
        this.lock = lock;
        this.conditionOfReloading = conditionOfReloading;
        this.reloading = reloading;
        this.daemonThreadFactory = daemonThreadFactory;
    }

    public final File getFile()
    {
        return this.file;
    }

    public final boolean isReloading()
    {
        return this.reloading;
    }

    @Override
    public final void loadEntity(final TypeOfStoredEntity loadedEntity)
            throws DataBaseLoadingEntityException
    {
        this.lock.lock();
        try(final FileOutputStream fileOutputStream = new FileOutputStream(this.file, true);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStreamNotWritingHeader(bufferedOutputStream))
        {
            while(this.reloading)
            {
                this.conditionOfReloading.await();
            }
            objectOutputStream.writeObject(loadedEntity);
        }
        catch(final IOException | InterruptedException cause)
        {
            throw new DataBaseLoadingEntityException(cause);
        }
        finally
        {
            this.lock.unlock();
        }
    }

    @Override
    public final Future<Collection<TypeOfStoredEntity>> offloadEntities()
    {
        final ExecutorService executorService = Executors.newSingleThreadExecutor(this.daemonThreadFactory);
        return executorService.submit(new OffLoaderFileDataBase());
    }

    private final class OffLoaderFileDataBase implements Callable<Collection<TypeOfStoredEntity>>
    {
        public OffLoaderFileDataBase()
        {
            super();
        }

        @Override
        public final Collection<TypeOfStoredEntity> call()
                throws OffloadingEntitiesException
        {
            final Collection<TypeOfStoredEntity> deserializedEntities = new LinkedHashSet<TypeOfStoredEntity>();
            FileDataBase.this.lock.lock();
            try(final FileInputStream fileInputStream = new FileInputStream(FileDataBase.this.file);
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                final ObjectInputStream objectInputStream = new ObjectInputStreamNotReadingHeader(bufferedInputStream))
            {
                while(FileDataBase.this.reloading)
                {
                    FileDataBase.this.conditionOfReloading.await();
                }
                Object lastDeserializedObject;
                TypeOfStoredEntity lastDeserializedEntity;
                while(true)
                {
                    lastDeserializedObject = objectInputStream.readObject();
                    if(!(lastDeserializedObject instanceof Entity))
                    {
                        throw new OffloadingEntitiesException("Object was found in data base, which is not "
                                + "entity. Class of found object: '" + lastDeserializedObject.getClass().getName() + "'.");
                    }
                    lastDeserializedEntity = (TypeOfStoredEntity)lastDeserializedObject;
                    deserializedEntities.add(lastDeserializedEntity);
                }
            }
            catch(final EOFException exceptionOfEndDeserialization)
            {
                return deserializedEntities;
            }
            catch(final IOException | ClassNotFoundException | InterruptedException cause)
            {
                throw new OffloadingEntitiesException(cause);
            }
            finally
            {
                FileDataBase.this.lock.unlock();
            }
        }
    }

    @Override
    public final Future<Boolean> reload(final Collection<TypeOfStoredEntity> newEntitiesOfDataBase)
    {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(new ReLoaderFileDataBase(newEntitiesOfDataBase));
    }

    private final class ReLoaderFileDataBase implements Callable<Boolean>
    {
        private final Collection<TypeOfStoredEntity> newEntitiesOfDataBase;

        public ReLoaderFileDataBase(final Collection<TypeOfStoredEntity> newEntitiesOfDataBase)
        {
            super();
            this.newEntitiesOfDataBase = newEntitiesOfDataBase;
        }

        @Override
        public final Boolean call()
        {
            try
            {
                if(this.newEntitiesOfDataBase.isEmpty())
                {
                    FileDataBase.this.clear();
                }
            }
            catch(final DataBaseClearingException cause)
            {
                throw new ReloadingEntitiesException(cause);
            }
            FileDataBase.this.lock.lock();
            FileDataBase.this.reloading = true;
            boolean successReloading;
            try(final FileOutputStream fileOutputStream = new FileOutputStream(FileDataBase.this.file);
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                final ObjectOutputStream objectOutputStream = new ObjectOutputStreamNotWritingHeader(
                        bufferedOutputStream))
            {
                for(final TypeOfStoredEntity serializedEntity : this.newEntitiesOfDataBase)
                {
                    objectOutputStream.writeObject(serializedEntity);
                }
                successReloading = true;
            }
            catch(final IOException cause)
            {
                successReloading = false;
            }
            finally
            {
                FileDataBase.this.reloading = false;
                FileDataBase.this.conditionOfReloading.signalAll();
                FileDataBase.this.lock.unlock();
            }
            return successReloading;
        }
    }

    @Override
    public final void clear()
            throws DataBaseClearingException
    {
        try(final FileOutputStream fileOutputStream = new FileOutputStream(FileDataBase.this.file);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream))
        {
            bufferedOutputStream.write("".getBytes(StandardCharsets.UTF_8));
        }
        catch(final IOException cause)
        {
            throw new DataBaseClearingException(cause);
        }
    }
}
