package by.teachmeskills.listeners;

import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.utils.DBCrudUtils;
import by.teachmeskills.utils.DBUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();
        DBUtils.initialize(ctx.getInitParameter("dbUrl"), ctx.getInitParameter("dbUsername"), ctx.getInitParameter("dbPassword"));
        try {
            DBCrudUtils.setConnection(DBUtils::getConnection);
        } catch (BadConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            DBCrudUtils.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
