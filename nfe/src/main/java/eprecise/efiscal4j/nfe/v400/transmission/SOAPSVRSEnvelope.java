
package eprecise.efiscal4j.nfe.v400.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.transmissor.TransmissibleEnvelope;
import lombok.NoArgsConstructor;


@NoArgsConstructor(force = true)
@XmlRootElement(name = "soapenv:Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPSVRSEnvelope implements Serializable, TransmissibleEnvelope {

    private static final long serialVersionUID = 1L;

    private final @XmlAttribute(name = "xmlns:soapenv") String xmlnsSoapenv = "http://schemas.xmlsoap.org/soap/envelope/";

    private final @XmlAttribute(name = "xmlns:nfe") String xmlnsNfe;

    private @XmlElement(name = "soapenv:Header") SOAPHeader soapHeader;

    private @XmlElement(name = "soapenv:Body") SOAPSVRSBody soapBody;

    public static class Builder {

        private SOAPHeader soapHeader;

        private SOAPSVRSBody soapBody;

        private String xmlnsNfe;

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
        public Builder withSoapBody(SOAPSVRSBody soapBody) {
            this.soapBody = soapBody;
            return this;
        }

        public Builder withXmlnsNfe(String xmlnsNfe) {
            this.xmlnsNfe = xmlnsNfe;
            return this;
        }

        public SOAPSVRSEnvelope build() {
            final SOAPSVRSEnvelope entity = new SOAPSVRSEnvelope(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public SOAPSVRSEnvelope(Builder builder) {
        this.soapHeader = builder.soapHeader;
        this.soapBody = builder.soapBody;
        this.xmlnsNfe = builder.xmlnsNfe;
    }
}