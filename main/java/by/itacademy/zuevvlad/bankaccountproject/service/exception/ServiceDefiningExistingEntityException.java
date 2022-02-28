package by.itacademy.zuevvlad.bankaccountproject.service.exception;

public final class ServiceDefiningExistingEntityException extends ServiceException
{
    public ServiceDefiningExistingEntityException()
    {
        super();
    }

    public ServiceDefiningExistingEntityException(final String description)
    {
        super(description);
    }

    public ServiceDefiningExistingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ServiceDefiningExistingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
