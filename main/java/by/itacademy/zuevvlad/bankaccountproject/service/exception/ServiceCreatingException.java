package by.itacademy.zuevvlad.bankaccountproject.service.exception;

public final class ServiceCreatingException extends ServiceException
{
    public ServiceCreatingException()
    {
        super();
    }

    public ServiceCreatingException(final String description)
    {
        super(description);
    }

    public ServiceCreatingException(final Exception cause)
    {
        super(cause);
    }

    public ServiceCreatingException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
