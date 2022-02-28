package by.itacademy.zuevvlad.bankaccountproject.controller.exception;

public final class ControllerDeletingEntityException extends ControllerException
{
    public ControllerDeletingEntityException()
    {
        super();
    }

    public ControllerDeletingEntityException(final String description)
    {
        super(description);
    }

    public ControllerDeletingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ControllerDeletingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
