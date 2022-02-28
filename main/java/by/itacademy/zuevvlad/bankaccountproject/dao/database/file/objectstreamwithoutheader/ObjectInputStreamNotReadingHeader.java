package by.itacademy.zuevvlad.bankaccountproject.dao.database.file.objectstreamwithoutheader;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public final class ObjectInputStreamNotReadingHeader extends ObjectInputStream
{
    public ObjectInputStreamNotReadingHeader(final InputStream inputStream)
            throws IOException
    {
        super(inputStream);
    }

    @Override
    public final void readStreamHeader()
    {

    }
}
