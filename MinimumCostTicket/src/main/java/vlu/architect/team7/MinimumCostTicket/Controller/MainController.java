package vlu.architect.team7.MinimumCostTicket.Controller;

import DTO.BusTrainDTO;
import DTO.PlaceItemDTO;
import Network.RestApiCaller;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@SuppressWarnings("all")
public class MainController {

    private final RestApiCaller caller = RestApiCaller.getInstance();

    private static Map<String, List<MinimumCostResponseItem>> cachedResponse;

    @RequestMapping("/")
    public Map<String, List<MinimumCostResponseItem>> home()
            throws IOException, ExecutionException, InterruptedException {
        if (cachedResponse != null)
            return cachedResponse;
        Map<String, List<MinimumCostResponseItem>> response = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<List<BusTrainDTO>> searchFuture = executorService.submit(new Callable<List<BusTrainDTO>>() {
            @Override
            public List<BusTrainDTO> call() throws Exception {
                return BusTrainDTO.createFromJsonMessage(
                        caller.get("http://localhost:8001/search", null));
            }
        });
        HashSet<PlaceItemDTO> mergedPlacesResult = new HashSet<>();
        String cachedPlaces = caller.get("http://localhost:8001/cache/get/places", null);
        if (!cachedPlaces.isEmpty())
            mergedPlacesResult = new HashSet<>(PlaceItemDTO.createFromJsonMessage(cachedPlaces));
        else {
            Future<List<PlaceItemDTO>> futaPlacesFuture = executorService.submit(new Callable<List<PlaceItemDTO>>() {
                @Override
                public List<PlaceItemDTO> call() throws Exception {
                    return PlaceItemDTO.createFromJsonMessage(
                            caller.get("http://localhost:8001/futa/places", null));
                }
            });
            Future<List<PlaceItemDTO>> vexerePlacesFuture = executorService.submit(new Callable<List<PlaceItemDTO>>() {
                @Override
                public List<PlaceItemDTO> call() throws Exception {
                    return PlaceItemDTO.createFromJsonMessage(
                            caller.get("http://localhost:8001/vexere/places", null));
                }
            });
            mergedPlacesResult = mergePlacesItems(futaPlacesFuture.get(), vexerePlacesFuture.get());
            updateCache("places", 3600000,
                    PlaceItemDTO.toJsonMessage(new ArrayList<>(mergedPlacesResult)));
        }
        List<BusTrainDTO> searchResult = searchFuture.get();
        Map<String, List<BusTrainDTO>> groupedTrains = searchResult.stream().collect(Collectors.groupingBy(
                BusTrainDTO::getStartDate
        ));
        for (Map.Entry<String, List<BusTrainDTO>> entry : groupedTrains.entrySet()) {
            List<BusTrainDTO> trainsPerDate = entry.getValue();
            List<MinimumCostResponseItem> responseItems = new LinkedList<>();
            for (PlaceItemDTO place : mergedPlacesResult) {
                Optional<BusTrainDTO> min = trainsPerDate
                        .stream()
                        .filter(item ->
                                item.getStartPlace().equals(place.getStartPlace())
                                    && item.getTargetPlace().equals(place.getTargetPlace())
                        )
                        .min((o1, o2) -> {
                            int firstCost = Integer.parseInt(o1.getCost().replaceAll(",", ""));
                            int secondCost = Integer.parseInt(o2.getCost().replaceAll(",", ""));
                            return firstCost - secondCost;
                });
                responseItems.add(new MinimumCostResponseItem(
                        place.getStartPlace(), place.getTargetPlace(),
                        min.isPresent() ? min.get() : null
                ));
            }
            response.put(entry.getKey(), responseItems);
        }
        executorService.shutdown();
        cachedResponse = response;
        return response;
    }

    private void updateCache(String key, int exp, String data) {
        new Thread(() -> {
            try {
                caller.post(String.format("http://localhost:8001/cache/set?key=%s&exp=%d", key, exp), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public HashSet<PlaceItemDTO> mergePlacesItems(List<PlaceItemDTO>... itemDTOS) {
        HashSet<PlaceItemDTO> resullt = new HashSet<>();
        for (List<PlaceItemDTO> dtos : itemDTOS)
            for (PlaceItemDTO dto : dtos)
                resullt.add(dto);
        return resullt;
    }

    @Getter @Setter
    class MinimumCostResponseItem {
        private String place;
        private BusTrainDTO value;

        public MinimumCostResponseItem(String startPlace, String targetPlace, BusTrainDTO value) {
            this.place = startPlace + " -> " + targetPlace;
            this.value = value;
        }
    }
}
