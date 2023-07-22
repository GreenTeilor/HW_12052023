package by.teachmeskills.controllers;

import by.teachmeskills.constants.SessionAttributesNames;
import by.teachmeskills.entities.User;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("profile")
public class ProfileController {

    private static final ProductService productService = new ProductServiceImplementation();

    @GetMapping
    public ModelAndView openProfilePage(@SessionAttribute(SessionAttributesNames.USER) User user) {
        return productService.getUserOrders(user);
    }

    @PostMapping
    public ModelAndView addAddressAndPhoneNumberInfo(@ModelAttribute(SessionAttributesNames.USER) User user, @SessionAttribute(SessionAttributesNames.USER) User userInSession) {
        return productService.addAddressAndPhoneNumberInfo(user.getAddress(), user.getPhoneNumber(), userInSession);
    }

}
