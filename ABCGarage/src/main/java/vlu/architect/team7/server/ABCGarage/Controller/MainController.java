package vlu.architect.team7.server.ABCGarage.Controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    public String home() {
        return "Hello world!";
    }
}
