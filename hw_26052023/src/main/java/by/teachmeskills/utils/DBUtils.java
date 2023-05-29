package by.teachmeskills.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static String dbUrl = "jdbc:mysql://localhost:3306/users_db";
    private static String dbUsername = "root";
    private static String dbPassword = "123";

    public static void initialize(String dbUrl, String dbUsername, String dbPassword) {
        DBUtils.dbUrl = dbUrl;
        DBUtils.dbUsername = dbUsername;
        DBUtils.dbPassword = dbPassword;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
