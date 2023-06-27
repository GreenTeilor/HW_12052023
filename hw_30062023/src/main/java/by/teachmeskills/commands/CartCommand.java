package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.enums.SessionAttributesEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.types.Cart;
import by.teachmeskills.utils.DBCrudUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartCommand implements BaseCommand {
    private static final Logger logger = LoggerFactory.getLogger(CartCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Cart cart = (Cart) request.getSession().getAttribute(SessionAttributesEnum.CART.getValue());

        try {
            switch (request.getParameter(RequestParametersEnum.TYPE.getValue())) {
                case "show" -> request.setAttribute(RequestAttributesEnum.CART.getValue(), cart);
                case "addProduct" -> {
                    cart.addProduct(DBCrudUtils.getProduct(Integer.parseInt(request.getParameter(RequestParametersEnum.ID.getValue()))));
                    return new ProductCommand().execute(request);
                }
                case "removeProduct" -> cart.removeProduct(Integer.parseInt(request.getParameter(RequestParametersEnum.ID.getValue())));
                case "removeAll" -> cart.clear();
                default -> {
                    return PagesPathsEnum.HOME_PAGE.getPath();
                }
            }
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        request.setAttribute(RequestAttributesEnum.CART.getValue(), cart);
        return PagesPathsEnum.CART_PAGE.getPath();
    }
}
