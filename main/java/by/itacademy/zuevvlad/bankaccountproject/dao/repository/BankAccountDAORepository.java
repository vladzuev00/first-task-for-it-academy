package by.itacademy.zuevvlad.bankaccountproject.dao.repository;

import by.itacademy.zuevvlad.bankaccountproject.dao.database.DataBase;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.exception.DataBaseCreatingException;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.file.FileDataBase;
import by.itacademy.zuevvlad.bankaccountproject.dao.database.file.property.BankAccountFileDataBaseProperty;
import by.itacademy.zuevvlad.bankaccountproject.dao.idgenerator.IdGenerator;
import by.itacademy.zuevvlad.bankaccountproject.dao.idgenerator.exception.IdGeneratorCreatingException;
import by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception.DAORepositoryCreatingException;
import by.itacademy.zuevvlad.bankaccountproject.entity.BankAccount;

import java.io.File;

public final class BankAccountDAORepository extends DAORepository<BankAccount>
{
    public static BankAccountDAORepository createBankAccountDAORepository()
            throws DAORepositoryCreatingException
    {
        try
        {
            final File fileOfDataBase = new File(
                    BankAccountFileDataBaseProperty.BANK_ACCOUNT_FILE_PATH_DATA_BASE.getValue());
            final FileDataBase<BankAccount> fileDataBase = FileDataBase.createFileDataBase(fileOfDataBase);

            final File fileWithLastGeneratedId = new File(
                    BankAccountFileDataBaseProperty.BANK_ACCOUNT_FILE_PATH_WITH_LAST_GENERATED_ID.getValue());
            final IdGenerator idGenerator = IdGenerator.createIdGenerator(fileWithLastGeneratedId);

            return new BankAccountDAORepository(fileDataBase, idGenerator);
        }
        catch(final DataBaseCreatingException | IdGeneratorCreatingException cause)
        {
            throw new DAORepositoryCreatingException(cause);
        }
    }

    private BankAccountDAORepository(final DataBase<BankAccount> dataBase, final IdGenerator idGenerator)
    {
        super(dataBase, idGenerator);
    }
}
