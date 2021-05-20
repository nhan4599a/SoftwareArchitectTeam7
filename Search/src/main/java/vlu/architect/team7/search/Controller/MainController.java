package vlu.architect.team7.search.Controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vlu.architect.team7.search.DTO.BusTrainDTO;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public List<BusTrainDTO> home() throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<BusTrainDTO[]> abcGarageResponse = callRest();
        CompletableFuture<BusTrainDTO[]> defGarageResponse = callSoap();
        CompletableFuture.allOf(abcGarageResponse, defGarageResponse).join();
        LinkedList<BusTrainDTO> response = new LinkedList<>();
        Collections.addAll(response, abcGarageResponse.get());
        Collections.addAll(response, defGarageResponse.get());
        return response;
    }

    @Async
    private CompletableFuture<BusTrainDTO[]> callRest() {
        LOGGER.info("call rest api");
        String abcGarageResponse = restTemplate.getForObject("http://abc-garage-caller", String.class);
        return CompletableFuture.completedFuture(BusTrainDTO.createList(abcGarageResponse));
    }

    @Async
    private CompletableFuture<BusTrainDTO[]> callSoap() {
        LOGGER.info("call soap api");
        String defGarageResponse = restTemplate.getForObject("http://def-garage-caller", String.class);
        return CompletableFuture.completedFuture(BusTrainDTO.createList(defGarageResponse));
    }
}
