package by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception;

public final class DAOFindingEntityException extends DAOException
{
    public DAOFindingEntityException()
    {
        super();
    }

    public DAOFindingEntityException(final String description)
    {
        super(description);
    }

    public DAOFindingEntityException(final Exception cause)
    {
        super(cause);
    }

    public DAOFindingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
