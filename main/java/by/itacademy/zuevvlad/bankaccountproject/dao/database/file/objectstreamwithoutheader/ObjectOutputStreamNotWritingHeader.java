package by.itacademy.zuevvlad.bankaccountproject.dao.database.file.objectstreamwithoutheader;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public final class ObjectOutputStreamNotWritingHeader extends ObjectOutputStream
{
    public ObjectOutputStreamNotWritingHeader(final OutputStream outputStream)
            throws IOException
    {
        super(outputStream);
    }

    @Override
    public final void writeStreamHeader()
    {

    }
}
