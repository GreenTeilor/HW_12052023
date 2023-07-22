package by.teachmeskills.services;

import by.teachmeskills.entities.Cart;
import by.teachmeskills.entities.Product;
import by.teachmeskills.entities.User;
import org.springframework.web.servlet.ModelAndView;

public interface ProductService extends BaseService<Product>{
    ModelAndView getCategoryProducts(String category);
    ModelAndView getProductById(int id);
    ModelAndView findProducts(String keyWords);
    ModelAndView addProductToCart(int id, Cart cart);
    ModelAndView getUserOrders(User user);
    ModelAndView addAddressAndPhoneNumberInfo(String address, String phoneNumber, User userInSession);
}
