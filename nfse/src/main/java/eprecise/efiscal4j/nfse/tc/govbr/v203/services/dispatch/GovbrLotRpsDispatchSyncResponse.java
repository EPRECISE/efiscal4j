
package eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch;

import java.util.Collection;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessageLot;
import eprecise.efiscal4j.nfse.tc.govbr.v203.compNfse.GovbrCompNFSe;
import eprecise.efiscal4j.nfse.tc.govbr.v203.compNfse.GovbrNFSeList;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAutorizedResponse;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;
import lombok.Builder;
import lombok.Getter;


@Builder
@XmlRootElement(name = "EnviarLoteRpsSincronoResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrLotRpsDispatchSyncResponse extends Receivable implements NFSeDispatchAutorizedResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/v203/nfse_v2_03.xsd";

    private @Getter final @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(min = 1, max = 15) String lotNumber;

    private @Getter final @XmlElement(name = "DataRecebimento") @NFSeDate String receiptDate;

    private @Getter final @XmlElement(name = "Protocolo") @Size(min = 1, max = 50) String protocolNumber;

    private @Getter final @NotNull @XmlElement(name = "ListaNfse") GovbrNFSeList nfseList;

    private @Getter final @NotNull @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(
            name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessage;

    private @Getter final @NotNull @XmlElementWrapper(name = "ListaMensagemRetornoLote") @XmlElement(
            name = "MensagemRetorno") Collection<CommonsNFSeReturnMessageLot> returnLotMessage;

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public Optional<CompNFSe> getCompNFSe() {
        return this.nfseList.getCompNFSeList().stream().findAny().map(GovbrCompNFSe.class::cast);
    }

}
