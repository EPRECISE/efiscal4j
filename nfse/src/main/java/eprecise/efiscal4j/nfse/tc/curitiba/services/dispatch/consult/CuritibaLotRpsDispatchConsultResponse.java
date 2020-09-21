
package eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult;

import java.util.Collection;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.curitiba.comNfse.CuritibaCompNFSe;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAutorizedResponse;
import eprecise.efiscal4j.signer.domain.SignatureType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@XmlRootElement(name = "ConsultarLoteRpsResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaLotRpsDispatchConsultResponse extends Receivable implements NFSeDispatchAutorizedResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/curitiba/nfse.xsd";

    private final @XmlElementWrapper(name = "ListaNfse") @XmlElement(name = "CompNfse") Collection<CuritibaCompNFSe> compNFSeList;

    public final @XmlElement(name = "Signature") SignatureType signature;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    @Override
    public Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    @Override
    public Optional<CompNFSe> getCompNFSe() {
        return compNFSeList.stream().findAny().map(CuritibaCompNFSe.class::cast);
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

}
