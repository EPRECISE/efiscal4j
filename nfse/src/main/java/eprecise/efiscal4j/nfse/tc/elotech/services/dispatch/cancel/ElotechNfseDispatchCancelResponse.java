
package eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.cancel;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.govbr.cancel.GovbrNfseCancelResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchCancellationAutorizedResponse;


@XmlRootElement(name = "ConsultarLoteRpsResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechNfseDispatchCancelResponse extends Receivable implements NFSeDispatchCancellationAutorizedResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/servico_cancelar_nfse_resposta.xsd";

    public @XmlElement(name = "Cancelamento") GovbrNfseCancelResponse cancelResponse;

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    private @XmlTransient QName qName = new QName("ConsultarLoteRpsResposta");

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

        public ElotechNfseDispatchCancelResponse build() throws Exception {
            final ElotechNfseDispatchCancelResponse entity = new ElotechNfseDispatchCancelResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechNfseDispatchCancelResponse() {
        cancelResponse = null;
        returnMessageList = null;
    }

    public ElotechNfseDispatchCancelResponse(final Builder builder) {
        cancelResponse = builder.cancelResponse;
        returnMessageList = builder.returnMessageList;
    }

    public QName getqName() {
        return qName;
    }

    public void setqName(final QName qName) {
        this.qName = qName;
    }

    public GovbrNfseCancelResponse getCancelResponse() {
        return cancelResponse;
    }

    @Override
    public Collection<CommonsNFSeReturnMessage> getReturnMessageList() {
        return returnMessageList;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;

    }

    @Override
    public QName getQName() {
        return qName;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

}
