
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlRootElement(name = "Envelope", namespace = "http://www.w3.org/2003/05/soap-envelope")
@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPEnvelopeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns:env") @NotNull final String xmlnsEnv = "http://www.w3.org/2003/05/soap-envelope";

    private @XmlElement(name = "Header", namespace = "http://www.w3.org/2003/05/soap-envelope") @NotNull final SOAPHeaderResponse soapHeaderResponse;

    private @XmlElement(name = "Body", namespace = "http://www.w3.org/2003/05/soap-envelope") @NotNull final SOAPBodyResponse soapBodyResponse;

    public static class Builder {

        private SOAPHeaderResponse soapHeaderResponse;

        private SOAPBodyResponse soapBodyResponse;

        /**
         * 
         * @param soapHeaderResponse
         * @return
         */
        public Builder withSoapHeaderResponse(SOAPHeaderResponse soapHeaderResponse) {
            this.soapHeaderResponse = soapHeaderResponse;
            return this;
        }

        /**
         * 
         * @param soapBodyResponse
         * @return
         */
        public Builder withSoapBodyResponse(SOAPBodyResponse soapBodyResponse) {
            this.soapBodyResponse = soapBodyResponse;
            return this;
        }

        public SOAPEnvelopeResponse build() {
            final SOAPEnvelopeResponse entity = new SOAPEnvelopeResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public SOAPEnvelopeResponse() {
        this.soapHeaderResponse = null;
        this.soapBodyResponse = null;
    }

    public SOAPEnvelopeResponse(Builder builder) {
        this.soapHeaderResponse = builder.soapHeaderResponse;
        this.soapBodyResponse = builder.soapBodyResponse;
    }

    public SOAPHeaderResponse getSoapHeaderResponse() {
        return this.soapHeaderResponse;
    }

    public SOAPBodyResponse getSoapBodyResponse() {
        return this.soapBodyResponse;
    }
}
