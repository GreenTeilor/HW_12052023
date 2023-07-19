package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.entities.User;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.services.UserService;
import by.teachmeskills.services.implementation.UserServiceImplementation;
import by.teachmeskills.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("register")
public class RegisterController {

    private static final UserService service = new UserServiceImplementation();
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @GetMapping
    public String openRegisterPage() {
        return PagesPaths.REGISTER_PAGE;
    }

    @PostMapping
    public ModelAndView registerUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.REGISTER_PAGE);
        ValidatorUtils.Status status = ValidatorUtils.validateForm(user.getName(), user.getLastName(),
                user.getEmail(), user.getBirthDate(), user.getPassword());
        if (status == ValidatorUtils.Status.VALID) {
            try {
                user.setBalance(BigDecimal.valueOf(0.0));
                user.setRegistrationDate(LocalDate.now());
                service.create(user);
                modelAndView.addObject(RequestAttributesNames.STATUS, status.toString());
                modelAndView.addObject(RequestAttributesNames.COLOR, "green");
            } catch (BadConnectionException e) {
                logger.error(e.getMessage());
            } catch (UserAlreadyExistsException e) {
                modelAndView.addObject(RequestAttributesNames.STATUS, e.getMessage());
                modelAndView.addObject(RequestAttributesNames.COLOR, "red");
            }
        } else {
            modelAndView.addObject(RequestAttributesNames.STATUS, status.toString());
            modelAndView.addObject(RequestAttributesNames.COLOR, "red");
        }
        return modelAndView;
    }
}
