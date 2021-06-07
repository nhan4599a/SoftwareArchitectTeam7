package vlu.architect.team7.vexere.caller.Controller;

import DTO.BusTrainDTO;
import DTO.PlaceItemDTO;
import Network.SoapApiCaller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.SOAPException;
import java.util.HashMap;
import java.util.List;

@RestController
public class MainController {

    private static final String NAMESPACE = "http://architect.vlu/team7/partner/vexere/service/soap";
    private final SoapApiCaller caller = SoapApiCaller.getInstance(
            "http://localhost:8201/", NAMESPACE);

    @RequestMapping("/trains")
    public List<BusTrainDTO> getBusTrains() throws SOAPException {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("keyword", "something");
        return BusTrainDTO.createFromSoapMessage(caller.call("FindAllBusTrainRequest", parameters));
    }

    @RequestMapping("/places")
    public List<PlaceItemDTO> getAvailablePlaces() throws SOAPException {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("ignore", "ignore");
        return PlaceItemDTO.createFromSoapMessage(caller.call("FindAllPlacesRequest", parameters));
    }
}
