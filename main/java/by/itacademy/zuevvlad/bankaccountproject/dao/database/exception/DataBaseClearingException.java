package by.itacademy.zuevvlad.bankaccountproject.dao.database.exception;

public final class DataBaseClearingException extends DataBaseException
{
    public DataBaseClearingException()
    {
        super();
    }

    public DataBaseClearingException(final String description)
    {
        super(description);
    }

    public DataBaseClearingException(final Exception cause)
    {
        super(cause);
    }

    public DataBaseClearingException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
