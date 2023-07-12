package by.teachmeskills.repositories;

import by.teachmeskills.entities.Product;
import by.teachmeskills.exceptions.BadConnectionException;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product>{
    List<Product> getCategoryProducts(String category) throws BadConnectionException;
    Product getProductById(int id) throws BadConnectionException;

    List<Product> findProducts(String keyWords) throws BadConnectionException;
}
