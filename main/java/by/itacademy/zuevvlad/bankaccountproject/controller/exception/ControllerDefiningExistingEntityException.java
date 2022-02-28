package by.itacademy.zuevvlad.bankaccountproject.controller.exception;

public final class ControllerDefiningExistingEntityException extends ControllerException
{
    public ControllerDefiningExistingEntityException()
    {
        super();
    }

    public ControllerDefiningExistingEntityException(final String description)
    {
        super(description);
    }

    public ControllerDefiningExistingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ControllerDefiningExistingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
