package SOAP;

import org.w3c.dom.NodeList;

import javax.xml.soap.Node;
import java.util.function.Consumer;

public final class SoapHelper {
    public static void loopOverNodeList(NodeList nodeList, Consumer<Node> consumer) {
        final int LENGTH = nodeList.getLength();
        for (int i = 0; i < LENGTH; i++)
            consumer.accept((Node) nodeList.item(i));
    }
}
