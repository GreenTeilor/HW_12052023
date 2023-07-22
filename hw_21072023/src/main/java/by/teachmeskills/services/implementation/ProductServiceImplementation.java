package by.teachmeskills.services.implementation;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.entities.Cart;
import by.teachmeskills.entities.Order;
import by.teachmeskills.entities.Product;
import by.teachmeskills.entities.Statistics;
import by.teachmeskills.entities.User;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.repositories.CategoryRepository;
import by.teachmeskills.repositories.ProductRepository;
import by.teachmeskills.repositories.UserRepository;
import by.teachmeskills.repositories.implementation.CategoryRepositoryImplementation;
import by.teachmeskills.repositories.implementation.ProductRepositoryImplementation;
import by.teachmeskills.repositories.implementation.UserRepositoryImplementation;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository = new ProductRepositoryImplementation();
    private final UserRepository userRepository = new UserRepositoryImplementation();
    private final CategoryRepository categoryRepository = new CategoryRepositoryImplementation();
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplementation.class);

    @Override
    public ModelAndView getCategoryProducts(String category) {
        try {
            ModelAndView modelAndView = new ModelAndView(PagesPaths.CATEGORY_PAGE);
            List<Product> products = productRepository.getCategoryProducts(category);
            modelAndView.addObject(RequestAttributesNames.CATEGORY_PRODUCTS, products);
            return modelAndView;
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
    }

    @Override
    public ModelAndView getProductById(int id) {
        try {
            ModelAndView modelAndView = new ModelAndView(PagesPaths.PRODUCT_PAGE);
            Product product = productRepository.getProductById(id);
            modelAndView.addObject(product.getName());
            modelAndView.addObject(product);
            return modelAndView;
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
    }

    @Override
    public ModelAndView findProducts(String keyWords) {
        try {
            ModelAndView modelAndView = new ModelAndView(PagesPaths.SEARCH_PAGE);
            modelAndView.addObject(RequestAttributesNames.PRODUCTS, productRepository.findProducts(keyWords));
            modelAndView.addObject(RequestAttributesNames.CATEGORIES, categoryRepository.read());
            return modelAndView;
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
    }

    @Override
    public ModelAndView addProductToCart(int id, Cart cart) {
        try {
            ModelAndView modelAndView = new ModelAndView(PagesPaths.PRODUCT_PAGE);
            Product product = productRepository.getProductById(id);
            cart.addProduct(product);
            modelAndView.addObject(product.getName());
            modelAndView.addObject(product);
            return modelAndView;
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
    }

    public static ModelAndView makeModelAndView(User user, Statistics statistics, List<Order> orders) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.PROFILE_PAGE);
        modelAndView.addObject(user);
        modelAndView.addObject(statistics);
        modelAndView.addObject(RequestAttributesNames.ORDERS, orders);
        return modelAndView;
    }

    public ModelAndView getUserOrders(User user) {
        try {
            Statistics statistics = new Statistics(10, 2, 5, "Фантастика");
            List<Product> list1 = new ArrayList<>(List.of(productRepository.getProductById(1), productRepository.getProductById(2), productRepository.getProductById(3)));
            ;
            List<Product> list2 = new ArrayList<>(List.of(productRepository.getProductById(2), productRepository.getProductById(1)));
            List<Order> orders = new ArrayList<>(List.of(Order.builder().id(1).date(LocalDate.now()).products(list1).userId(2).price(BigDecimal.valueOf(40.0)).build(),
                    Order.builder().id(1).date(LocalDate.now()).products(list2).userId(2).price(BigDecimal.valueOf(50.0)).build()));
            return makeModelAndView(user, statistics, orders);
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
    }

    public ModelAndView addAddressAndPhoneNumberInfo(String address, String phoneNumber, User user) {
        try {
            Statistics statistics = new Statistics(10, 2, 5, "Фантастика");
            List<Product> list1 = new ArrayList<>(List.of(productRepository.getProductById(1), productRepository.getProductById(2), productRepository.getProductById(3)));
            List<Product> list2 = new ArrayList<>(List.of(productRepository.getProductById(2), productRepository.getProductById(1)));
            List<Order> orders = new ArrayList<>(List.of(Order.builder().id(1).date(LocalDate.now()).products(list1).userId(2).price(BigDecimal.valueOf(40.0)).build(),
                    Order.builder().id(1).date(LocalDate.now()).products(list2).userId(2).price(BigDecimal.valueOf(50.0)).build()));
            if (ValidatorUtils.isValidAddress(address) && ValidatorUtils.isValidPhoneNumber(phoneNumber)) {
                user.setAddress(address);
                user.setPhoneNumber(phoneNumber);
                userRepository.updateAddressAndPhoneNumber(address, phoneNumber, user.getEmail());
            }
            return makeModelAndView(user, statistics, orders);
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
            return new ModelAndView(PagesPaths.ERROR_PAGE);
        }
    }

    @Override
    public ModelAndView create(Product product) {
        try {
            ModelAndView modelAndView = new ModelAndView(PagesPaths.PRODUCT_PAGE);
            productRepository.create(product);
            return modelAndView;
        } catch (BadConnectionException | UserAlreadyExistsException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
    }

    @Override
    public ModelAndView read() {
        try {
            ModelAndView modelAndView = new ModelAndView(PagesPaths.SEARCH_PAGE);
            modelAndView.addObject(RequestAttributesNames.PRODUCTS, productRepository.read());
            modelAndView.addObject(RequestAttributesNames.CATEGORIES, categoryRepository.read());
            return modelAndView;
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
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
