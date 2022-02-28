package by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception;

public final class DAORepositoryCreatingException extends DAOException
{
    public DAORepositoryCreatingException()
    {
        super();
    }

    public DAORepositoryCreatingException(final String description)
    {
        super(description);
    }

    public DAORepositoryCreatingException(final Exception cause)
    {
        super(cause);
    }

    public DAORepositoryCreatingException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
