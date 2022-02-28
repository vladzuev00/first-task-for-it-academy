package by.itacademy.zuevvlad.bankaccountproject.controller;

import by.itacademy.zuevvlad.bankaccountproject.controller.exception.*;
import by.itacademy.zuevvlad.bankaccountproject.entity.Entity;
import by.itacademy.zuevvlad.bankaccountproject.service.entityspecification.EntitySpecification;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface Controller<TypeOfEntity extends Entity>
{
    public abstract void addEntity(final TypeOfEntity addedEntity)
            throws ControllerAddingEntityException;
    public abstract void addEntities(final Collection<TypeOfEntity> addedEntities)
            throws ControllerAddingEntityException;

    public abstract boolean isEntityWithGivenIdExisting(final long idOfResearchEntity)
            throws ControllerDefiningExistingEntityException;

    public abstract TypeOfEntity findEntity(final long idOfFoundEntity)
            throws ControllerFindingEntityException;
    public abstract Collection<TypeOfEntity> findEntities()
            throws ControllerFindingEntityException;
    public abstract Collection<TypeOfEntity> findEntities(final EntitySpecification<TypeOfEntity> entitySpecification)
            throws ControllerFindingEntityException;
    public abstract <TypeOfGroupingKey> Map<TypeOfGroupingKey, List<TypeOfEntity>> findGroupedEntities(
            final Function<TypeOfEntity, TypeOfGroupingKey> functionToFindGroupingKey)
            throws ControllerFindingEntityException;
    public abstract List<TypeOfEntity> findSortedEntities(final Comparator<? super TypeOfEntity> comparator)
            throws ControllerFindingEntityException;

    public abstract int findAmountOfEntities()
            throws ControllerFindingAmountOfEntitiesException;

    public abstract void updateEntity(final TypeOfEntity updatedEntity)
            throws ControllerUpdatingEntityException;
    public abstract void updateEntities(final Collection<TypeOfEntity> updatedEntities)
            throws ControllerUpdatingEntityException;

    public abstract void deleteEntity(final long idOfDeletedEntity)
            throws ControllerDeletingEntityException;
    public abstract void deleteEntity(final TypeOfEntity deletedEntity)
            throws ControllerDeletingEntityException;
    public abstract void deleteEntities(final Collection<TypeOfEntity> deletedEntities)
            throws ControllerDeletingEntityException;
    public abstract void deleteEntities()
            throws ControllerDeletingEntityException;
    public abstract void deleteEntities(final EntitySpecification<TypeOfEntity> entitySpecification)
            throws ControllerDeletingEntityException;
}
