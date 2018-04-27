
package eprecise.efiscal4j.nfe.v310.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.transmissor.TransmissibleEnvelope;


@XmlRootElement(name = "soap:Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPEnvelope implements Serializable, TransmissibleEnvelope {

    private static final long serialVersionUID = 1L;

    private final @XmlAttribute(name = "xmlns:soap") String xmlnsSoap = "http://www.w3.org/2003/05/soap-envelope";

    private final @XmlAttribute(name = "xmlns:xsd") String xmlnsXsd = "http://www.w3.org/2001/XMLSchema";

    private final @XmlAttribute(name = "xmlns:xsi") String xmlnsXsi = "http://www.w3.org/2001/XMLSchema-instance";

    private @XmlElement(name = "soap:Header") SOAPHeader soapHeader;

    private @XmlElement(name = "soap:Body") SOAPBody soapBody;

    public static class Builder {

        private SOAPHeader soapHeader;

        private SOAPBody soapBody;

        /**
         * 
         * @param soapHeader
         * @return
         */
        public Builder withSoapHeader(SOAPHeader soapHeader) {
            this.soapHeader = soapHeader;
            return this;
        }

        /**
         * 
         * @param soapBody
         * @return
         */
        public Builder withSoapBody(SOAPBody soapBody) {
            this.soapBody = soapBody;
            return this;
        }

        public SOAPEnvelope build() {
            final SOAPEnvelope entity = new SOAPEnvelope(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public SOAPEnvelope() {

    }

    public SOAPEnvelope(Builder builder) {
        this.soapHeader = builder.soapHeader;
        this.soapBody = builder.soapBody;
    }
}
