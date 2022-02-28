package by.itacademy.zuevvlad.bankaccountproject.service.exception;

public final class ServiceFindingAmountOfEntitiesException extends ServiceException
{
    public ServiceFindingAmountOfEntitiesException()
    {
        super();
    }

    public ServiceFindingAmountOfEntitiesException(final String description)
    {
        super(description);
    }

    public ServiceFindingAmountOfEntitiesException(final Exception cause)
    {
        super(cause);
    }

    public ServiceFindingAmountOfEntitiesException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
