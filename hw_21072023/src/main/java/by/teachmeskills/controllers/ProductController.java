package by.teachmeskills.controllers;

import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.implementation.ProductServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("products")
public class ProductController {
    private static final ProductService service = new ProductServiceImplementation();

    @GetMapping("{id}")
    public ModelAndView openProductPage(@PathVariable int id) {
        return service.getProductById(id);
    }

}
