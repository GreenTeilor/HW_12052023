package by.teachmeskills.controllers;

import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("search")
public class SearchController {
    private static final ProductService productService = new ProductServiceImplementation();

    @GetMapping
    public ModelAndView openSearchPage() {
        return productService.read();
    }

    @PostMapping
    public ModelAndView search(String searchCriteria) {
        return productService.findProducts(searchCriteria);
    }
}
