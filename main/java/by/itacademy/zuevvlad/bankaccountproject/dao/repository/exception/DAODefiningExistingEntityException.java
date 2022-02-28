package by.itacademy.zuevvlad.bankaccountproject.dao.repository.exception;

public final class DAODefiningExistingEntityException extends DAOException
{
    public DAODefiningExistingEntityException()
    {
        super();
    }

    public DAODefiningExistingEntityException(final String description)
    {
        super(description);
    }

    public DAODefiningExistingEntityException(final Exception cause)
    {
        super(cause);
    }

    public DAODefiningExistingEntityException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
