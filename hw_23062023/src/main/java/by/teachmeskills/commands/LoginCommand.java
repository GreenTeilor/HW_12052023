package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.enums.SessionAttributesEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.types.Cart;
import by.teachmeskills.types.Category;
import by.teachmeskills.types.User;
import by.teachmeskills.utils.DBCrudUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.teachmeskills.utils.HttpRequestParamValidatorUtils.validateParametersNotNull;

public class LoginCommand implements BaseCommand{

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(RequestParametersEnum.EMAIL.getValue());
        String password = request.getParameter(RequestParametersEnum.PASSWORD.getValue());

        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttributesEnum.USER.getValue()) == null) {
            try {
                validateParametersNotNull(email, password);
                session.setAttribute(SessionAttributesEnum.USER.getValue(), DBCrudUtils.getUser(email, password));
                session.setAttribute(SessionAttributesEnum.CART.getValue(), new Cart());
            } catch (BadConnectionException e) {
                System.out.println(e.getMessage());
            } catch (CommandException e) {
                return PagesPathsEnum.LOGIN_PAGE.getPath();
            }
        }
        return checkReceivedUser(request);
    }

    private String checkReceivedUser(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(SessionAttributesEnum.USER.getValue());
        if (user != null) {
            request.getSession().setAttribute(SessionAttributesEnum.USER.getValue(), user);
            List<Category> categories = null;
            try {
                categories = DBCrudUtils.getCategories();
            } catch (BadConnectionException e) {
                System.out.println(e.getMessage());
            }

            request.setAttribute(RequestAttributesEnum.CATEGORIES.getValue(), categories);
            request.setAttribute(RequestAttributesEnum.NAME.getValue(), user.name());
            request.setAttribute(RequestAttributesEnum.LAST_NAME.getValue(), user.lastName());
            request.setAttribute(RequestAttributesEnum.BALANCE.getValue(), user.balance());

            return PagesPathsEnum.HOME_PAGE.getPath();
        } else {
            request.setAttribute(RequestAttributesEnum.STATUS.getValue(), "Неверный логин или пароль");
            return PagesPathsEnum.LOGIN_PAGE.getPath();
        }
    }
}
