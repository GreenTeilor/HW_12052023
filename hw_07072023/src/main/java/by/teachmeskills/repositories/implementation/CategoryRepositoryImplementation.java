package by.teachmeskills.repositories.implementation;

import by.teachmeskills.entities.Category;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.repositories.CategoryRepository;
import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImplementation implements CategoryRepository {
    private final static String GET_CATEGORIES_QUERY = "SELECT * FROM categories";
    @Override
    public Category create(Category entity) {
        return null;
    }

    @Override
    public List<Category> read() throws BadConnectionException {
        List<Category> result = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(GET_CATEGORIES_QUERY);
            while (set.next()) {
                result.add(Category.builder().id(set.getInt("id")).name(set.getString("name")).imagePath(set.getString("imagePath")).build());
            }
            set.close();
            return result;
        } catch (SQLException e) {
            throw new BadConnectionException("Unable to execute query GET_CATEGORIES_QUERY");
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public Category update(Category entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
