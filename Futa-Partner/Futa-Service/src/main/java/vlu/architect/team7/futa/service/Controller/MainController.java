package vlu.architect.team7.futa.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.futa.service.DAO.BusTrainDAO;
import vlu.architect.team7.futa.service.DTO.BusTrainDTO;
import vlu.architect.team7.futa.service.Model.BusTrain;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private BusTrainDAO busTrainDAO;

    @RequestMapping("/")
    public List<BusTrainDTO> findAll() throws InterruptedException {
        List<BusTrain> trains = new ArrayList<>();
        busTrainDAO.findAll().forEach(trains::add);
        Thread.sleep(5000);
        return BusTrainDTO.create(trains);
    }
}
