package vlu.architect.team7.search.Controller;

import DTO.BusTrainDTO;
import Network.RestApiCaller;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@RestController
public class MainController {

    private final RestApiCaller caller = RestApiCaller.getInstance();

    @RequestMapping("/")
    public List<BusTrainDTO> home()
            throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<List<BusTrainDTO>> restFuture = executorService.submit(this::callRest);
        Future<List<BusTrainDTO>> soapFuture = executorService.submit(this::callSoap);
        List<BusTrainDTO> futaResult = restFuture.get();
        List<BusTrainDTO> vexereResult = soapFuture.get();
        executorService.shutdown();

        // we need to create a new list to contains all data
        // we can't add vexere result to futa result directly
        // because that will change the futa result size
        // that will cause unexpected behavior on posting cached data in another thread
        LinkedList<BusTrainDTO> response = new LinkedList<>(futaResult);
        response.addAll(vexereResult);
        return response;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    private List<BusTrainDTO> callRest() throws Exception {
        try {
            String cachedData = getCachedData("futa");
            if (!cachedData.equals("[]") && !cachedData.isEmpty()) {
                return BusTrainDTO.createFromJsonMessage(cachedData);
            }
        } catch (Exception ignored) { }
        String futaData = caller.get("http://localhost:8001/futa/trains", null);
        new Thread(() -> {
            try {
                pushCachedDate("futa", futaData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return BusTrainDTO.createFromJsonMessage(futaData);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    private List<BusTrainDTO> callSoap() throws Exception {
        try {
            String cachedData = getCachedData("vexere");
            if (!cachedData.equals("[]") && !cachedData.isEmpty()) {
                return BusTrainDTO.createFromJsonMessage(cachedData);
            }
        } catch (Exception ignored) { }
        String vexereData = caller.get("http://localhost:8001/vexere/trains", null);
        new Thread(() -> {
            try {
                pushCachedDate("vexere", vexereData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return BusTrainDTO.createFromJsonMessage(vexereData);
    }

    private List<BusTrainDTO> fallback(Throwable throwable) {
        throwable.printStackTrace();
        return new LinkedList<>();
    }

    private String getCachedData(String key) throws Exception {
        return caller.get(String.format("http://localhost:8001/cache/get/%s", key), null);
    }

    private void pushCachedDate(String key, String data) throws Exception {
        caller.post(String.format("http://localhost:8001/cache/set?key=%s&exp=60000", key), data);
    }
}