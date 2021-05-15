package vlu.architect.team7.search.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
