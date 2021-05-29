package vlu.architect.team7.cache.handler.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vlu.architect.team7.cache.handler.DTO.BusTrainDTO;
import vlu.architect.team7.cache.handler.Service.RedisService;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private RedisService service;

    @RequestMapping("/get")
    public List<BusTrainDTO> getAll() {
        try {
            return service.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@ModelAttribute List<BusTrainDTO> items) {
        service.addItemAll(items);
    }
}
