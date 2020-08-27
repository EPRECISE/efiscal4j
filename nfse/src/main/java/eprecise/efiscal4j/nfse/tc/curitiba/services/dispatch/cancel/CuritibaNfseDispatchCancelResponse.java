
package eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.cancel;

import java.util.Collection;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationCode;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.govbr.v100.cancel.GovbrNfseCancelResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchCancellationAutorizedResponse;
import eprecise.efiscal4j.signer.domain.SignatureType;


@XmlRootElement(name = "CancelarNfseResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaNfseDispatchCancelResponse extends Receivable implements NFSeDispatchCancellationAutorizedResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/servico_cancelar_nfse_resposta.xsd";

    public @XmlElement(name = "Cancelamento") GovbrNfseCancelResponse cancelResponse;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    private @XmlElement(name = "Signature") SignatureType signature;

    public static class Builder {

        public GovbrNfseCancelResponse cancelResponse;

        private Collection<CommonsNFSeReturnMessage> returnMessageList;

        /**
         * @param cancelResponse
         * @return
         */
        public Builder withCancelResponse(final GovbrNfseCancelResponse cancelResponse) {
            this.cancelResponse = cancelResponse;
            return this;
        }

        /**
         * @param returnMessageList
         * @return
         */
        public Builder withReturnMessageList(final Collection<CommonsNFSeReturnMessage> returnMessageList) {
            this.returnMessageList = returnMessageList;
            return this;
        }

        public CuritibaNfseDispatchCancelResponse build() throws Exception {
            final CuritibaNfseDispatchCancelResponse entity = new CuritibaNfseDispatchCancelResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CuritibaNfseDispatchCancelResponse() {
        cancelResponse = null;
        returnMessageList = null;
    }

    public CuritibaNfseDispatchCancelResponse(final Builder builder) {
        cancelResponse = builder.cancelResponse;
        returnMessageList = builder.returnMessageList;
    }

    public GovbrNfseCancelResponse getCancelResponse() {
        return cancelResponse;
    }

    @Override
    public Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public NFSeCancellationCode getCancellationCode() {
        return Optional.ofNullable(cancelResponse).map(cr -> cr.getConfirmation()).map(co -> co.getRequest()).map(re -> re.getInfo()).map(i -> i.getCancellationCode()).orElse(null);
    }

}
