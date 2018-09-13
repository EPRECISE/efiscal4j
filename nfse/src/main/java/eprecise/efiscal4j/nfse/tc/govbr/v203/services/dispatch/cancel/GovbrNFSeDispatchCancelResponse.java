
package eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@XmlRootElement(name = "CancelarNfseResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeDispatchCancelResponse implements TransmissibleBodyImpl, NFSeRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/v203/nfse_v2_03.xsd";

    private @Getter final @XmlAttribute(name = "xmlns") String xmlns = "http://www.abrasf.org.br/nfse.xsd";

    private @Getter final @NotNull @XmlAttribute(name = "RetCancelamento") GovbrNFSeRetCancel retCancel;

    private @Getter final @NotNull @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(
            name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessage;

    private @Getter @Setter @Builder.Default @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoEnvio");

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

}
