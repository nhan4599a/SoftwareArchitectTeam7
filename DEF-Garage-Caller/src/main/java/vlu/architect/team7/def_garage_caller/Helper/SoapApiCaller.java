package vlu.architect.team7.def_garage_caller.Helper;

import vlu.architect.team7.def_garage_caller.DTO.BusTrainDTO;

import javax.xml.soap.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoapApiCaller {

    private static final String NAMESPACE_PREFIX = "tns";

    private final String soapEndpointUrl, soapAction, soapNamespace;

    public SoapApiCaller(String soapEndpointUrl, String soapAction, String soapNamespace) {
        this.soapEndpointUrl = soapEndpointUrl;
        this.soapAction = soapAction;
        this.soapNamespace = soapNamespace;
    }

    public List<BusTrainDTO> call(HashMap<String, String> parameters) throws SOAPException, IOException {
        SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage response = connection.call(createSoapRequest(parameters), soapEndpointUrl);
        return BusTrainDTO.create(response);
    }

    private SOAPMessage createSoapRequest(HashMap<String, String> parameters) throws SOAPException {
        SOAPMessage message = MessageFactory.newInstance().createMessage();
        addSoapEnvelope(message, parameters);
        message.saveChanges();
        return message;
    }

    private void addSoapEnvelope(SOAPMessage message, HashMap<String, String> parameters) throws SOAPException {
        SOAPPart part = message.getSOAPPart();
        SOAPEnvelope envelope = part.getEnvelope();
        envelope.addNamespaceDeclaration(NAMESPACE_PREFIX, soapNamespace);
        SOAPBody body = envelope.getBody();
        SOAPElement actionElement = body.addChildElement(soapAction, NAMESPACE_PREFIX);
        if (parameters != null)
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            actionElement.addChildElement(entry.getKey(), NAMESPACE_PREFIX).addTextNode(entry.getValue());
        }
    }
}
