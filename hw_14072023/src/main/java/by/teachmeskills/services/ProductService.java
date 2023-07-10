package by.teachmeskills.services;

import by.teachmeskills.entities.Product;
import by.teachmeskills.exceptions.BadConnectionException;

import java.util.List;

public interface ProductService extends BaseService<Product>{
    List<Product> getCategoryProducts(String category) throws BadConnectionException;
    Product getProductById(int id) throws BadConnectionException;
    List<Product> findProducts(String keyWords) throws BadConnectionException;
}
