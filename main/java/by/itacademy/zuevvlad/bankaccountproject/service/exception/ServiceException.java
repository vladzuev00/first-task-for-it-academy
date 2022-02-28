package by.itacademy.zuevvlad.bankaccountproject.service.exception;

public class ServiceException extends Exception
{
    public ServiceException()
    {
        super();
    }

    public ServiceException(final String description)
    {
        super(description);
    }

    public ServiceException(final Exception cause)
    {
        super(cause);
    }

    public ServiceException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
