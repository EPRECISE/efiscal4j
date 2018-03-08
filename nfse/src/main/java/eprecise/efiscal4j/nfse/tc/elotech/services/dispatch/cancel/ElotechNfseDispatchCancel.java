
package eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.cancel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.elotech.cancel.ElotechNfseCancelRequest;
import eprecise.efiscal4j.nfse.tc.elotech.services.ElotechApplicant;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;


@XmlRootElement(name = "CancelarNfseEnvio")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechNfseDispatchCancel implements TransmissibleBodyImpl, NFSeRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfse/xsd/elotech/nfse_v2_03.xsd";

    private final @XmlAttribute(name = "xmlns") String xmlns = "http://shad.elotech.com.br/schemas/iss/nfse_v2_03.xsd";

    private final @NotNull @XmlElement(name = "IdentificacaoRequerente") ElotechApplicant applicant;

    private final @XmlElement(name = "Pedido") ElotechNfseCancelRequest cancelRequest;

    private @XmlTransient QName qName = new QName("CancelarNfseEnvio");

    public static class Builder {

        private ElotechApplicant applicant;

        private ElotechNfseCancelRequest cancelRequest;

        /**
         * @param applicant
         * @return
         */
        public Builder withApplicant(final ElotechApplicant applicant) {
            this.applicant = applicant;
            return this;
        }

        /**
         * @param cancelRequest
         * @return
         */
        public Builder withCancelRequest(final ElotechNfseCancelRequest cancelRequest) {
            this.cancelRequest = cancelRequest;
            return this;
        }

        public ElotechNfseDispatchCancel build() {
            final ElotechNfseDispatchCancel entity = new ElotechNfseDispatchCancel(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechNfseDispatchCancel() {
        applicant = null;
        cancelRequest = null;
    }

    public ElotechNfseDispatchCancel(final Builder builder) {
        applicant = builder.applicant;
        cancelRequest = builder.cancelRequest;
    }

    public ElotechApplicant getApplicant() {
        return applicant;
    }

    public ElotechNfseCancelRequest getCancelRequest() {
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
