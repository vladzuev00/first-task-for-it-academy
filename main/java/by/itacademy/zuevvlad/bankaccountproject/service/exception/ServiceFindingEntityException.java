package by.itacademy.zuevvlad.bankaccountproject.service.exception;

public final class ServiceFindingEntityException extends ServiceException
{
    public ServiceFindingEntityException()
    {
        super();
    }

    public ServiceFindingEntityException(final String description)
    {
        super(description);
    }

    public ServiceFindingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ServiceFindingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
