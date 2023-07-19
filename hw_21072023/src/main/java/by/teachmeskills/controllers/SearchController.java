package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.CategoryServiceImplementation;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("search")
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
    private static final ProductService productService = new ProductServiceImplementation();
    private static final CategoryService categoryService = new CategoryServiceImplementation();

    @GetMapping
    public ModelAndView openSearchPage() {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.SEARCH_PAGE);
        try {
            modelAndView.addObject(RequestAttributesNames.PRODUCTS, productService.read());
            modelAndView.addObject(RequestAttributesNames.CATEGORIES, categoryService.read());
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping
    public ModelAndView search(String searchCriteria) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.SEARCH_PAGE);
        try {
            modelAndView.addObject(RequestAttributesNames.PRODUCTS, productService.findProducts(searchCriteria));
            modelAndView.addObject(RequestAttributesNames.CATEGORIES, categoryService.read());
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return modelAndView;
    }
}
