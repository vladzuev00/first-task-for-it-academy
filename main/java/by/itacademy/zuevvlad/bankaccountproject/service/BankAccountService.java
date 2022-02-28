package by.itacademy.zuevvlad.bankaccountproject.service;

import by.itacademy.zuevvlad.bankaccountproject.controller.exception.ControllerException;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.BankAccountDAORepository;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.DAORepository;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception.*;
import by.itacademy.zuevvlad.bankaccountproject.entity.BankAccount;
import by.itacademy.zuevvlad.bankaccountproject.service.entityspecification.EntitySpecification;
import by.itacademy.zuevvlad.bankaccountproject.service.exception.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class BankAccountService implements Service<BankAccount>
{
    private final DAORepository<BankAccount> bankAccountDAORepository;

    public BankAccountService()
            throws ServiceCreatingException
    {
        super();

        try
        {
            this.bankAccountDAORepository = BankAccountDAORepository.createBankAccountDAORepository();
        }
        catch(final DAORepositoryCreatingException cause)
        {
            throw new ServiceCreatingException(cause);
        }
    }

    @Override
    public final void addEntity(final BankAccount addedBankAccount)
            throws ServiceAddingEntityException
    {
        try
        {
            this.bankAccountDAORepository.addEntity(addedBankAccount);
        }
        catch(final DAOAddingEntityException cause)
        {
            throw new ServiceAddingEntityException(cause);
        }
    }

    @Override
    public final void addEntities(final Collection<BankAccount> addedBankAccounts)
            throws ServiceAddingEntityException
    {
        try
        {
            this.bankAccountDAORepository.addEntities(addedBankAccounts);
        }
        catch(final DAOAddingEntityException cause)
        {
            throw new ServiceAddingEntityException(cause);
        }
    }

    @Override
    public final boolean isEntityWithGivenIdExisting(final long idOfResearchBankAccount)
            throws ServiceDefiningExistingEntityException
    {
        try
        {
            return this.bankAccountDAORepository.isEntityWithGivenIdExisting(idOfResearchBankAccount);
        }
        catch(final DAODefiningExistingEntityException cause)
        {
            throw new ServiceDefiningExistingEntityException(cause);
        }
    }

    @Override
    public final BankAccount findEntity(final long idOfFoundBankAccount)
            throws ServiceFindingEntityException
    {
        try
        {
            final Optional<BankAccount> optionalOfBankAccount = this.bankAccountDAORepository.findEntity(
                    idOfFoundBankAccount);
            return optionalOfBankAccount.orElseThrow();
        }
        catch(final DAOFindingEntityException | NoSuchElementException cause)
        {
            throw new ServiceFindingEntityException(cause);
        }
    }

    @Override
    public final Collection<BankAccount> findEntities()
            throws ServiceFindingEntityException
    {
        try
        {
            return this.bankAccountDAORepository.findEntities();
        }
        catch(final DAOFindingEntityException cause)
        {
            throw new ServiceFindingEntityException(cause);
        }
    }

    @Override
    public final Collection<BankAccount> findEntities(final EntitySpecification<BankAccount> bankAccountSpecification)
            throws ServiceFindingEntityException
    {
        try
        {
            final Collection<BankAccount> allBankAccount = this.bankAccountDAORepository.findEntities();
            return allBankAccount.stream().filter(bankAccountSpecification::isMatch).collect(Collectors.toSet());
        }
        catch(final DAOFindingEntityException cause)
        {
            throw new ServiceFindingEntityException(cause);
        }
    }

    @Override
    public final <TypeOfGroupingKey> Map<TypeOfGroupingKey, List<BankAccount>> findGroupedEntities(
            final Function<BankAccount, TypeOfGroupingKey> functionToFindGroupingKey)
            throws ServiceFindingEntityException
    {
        try
        {
            final Collection<BankAccount> allBankAccounts = this.bankAccountDAORepository.findEntities();
            return allBankAccounts.stream().collect(Collectors.groupingBy(functionToFindGroupingKey));
        }
        catch(final DAOFindingEntityException cause)
        {
            throw new ServiceFindingEntityException(cause);
        }
    }

    @Override
    public final List<BankAccount> findSortedEntities(final Comparator<? super BankAccount> comparator)
            throws ServiceFindingEntityException
    {
        try
        {
            final Collection<BankAccount> allBankAccounts = this.bankAccountDAORepository.findEntities();
            final List<BankAccount> listOfAllBankAccounts = new ArrayList<>(allBankAccounts);
            listOfAllBankAccounts.sort(comparator);
            return listOfAllBankAccounts;
        }
        catch(final DAOFindingEntityException cause)
        {
            throw new ServiceFindingEntityException(cause);
        }
    }

    @Override
    public final int findAmountOfEntities()
            throws ServiceFindingAmountOfEntitiesException
    {
        try
        {
            return this.bankAccountDAORepository.findAmountOfEntities();
        }
        catch(final DAOFindingAmountOfEntitiesException cause)
        {
            throw new ServiceFindingAmountOfEntitiesException(cause);
        }
    }

    @Override
    public final void updateEntity(final BankAccount updatedBankAccount)
            throws ServiceUpdatingEntityException
    {
        try
        {
            this.bankAccountDAORepository.updateEntity(updatedBankAccount);
        }
        catch(final DAOUpdatingEntityException cause)
        {
            throw new ServiceUpdatingEntityException(cause);
        }
    }

    @Override
    public final void updateEntities(final Collection<BankAccount> updatedBankAccounts)
            throws ServiceUpdatingEntityException
    {
        try
        {
            this.bankAccountDAORepository.updateEntities(updatedBankAccounts);
        }
        catch(final DAOUpdatingEntityException cause)
        {
            throw new ServiceUpdatingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntity(final long idOfDeletedBankAccount)
            throws ServiceDeletingEntityException
    {
        try
        {
            this.bankAccountDAORepository.deleteEntity(idOfDeletedBankAccount);
        }
        catch(final DAODeletingEntityException cause)
        {
            throw new ServiceDeletingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntity(final BankAccount deletedBankAccount)
            throws ServiceDeletingEntityException
    {
        try
        {
            this.bankAccountDAORepository.deleteEntity(deletedBankAccount);
        }
        catch(final DAODeletingEntityException cause)
        {
            throw new ServiceDeletingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntities(final Collection<BankAccount> deletedBankAccounts)
            throws ServiceDeletingEntityException
    {
        try
        {
            this.bankAccountDAORepository.deleteEntities(deletedBankAccounts);
        }
        catch(final DAODeletingEntityException cause)
        {
            throw new ServiceDeletingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntities()
            throws ServiceDeletingEntityException
    {
        try
        {
            this.bankAccountDAORepository.deleteEntities();
        }
        catch(final DAODeletingEntityException cause)
        {
            throw new ServiceDeletingEntityException(cause);
        }
    }

    @Override
    public final void deleteEntities(final EntitySpecification<BankAccount> bankAccountSpecification)
            throws ServiceDeletingEntityException
    {
        try
        {
            final Collection<BankAccount> allBankAccounts = this.bankAccountDAORepository.findEntities();
            final Collection<BankAccount> matchingBankAccounts = allBankAccounts.parallelStream().filter(
                    bankAccountSpecification::isMatch).collect(Collectors.toSet());
            this.bankAccountDAORepository.deleteEntities(matchingBankAccounts);
        }
        catch(final DAOFindingEntityException | DAODeletingEntityException cause)
        {
            throw new ServiceDeletingEntityException(cause);
        }
    }

    public final BigDecimal findSumOfMoneyOfAllBankAccounts()
            throws ServiceException
    {
        try
        {
            final Collection<BankAccount> allBankAccounts = this.bankAccountDAORepository.findEntities();
            return allBankAccounts.stream().map(BankAccount::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        catch(final DAOFindingEntityException cause)
        {
            throw new ServiceException(cause);
        }
    }

    public final BigDecimal findSumOfMoneyOfMatchingBankAccounts(
            final EntitySpecification<BankAccount> bankAccountSpecification)
            throws ServiceException
    {
        try
        {
            final Collection<BankAccount> allBankAccounts = this.bankAccountDAORepository.findEntities();
            return allBankAccounts.stream().filter(bankAccountSpecification::isMatch).map(BankAccount::getMoney)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        catch(final DAOFindingEntityException cause)
        {
            throw new ServiceException(cause);
        }
    }
}
