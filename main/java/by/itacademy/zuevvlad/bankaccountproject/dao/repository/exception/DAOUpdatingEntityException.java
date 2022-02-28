package by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception;

public final class DAOUpdatingEntityException extends DAOException
{
    public DAOUpdatingEntityException()
    {
        super();
    }

    public DAOUpdatingEntityException(final String description)
    {
        super(description);
    }

    public DAOUpdatingEntityException(final Exception cause)
    {
        super(cause);
    }

    public DAOUpdatingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
