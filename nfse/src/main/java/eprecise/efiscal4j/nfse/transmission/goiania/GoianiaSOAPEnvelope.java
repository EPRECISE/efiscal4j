
package eprecise.efiscal4j.nfse.transmission.goiania;

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
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@XmlRootElement(name = "Envelope", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI)
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaSOAPEnvelope implements Serializable, Assignable, TransmissibleEnvelope, NFSeRequest {

    private static final long serialVersionUID = 1L;

    private @Getter final @XmlElement(name = "Body", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI) GoianiaSOAPBody soapBody;

    public GoianiaSOAPEnvelope sign(final Signer signer) throws Exception {
        return (GoianiaSOAPEnvelope) signer.sign(this);
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper()).serialize();
    }

    @Override
    public Assignable getAsEntity(String xml) {
        return new FiscalDocumentDeserializer<>(xml, GoianiaSOAPEnvelope.class).deserialize();
    }

}
