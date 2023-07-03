package by.teachmeskills.repositories.implementation;

import by.teachmeskills.entities.Product;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.repositories.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImplementation implements ProductRepository {
    private final static String GET_CATEGORY_PRODUCTS_QUERY = "SELECT * FROM products WHERE category = ?";
    private final static String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";

    @Override
    public List<Product> getCategoryProducts(String category) throws BadConnectionException {
        List<Product> result = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_PRODUCTS_QUERY)) {
            statement.setString(1, category);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                //result.add(new Product(set.getInt("id"), set.getString("name"),
                //        set.getString("description"), set.getString("imagePath"), set.getString("category"),
                //        set.getBigDecimal("price")));
                result.add(Product.builder().id(set.getInt("id")).name(set.getString("name")).description(set.getString("description")).
                        description(set.getString("description")).imagePath(set.getString("imagePath")).category(set.getString("category")).
                        price(set.getBigDecimal("price")).build());
            }
            set.close();
            return result;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query GET_PRODUCTS_IN_CATEGORY_QUERY");
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public Product getProductById(int id) throws BadConnectionException {
        Connection connection = pool.getConnection();
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                product = Product.builder().id(set.getInt("id")).name(set.getString("name")).description(set.getString("description")).
                        description(set.getString("description")).imagePath(set.getString("imagePath")).category(set.getString("category")).
                        price(set.getBigDecimal("price")).build();
            }
            set.close();
            return product;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query GET_PRODUCT_BY_ID_QUERY");
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public List<Product> read() {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
