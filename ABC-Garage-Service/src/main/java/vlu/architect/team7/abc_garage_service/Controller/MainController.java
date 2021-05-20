package vlu.architect.team7.abc_garage_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.abc_garage_service.DAO.BusTrainDAO;
import vlu.architect.team7.abc_garage_service.DTO.BusTrainDTO;
import vlu.architect.team7.abc_garage_service.Model.BusTrain;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private BusTrainDAO busTrainDAO;

    @RequestMapping("/")
    public List<BusTrainDTO> findAll() {
        List<BusTrain> trains = new ArrayList<>();
        busTrainDAO.findAll().forEach(trains::add);
        return BusTrainDTO.create(trains);
    }
}
