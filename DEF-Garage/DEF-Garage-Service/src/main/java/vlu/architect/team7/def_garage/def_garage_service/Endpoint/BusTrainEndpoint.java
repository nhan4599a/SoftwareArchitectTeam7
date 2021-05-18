package vlu.architect.team7.def_garage.def_garage_service.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import vlu.architect.team7.def_garage.def_garage_service.DAO.BusTrainDAO;
import vlu.architect.team7.def_garage.def_garage_service.Model.BusTrain;
import vlu.architect.team7.def_garage.def_garage_service.soap.BusTrainDTO;
import vlu.architect.team7.def_garage.def_garage_service.soap.FindAllBusTrainRequest;
import vlu.architect.team7.def_garage.def_garage_service.soap.FindAllBusTrainResponse;

import java.util.List;

@Endpoint
public class BusTrainEndpoint {

    private static final String NAMESPACE = "http://architect.vlu/team7/def_garage/def_garage_service/soap";

    @Autowired
    private BusTrainDAO busTrainDAO;

    @PayloadRoot(namespace = NAMESPACE, localPart = "FindAllBusTrainRequest")
    @ResponsePayload
    public FindAllBusTrainResponse findAll(@RequestPayload FindAllBusTrainRequest request) {
        FindAllBusTrainResponse response = new FindAllBusTrainResponse();
        List<BusTrainDTO> trains = response.getBusTrainDTO();
        for (BusTrain train : busTrainDAO.findAll()) {
            trains.add(new BusTrainDTO(train));
        }
        return response;
    }
}