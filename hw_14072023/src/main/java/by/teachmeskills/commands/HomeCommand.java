package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.SessionAttributesEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.entities.Category;
import by.teachmeskills.entities.User;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.implementation.CategoryServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class HomeCommand implements BaseCommand{

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            if (request.getSession().getAttribute(SessionAttributesEnum.USER.getValue()) != null) {
                CategoryService service = new CategoryServiceImplementation();
                List<Category> categories = service.read();
                request.setAttribute(RequestAttributesEnum.CATEGORIES.getValue(), categories);
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(SessionAttributesEnum.USER.getValue());
                request.setAttribute(RequestAttributesEnum.NAME.getValue(), user.getName());
                request.setAttribute(RequestAttributesEnum.LAST_NAME.getValue(), user.getLastName());
                request.setAttribute(RequestAttributesEnum.BALANCE.getValue(), user.getBalance());
                return PagesPathsEnum.HOME_PAGE.getPath();
            } else {
                return PagesPathsEnum.LOGIN_PAGE.getPath();
            }
        } catch (BadConnectionException e) {
            throw new RuntimeException(e);
        }
    }
}
