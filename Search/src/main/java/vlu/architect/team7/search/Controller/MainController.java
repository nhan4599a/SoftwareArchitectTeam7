package vlu.architect.team7.search.Controller;

import CircuitBreaker.CircuitBreakerHelper;
import DTO.BusTrainDTO;
import Network.RestApiCaller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@SuppressWarnings("all")
@RestController
public class MainController {

    private final RestApiCaller caller = RestApiCaller.getInstance();

    @RequestMapping("/")
    public List<BusTrainDTO> home()
            throws InterruptedException, ExecutionException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        final List<BusTrainDTO> DEFAULT_VALUE = new LinkedList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<List<BusTrainDTO>> restFuture = executorService.submit(new Callable<List<BusTrainDTO>>() {
            @Override
            public List<BusTrainDTO> call() throws Exception {
                return (List<BusTrainDTO>) CircuitBreakerHelper.execute(
                        MainController.class, "callRest",
                        MainController.this, DEFAULT_VALUE, null);
            }
        });
        Future<List<BusTrainDTO>> soapFuture = executorService.submit(new Callable<List<BusTrainDTO>>() {
            @Override
            public List<BusTrainDTO> call() throws Exception {
                return (List<BusTrainDTO>) CircuitBreakerHelper.execute(
                        MainController.class, "callSoap",
                        MainController.this, DEFAULT_VALUE, null);
            }
        });
        List<BusTrainDTO> futaResult = restFuture.get();
        List<BusTrainDTO> vexereResult = soapFuture.get();
        new Thread(() -> {
            try {
                caller.post("http://localhost:8001/cache/set?key=futa&exp=600000",
                        BusTrainDTO.toJSONMessage(futaResult));
                caller.post("http://localhost:8001/cache/set?key=vexere&exp=600000",
                        BusTrainDTO.toJSONMessage(vexereResult));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        executorService.shutdown();

        // we need to create a new list to contains all data
        // we can't add vexere result to futa result directly
        // because, that will be change the futa result size
        // that will cause unexpected behavior on post data cache in another thread
        LinkedList<BusTrainDTO> response = new LinkedList<>(futaResult);
        response.addAll(vexereResult);
        return response;
    }

    private List<BusTrainDTO> callRest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<List<BusTrainDTO>> cacheFuture = executorService.submit(new Callable<List<BusTrainDTO>>() {
            @Override
            public List<BusTrainDTO> call() throws Exception {
                try {
                    return BusTrainDTO.createFromJsonMessage(getCachedData("futa"));
                } catch (Exception e) {
                    return new LinkedList<>();
                }
            }
        });
        Future<List<BusTrainDTO>> restFuture = executorService.submit(new Callable<List<BusTrainDTO>>() {
            @Override
            public List<BusTrainDTO> call() throws Exception {
                String futa = caller.get("http://localhost:8001/futa/trains", null);
                return BusTrainDTO.createFromJsonMessage(futa);
            }
        });
        List<BusTrainDTO> cachedData = cacheFuture.get();
        if (!cachedData.isEmpty())
            return cachedData;
        List<BusTrainDTO> restData = restFuture.get();
        executorService.shutdown();
        return restData;
    }

    private List<BusTrainDTO> callSoap() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<List<BusTrainDTO>> cacheFuture = executorService.submit(new Callable<List<BusTrainDTO>>() {
            @Override
            public List<BusTrainDTO> call() throws Exception {
                try {
                    return BusTrainDTO.createFromJsonMessage(getCachedData("vexere"));
                } catch (Exception e) {
                    return new LinkedList<>();
                }
            }
        });
        Future<List<BusTrainDTO>> vexereFuture = executorService.submit(new Callable<List<BusTrainDTO>>() {
            @Override
            public List<BusTrainDTO> call() throws Exception {
                String vexereResponse = caller.get("http://localhost:8001/vexere/trains", null);
                return BusTrainDTO.createFromJsonMessage(vexereResponse);
            }
        });
        List<BusTrainDTO> cachedData = cacheFuture.get();
        if (!cachedData.isEmpty())
            return cachedData;
        List<BusTrainDTO> vexereData = vexereFuture.get();
        executorService.shutdown();
        return vexereData;
    }

    private String getCachedData(String key) throws Exception {
        return caller.get("http://localhost:8001/cache/get/" + key, null);
    }
}