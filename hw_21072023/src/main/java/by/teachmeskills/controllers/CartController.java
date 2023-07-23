package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.constants.SessionAttributesNames;
import by.teachmeskills.entities.Cart;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cart")
@SessionAttributes(SessionAttributesNames.CART)
public class CartController {

    private static final ProductService service = new ProductServiceImplementation();

    @GetMapping
    public ModelAndView openCartPage(@ModelAttribute(SessionAttributesNames.CART) Cart cart) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.CART_PAGE);
        modelAndView.addObject(RequestAttributesNames.CART, cart);
        return new ModelAndView(PagesPaths.CART_PAGE);
    }

    @PostMapping
    public ModelAndView makeOperation(@ModelAttribute(SessionAttributesNames.CART) Cart cart, @RequestParam String actionType, Integer productId) {
        return service.processCartOperation(cart, actionType, productId);
    }

    @ModelAttribute(SessionAttributesNames.CART)
    public Cart initializeCartInSession() {
        return new Cart();
    }
}
