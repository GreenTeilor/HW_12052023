package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.constants.SessionAttributesNames;
import by.teachmeskills.entities.Cart;
import by.teachmeskills.entities.User;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.services.UserService;
import by.teachmeskills.services.implementation.UserServiceImplementation;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginController {
    private static final UserService userService = new UserServiceImplementation();
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public String openLoginPage() {
        return PagesPaths.LOGIN_PAGE;
    }

    @PostMapping
    public ModelAndView login(@ModelAttribute User user, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.LOGIN_PAGE);
        try {
            User authenticatedUser = userService.getUser(user.getEmail(), user.getPassword());
            if (authenticatedUser != null) {
                session.setAttribute(SessionAttributesNames.USER, authenticatedUser);
                session.setAttribute(SessionAttributesNames.CART, new Cart());
                return new ModelAndView("redirect:" + PagesPaths.HOME_PAGE);
            } else {
                modelAndView.addObject(RequestAttributesNames.STATUS, "Неверный логин или пароль");
            }
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return modelAndView;
    }
}
