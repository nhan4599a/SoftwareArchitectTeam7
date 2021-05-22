package vlu.architect.team7.search.DTO;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BusTrainDTO {
    private String licensePlate, departureTime, startPlace, targetPlace;

    public static List<BusTrainDTO> createList(String jsonStr) {
        BusTrainDTO[] temp = new Gson().fromJson(jsonStr, BusTrainDTO[].class);
        LinkedList<BusTrainDTO> list = new LinkedList<>();
        Collections.addAll(list, temp);
        return list;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public String getTargetPlace() {
        return targetPlace;
    }
}
