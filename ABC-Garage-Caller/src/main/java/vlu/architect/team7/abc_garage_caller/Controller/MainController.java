package vlu.architect.team7.abc_garage_caller.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.abc_garage_caller.Helper.RestAPICaller;

@RestController
public class MainController {

    @RequestMapping("/")
    public String main() {
        try {
            RestAPICaller caller = new RestAPICaller("http://localhost:8101/");
            return caller.get(null);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
