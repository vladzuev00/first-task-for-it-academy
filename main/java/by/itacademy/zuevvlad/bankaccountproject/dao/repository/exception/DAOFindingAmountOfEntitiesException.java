package by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception;

public final class DAOFindingAmountOfEntitiesException extends DAOException
{
    public DAOFindingAmountOfEntitiesException()
    {
        super();
    }

    public DAOFindingAmountOfEntitiesException(final String description)
    {
        super(description);
    }

    public DAOFindingAmountOfEntitiesException(final Exception cause)
    {
        super(cause);
    }

    public DAOFindingAmountOfEntitiesException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
