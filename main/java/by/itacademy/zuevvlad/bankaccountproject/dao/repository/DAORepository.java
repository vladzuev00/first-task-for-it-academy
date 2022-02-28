package by.itacademy.zuevvlad.bankaccountproject.dao.repository;

import by.itacademy.zuevvlad.bankaccountproject.dao.database.DataBase;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.exception.DataBaseClearingException;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.exception.DataBaseLoadingEntityException;
import by.itacademy.zuevvlad.bankaccountproject.dao.idgenerator.IdGenerator;
import by.itacademy.zuevvlad.bankaccountproject.dao.idgenerator.exception.IdGeneratorGenerationException;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception.*;
import by.itacademy.zuevvlad.bankaccountproject.entity.Entity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public abstract class DAORepository<TypeOfStoredEntity extends Entity>
{
    private final DataBase<TypeOfStoredEntity> dataBase;
    private final IdGenerator idGenerator;

    public DAORepository(final DataBase<TypeOfStoredEntity> dataBase, final IdGenerator idGenerator)
    {
        super();

        this.dataBase = dataBase;
        this.idGenerator = idGenerator;
    }

    public final void addEntity(final TypeOfStoredEntity addedEntity)
            throws DAOAddingEntityException
    {
        try
        {
            final long generatedId = this.idGenerator.generateId();
            addedEntity.setId(generatedId);
            this.dataBase.loadEntity(addedEntity);
        }
        catch(final IdGeneratorGenerationException | DataBaseLoadingEntityException cause)
        {
            throw new DAOAddingEntityException(cause);
        }
    }

    public final void addEntities(final Collection<TypeOfStoredEntity> addedEntities)
            throws DAOAddingEntityException
    {
        for(final TypeOfStoredEntity addedEntity : addedEntities)
        {
            this.addEntity(addedEntity);
        }
    }

    public final boolean isEntityWithGivenIdExisting(final long idOfResearchEntity)
            throws DAODefiningExistingEntityException
    {
        try
        {
            final Future<Collection<TypeOfStoredEntity>> futureOfAllEntities = this.dataBase.offloadEntities();
            final Collection<TypeOfStoredEntity> allEntities = futureOfAllEntities.get();
            return allEntities.parallelStream().anyMatch((final TypeOfStoredEntity researchEntity) ->
            {
                return researchEntity.getId() == idOfResearchEntity;
            });
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DAODefiningExistingEntityException(cause);
        }
    }

    public final Optional<TypeOfStoredEntity> findEntity(final long idOfResearchEntity)
            throws DAOFindingEntityException
    {
        try
        {
            final Future<Collection<TypeOfStoredEntity>> futureOfAllEntities = this.dataBase.offloadEntities();
            final Collection<TypeOfStoredEntity> allEntities = futureOfAllEntities.get();
            for(final TypeOfStoredEntity entity : allEntities)
            {
                if(entity.getId() == idOfResearchEntity)
                {
                    return Optional.of(entity);
                }
            }
            return Optional.empty();
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DAOFindingEntityException(cause);
        }
    }

    public final Collection<TypeOfStoredEntity> findEntities()
            throws DAOFindingEntityException
    {
        try
        {
            final Future<Collection<TypeOfStoredEntity>> futureOfAllEntities = this.dataBase.offloadEntities();
            return futureOfAllEntities.get();
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DAOFindingEntityException(cause);
        }
    }

    public final int findAmountOfEntities()
            throws DAOFindingAmountOfEntitiesException
    {
        try
        {
            final Future<Collection<TypeOfStoredEntity>> futureOfAllEntities = this.dataBase.offloadEntities();
            final Collection<TypeOfStoredEntity> allEntities = futureOfAllEntities.get();
            return allEntities.size();
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DAOFindingAmountOfEntitiesException(cause);
        }
    }

    public final void updateEntity(final TypeOfStoredEntity updatedEntity)
            throws DAOUpdatingEntityException
    {
        try
        {
            final Future<Collection<TypeOfStoredEntity>> futureOfAllEntities = this.dataBase.offloadEntities();
            final Collection<TypeOfStoredEntity> allEntities = futureOfAllEntities.get();
            for(final TypeOfStoredEntity entity : allEntities)
            {
                if(entity.getId() == updatedEntity.getId())
                {
                    allEntities.remove(entity);
                    break;
                }
            }
            allEntities.add(updatedEntity);
            final Future<Boolean> futureOfSuccessReloading = this.dataBase.reload(allEntities);

            final boolean successReloading = futureOfSuccessReloading.get();
            if(!successReloading)
            {
                throw new DAOUpdatingEntityException("Reloading after updating entities has been failed.");
            }
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DAOUpdatingEntityException(cause);
        }
    }

    public final void updateEntities(final Collection<TypeOfStoredEntity> updatedEntities)
            throws DAOUpdatingEntityException
    {
        try
        {
            final Future<Collection<TypeOfStoredEntity>> futureOfAllEntities = this.dataBase.offloadEntities();
            final List<Long> idOfUpdatedEntities = updatedEntities.parallelStream().map(TypeOfStoredEntity::getId)
                    .collect(Collectors.toList());

            final Collection<TypeOfStoredEntity> allEntities = futureOfAllEntities.get();
            allEntities.removeIf((final TypeOfStoredEntity entity) -> idOfUpdatedEntities.contains(entity.getId()));
            allEntities.addAll(updatedEntities);

            this.dataBase.reload(allEntities);
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DAOUpdatingEntityException(cause);
        }
    }

    public final void deleteEntity(final long idOfDeletedEntity)
            throws DAODeletingEntityException
    {
        try
        {
            final Future<Collection<TypeOfStoredEntity>> futureOfAllEntities = this.dataBase.offloadEntities();
            final Collection<TypeOfStoredEntity> allEntities = futureOfAllEntities.get();
            for(final TypeOfStoredEntity entity : allEntities)
            {
                if(entity.getId() == idOfDeletedEntity)
                {
                    allEntities.remove(entity);
                    break;
                }
            }
            final Future<Boolean> futureOfSuccessReloading = this.dataBase.reload(allEntities);
            if(!futureOfSuccessReloading.get())
            {
                throw new DAODeletingEntityException("Reloading data base after deleting entity has been failed.");
            }
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DAODeletingEntityException(cause);
        }
    }

    public final void deleteEntity(final TypeOfStoredEntity deletedEntity)
            throws DAODeletingEntityException
    {
        this.deleteEntity(deletedEntity.getId());
    }

    public final void deleteEntities(final Collection<TypeOfStoredEntity> deletedEntities)
            throws DAODeletingEntityException
    {
        try
        {
            final Future<Collection<TypeOfStoredEntity>> futureOfAllEntities = this.dataBase.offloadEntities();
            final List<Long> idOfDeletedEntities = deletedEntities.parallelStream().map(TypeOfStoredEntity::getId)
                    .collect(Collectors.toList());

            final Collection<TypeOfStoredEntity> allEntities = futureOfAllEntities.get();
            allEntities.removeIf((final TypeOfStoredEntity entity) -> idOfDeletedEntities.contains(entity.getId()));

            this.dataBase.reload(allEntities);
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DAODeletingEntityException(cause);
        }
    }

    public final void deleteEntities()
            throws DAODeletingEntityException
    {
        try
        {
            this.dataBase.clear();
        }
        catch(final DataBaseClearingException cause)
        {
            throw new DAODeletingEntityException(cause);
        }
    }
}
