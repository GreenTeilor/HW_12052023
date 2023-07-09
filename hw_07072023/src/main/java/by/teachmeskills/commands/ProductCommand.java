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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCommand implements BaseCommand{
    private static final Logger logger = LoggerFactory.getLogger(ProductCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Product product;
        ProductService service = new ProductServiceImplementation();
        try {
            product = service.getProductById((Integer.parseInt(request.getParameter(RequestParametersEnum.ID.getValue()))));
            request.setAttribute(RequestAttributesEnum.PRODUCT_NAME.getValue(), product.getName());
            request.setAttribute(RequestAttributesEnum.PRODUCT.getValue(), product);
        } catch (BadConnectionException e) {
            logger.info(e.getMessage());
        }
        return PagesPathsEnum.PRODUCT_PAGE.getPath();
    }
}
