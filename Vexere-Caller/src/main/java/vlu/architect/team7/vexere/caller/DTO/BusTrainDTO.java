package vlu.architect.team7.vexere.caller.DTO;

import org.apache.commons.lang.NullArgumentException;
import org.w3c.dom.NodeList;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class BusTrainDTO {
    private String licensePlate, departureTime, startPlace, targetPlace;

    private BusTrainDTO(Node rootNode) throws SOAPException {
        if (rootNode == null) {
            throw new IllegalArgumentException(new NullArgumentException("message"));
        }
        loopOverNodeList(rootNode.getChildNodes(), item -> {
            String nodeName = item.getNodeName();
            String fieldName = nodeName.split(":")[1];
            try {
                BusTrainDTO.class.getDeclaredField(fieldName).set(this, item.getTextContent());
            } catch (IllegalAccessException | NoSuchFieldException ignored) {}
        });
    }

    private static void loopOverNodeList(NodeList nodeList, Consumer<Node> consumer) {
        final int LENGTH = nodeList.getLength();
        for (int i = 0; i < LENGTH; i++)
            consumer.accept((Node) nodeList.item(i));
    }

    public static List<BusTrainDTO> create(SOAPMessage message) throws SOAPException {
        LinkedList<BusTrainDTO> response = new LinkedList<>();
        NodeList nodeList = message.getSOAPBody().getChildNodes().item(0).getChildNodes();
        loopOverNodeList(nodeList, item -> {
            try {
                response.add(new BusTrainDTO(item));
            } catch (SOAPException ignored) { }
        });
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
