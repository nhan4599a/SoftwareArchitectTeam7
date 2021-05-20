package vlu.architect.team7.abc_garage_service.DTO;

import org.apache.commons.lang.NullArgumentException;
import vlu.architect.team7.abc_garage_service.Model.BusTrain;

import java.util.LinkedList;
import java.util.List;

public class BusTrainDTO {
    private final String licensePlate, departureTime, startPlace, targetPlace;

    private BusTrainDTO(BusTrain entity) {
        if (entity == null) {
            throw new IllegalArgumentException(new NullArgumentException("entity"));
        }
        this.licensePlate = entity.getLicensePlate();
        this.departureTime = String.format("%s %s", entity.getStartDate(), entity.getStartTime());
        this.startPlace = entity.getStartPlace().getPlaceName();
        this.targetPlace = entity.getTargetPlace().getPlaceName();
    }

    public static BusTrainDTO create(BusTrain entity) {
        return new BusTrainDTO(entity);
    }

    public static List<BusTrainDTO> create(List<BusTrain> entityList) {
        LinkedList<BusTrainDTO> response = new LinkedList<>();
        for (BusTrain busTrain : entityList)
            response.add(new BusTrainDTO(busTrain));
        return response;
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
