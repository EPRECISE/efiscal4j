
package eprecise.efiscal4j.nfse.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.transmissor.TransmissibleEnvelope;


@XmlRootElement(name = "Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPEnvelope implements Serializable, TransmissibleEnvelope {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Header") SOAPHeader soapHeader;

    private @XmlElement(name = "Body") SOAPBody soapBody;

    public static class Builder {

        private SOAPHeader soapHeader;

        private SOAPBody soapBody;

        /**
         *
         * @param soapHeader
         * @return
         */
        public Builder withSoapHeader(final SOAPHeader soapHeader) {
            this.soapHeader = soapHeader;
            return this;
        }

        /**
         *
         * @param soapBody
         * @return
         */
        public Builder withSoapBody(final SOAPBody soapBody) {
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

    public SOAPEnvelope(final Builder builder) {
        soapHeader = builder.soapHeader;
        soapBody = builder.soapBody;
    }
}
