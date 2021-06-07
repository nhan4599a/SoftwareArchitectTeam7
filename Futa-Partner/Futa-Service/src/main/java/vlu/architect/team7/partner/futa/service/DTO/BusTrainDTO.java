package vlu.architect.team7.partner.futa.service.DTO;

import lombok.Getter;
import org.apache.commons.lang.NullArgumentException;
import vlu.architect.team7.partner.futa.service.Model.BusSeat;
import vlu.architect.team7.partner.futa.service.Model.BusTrain;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

@Getter
public class BusTrainDTO {
    private final String licensePlate, departureTime, startPlace, targetPlace, cost, seats;

    private BusTrainDTO(BusTrain entity) {
        if (entity == null) {
            throw new IllegalArgumentException(new NullArgumentException("entity"));
        }
        this.licensePlate = entity.getLicensePlate();
        this.departureTime = String.format("%s %s", entity.getStartDate(), entity.getStartTime());
        this.startPlace = entity.getStartPlace().getPlaceName();
        this.targetPlace = entity.getTargetPlace().getPlaceName();
        this.cost = NumberFormat.getNumberInstance().format(entity.getCost());
        StringBuilder builder = new StringBuilder();
        for (BusSeat seat : entity.getSeats())
            builder.append(seat.getSeatNumber()).append(",");
        if (builder.length() >= 1)
            builder.deleteCharAt(builder.length() - 1);
        this.seats = "[" + builder + "]";
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
}
