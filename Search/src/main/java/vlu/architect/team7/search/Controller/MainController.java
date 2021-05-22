package vlu.architect.team7.search.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vlu.architect.team7.search.DTO.BusTrainDTO;
import vlu.architect.team7.search.Helper.CircuitBreakerHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SuppressWarnings("all")
@RestController
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public List<BusTrainDTO> home()
            throws InterruptedException, ExecutionException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final CompletableFuture<List<BusTrainDTO>> defaultValue = CompletableFuture.completedFuture(new LinkedList<>());
        CompletableFuture<List<BusTrainDTO>> restResultFuture =
                (CompletableFuture<List<BusTrainDTO>>) CircuitBreakerHelper.execute(
                        MainController.class, "callRest", this, defaultValue, null);
        CompletableFuture<List<BusTrainDTO>> soapResultFuture =
                (CompletableFuture<List<BusTrainDTO>>) CircuitBreakerHelper.execute(
                        MainController.class, "callSoap", this, defaultValue, null);
        CompletableFuture.allOf(restResultFuture, soapResultFuture).join();
        LinkedList<BusTrainDTO> result = new LinkedList<>(restResultFuture.get());
        result.addAll(soapResultFuture.get());
        return result;
    }

    @Async
    private CompletableFuture<List<BusTrainDTO>> callRest() {
        String abcGarageResponse = restTemplate.getForObject("http://abc-garage-caller", String.class);
        return CompletableFuture.completedFuture(BusTrainDTO.createList(abcGarageResponse));
    }

    @Async
    private CompletableFuture<List<BusTrainDTO>> callSoap() {
        String defGarageResponse = restTemplate.getForObject("http://def-garage-caller", String.class);
        return CompletableFuture.completedFuture(BusTrainDTO.createList(defGarageResponse));
    }
}