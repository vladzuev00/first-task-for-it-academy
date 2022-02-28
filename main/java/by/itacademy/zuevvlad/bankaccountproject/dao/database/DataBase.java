package by.itacademy.zuevvlad.bankaccountproject.dao.database;

import by.itacademy.zuevvlad.bankaccountproject.dao.database.exception.*;
import by.itacademy.zuevvlad.bankaccountproject.entity.Entity;

import java.util.Collection;
import java.util.concurrent.Future;

public interface DataBase<TypeOfStoredEntity extends Entity>
{
    public abstract void loadEntity(final TypeOfStoredEntity loadedEntity)
            throws DataBaseLoadingEntityException;
    public abstract Future<Collection<TypeOfStoredEntity>> offloadEntities();
    public abstract Future<Boolean> reload(final Collection<TypeOfStoredEntity> newEntitiesOfDataBase)
            throws ReloadingEntitiesException;
    public abstract void clear()
        throws DataBaseClearingException;
}