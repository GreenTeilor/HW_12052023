package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.enums.SessionAttributesEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.entities.Cart;
import by.teachmeskills.entities.Category;
import by.teachmeskills.entities.User;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.UserService;
import by.teachmeskills.services.implementation.CategoryServiceImplementation;
import by.teachmeskills.services.implementation.UserServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.teachmeskills.utils.HttpRequestParamValidatorUtils.validateParametersNotNull;

public class LoginCommand implements BaseCommand{
    private static final Logger logger = LoggerFactory.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(RequestParametersEnum.EMAIL.getValue());
        String password = request.getParameter(RequestParametersEnum.PASSWORD.getValue());

        HttpSession session = request.getSession();
        UserService service = new UserServiceImplementation();
        if (session.getAttribute(SessionAttributesEnum.USER.getValue()) == null) {
            try {
                validateParametersNotNull(email, password);
                session.setAttribute(SessionAttributesEnum.USER.getValue(), service.getUser(email, password));
                session.setAttribute(SessionAttributesEnum.CART.getValue(), new Cart());
            } catch (BadConnectionException e) {
                logger.error(e.getMessage());
            } catch (CommandException e) {
                return PagesPathsEnum.LOGIN_PAGE.getPath();
            }
        }
        return checkReceivedUser(request);
    }

    private String checkReceivedUser(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(SessionAttributesEnum.USER.getValue());
        CategoryService service = new CategoryServiceImplementation();
        if (user != null) {
            request.getSession().setAttribute(SessionAttributesEnum.USER.getValue(), user);
            List<Category> categories = null;
            try {
                categories = service.read();
            } catch (BadConnectionException e) {
                logger.error(e.getMessage());
            }

            request.setAttribute(RequestAttributesEnum.CATEGORIES.getValue(), categories);
            request.setAttribute(RequestAttributesEnum.NAME.getValue(), user.getName());
            request.setAttribute(RequestAttributesEnum.LAST_NAME.getValue(), user.getLastName());
            request.setAttribute(RequestAttributesEnum.BALANCE.getValue(), user.getBalance());

            return PagesPathsEnum.HOME_PAGE.getPath();
        } else {
            request.setAttribute(RequestAttributesEnum.STATUS.getValue(), "Неверный логин или пароль");
            return PagesPathsEnum.LOGIN_PAGE.getPath();
        }
    }
}
