package by.teachmeskills.controllers;

import by.teachmeskills.constants.SessionAttributesNames;
import by.teachmeskills.entities.Cart;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
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

    @GetMapping
    public ModelAndView openProductPage(@RequestParam int id) {
        return service.getProductById(id);
    }

    @PostMapping
    public ModelAndView addProductToCart(@RequestParam int productId, @SessionAttribute(SessionAttributesNames.CART) Cart cart) {
        return service.addProductToCard(productId, cart);
    }

}
