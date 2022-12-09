
package eprecise.efiscal4j.nfse.tc.goiania.services.dispatch;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessageLot;
import eprecise.efiscal4j.nfse.tc.goiania.compNfse.GoianiaCompNFSe;
import eprecise.efiscal4j.nfse.tc.goiania.compNfse.GoianiaNFSeList;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAutorizedResponse;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlRootElement(name = "GerarNfseResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoianiaLotRpsDispatchSyncResponse extends Receivable implements NFSeDispatchAutorizedResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/goiania/nfse_gyn_v02.xsd";

    private @Getter @Builder.Default final @XmlAttribute(name = "xmlns") String xmlns = "http://nfse.goiania.go.gov.br/xsd/nfse_gyn_v02.xsd";

    private @Getter final @XmlElement(name = "NumeroLote") @NFSeNonNegativeInteger @Size(min = 1, max = 15) String lotNumber;

    private @Getter final @XmlElement(name = "DataRecebimento") @NFSeDate String receiptDate;

    private @Getter final @XmlElement(name = "Protocolo") @Size(min = 1, max = 50) String protocolNumber;

    private @Getter final @NotNull @XmlElement(name = "ListaNfse") GoianiaNFSeList nfseList;

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
        if(this.nfseList != null && nfseList.getCompNFSeList() != null) {
            return this.nfseList.getCompNFSeList().stream().findAny().map(GoianiaCompNFSe.class::cast);
        }
        return Optional.empty();
    }
    
    @Override
    public Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return returnMessage;
    }
    
    @Override
    public Collection<CommonsNFSeReturnMessageLot> getReturnMessageLotList() {
        return returnLotMessage;
    }

}
