package vlu.architect.team7.search.DTO;

import com.google.gson.Gson;

public class BusTrainDTO {
    private String licensePlate, departureTime, startPlace, targetPlace;

    public static BusTrainDTO[] createList(String jsonStr) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, BusTrainDTO[].class);
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
