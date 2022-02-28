package by.itacademy.zuevvlad.bankaccountproject.controller.exception;

public class ControllerException extends Exception
{
    public ControllerException()
    {
        super();
    }

    public ControllerException(final String description)
    {
        super(description);
    }

    public ControllerException(final Exception cause)
    {
        super(cause);
    }

    public ControllerException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
