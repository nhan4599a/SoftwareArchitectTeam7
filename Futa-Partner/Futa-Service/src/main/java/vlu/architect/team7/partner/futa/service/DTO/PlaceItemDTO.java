package vlu.architect.team7.partner.futa.service.DTO;

import lombok.Getter;

@Getter
public class PlaceItemDTO {
    private final String startPlace, targetPlace;

    public PlaceItemDTO(String startPlace, String targetPlace) {
        this.startPlace = startPlace;
        this.targetPlace = targetPlace;
    }
}
