
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
public class ElotechSOAPEnvelope implements Serializable, Assignable, TransmissibleEnvelope, NFSeRequest {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Header", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI) ElotechSOAPHeader soapHeader;

    private @XmlElement(name = "Body", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI) ElotechSOAPBody soapBody;

    public static class Builder {

        private ElotechSOAPHeader soapHeader;

        private ElotechSOAPBody soapBody;

        /**
         *
         * @param soapHeader
         * @return
         */
        public Builder withSoapHeader(final ElotechSOAPHeader soapHeader) {
            this.soapHeader = soapHeader;
            return this;
        }

        /**
         *
         * @param soapBody
         * @return
         */
        public Builder withSoapBody(final ElotechSOAPBody soapBody) {
            this.soapBody = soapBody;
            return this;
        }

        public ElotechSOAPEnvelope build() {
            final ElotechSOAPEnvelope entity = new ElotechSOAPEnvelope(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        public ElotechSOAPEnvelope build(final Signer signer) throws Exception {
            ElotechSOAPEnvelope entity = new ElotechSOAPEnvelope(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (ElotechSOAPEnvelope) signer.sign(entity);
            return entity;
        }

    }

    public ElotechSOAPEnvelope() {

    }

    public ElotechSOAPEnvelope(final Builder builder) {
        soapHeader = builder.soapHeader;
        soapBody = builder.soapBody;
    }

    public ElotechSOAPHeader getSoapHeader() {
        return soapHeader;
    }

    public ElotechSOAPBody getSoapBody() {
        return soapBody;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper()).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, ElotechSOAPEnvelope.class).deserialize();
    }
}
