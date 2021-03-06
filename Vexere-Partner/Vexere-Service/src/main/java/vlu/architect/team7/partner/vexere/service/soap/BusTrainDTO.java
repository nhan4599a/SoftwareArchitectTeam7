//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.06 at 07:33:45 PM ICT 
//


package vlu.architect.team7.partner.vexere.service.soap;

import lombok.Getter;
import vlu.architect.team7.partner.vexere.service.Model.BusSeat;
import vlu.architect.team7.partner.vexere.service.Model.BusTrain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.text.NumberFormat;


/**
 * <p>Java class for BusTrainDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusTrainDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="licensePlate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departureTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startPlace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetPlace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seats" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusTrainDTO", propOrder = {
    "licensePlate",
    "departureTime",
    "startPlace",
    "targetPlace",
    "cost",
    "seats"
})
@Getter
public class BusTrainDTO {

    @XmlElement(required = true)
    protected String licensePlate;
    @XmlElement(required = true)
    protected String departureTime;
    @XmlElement(required = true)
    protected String startPlace;
    @XmlElement(required = true)
    protected String targetPlace;
    @XmlElement(required = true)
    protected String cost;
    @XmlElement(required = true)
    protected String seats;

    public static BusTrainDTO from(BusTrain entity) {
        BusTrainDTO dto = new BusTrainDTO();
        dto.licensePlate = entity.getLicensePlate();
        dto.departureTime = String.format("%s %s", entity.getStartDate(), entity.getStartTime());
        dto.cost = NumberFormat.getNumberInstance().format(entity.getCost());
        dto.startPlace = entity.getStartPlace().getPlaceName();
        dto.targetPlace = entity.getTargetPlace().getPlaceName();
        dto.cost = NumberFormat.getNumberInstance().format(entity.getCost());
        StringBuilder builder = new StringBuilder();
        for (BusSeat seat : entity.getSeats())
            builder.append(seat.getSeatNumber()).append(",");
        if (builder.length() > 0)
            builder.deleteCharAt(builder.length() - 1);
        dto.seats = "[" + builder + "]";
        return dto;
    }
}
