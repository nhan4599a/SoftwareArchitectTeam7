package vlu.architect.team7.abc_garage_caller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MainController {

    @RequestMapping("/")
    public List<Object> main() {
        return null;
    }
}
