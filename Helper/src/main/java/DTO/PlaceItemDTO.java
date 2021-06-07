package DTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.apache.commons.lang.NullArgumentException;
import org.w3c.dom.NodeList;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static Reflection.ReflectionHelper.setProperty;
import static SOAP.SoapHelper.loopOverNodeList;

@Getter
public class PlaceItemDTO {
    private String startPlace, targetPlace;

    private PlaceItemDTO(Node rootNode) throws SOAPException {
        if (rootNode == null) {
            throw new IllegalArgumentException(new NullArgumentException("message"));
        }
        loopOverNodeList(rootNode.getChildNodes(), item -> {
            String nodeName = item.getNodeName();
            String fieldName = nodeName.split(":")[1];
            try {
                setProperty(PlaceItemDTO.class, this, fieldName, item.getTextContent());
            } catch (IllegalAccessException | NoSuchFieldException ignored) {}
        });
    }

    public static List<PlaceItemDTO> createFromJsonMessage(String json) {
        Gson gson = new GsonBuilder().create();
        PlaceItemDTO[] items = gson.fromJson(json, PlaceItemDTO[].class);
        if (items == null)
            return new LinkedList<>();
        return new LinkedList<>(Arrays.asList(items));
    }

    public static List<PlaceItemDTO> createFromSoapMessage(SOAPMessage message) throws SOAPException {
        LinkedList<PlaceItemDTO> response = new LinkedList<>();
        NodeList nodeList = message.getSOAPBody().getChildNodes().item(0).getChildNodes();
        loopOverNodeList(nodeList, item -> {
            try {
                response.add(new PlaceItemDTO(item));
            } catch (SOAPException ignored) { }
        });
        return response;
    }

    public static String toJsonMessage(List<PlaceItemDTO> items) {
        return new GsonBuilder().create().toJson(items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceItemDTO dto = (PlaceItemDTO) o;
        return Objects.equals(startPlace, dto.startPlace) && Objects.equals(targetPlace, dto.targetPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPlace, targetPlace);
    }
}
