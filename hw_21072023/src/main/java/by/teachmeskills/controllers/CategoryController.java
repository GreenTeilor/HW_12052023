package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.entities.Product;
import by.teachmeskills.exceptions.BadConnectionException;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    private static final ProductService service = new ProductServiceImplementation();
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    public ModelAndView openCategory(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView(PagesPaths.CATEGORY_PAGE);
        try {
            List<Product> products = service.getCategoryProducts(name);
            modelAndView.addObject(RequestAttributesNames.CATEGORY_PRODUCTS, products);
            modelAndView.addObject(RequestAttributesNames.CATEGORY_NAME, name);
        } catch (BadConnectionException e) {
            logger.error(e.getMessage());
        }
        return modelAndView;
    }
}
