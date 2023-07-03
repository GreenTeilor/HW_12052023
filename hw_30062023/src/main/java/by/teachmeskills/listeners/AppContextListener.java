package by.teachmeskills.listeners;

import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.utils.DBCrudUtils;
import by.teachmeskills.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(AppContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            DBCrudUtils.setConnection(pool::getConnection);
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ConnectionPool.getInstance().returnConnection(DBCrudUtils.getConnection());
        ConnectionPool.getInstance().closeConnections();
    }
}
