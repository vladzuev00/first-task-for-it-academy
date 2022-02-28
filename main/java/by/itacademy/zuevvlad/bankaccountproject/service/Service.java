package by.itacademy.zuevvlad.bankaccountproject.service;

import by.itacademy.zuevvlad.bankaccountproject.entity.Entity;
import by.itacademy.zuevvlad.bankaccountproject.service.entityspecification.EntitySpecification;
import by.itacademy.zuevvlad.bankaccountproject.service.exception.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface Service<TypeOfEntity extends Entity>
{
    public abstract void addEntity(final TypeOfEntity addedEntity)
            throws ServiceAddingEntityException;
    public abstract void addEntities(final Collection<TypeOfEntity> addedEntities)
            throws ServiceAddingEntityException;

    public abstract boolean isEntityWithGivenIdExisting(final long idOfResearchEntity)
            throws ServiceDefiningExistingEntityException;

    public abstract TypeOfEntity findEntity(final long idOfFoundEntity)
            throws ServiceFindingEntityException;
    public abstract Collection<TypeOfEntity> findEntities()
            throws ServiceFindingEntityException;
    public abstract Collection<TypeOfEntity> findEntities(final EntitySpecification<TypeOfEntity> entitySpecification)
            throws ServiceFindingEntityException;
    public abstract <TypeOfGroupingKey> Map<TypeOfGroupingKey, List<TypeOfEntity>> findGroupedEntities(
        final Function<TypeOfEntity, TypeOfGroupingKey> functionToFindGroupingKey)
            throws ServiceFindingEntityException;
    public abstract List<TypeOfEntity> findSortedEntities(final Comparator<? super TypeOfEntity> comparator)
            throws ServiceFindingEntityException;

    public abstract int findAmountOfEntities()
            throws ServiceFindingAmountOfEntitiesException;

    public abstract void updateEntity(final TypeOfEntity updatedEntity)
            throws ServiceUpdatingEntityException;
    public abstract void updateEntities(final Collection<TypeOfEntity> updatedEntities)
            throws ServiceUpdatingEntityException;

    public abstract void deleteEntity(final long idOfDeletedEntity)
            throws ServiceDeletingEntityException;
    public abstract void deleteEntity(final TypeOfEntity deletedEntity)
            throws ServiceDeletingEntityException;
    public abstract void deleteEntities(final Collection<TypeOfEntity> deletedEntities)
            throws ServiceDeletingEntityException;
    public abstract void deleteEntities()
            throws ServiceDeletingEntityException;
    public abstract void deleteEntities(final EntitySpecification<TypeOfEntity> entitySpecification)
            throws ServiceDeletingEntityException;
}
