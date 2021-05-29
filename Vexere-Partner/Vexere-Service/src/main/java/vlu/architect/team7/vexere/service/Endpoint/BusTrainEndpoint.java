package vlu.architect.team7.vexere.service.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import vlu.architect.team7.vexere.service.DAO.BusTrainDAO;
import vlu.architect.team7.vexere.service.Model.BusTrain;
import vlu.architect.team7.vexere.service.soap.BusTrainDTO;
import vlu.architect.team7.vexere.service.soap.FindAllBusTrainRequest;
import vlu.architect.team7.vexere.service.soap.FindAllBusTrainResponse;

import java.util.List;

@Endpoint
public class BusTrainEndpoint {

    private static final String NAMESPACE = "http://architect.vlu/team7/vexere/service/soap";

    @Autowired
    private BusTrainDAO busTrainDAO;

    @PayloadRoot(namespace = NAMESPACE, localPart = "FindAllBusTrainRequest")
    @ResponsePayload
    public FindAllBusTrainResponse findAll(@RequestPayload FindAllBusTrainRequest request) {
        FindAllBusTrainResponse response = new FindAllBusTrainResponse();
        List<BusTrainDTO> trains = response.getBusTrainDTO();
        for (BusTrain train : busTrainDAO.findAll()) {
            trains.add(BusTrainDTO.from(train));
        }
        return response;
    }
}