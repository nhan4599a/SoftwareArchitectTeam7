package vlu.architect.team7.abc_garage.abc_garage_service.DTO;

import org.apache.commons.lang.NullArgumentException;
import vlu.architect.team7.abc_garage.abc_garage_service.Model.Place;

import java.util.LinkedList;
import java.util.List;

public class PlaceDTO {
    private String name;

    private PlaceDTO(Place entity) {
        if (entity == null)
            throw new IllegalArgumentException(new NullArgumentException("entity"));
        this.name = entity.getPlaceName();
    }

    public PlaceDTO create(Place entity) {
        return new PlaceDTO(entity);
    }

    public List<PlaceDTO> create(List<Place> entityList) {
        LinkedList<PlaceDTO> response = new LinkedList<>();
        for (Place busTrain : entityList)
            response.add(new PlaceDTO(busTrain));
        return response;
    }
}
