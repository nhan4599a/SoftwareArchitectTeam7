package vlu.architect.team7.partner.vexere.service.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import vlu.architect.team7.partner.vexere.service.DAO.BusTrainDAO;
import vlu.architect.team7.partner.vexere.service.DAO.PlaceDAO;
import vlu.architect.team7.partner.vexere.service.Model.Place;
import vlu.architect.team7.partner.vexere.service.soap.*;
import vlu.architect.team7.partner.vexere.service.Model.BusTrain;

import java.util.LinkedList;
import java.util.List;

@Endpoint
public class WebServiceEndpoint {

    private static final String NAMESPACE = "http://architect.vlu/team7/partner/vexere/service/soap";

    @Autowired
    private BusTrainDAO busTrainDAO;

    @Autowired
    private PlaceDAO placeDAO;

    @PayloadRoot(namespace = NAMESPACE, localPart = "FindAllBusTrainRequest")
    @ResponsePayload
    public FindAllBusTrainResponse findAllTrains(@RequestPayload FindAllBusTrainRequest request) {
        FindAllBusTrainResponse response = new FindAllBusTrainResponse();
        List<BusTrainDTO> trains = response.getBusTrainDTO();
        for (BusTrain train : busTrainDAO.findAll())
            trains.add(BusTrainDTO.from(train));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "FindAllPlacesRequest")
    @ResponsePayload
    public FindAllPLacesResponse findAllPlaces(@RequestPayload FindAllPlacesRequest request) {
        FindAllPLacesResponse response = new FindAllPLacesResponse();
        List<Place> places = new LinkedList<>();
        placeDAO.findAll().forEach(places::add);
        List<PlaceItemDTO> items = response.getPlaceItemDTO();
        final int MAX_SIZE = places.size();
        for (int i = 0; i < MAX_SIZE; i++)
            for (int j = i + 1; j < MAX_SIZE; j++)
                items.add(PlaceItemDTO.create(places.get(i).getPlaceName(), places.get(j).getPlaceName()));
        return response;
    }
}