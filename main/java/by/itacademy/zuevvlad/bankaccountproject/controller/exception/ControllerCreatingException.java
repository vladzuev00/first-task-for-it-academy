package by.itacademy.zuevvlad.bankaccountproject.controller.exception;

public final class ControllerCreatingException extends ControllerException
{
    public ControllerCreatingException()
    {
        super();
    }

    public ControllerCreatingException(final String description)
    {
        super(description);
    }

    public ControllerCreatingException(final Exception cause)
    {
        super(cause);
    }

    public ControllerCreatingException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
