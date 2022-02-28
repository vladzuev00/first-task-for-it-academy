package by.itacademy.zuevvlad.bankaccountproject.controller;

import by.itacademy.zuevvlad.bankaccountproject.controller.exception.*;
import by.itacademy.zuevvlad.bankaccountproject.entity.BankAccount;
import by.itacademy.zuevvlad.bankaccountproject.service.BankAccountService;
import by.itacademy.zuevvlad.bankaccountproject.service.Service;
import by.itacademy.zuevvlad.bankaccountproject.service.entityspecification.EntitySpecification;
import by.itacademy.zuevvlad.bankaccountproject.service.exception.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class BankAccountController implements Controller<BankAccount>
{
    private final Service<BankAccount> bankAccountService;

    public BankAccountController()
            throws ControllerCreatingException
    {
        super();

        try
        {
            this.bankAccountService = new BankAccountService();
        }
        catch(final ServiceCreatingException cause)
        {
            throw new ControllerCreatingException(cause);
        }
    }

    @Override
    public final void addEntity(final BankAccount addedBankAccount)
            throws ControllerAddingEntityException
    {
        try
        {
            this.bankAccountService.addEntity(addedBankAccount);
        }
        catch(final ServiceAddingEntityException cause)
        {
            throw new ControllerAddingEntityException(cause);
        }
    }

    @Override
    public final void addEntities(final Collection<BankAccount> addedBankAccounts)
            throws ControllerAddingEntityException
    {
        try
        {
            this.bankAccountService.addEntities(addedBankAccounts);
        }
        catch(final ServiceAddingEntityException cause)
        {
            throw new ControllerAddingEntityException(cause);
        }
    }

    @Override
    public final boolean isEntityWithGivenIdExisting(final long idOfResearchBankAccount)
            throws ControllerDefiningExistingEntityException
    {
        try
        {
            return this.bankAccountService.isEntityWithGivenIdExisting(idOfResearchBankAccount);
        }
        catch(final ServiceDefiningExistingEntityException cause)
        {
            throw new ControllerDefiningExistingEntityException(cause);
        }
    }

    @Override
    public final BankAccount findEntity(final long idOfFoundBankAccount)
            throws ControllerFindingEntityException
    {
        try
        {
            return this.bankAccountService.findEntity(idOfFoundBankAccount);
        }
        catch(final ServiceFindingEntityException cause)
        {
            throw new ControllerFindingEntityException(cause);
        }
    }

    @Override
    public final Collection<BankAccount> findEntities()
            throws ControllerFindingEntityException
    {
        try
        {
            return this.bankAccountService.findEntities();
        }
        catch(final ServiceFindingEntityException cause)
        {
            throw new ControllerFindingEntityException(cause);
        }
    }

    @Override
    public final Collection<BankAccount> findEntities(final EntitySpecification<BankAccount> bankAccountSpecification)
            throws ControllerFindingEntityException
    {
        try
        {
            return this.bankAccountService.findEntities(bankAccountSpecification);
        }
        catch(final ServiceFindingEntityException cause)
        {
            throw new ControllerFindingEntityException(cause);
        }
    }

    @Override
    public final <TypeOfGroupingKey> Map<TypeOfGroupingKey, List<BankAccount>> findGroupedEntities(
            final Function<BankAccount, TypeOfGroupingKey> functionToFindGroupingKey)
            throws ControllerFindingEntityException
    {
        try
        {
            return this.bankAccountService.findGroupedEntities(functionToFindGroupingKey);
        }
        catch(final ServiceFindingEntityException cause)
        {
            throw new ControllerFindingEntityException(cause);
        }
    }

    @Override
    public final List<BankAccount> findSortedEntities(final Comparator<? super BankAccount> comparator)
            throws ControllerFindingEntityException
    {
        try
        {
            return this.bankAccountService.findSortedEntities(comparator);
        }
        catch(final ServiceFindingEntityException cause)
        {
            throw new ControllerFindingEntityException(cause);
        }
    }

    @Override
    public final int findAmountOfEntities()
            throws ControllerFindingAmountOfEntitiesException
    {
        try
        {
            return this.bankAccountService.findAmountOfEntities();
        }
        catch(final ServiceFindingAmountOfEntitiesException cause)
        {
            throw new ControllerFindingAmountOfEntitiesException(cause);
        }
    }

    @Override
    public final void updateEntity(final BankAccount updatedBankAccount)
            throws ControllerUpdatingEntityException
    {
        try
        {
            this.bankAccountService.updateEntity(updatedBankAccount);
        }
        catch(final ServiceUpdatingEntityException cause)
        {
            throw new ControllerUpdatingEntityException(cause);
        }
    }

    @Override
    public final void updateEntities(final Collection<BankAccount> updatedBankAccounts)
            throws ControllerUpdatingEntityException
    {
        try
        {
            this.bankAccountService.updateEntities(updatedBankAccounts);
        }
        catch(final ServiceUpdatingEntityException cause)
        {
            throw new ControllerUpdatingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntity(final long idOfDeletedBankAccount)
            throws ControllerDeletingEntityException
    {
        try
        {
            this.bankAccountService.deleteEntity(idOfDeletedBankAccount);
        }
        catch(final ServiceDeletingEntityException cause)
        {
            throw new ControllerDeletingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntity(final BankAccount deletedBankAccount)
            throws ControllerDeletingEntityException
    {
        try
        {
            this.bankAccountService.deleteEntity(deletedBankAccount);
        }
        catch(final ServiceDeletingEntityException cause)
        {
            throw new ControllerDeletingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntities(final Collection<BankAccount> deletedBankAccounts)
            throws ControllerDeletingEntityException
    {
        try
        {
            this.bankAccountService.deleteEntities(deletedBankAccounts);
        }
        catch(final ServiceDeletingEntityException cause)
        {
            throw new ControllerDeletingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntities()
            throws ControllerDeletingEntityException
    {
        try
        {
            this.bankAccountService.deleteEntities();
        }
        catch(final ServiceDeletingEntityException cause)
        {
            throw new ControllerDeletingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntities(final EntitySpecification<BankAccount> bankAccountSpecification)
            throws ControllerDeletingEntityException
    {
        try
        {
            this.bankAccountService.deleteEntities(bankAccountSpecification);
        }
        catch(final ServiceDeletingEntityException cause)
        {
            throw new ControllerDeletingEntityException(cause);
        }
    }
}
