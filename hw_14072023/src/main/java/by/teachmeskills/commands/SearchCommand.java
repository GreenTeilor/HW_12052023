package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathsEnum;
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

public class SearchCommand implements BaseCommand{
    private static final Logger logger = LoggerFactory.getLogger(SearchCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            ProductService productService = new ProductServiceImplementation();
            CategoryService categoryService = new CategoryServiceImplementation();
            switch (request.getParameter(RequestParametersEnum.TYPE.getValue())) {
                case "show" -> {
                    request.setAttribute("products", productService.read());
                    request.setAttribute("categories", categoryService.read());
                }
                case "find" -> {
                }
                default -> {
                    return PagesPathsEnum.HOME_PAGE.getPath();
                }
            }
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return PagesPathsEnum.SEARCH_PAGE.getPath();
    }
}
