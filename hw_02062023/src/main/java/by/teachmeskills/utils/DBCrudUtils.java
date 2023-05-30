package by.teachmeskills.utils;

import by.teachmeskills.User;
import by.teachmeskills.exceptions.BadConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

public class DBCrudUtils {
    private final static String SEARCH_QUERY = "SELECT * FROM users WHERE login = ? and password = ?";
    private static Connection connection;

    private DBCrudUtils() {

    }

    public static void setConnection(Supplier<Connection> connector) throws BadConnectionException {
        connection = connector.get();
        if (connection == null) {
            throw new BadConnectionException("Connection is not established");
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static boolean isUserPresent(User user) throws BadConnectionException {
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_QUERY)) {
            statement.setString(1, user.login());
            statement.setString(2, user.password());
            ResultSet set = statement.executeQuery();
            int size = 0;
            while (set.next()) {
                ++size;
            }
            set.close();
            return size != 0;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query");
        }
    }
}
