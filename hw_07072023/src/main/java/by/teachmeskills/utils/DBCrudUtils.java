package by.teachmeskills.utils;

import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.types.Category;
import by.teachmeskills.types.Product;
import by.teachmeskills.types.User;
import by.teachmeskills.exceptions.BadConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class DBCrudUtils {
    private final static String SEARCH_USER_QUERY = "SELECT * FROM users WHERE email = ? and password = ?";
    private final static String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private final static String ADD_USER_QUERY = "INSERT INTO users (id, name, lastName, email, birthDate, registrationDate, balance, password) VALUES (?, ?, ?, ?, ?, ?, 0.0, ?)";
    private final static String GET_CATEGORIES_QUERY = "SELECT * FROM categories";
    private final static String GET_CATEGORY_PRODUCTS_QUERY = "SELECT * FROM products WHERE category = ?";
    private final static String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final static String UPDATE_ADDRESS_AND_PHONE_NUMBER_QUERY = "UPDATE users SET address = ?, phoneNumber = ? WHERE email = ?";
    private static Connection connection;

    private DBCrudUtils() {

    }

    public static void setConnection(Supplier<Connection> connector) throws BadConnectionException {
        connection = connector.get();
        if (connection == null) {
            throw new BadConnectionException("Connection is not established");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static User getUser(String email, String password) throws BadConnectionException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_USER_QUERY)) {
            statement.setString(1, email);
            statement.setString(2, HashUtils.getHash(password));
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                user = new User(set.getString("name"), set.getString("lastName"),
                        set.getString("email"), set.getTimestamp("birthDate").toLocalDateTime().toLocalDate(),
                        set.getTimestamp("registrationDate").toLocalDateTime().toLocalDate(), set.getBigDecimal("balance"), set.getString("password"),
                        set.getString("address"), set.getString("phoneNumber"));
            }
            set.close();
            return user;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query SEARCH_USER_QUERY");
        }
    }

    public static User getUserByEmail(String email) throws BadConnectionException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                user = new User(set.getString("name"), set.getString("lastName"),
                        set.getString("email"), set.getTimestamp("birthDate").toLocalDateTime().toLocalDate(),
                        set.getTimestamp("registrationDate").toLocalDateTime().toLocalDate(), set.getBigDecimal("balance"), set.getString("password"),
                        set.getString("address"), set.getString("phoneNumber"));
            }
            set.close();
            return user;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query GET_USER_BY_EMAIL_QUERY");
        }
    }

    public static void addUser(User user) throws BadConnectionException, UserAlreadyExistsException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER_QUERY)) {
            if (getUserByEmail(user.email()) != null) {
                throw new UserAlreadyExistsException("Такой пользователь уже существует");
            }
            statement.setString(1, String.valueOf(UUID.randomUUID()));
            statement.setString(2, user.name());
            statement.setString(3, user.lastName());
            statement.setString(4, user.email());
            statement.setTimestamp(5, Timestamp.valueOf(user.birthDate().atStartOfDay()));
            statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(7, HashUtils.getHash(user.password()));
            statement.execute();
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query ADD_USER_QUERY");
        }
    }

    public static List<Category> getCategories() throws BadConnectionException {
        List<Category> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(GET_CATEGORIES_QUERY);
            while (set.next()) {
                result.add(new Category(set.getString("name"), set.getString("imagePath")));
            }
            set.close();
            return result;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query GET_CATEGORIES_QUERY");
        }
    }

    public static List<Product> getCategoryProducts(String category) throws BadConnectionException {
        List<Product> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_PRODUCTS_QUERY)) {
            statement.setString(1, category);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(new Product(set.getInt("id"), set.getString("name"),
                        set.getString("description"), set.getString("imagePath"), set.getString("category"),
                        set.getBigDecimal("price")));
            }
            set.close();
            return result;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query GET_PRODUCTS_IN_CATEGORY_QUERY");
        }
    }

    public static Product getProduct(int id) throws BadConnectionException {
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = new Product(set.getInt("id"), set.getString("name"),
                        set.getString("description"), set.getString("imagePath"), set.getString("category"),
                        set.getBigDecimal("price"));
            }
            set.close();
            return product;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query GET_PRODUCT_BY_ID_QUERY");
        }
    }

    public static void updateAddressAndPhoneNumber(String address, String phoneNumber, String email) throws BadConnectionException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ADDRESS_AND_PHONE_NUMBER_QUERY)) {
            statement.setString(1, address);
            statement.setString(2, phoneNumber);
            statement.setString(3, email);
            statement.execute();
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query UPDATE_ADDRESS_AND_PHONE_NUMBER_QUERY");
        }
    }
}
