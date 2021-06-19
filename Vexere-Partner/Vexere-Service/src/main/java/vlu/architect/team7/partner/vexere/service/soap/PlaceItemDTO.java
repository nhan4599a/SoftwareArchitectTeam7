package vlu.architect.team7.partner.vexere.service.soap;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlaceItemDTO", propOrder = {
    "startPlace",
    "targetPlace"
})
@Getter
public class PlaceItemDTO {

    @XmlElement(required = true)
    protected String startPlace;
    @XmlElement(required = true)
    protected String targetPlace;

    public static PlaceItemDTO create(String startPlace, String targetPlace) {
        PlaceItemDTO itemDTO = new PlaceItemDTO();
        itemDTO.startPlace = startPlace;
        itemDTO.targetPlace = targetPlace;
        return itemDTO;
    }
}
