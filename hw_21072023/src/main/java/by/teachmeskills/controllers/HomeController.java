package by.teachmeskills.controllers;

import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.entities.User;
import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.SessionAttributesNames;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.implementation.CategoryServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("home")
@SessionAttributes(SessionAttributesNames.USER)
public class HomeController {
    private static final CategoryService categoryService = new CategoryServiceImplementation();
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public ModelAndView openHomePage(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.HOME_PAGE);
        try {
            modelAndView.addObject(RequestAttributesNames.CATEGORIES, categoryService.read());
            modelAndView.addObject(RequestAttributesNames.NAME, user.getName());
            modelAndView.addObject(RequestAttributesNames.LAST_NAME, user.getLastName());
            modelAndView.addObject(RequestAttributesNames.BALANCE, user.getBalance());
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return modelAndView;
    }
}

