package by.itacademy.zuevvlad.bankaccountproject.dao.database.file.daemonthreadfactory;

import java.util.concurrent.ThreadFactory;

public final class DaemonThreadFactory implements ThreadFactory
{
    public static DaemonThreadFactory createDaemonThreadFactory()
    {
        if(DaemonThreadFactory.daemonThreadFactory == null)
        {
            synchronized(DaemonThreadFactory.class)
            {
                if(DaemonThreadFactory.daemonThreadFactory == null)
                {
                    DaemonThreadFactory.daemonThreadFactory = new DaemonThreadFactory();
                }
            }
        }
        return DaemonThreadFactory.daemonThreadFactory;
    }

    private static DaemonThreadFactory daemonThreadFactory = null;

    private DaemonThreadFactory()
    {
        super();
    }

    @Override
    public final Thread newThread(final Runnable runnable)
    {
        final Thread createdThread = new Thread(runnable);
        createdThread.setDaemon(true);
        return createdThread;
    }
}
