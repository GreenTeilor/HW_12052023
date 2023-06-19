package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.types.Product;
import by.teachmeskills.utils.DBCrudUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CategoryCommand implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String categoryName = request.getParameter(RequestParametersEnum.NAME.getValue());
        List<Product> products;
        try {
            products = DBCrudUtils.getCategoryProducts(categoryName);
        } catch (BadConnectionException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute(RequestAttributesEnum.CATEGORY_NAME.getValue(), categoryName);
        request.setAttribute(RequestAttributesEnum.CATEGORY_PRODUCTS.getValue(), products);
        return PagesPathsEnum.CATEGORY_PAGE.getPath();
    }
}
