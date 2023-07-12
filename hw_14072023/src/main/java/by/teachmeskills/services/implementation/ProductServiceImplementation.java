package by.teachmeskills.services.implementation;

import by.teachmeskills.entities.Product;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.repositories.ProductRepository;
import by.teachmeskills.repositories.implementation.ProductRepositoryImplementation;
import by.teachmeskills.services.ProductService;

import java.util.List;

public class ProductServiceImplementation implements ProductService {
    ProductRepository productRepository = new ProductRepositoryImplementation();

    @Override
    public List<Product> getCategoryProducts(String category) throws BadConnectionException {
        return productRepository.getCategoryProducts(category);
    }

    @Override
    public Product getProductById(int id) throws BadConnectionException {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> findProducts(String keyWords) throws BadConnectionException {
        return productRepository.findProducts(keyWords);
    }

    @Override
    public Product create(Product product) throws BadConnectionException, UserAlreadyExistsException {
        return productRepository.create(product);
    }

    @Override
    public List<Product> read() throws BadConnectionException {
        return productRepository.read();
    }

    @Override
    public Product update(Product product) throws BadConnectionException {
        return productRepository.update(product);
    }

    @Override
    public void delete(int id) throws BadConnectionException {
        productRepository.delete(id);
    }
}
