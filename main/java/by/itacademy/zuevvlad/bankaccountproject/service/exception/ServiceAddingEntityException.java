package by.itacademy.zuevvlad.bankaccountproject.service.exception;

public final class ServiceAddingEntityException extends ServiceException
{
    public ServiceAddingEntityException()
    {
        super();
    }

    public ServiceAddingEntityException(final String description)
    {
        super(description);
    }

    public ServiceAddingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ServiceAddingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
