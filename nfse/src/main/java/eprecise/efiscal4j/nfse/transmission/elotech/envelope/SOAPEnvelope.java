
package eprecise.efiscal4j.nfse.transmission.elotech.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.transmissor.TransmissibleEnvelope;


@XmlRootElement(name = "Envelope", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI)
@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPEnvelope implements Serializable, Assignable, TransmissibleEnvelope, NFSeRequest {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Header", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI) SOAPHeader soapHeader;

    private @XmlElement(name = "Body", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI) SOAPBody soapBody;

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

    public SOAPHeader getSoapHeader() {
        return soapHeader;
    }

    public SOAPBody getSoapBody() {
        return soapBody;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper()).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, SOAPEnvelope.class).deserialize();
    }
}
