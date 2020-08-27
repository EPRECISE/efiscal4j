
package eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.cancel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.curitiba.cancel.CuritibaNfseCancelRequest;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;


@XmlRootElement(name = "CancelarNfseEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuritibaNfseDispatchCancel implements TransmissibleBodyImpl, NFSeRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/curitiba/nfse.xsd";

    private final @XmlElement(name = "Pedido") CuritibaNfseCancelRequest cancelRequest;

    private @XmlTransient QName qName = new QName("CancelarNfseEnvio");

    public static class Builder {

        private CuritibaNfseCancelRequest cancelRequest;

        /**
         * @param cancelRequest
         * @return
         */
        public Builder withCancelRequest(final CuritibaNfseCancelRequest cancelRequest) {
            this.cancelRequest = cancelRequest;
            return this;
        }

        public CuritibaNfseDispatchCancel build() {
            final CuritibaNfseDispatchCancel entity = new CuritibaNfseDispatchCancel(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CuritibaNfseDispatchCancel() {
        cancelRequest = null;
    }

    public CuritibaNfseDispatchCancel(final Builder builder) {
        cancelRequest = builder.cancelRequest;
    }

    public CuritibaNfseCancelRequest getCancelRequest() {
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
