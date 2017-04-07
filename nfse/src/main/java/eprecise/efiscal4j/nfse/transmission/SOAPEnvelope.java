
package eprecise.efiscal4j.nfse.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.signer.NFSeNamespaceMapper;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.transmissor.TransmissibleEnvelope;


@XmlRootElement(name = "SOAP-ENV:Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPEnvelope implements Serializable, Assignable, TransmissibleEnvelope {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns = "http://schemas.xmlsoap.org/soap/envelope/";

    private @XmlElement(name = "SOAP-ENV:Header") SOAPHeader soapHeader;

    private @XmlElement(name = "SOAP-ENV:Body") SOAPBody soapBody;

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

        public SOAPEnvelope build(final Signer signer) throws Exception {
            SOAPEnvelope entity = new SOAPEnvelope(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (SOAPEnvelope) signer.sign(entity);
            return entity;
        }

    }

    public SOAPEnvelope() {

    }

    public SOAPEnvelope(final Builder builder) {
        soapHeader = builder.soapHeader;
        soapBody = builder.soapBody;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).withNamespacePrefixMapper(new NFSeNamespaceMapper()).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, SOAPEnvelope.class).deserialize();
    }
}
