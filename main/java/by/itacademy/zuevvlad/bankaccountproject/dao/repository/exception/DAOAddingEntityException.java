package by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception;

public final class DAOAddingEntityException extends DAOException
{
    public DAOAddingEntityException()
    {
        super();
    }

    public DAOAddingEntityException(final String description)
    {
        super(description);
    }

    public DAOAddingEntityException(final Exception cause)
    {
        super(cause);
    }

    public DAOAddingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
