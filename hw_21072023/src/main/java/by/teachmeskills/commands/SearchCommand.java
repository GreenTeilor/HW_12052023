package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
import by.teachmeskills.enums.RequestAttributesEnum;
import by.teachmeskills.enums.RequestParametersEnum;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.CategoryServiceImplementation;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static by.teachmeskills.utils.HttpRequestParamValidatorUtils.validateParametersNotNull;

public class SearchCommand implements BaseCommand {
    private static final Logger logger = LoggerFactory.getLogger(SearchCommand.class);
    private static final ProductService productService = new ProductServiceImplementation();
    private static final CategoryService categoryService = new CategoryServiceImplementation();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            String searchCriteria = request.getParameter(RequestParametersEnum.SEARCH_CRITERIA.getValue());
            try {
                validateParametersNotNull(searchCriteria);
                request.setAttribute(RequestAttributesEnum.PRODUCTS.getValue(), productService.findProducts(searchCriteria));
            } catch (CommandException e) {
                request.setAttribute(RequestAttributesEnum.PRODUCTS.getValue(), productService.read());
            }
            request.setAttribute(RequestAttributesEnum.CATEGORIES.getValue(), categoryService.read());
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return PagesPathsEnum.SEARCH_PAGE.getPath();
    }
}
