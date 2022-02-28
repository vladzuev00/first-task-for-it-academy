package by.itacademy.zuevvlad.bankaccountproject.dao.database.exception;

public final class DataBaseLoadingEntityException extends DataBaseException
{
    public DataBaseLoadingEntityException()
    {
        super();
    }

    public DataBaseLoadingEntityException(final String description)
    {
        super(description);
    }

    public DataBaseLoadingEntityException(final Exception cause)
    {
        super(cause);
    }

    public DataBaseLoadingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
