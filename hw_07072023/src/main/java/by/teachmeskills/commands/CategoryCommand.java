package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.entities.Product;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CategoryCommand implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String categoryName = request.getParameter(RequestParametersEnum.NAME.getValue());
        List<Product> products;
        ProductService service = new ProductServiceImplementation();
        try {
            products = service.getCategoryProducts(categoryName);
        } catch (BadConnectionException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute(RequestAttributesEnum.CATEGORY_NAME.getValue(), categoryName);
        request.setAttribute(RequestAttributesEnum.CATEGORY_PRODUCTS.getValue(), products);
        return PagesPathsEnum.CATEGORY_PAGE.getPath();
    }
}
