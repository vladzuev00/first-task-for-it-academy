package by.itacademy.zuevvlad.bankaccountproject.controller.exception;

public final class ControllerFindingAmountOfEntitiesException extends ControllerException
{
    public ControllerFindingAmountOfEntitiesException()
    {
        super();
    }

    public ControllerFindingAmountOfEntitiesException(final String description)
    {
        super(description);
    }

    public ControllerFindingAmountOfEntitiesException(final Exception cause)
    {
        super(cause);
    }

    public ControllerFindingAmountOfEntitiesException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
