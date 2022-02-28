package by.itacademy.zuevvlad.bankaccountproject.controller.exception;

public final class ControllerFindingEntityException extends ControllerException
{
    public ControllerFindingEntityException()
    {
        super();
    }

    public ControllerFindingEntityException(final String description)
    {
        super(description);
    }

    public ControllerFindingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ControllerFindingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
