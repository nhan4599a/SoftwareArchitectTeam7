package vlu.architect.team7.partner.futa.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.partner.futa.service.DAO.BusTrainDAO;
import vlu.architect.team7.partner.futa.service.DAO.PlaceDAO;
import vlu.architect.team7.partner.futa.service.DTO.BusTrainDTO;
import vlu.architect.team7.partner.futa.service.DTO.PlaceItemDTO;
import vlu.architect.team7.partner.futa.service.Model.BusTrain;
import vlu.architect.team7.partner.futa.service.Model.Place;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static vlu.architect.team7.partner.futa.service.Helper.MathHelper.combinationSize;

@RestController
public class MainController {

    @Autowired
    private BusTrainDAO busTrainDAO;

    @Autowired
    private PlaceDAO placeDAO;

    @RequestMapping("/trains")
    public List<BusTrainDTO> findAllTrains() throws InterruptedException {
        final List<BusTrain> trains = new ArrayList<>();
        busTrainDAO.findAll().forEach(trains::add);
        return BusTrainDTO.create(trains);
    }

    @RequestMapping("/places")
    public PlaceItemDTO[] getAvailableTrains() {
        final List<Place> places = new LinkedList<>();
        placeDAO.findAll().forEach(places::add);
        final int MAX_SIZE = places.size();
        final PlaceItemDTO[] result = new PlaceItemDTO[combinationSize(2, MAX_SIZE)];
        int currentIndex = 0;
        for (int i = 0; i < MAX_SIZE; i++)
            for (int j = i + 1; j < MAX_SIZE; j++)
                result[currentIndex++] = new PlaceItemDTO(
                        places.get(i).getPlaceName(), places.get(j).getPlaceName());
        return result;
    }
}
