package by.teachmeskills.controllers;

import by.teachmeskills.constants.RequestAttributesNames;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("category")
public class CategoryController {
    private static final ProductService service = new ProductServiceImplementation();

    @GetMapping
    public ModelAndView openCategory(@RequestParam String name) {
        ModelAndView modelAndView = service.getCategoryProducts(name);
        modelAndView.addObject(RequestAttributesNames.CATEGORY_NAME, name);
        return modelAndView;
    }
}
