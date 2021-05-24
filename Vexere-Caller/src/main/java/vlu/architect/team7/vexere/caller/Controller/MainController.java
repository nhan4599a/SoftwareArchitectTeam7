package vlu.architect.team7.vexere.caller.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.vexere.caller.DTO.BusTrainDTO;
import vlu.architect.team7.vexere.caller.Helper.SoapApiCaller;

import javax.xml.soap.SOAPException;
import java.util.HashMap;
import java.util.List;

@RestController
public class MainController {

    private static final String NAMESPACE = "http://architect.vlu/team7/vexere/service/soap";

    @RequestMapping("/")
    public List<BusTrainDTO> main() throws SOAPException {
        SoapApiCaller caller = new SoapApiCaller("http://localhost:8201/", "FindAllBusTrainRequest", NAMESPACE);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("keyword", "something");
        return caller.call(parameters);
    }
}
