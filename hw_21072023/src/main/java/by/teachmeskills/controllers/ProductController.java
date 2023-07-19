package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.SessionAttributesNames;
import by.teachmeskills.entities.Cart;
import by.teachmeskills.entities.Product;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("product")
public class ProductController {
    private static final ProductService service = new ProductServiceImplementation();
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ModelAndView openProductPage(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.PRODUCT_PAGE);
        try {
            Product product = service.getProductById((id));
            modelAndView.addObject(product.getName());
            modelAndView.addObject(product);
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addProductToCart(@RequestParam int productId, @SessionAttribute(name = SessionAttributesNames.CART) Cart cart) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.PRODUCT_PAGE);
        try {
            Product product = service.getProductById((productId));
            cart.addProduct(product);
            modelAndView.addObject(product.getName());
            modelAndView.addObject(product);
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return modelAndView;
    }

}
