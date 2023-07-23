package by.teachmeskills.services.implementation;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.entities.User;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.UserAlreadyExistsException;
import by.teachmeskills.repositories.UserRepository;
import by.teachmeskills.repositories.implementation.UserRepositoryImplementation;
import by.teachmeskills.services.UserService;
import by.teachmeskills.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class UserServiceImplementation implements UserService {
    UserRepository userRepository = new UserRepositoryImplementation();
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Override
    public User getUserByEmail(String email) throws BadConnectionException {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserById(int id) throws BadConnectionException {
        return userRepository.getUserById(id);
    }

    @Override
    public ModelAndView getUser(String email, String password, Model model) {
        try {
            ModelAndView modelAndView = new ModelAndView(PagesPaths.LOGIN_PAGE);
            User authenticatedUser = userRepository.getUser(email, password);
            if (authenticatedUser != null) {
                model.addAttribute(RequestAttributesNames.USER, authenticatedUser);
                return new ModelAndView("redirect:" + PagesPaths.HOME_PAGE);
            } else {
                modelAndView.addObject(RequestAttributesNames.STATUS, "Неверный логин или пароль");
                return modelAndView;
            }
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
    }

    @Override
    public void updateAddressAndPhoneNumber(String address, String phoneNumber, String email) throws BadConnectionException {
        userRepository.updateAddressAndPhoneNumber(address, phoneNumber, email);
    }

    @Override
    public ModelAndView create(User user) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.REGISTER_PAGE);
        ValidatorUtils.Status status = ValidatorUtils.validateForm(user.getName(), user.getLastName(),
                user.getEmail(), user.getBirthDate(), user.getPassword());
        if (status == ValidatorUtils.Status.VALID) {
            try {
                user.setBalance(BigDecimal.valueOf(0.0));
                user.setRegistrationDate(LocalDate.now());
                userRepository.create(user);
                modelAndView.addObject(RequestAttributesNames.STATUS, status.toString());
                modelAndView.addObject(RequestAttributesNames.COLOR, "green");
            } catch (BadConnectionException e) {
                logger.error(e.getMessage());
                return new ModelAndView(PagesPaths.ERROR_PAGE);
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

    @Override
    public ModelAndView read() {
        try {
            ModelAndView modelAndView = new ModelAndView(PagesPaths.HOME_PAGE);
            modelAndView.addObject(RequestAttributesNames.USER, userRepository.read());
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView(PagesPaths.ERROR_PAGE);
    }

    @Override
    public User update(User user) throws BadConnectionException {
        return userRepository.update(user);
    }

    @Override
    public void delete(int id) throws BadConnectionException {
        userRepository.delete(id);
    }
}
