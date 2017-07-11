
package eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.cancel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

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
import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationCode;
import eprecise.efiscal4j.nfse.tc.commons.messages.CommonsNFSeReturnMessage;
import eprecise.efiscal4j.nfse.tc.elotech.cancel.ElotechNfseCancelResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchCancellationAutorizedResponse;


@XmlRootElement(name = "CancelarNfseResposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechNfseDispatchCancelResponse extends Receivable implements NFSeDispatchCancellationAutorizedResponse {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/elotech/nfse_v1_2.xsd";

    private final @XmlElementWrapper(name = "ListaMensagemRetorno") @XmlElement(name = "MensagemRetorno") Collection<CommonsNFSeReturnMessage> returnMessageList;

    private final @XmlElement(name = "tcRetCancelamento") CancellationReturn cancellationReturn;

    private @XmlTransient QName qName = new QName("CancelarNfseResposta");

    public static class Builder {

        public CancellationReturn cancellationReturn;

        private Collection<CommonsNFSeReturnMessage> returnMessageList;

        /**
         * @param cancellationReturn
         * @return
         */
        public Builder withCancellationReturn(final CancellationReturn cancellationReturn) {
            this.cancellationReturn = cancellationReturn;
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
        cancellationReturn = null;
        returnMessageList = null;
    }

    public ElotechNfseDispatchCancelResponse(final Builder builder) {
        cancellationReturn = builder.cancellationReturn;
        returnMessageList = builder.returnMessageList;
    }

    public QName getqName() {
        return qName;
    }

    public void setqName(final QName qName) {
        this.qName = qName;
    }

    public CancellationReturn getCancellationReturn() {
        return cancellationReturn;
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CancellationReturn implements Serializable {

        private static final long serialVersionUID = 1L;

        private final @XmlElement(name = "NfseCancelamento") ElotechNfseCancelResponse cancelResponse;

        public static class Builder {

            private ElotechNfseCancelResponse cancelResponse;

            public Builder withCancelResponse(final ElotechNfseCancelResponse cancelResponse) {
                this.cancelResponse = cancelResponse;
                return this;
            }

            public CancellationReturn build() throws Exception {
                final CancellationReturn entity = new CancellationReturn(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public CancellationReturn() {
            cancelResponse = null;
        }

        public CancellationReturn(final Builder builder) {
            cancelResponse = builder.cancelResponse;
        }

        public ElotechNfseCancelResponse getCancelResponse() {
            return cancelResponse;
        }

    }

    @Override
    public NFSeCancellationCode getCancellationCode() {
        return Optional.ofNullable(cancellationReturn).map(cr -> cr.getCancelResponse()).map(cr -> cr.getConfirmation()).map(c -> c.getRequest()).map(r -> r.getInfo())
                .map(i -> i.getCancellationCode()).orElse(null);
    }

}
