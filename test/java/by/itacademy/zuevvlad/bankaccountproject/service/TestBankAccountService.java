package by.itacademy.zuevvlad.bankaccountproject.service;

import by.itacademy.zuevvlad.bankaccountproject.dao.repository.BankAccountDAORepository;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.DAORepository;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception.DAOAddingEntityException;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception.DAODeletingEntityException;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception.DAORepositoryCreatingException;
import by.itacademy.zuevvlad.bankaccountproject.entity.BankAccount;
import by.itacademy.zuevvlad.bankaccountproject.service.entityspecification.EntitySpecification;
import by.itacademy.zuevvlad.bankaccountproject.service.exception.ServiceCreatingException;
import by.itacademy.zuevvlad.bankaccountproject.service.exception.ServiceException;
import by.itacademy.zuevvlad.bankaccountproject.service.exception.ServiceFindingEntityException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class TestBankAccountService
{
    private final BankAccountService bankAccountService;
    private final DAORepository<BankAccount> bankAccountDAORepository;

    public TestBankAccountService()
            throws ServiceCreatingException, DAORepositoryCreatingException
    {
        super();

        this.bankAccountService = new BankAccountService();
        this.bankAccountDAORepository = BankAccountDAORepository.createBankAccountDAORepository();
    }

    private static final List<BankAccount> BANK_ACCOUNTS_LOADED_IN_DATA_BASE_FOR_TEST = new ArrayList<BankAccount>()
    {
        {
            final BigDecimal money = new BigDecimal("10000");
            final boolean blocked = false;
            final BankAccount bankAccount = new BankAccount(money, blocked);
            this.add(bankAccount);
        }
        {
            final BigDecimal money = new BigDecimal("20000");
            final boolean blocked = true;
            final BankAccount bankAccount = new BankAccount(money, blocked);
            this.add(bankAccount);
        }
        {
            final BigDecimal money = new BigDecimal("30000");
            final boolean blocked = false;
            final BankAccount bankAccount = new BankAccount(money, blocked);
            this.add(bankAccount);
        }
        {
            final BigDecimal money = new BigDecimal("-10000");
            final boolean blocked = true;
            final BankAccount bankAccount = new BankAccount(money, blocked);
            this.add(bankAccount);
        }
        {
            final BigDecimal money = new BigDecimal("-20000");
            final boolean blocked = false;
            final BankAccount bankAccount = new BankAccount(money, blocked);
            this.add(bankAccount);
        }
        {
            final BigDecimal money = new BigDecimal("-30000");
            final boolean blocked = true;
            final BankAccount bankAccount = new BankAccount(money, blocked);
            this.add(bankAccount);
        }
    };

    @BeforeMethod
    public final void loadBankAccountsInDataBase()
            throws DAODeletingEntityException, DAOAddingEntityException
    {
        this.bankAccountDAORepository.deleteEntities();
        this.bankAccountDAORepository.addEntities(TestBankAccountService.BANK_ACCOUNTS_LOADED_IN_DATA_BASE_FOR_TEST);
    }

    @Test
    public final void sortedByMoneyBankAccountsShouldBeFound()
            throws ServiceFindingEntityException
    {
        final Comparator<BankAccount> bankAccountComparatorByMoney = Comparator.comparing(BankAccount::getMoney);

        final List<BankAccount> actualSortedBankAccounts = this.bankAccountService.findSortedEntities(
                bankAccountComparatorByMoney);

        final List<BankAccount> expectedSortedBankAccounts= new ArrayList<BankAccount>(
                TestBankAccountService.BANK_ACCOUNTS_LOADED_IN_DATA_BASE_FOR_TEST);
        expectedSortedBankAccounts.sort(bankAccountComparatorByMoney);

        Assert.assertEquals(actualSortedBankAccounts, expectedSortedBankAccounts);
    }

    @Test
    public final void sortedByMoneyBankAccountsShouldNotBeFoundInEmptyDataBase()
            throws ServiceFindingEntityException, DAODeletingEntityException
    {
        this.bankAccountDAORepository.deleteEntities();

        final Comparator<BankAccount> bankAccountComparatorByMoney = Comparator.comparing(BankAccount::getMoney);
        final List<BankAccount> sortedBankAccounts = this.bankAccountService.findSortedEntities(
                bankAccountComparatorByMoney);

        Assert.assertTrue(sortedBankAccounts.isEmpty());
    }

    @Test
    public final void sumOfMoneyOfAllBankAccountsShouldBeFound()
            throws ServiceException
    {
        final BigDecimal actualSumOfMoney = this.bankAccountService.findSumOfMoneyOfAllBankAccounts();
        final BigDecimal expectedSumOfMoney = TestBankAccountService.BANK_ACCOUNTS_LOADED_IN_DATA_BASE_FOR_TEST
                .stream().map(BankAccount::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.assertEquals(actualSumOfMoney, expectedSumOfMoney);
    }

    @Test
    public final void sumOfMoneyOfAllBankAccountsInEmptyDataBaseShouldBeEqualZero()
            throws DAODeletingEntityException, ServiceException
    {
        this.bankAccountDAORepository.deleteEntities();

        final BigDecimal actualSumOfMoney = this.bankAccountService.findSumOfMoneyOfAllBankAccounts();
        final BigDecimal expectedSumOfMoney = BigDecimal.ZERO;
        Assert.assertEquals(actualSumOfMoney, expectedSumOfMoney);
    }

    @Test
    public final void sumOfMoneyOfBankAccountsWithNegativeMoneyShouldBeFound()
            throws ServiceException
    {
        final EntitySpecification<BankAccount> specificationOfNegativeMoney = (final BankAccount bankAccount) ->
        {
            return bankAccount.getMoney().compareTo(BigDecimal.ZERO) < 0;
        };
        final BigDecimal actualSumOfMoney = this.bankAccountService.findSumOfMoneyOfMatchingBankAccounts(
                specificationOfNegativeMoney);
        final BigDecimal expectedSumOfMoney = TestBankAccountService.BANK_ACCOUNTS_LOADED_IN_DATA_BASE_FOR_TEST.stream()
                .filter(specificationOfNegativeMoney::isMatch).map(BankAccount::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.assertEquals(actualSumOfMoney, expectedSumOfMoney);
    }

    @Test
    public final void sumOfMoneyOfBankAccountsWithPositiveMoneyShouldBeFound()
            throws ServiceException
    {
        final EntitySpecification<BankAccount> specificationOfPositiveMoney = (final BankAccount bankAccount) ->
        {
            return bankAccount.getMoney().compareTo(BigDecimal.ZERO) > 0;
        };
        final BigDecimal actualSumOfMoney = this.bankAccountService.findSumOfMoneyOfMatchingBankAccounts(
                specificationOfPositiveMoney);
        final BigDecimal expectedSumOfMoney = TestBankAccountService.BANK_ACCOUNTS_LOADED_IN_DATA_BASE_FOR_TEST.stream()
                .filter(specificationOfPositiveMoney::isMatch).map(BankAccount::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.assertEquals(actualSumOfMoney, expectedSumOfMoney);
    }
}
