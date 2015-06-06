
package eprecise.efiscal4j.transmissor;

import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import eprecise.efiscal4j.commons.domain.transmission.SOAPBody;
import eprecise.efiscal4j.commons.domain.transmission.Transmissible;


/**
 * 
 * @author Felipe Bueno
 *
 */
public class SOAPMessageCreator implements Serializable {

    private static final long serialVersionUID = 1L;

    private final SOAPBody soapBody;

    public static class Builder {

        private SOAPBody soapBody;

        /**
         * @see Transmissible
         * 
         * @param transmissible
         * @return
         * 
         */
        public Builder withSOAPBody(SOAPBody soapBody) {
            this.soapBody = soapBody;
            return this;
        }

        public SOAPMessageCreator build() throws Exception {

            final JAXBElement<Transmissible> transmissibleElement = new JAXBElement<Transmissible>(this.soapBody.getTransmissible().getQName(), Transmissible.class, this.soapBody.getTransmissible());

            final XMLOutputFactory xof = XMLOutputFactory.newFactory();

            final XMLStreamWriter xsw = xof.createXMLStreamWriter(System.out);

            xsw.writeStartDocument();

            xsw.writeStartElement("soap", "Envelope", "http://www.w3.org/2003/05/soap-envelope");
            xsw.writeNamespace("soap", "http://www.w3.org/2003/05/soap-envelope");
            xsw.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            xsw.writeNamespace("xsd", "http://www.w3.org/2001/XMLSchema");

            xsw.writeEmptyElement("soap", "Header", "http://www.w3.org/2003/05/soap-envelope");

            xsw.writeStartElement("soap", "Body", "http://www.w3.org/2003/05/soap-envelope");

            final JAXBContext jc = JAXBContext.newInstance(Transmissible.class);

            final Marshaller marshaller = jc.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            marshaller.marshal(transmissibleElement, xsw);

            xsw.writeEndDocument();

            xsw.close();

            return null;
        }
    }

    public SOAPMessageCreator() {
        this.soapBody = null;
    }

    public SOAPMessageCreator(Builder builder) {
        this.soapBody = builder.soapBody;
    }

    public SOAPBody getSoapBody() {
        return this.soapBody;
    }

}
