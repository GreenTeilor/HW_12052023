package by.teachmeskills.controllers;

import by.teachmeskills.constants.PagesPaths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("about")
public class AboutAuthorController {
    @GetMapping
    public String openAboutAuthorPage() {
        return PagesPaths.ABOUT_PAGE;
    }
}
