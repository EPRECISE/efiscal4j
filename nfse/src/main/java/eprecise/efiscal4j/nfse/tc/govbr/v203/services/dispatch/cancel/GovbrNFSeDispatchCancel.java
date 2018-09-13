
package eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrNFSeCancelRequest;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@XmlRootElement(name = "CancelarNfseEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeDispatchCancel implements TransmissibleBodyImpl, NFSeRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/v203/nfse_v2_03.xsd";

    private @Getter final @XmlAttribute(name = "xmlns") String xmlns = "http://www.abrasf.org.br/nfse.xsd";

    private @Getter final @NotNull @XmlAttribute(name = "Pedido") GovbrNFSeCancelRequest request;;

    private @Getter @Setter @Builder.Default @XmlTransient QName qName = new QName("EnviarLoteRpsSincronoEnvio");

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }
}
