
package eprecise.efiscal4j.transmissor.utils;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;


public class SOAPNamespaceHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(final SOAPMessageContext context) {
        final Boolean isSoapRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        final SOAPMessage soapMsg = context.getMessage();
        try {
            if (isSoapRequest) {
                soapMsg.getSOAPPart().getEnvelope().setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                soapMsg.getSOAPPart().getEnvelope().setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
                soapMsg.getSOAPPart().getEnvelope().setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");

                soapMsg.getSOAPPart().getEnvelope().removeAttributeNS("http://schemas.xmlsoap.org/soap/envelope/", "env");
                soapMsg.getSOAPPart().getEnvelope().removeAttribute("xmlns:env");
                soapMsg.getSOAPPart().getEnvelope().removeAttributeNS("http://schemas.xmlsoap.org/soap/envelope/", "S");
                soapMsg.getSOAPPart().getEnvelope().removeAttribute("xmlns:S");

                soapMsg.getSOAPPart().getEnvelope().setPrefix("soap");
                soapMsg.getSOAPBody().setPrefix("soap");

                final SOAPHeader soapHeader = soapMsg.getSOAPHeader();
                soapHeader.setPrefix("soap");

                final Iterator<SOAPElement> childElements = soapHeader.getChildElements();
                while (childElements.hasNext()) {
                    final SOAPElement soapElement = childElements.next();
                    soapElement.setPrefix(null);
                    soapElement.removeAttributeNS("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3", "ns2");
                    soapElement.removeAttribute("xmlns:ns2");

                    final Iterator<SOAPElement> internalChildElements = soapElement.getChildElements();
                    while (internalChildElements.hasNext()) {
                        final SOAPElement internalSoapElement = internalChildElements.next();
                        internalSoapElement.removeNamespaceDeclaration("");
                    }

                    break;
                }

                final SOAPBody soapBody = soapMsg.getSOAPBody();
                soapBody.setPrefix("soap");

                soapMsg.saveChanges();

            }
        } catch (final SOAPException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}
