package vlu.architect.team7.abcgarage.ABCGarageService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.abcgarage.ABCGarageService.DAO.BusTrainDAO;
import vlu.architect.team7.abcgarage.ABCGarageService.Model.BusTrain;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private BusTrainDAO busTrainDAO;

    @RequestMapping("/")
    public List<BusTrain> main() {
        List<BusTrain> trains = new ArrayList<>();
        busTrainDAO.findAll().forEach(trains::add);
        return trains;
    }
}
