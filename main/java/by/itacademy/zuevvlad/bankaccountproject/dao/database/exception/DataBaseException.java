package by.itacademy.zuevvlad.bankaccountproject.dao.database.exception;

public class DataBaseException extends Exception
{
    public DataBaseException()
    {
        super();
    }

    public DataBaseException(final String description)
    {
        super(description);
    }

    public DataBaseException(final Exception cause)
    {
        super(cause);
    }

    public DataBaseException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
