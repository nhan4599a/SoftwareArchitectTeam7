package vlu.architect.team7.abc_garage_caller.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.abc_garage_caller.Helper.RestApiCaller;

@RestController
public class MainController {

    @RequestMapping("/")
    public String main() {
        try {
            RestApiCaller caller = new RestApiCaller("http://localhost:8100/");
            return caller.get(null);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
