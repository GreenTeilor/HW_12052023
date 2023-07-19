package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.constants.SessionAttributesNames;
import by.teachmeskills.entities.Order;
import by.teachmeskills.entities.Product;
import by.teachmeskills.entities.Statistics;
import by.teachmeskills.entities.User;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.UserService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import by.teachmeskills.services.implementation.UserServiceImplementation;
import by.teachmeskills.utils.ValidatorUtils;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("profile")
@SessionAttributes(SessionAttributesNames.USER)
public class ProfileController {

    private static final ProductService productService = new ProductServiceImplementation();
    private static final UserService userService = new UserServiceImplementation();
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @GetMapping
    public ModelAndView openProfilePage(@ModelAttribute User user) {
        Statistics statistics = new Statistics(10, 2, 5, "Фантастика");
        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();
        try {
            list1 = new ArrayList<>(List.of(productService.getProductById(1), productService.getProductById(2), productService.getProductById(3)));
            list2 = new ArrayList<>(List.of(productService.getProductById(2), productService.getProductById(1)));
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        List<Order> orders = new ArrayList<>(List.of(Order.builder().id(1).date(LocalDate.now()).products(list1).userId(2).price(BigDecimal.valueOf(40.0)).build(),
                Order.builder().id(1).date(LocalDate.now()).products(list2).userId(2).price(BigDecimal.valueOf(50.0)).build()));
        return makeModelAndView(user, statistics, orders);
    }

    @PostMapping
    public ModelAndView addAddressAndPhoneNumberInfo(@RequestParam String address, @RequestParam String phoneNumber, @ModelAttribute User user) {
        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>();
        try {
            list1 = new ArrayList<>(List.of(productService.getProductById(1), productService.getProductById(2), productService.getProductById(3)));
            list2 = new ArrayList<>(List.of(productService.getProductById(2), productService.getProductById(1)));
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        List<Order> orders = new ArrayList<>(List.of(Order.builder().id(1).date(LocalDate.now()).products(list1).userId(2).price(BigDecimal.valueOf(40.0)).build(),
                Order.builder().id(1).date(LocalDate.now()).products(list2).userId(2).price(BigDecimal.valueOf(50.0)).build()));
        Statistics statistics = new Statistics(10, 2, 5, "Фантастика");
        if (ValidatorUtils.isValidAddress(address) && ValidatorUtils.isValidPhoneNumber(phoneNumber)) {
            try {
                user.setAddress(address);
                user.setPhoneNumber(phoneNumber);
                userService.updateAddressAndPhoneNumber(address, phoneNumber, user.getEmail());
            } catch (BadConnectionException e) {
                logger.error(e.getMessage());
            }
        }
        return makeModelAndView(user, statistics, orders);
    }

    public static ModelAndView makeModelAndView(User user, Statistics statistics, List<Order> orders) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.PROFILE_PAGE);
        modelAndView.addObject(user);
        modelAndView.addObject(statistics);
        modelAndView.addObject(RequestAttributesNames.ORDERS, orders);
        return modelAndView;
    }

}
