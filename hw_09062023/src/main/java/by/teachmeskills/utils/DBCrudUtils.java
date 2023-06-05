package by.teachmeskills.utils;

import by.teachmeskills.types.Category;
import by.teachmeskills.types.Product;
import by.teachmeskills.types.User;
import by.teachmeskills.exceptions.BadConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DBCrudUtils {
    private final static String SEARCH_QUERY = "SELECT * FROM users WHERE email = ? and password = ?";
    private final static String GET_CATEGORIES_QUERY = "SELECT * FROM categories";
    private final static String GET_CATEGORY_PRODUCTS_QUERY = "SELECT * FROM products WHERE category = ?";
    private final static String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
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

    public static User getUser(String email, String password) throws BadConnectionException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_QUERY)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                user = new User(set.getString("name"), set.getString("lastName"),
                        set.getString("email"), set.getBigDecimal("balance"), set.getString("password"));
            }
            set.close();
            return user;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query SEARCH_QUERY");
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
            throw new BadConnectionException("Unable to execute query GET_PRODUCTS_IN_CATEGORY_QUERY");
        }
    }
}
