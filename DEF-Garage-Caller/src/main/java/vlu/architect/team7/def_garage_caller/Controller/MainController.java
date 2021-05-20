package vlu.architect.team7.def_garage_caller.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.def_garage_caller.DTO.BusTrainDTO;
import vlu.architect.team7.def_garage_caller.Helper.SoapApiCaller;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class MainController {

    private static final String NAMESPACE = "http://architect.vlu/team7/def_garage/def_garage_service/soap";

    @RequestMapping("/")
    public List<BusTrainDTO> main() throws SOAPException, IOException {
        SoapApiCaller caller = new SoapApiCaller("http://localhost:8200/", "FindAllBusTrainRequest", NAMESPACE);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("keyword", "abc");
        return caller.call(parameters);
    }
}
