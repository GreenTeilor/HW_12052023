package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.types.Product;
import by.teachmeskills.utils.DBCrudUtils;
import jakarta.servlet.http.HttpServletRequest;

public class ProductCommand implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Product product;
        try {
            product = DBCrudUtils.getProduct(Integer.parseInt(request.getParameter(RequestParametersEnum.ID.getValue())));
            request.setAttribute(RequestAttributesEnum.PRODUCT_NAME.getValue(), product.name());
            request.setAttribute(RequestAttributesEnum.PRODUCT.getValue(), product);
        } catch (BadConnectionException e) {
            System.out.println(e.getMessage());
        }
        return PagesPathsEnum.PRODUCT_PAGE.getPath();
    }
}
