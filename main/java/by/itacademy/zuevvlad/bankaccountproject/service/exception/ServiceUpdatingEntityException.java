package by.itacademy.zuevvlad.bankaccountproject.service.exception;

public final class ServiceUpdatingEntityException extends ServiceException
{
    public ServiceUpdatingEntityException()
    {
        super();
    }

    public ServiceUpdatingEntityException(final String description)
    {
        super(description);
    }

    public ServiceUpdatingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ServiceUpdatingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
