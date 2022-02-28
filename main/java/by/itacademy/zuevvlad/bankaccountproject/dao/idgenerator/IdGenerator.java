package by.itacademy.zuevvlad.bankaccountproject.dao.idgenerator;

import by.itacademy.zuevvlad.bankaccountproject.dao.idgenerator.exception.IdGeneratorCreatingException;
import by.itacademy.zuevvlad.bankaccountproject.dao.idgenerator.exception.IdGeneratorGenerationException;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class IdGenerator
{
    private long nextGeneratedId;
    private final Lock lock;
    private final File fileWithLastGeneratedId;

    private IdGenerator(final long nextGeneratedId, final File fileWithLastGeneratedId)
    {
        super();
        this.nextGeneratedId = nextGeneratedId;
        this.lock = new ReentrantLock();
        this.fileWithLastGeneratedId = fileWithLastGeneratedId;
    }

    public static IdGenerator createIdGenerator(final File fileWithLastGeneratedId)
            throws IdGeneratorCreatingException
    {
        try(final FileInputStream fileInputStream = new FileInputStream(fileWithLastGeneratedId);
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream))
        {
            final long lastGeneratedId = objectInputStream.readLong();
            final long nextGeneratedIdOfGenerator = lastGeneratedId + 1;
            return new IdGenerator(nextGeneratedIdOfGenerator, fileWithLastGeneratedId);
        }
        catch(final EOFException eofException)    //when file is empty(id haven't been generated yet)
        {
            return new IdGenerator(IdGenerator.INITIAL_VALUE_OF_NEXT_GENERATED_ID, fileWithLastGeneratedId);
        }
        catch(final IOException cause)
        {
            throw new IdGeneratorCreatingException(cause);
        }
    }

    private static final long INITIAL_VALUE_OF_NEXT_GENERATED_ID = 0;

    public final long generateId()
            throws IdGeneratorGenerationException
    {
        this.lock.lock();
        try
        {
            final long generatedId = this.nextGeneratedId;
            this.nextGeneratedId++;
            this.writeLastGeneratedId(generatedId);
            return generatedId;
        }
        finally
        {
            this.lock.unlock();
        }
    }

    private void writeLastGeneratedId(final long writtenId)
            throws IdGeneratorGenerationException
    {
        this.lock.lock();
        try(final FileOutputStream fileOutputStream = new FileOutputStream(this.fileWithLastGeneratedId);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            final ObjectOutputStream objectInputStream = new ObjectOutputStream(bufferedOutputStream))
        {
            objectInputStream.writeLong(writtenId);
        }
        catch(final IOException cause)
        {
            throw new IdGeneratorGenerationException(cause);
        }
        finally
        {
            this.lock.unlock();
        }
    }
}
