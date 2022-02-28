package by.itacademy.zuevvlad.bankaccountproject.service.exception;

public final class ServiceDeletingEntityException extends ServiceException
{
    public ServiceDeletingEntityException()
    {
        super();
    }

    public ServiceDeletingEntityException(final String description)
    {
        super(description);
    }

    public ServiceDeletingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ServiceDeletingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
