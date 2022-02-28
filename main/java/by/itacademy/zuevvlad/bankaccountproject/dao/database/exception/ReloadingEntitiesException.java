package by.itacademy.zuevvlad.bankaccountproject.dao.database.exception;

public final class ReloadingEntitiesException extends RuntimeException
{
    public ReloadingEntitiesException()
    {
        super();
    }

    public ReloadingEntitiesException(final String description)
    {
        super(description);
    }

    public ReloadingEntitiesException(final Exception cause)
    {
        super(cause);
    }

    public ReloadingEntitiesException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
