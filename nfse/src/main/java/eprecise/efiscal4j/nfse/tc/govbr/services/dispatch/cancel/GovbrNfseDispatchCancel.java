
package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.cancel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.govbr.cancel.GovbrNfseCancelRequest;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;


@XmlRootElement(name = "CancelarNfseEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNfseDispatchCancel implements TransmissibleBodyImpl, NFSeRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/govbr/servico_cancelar_nfse_envio.xsd";

    private final @XmlElement(name = "Pedido") GovbrNfseCancelRequest cancelRequest;

    private @XmlTransient QName qName = new QName("CancelarNfseEnvio");

    public static class Builder {

        private GovbrNfseCancelRequest cancelRequest;

        /**
         * @param cancelRequest
         * @return
         */
        public Builder withCancelRequest(final GovbrNfseCancelRequest cancelRequest) {
            this.cancelRequest = cancelRequest;
            return this;
        }

        public GovbrNfseDispatchCancel build() {
            final GovbrNfseDispatchCancel entity = new GovbrNfseDispatchCancel(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrNfseDispatchCancel() {
        cancelRequest = null;
    }

    public GovbrNfseDispatchCancel(final Builder builder) {
        cancelRequest = builder.cancelRequest;
    }

    public GovbrNfseCancelRequest getCancelRequest() {
        return cancelRequest;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return qName;
    }

}
