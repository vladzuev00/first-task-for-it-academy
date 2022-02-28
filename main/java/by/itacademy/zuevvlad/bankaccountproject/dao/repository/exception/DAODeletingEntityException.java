package by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception;

public final class DAODeletingEntityException extends DAOException
{
    public DAODeletingEntityException()
    {
        super();
    }

    public DAODeletingEntityException(final String description)
    {
        super(description);
    }

    public DAODeletingEntityException(final Exception cause)
    {
        super(cause);
    }

    public DAODeletingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
