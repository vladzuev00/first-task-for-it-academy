package by.itacademy.zuevvlad.bankaccountproject.controller.exception;

public final class ControllerAddingEntityException extends Exception
{
    public ControllerAddingEntityException()
    {
        super();
    }

    public ControllerAddingEntityException(final String description)
    {
        super(description);
    }

    public ControllerAddingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ControllerAddingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
