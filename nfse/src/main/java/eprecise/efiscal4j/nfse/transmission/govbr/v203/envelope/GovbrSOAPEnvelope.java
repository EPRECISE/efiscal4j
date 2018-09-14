
package eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.transmissor.TransmissibleEnvelope;
import lombok.Builder;
import lombok.Getter;


@Builder
@XmlRootElement(name = "Envelope", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI)
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrSOAPEnvelope implements Serializable, Assignable, TransmissibleEnvelope, NFSeRequest {

    private static final long serialVersionUID = 1L;

    private @Getter final @XmlElement(name = "Header", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI) GovbrSOAPHeader soapHeader;

    private @Getter final @XmlElement(name = "Body", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI) GovbrSOAPBody soapBody;

    public GovbrSOAPEnvelope sign(final Signer signer) throws Exception {
        return (GovbrSOAPEnvelope) signer.sign(this);
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper()).serialize();
    }

    @Override
    public Assignable getAsEntity(String xml) {
        return new FiscalDocumentDeserializer<>(xml, GovbrSOAPEnvelope.class).deserialize();
    }

}
