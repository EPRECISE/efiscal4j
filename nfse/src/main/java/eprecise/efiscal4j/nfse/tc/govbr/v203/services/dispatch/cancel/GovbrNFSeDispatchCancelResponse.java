
package eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel;

import java.util.Collection;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationCode;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrNFSeCancel;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrNFSeCancelConfirmation;
import eprecise.efiscal4j.nfse.tc.govbr.v203.cancel.GovbrNFSeCancelRequest;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchCancellationAutorizedResponse;
import eprecise.efiscal4j.signer.domain.SignatureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@XmlRootElement(name = "CancelarNfseResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeDispatchCancelResponse extends Receivable implements NFSeDispatchCancellationAutorizedResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/v203/nfse_v2_03.xsd";

    private @Getter @Builder.Default final @XmlAttribute(name = "xmlns") String xmlns = "http://www.abrasf.org.br/nfse.xsd";

    private @Getter final @XmlElement(name = "RetCancelamento") GovbrNFSeCancellationReturn cancellationReturn;

    private @Getter final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(
            name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessage;
    
    private @Getter @XmlElement(name = "Signature") SignatureType signature;

    private @Getter @Setter @Builder.Default @XmlTransient QName qName = new QName("CancelarNfseResposta");

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public NFSeCancellationCode getCancellationCode() {
        return Optional.ofNullable(cancellationReturn).map(GovbrNFSeCancellationReturn::getCancel).map(GovbrNFSeCancel::getConfirmation)
                .map(GovbrNFSeCancelConfirmation::getRequest).map(GovbrNFSeCancelRequest::getInfo)
                .map(GovbrNFSeCancelRequest.GovbrNFSeCancelRequestInfo::getCancellationCode).orElse(null);
    }

}
