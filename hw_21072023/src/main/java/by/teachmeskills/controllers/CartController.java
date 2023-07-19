package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.constants.SessionAttributesNames;
import by.teachmeskills.entities.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cart")
public class CartController {

    @GetMapping
    public ModelAndView openCartPage() {
        return new ModelAndView();
    }

    @PostMapping
    public ModelAndView makeOperation(@SessionAttribute(name= SessionAttributesNames.CART) Cart cart, @RequestParam String actionType, Integer productId) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.CART_PAGE);
        switch (actionType) {
            case "removeProduct" -> cart.removeProduct(productId);
            case "clearCart" -> cart.clear();
            case "makeOrder" -> {}
            default -> throw new RuntimeException("Unknown parameter value");
        }
        modelAndView.addObject(RequestAttributesNames.PRODUCTS, cart.getProducts());
        return modelAndView;
    }
}
