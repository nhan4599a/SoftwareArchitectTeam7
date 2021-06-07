package vlu.architect.team7.futa.caller.Controller;

import Network.RestApiCaller;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final RestApiCaller caller = RestApiCaller.getInstance();

    @RequestMapping("/trains")
    public String getBusTrains() {
        try {
            LoggerFactory.getLogger(MainController.class).error("Received request");
            return caller.get("http://localhost:8101/futa/trains", null);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("/places")
    public String getAvailablePlaces() {
        try {
            return caller.get("http://localhost:8101/futa/places", null);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
