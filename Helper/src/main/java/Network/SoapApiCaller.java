package Network;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.Map;

public class SoapApiCaller {
    private static final String NAMESPACE_PREFIX = "tns";

    private static SoapApiCaller instance;

    private final String soapEndpointUrl, soapNamespace;

    private SoapApiCaller(String soapEndpointUrl, String soapNamespace) {
        this.soapEndpointUrl = soapEndpointUrl;
        this.soapNamespace = soapNamespace;
    }

    public static SoapApiCaller getInstance(String soapEndpointUrl, String soapNamespace) {
        if (instance == null)
            instance = new SoapApiCaller(soapEndpointUrl, soapNamespace);
        return instance;
    }

    public SOAPMessage call(String soapAction, HashMap<String, String> parameters) throws SOAPException {
        SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
        try {
            return connection.call(createSoapRequest(soapAction, parameters), soapEndpointUrl);
        } catch (SOAPException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SOAPMessage createSoapRequest(String soapAction, HashMap<String, String> parameters) throws SOAPException {
        SOAPMessage message = MessageFactory.newInstance().createMessage();
        addSoapEnvelope(message, soapAction, parameters);
        message.saveChanges();
        return message;
    }

    private void addSoapEnvelope(SOAPMessage message, String soapAction, HashMap<String, String> parameters) throws SOAPException {
        SOAPPart part = message.getSOAPPart();
        SOAPEnvelope envelope = part.getEnvelope();
        envelope.addNamespaceDeclaration(NAMESPACE_PREFIX, soapNamespace);
        SOAPBody body = envelope.getBody();
        SOAPElement actionElement = body.addChildElement(soapAction, NAMESPACE_PREFIX);
        if (parameters != null)
            for (Map.Entry<String, String> entry : parameters.entrySet())
                actionElement.addChildElement(entry.getKey(), NAMESPACE_PREFIX).addTextNode(entry.getValue());
    }
}
