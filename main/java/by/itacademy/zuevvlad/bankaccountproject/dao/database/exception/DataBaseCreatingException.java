package by.itacademy.zuevvlad.bankaccountproject.dao.database.exception;

public final class DataBaseCreatingException extends DataBaseException
{
    public DataBaseCreatingException()
    {
        super();
    }

    public DataBaseCreatingException(final String description)
    {
        super(description);
    }

    public DataBaseCreatingException(final Exception cause)
    {
        super(cause);
    }

    public DataBaseCreatingException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
