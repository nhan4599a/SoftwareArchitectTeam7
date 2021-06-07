package DTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.NullArgumentException;
import org.w3c.dom.NodeList;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static Reflection.ReflectionHelper.setProperty;
import static SOAP.SoapHelper.loopOverNodeList;

@Getter @NoArgsConstructor
public class BusTrainDTO {
    private String licensePlate, departureTime, startPlace, targetPlace, cost, seats;

    private BusTrainDTO(Node rootNode) throws SOAPException {
        if (rootNode == null) {
            throw new IllegalArgumentException(new NullArgumentException("message"));
        }
        loopOverNodeList(rootNode.getChildNodes(), item -> {
            String nodeName = item.getNodeName();
            String fieldName = nodeName.split(":")[1];
            try {
                setProperty(BusTrainDTO.class, this, fieldName, item.getTextContent());
            } catch (IllegalAccessException | NoSuchFieldException ignored) {}
        });
    }

    public static List<BusTrainDTO> createFromSoapMessage(SOAPMessage message) throws SOAPException {
        LinkedList<BusTrainDTO> response = new LinkedList<>();
        NodeList nodeList = message.getSOAPBody().getChildNodes().item(0).getChildNodes();
        loopOverNodeList(nodeList, item -> {
            try {
                response.add(new BusTrainDTO(item));
            } catch (SOAPException ignored) { }
        });
        return response;
    }

    public static List<BusTrainDTO> createFromJsonMessage(String json) {
        Gson gson = new GsonBuilder().create();
        BusTrainDTO[] temp = gson.fromJson(json, BusTrainDTO[].class);
        return new LinkedList<>(Arrays.asList(temp));
    }

    public static String toJSONMessage(List<BusTrainDTO> items) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(items);
    }

    public String getStartDate() {
        return this.getDepartureTime().split(" ")[0];
    }
}
