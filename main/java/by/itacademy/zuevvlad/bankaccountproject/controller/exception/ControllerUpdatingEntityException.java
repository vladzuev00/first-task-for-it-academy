package by.itacademy.zuevvlad.bankaccountproject.controller.exception;

public final class ControllerUpdatingEntityException extends ControllerException
{
    public ControllerUpdatingEntityException()
    {
        super();
    }

    public ControllerUpdatingEntityException(final String description)
    {
        super(description);
    }

    public ControllerUpdatingEntityException(final Exception cause)
    {
        super(cause);
    }

    public ControllerUpdatingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
