package vlu.architect.team7.search.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
