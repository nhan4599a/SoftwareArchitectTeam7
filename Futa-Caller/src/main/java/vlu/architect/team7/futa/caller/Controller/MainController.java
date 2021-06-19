package vlu.architect.team7.futa.caller.Controller;

import Network.RestApiCaller;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController {

    private final RestApiCaller caller = RestApiCaller.getInstance();

    @RequestMapping("/trains")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getBusTrains() throws IOException {
        return caller.get("http://localhost:8101/futa/trains", null);
    }

    @RequestMapping("/places")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getAvailablePlaces() throws IOException {
        return caller.get("http://localhost:8101/futa/places", null);
    }

    private String fallback(Throwable throwable) {
        throwable.printStackTrace();
        return "[]";
    }
}
