
package eprecise.efiscal4j.nfse.tc.curitiba.cancel;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.curitiba.CuritibaNFSeIdentifier;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@XmlRootElement(name = "CancelarNfseEnvio", namespace = "http://tempuri.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "info", "signature" })
public class CuritibaNfseCancelRequest extends DefaultAssignable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "InfPedidoCancelamento") @NotNull CuritibaNfseCancelRequestInfo info;

    public static class Builder {

        private CuritibaNfseCancelRequestInfo info;

        /**
         * @param info
         * @return
         */
        public Builder withInfo(final CuritibaNfseCancelRequestInfo info) {
            this.info = info;
            return this;
        }

        public CuritibaNfseCancelRequest build() {
            final CuritibaNfseCancelRequest entity = new CuritibaNfseCancelRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        public CuritibaNfseCancelRequest build(final Signer signer) throws Exception {
            CuritibaNfseCancelRequest entity = new CuritibaNfseCancelRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (CuritibaNfseCancelRequest) signer.sign(entity);
            return entity;
        }
    }

    public CuritibaNfseCancelRequest() {
        info = null;
    }

    public CuritibaNfseCancelRequest(final Builder builder) {
        info = builder.info;
    }

    public CuritibaNfseCancelRequestInfo getInfo() {
        return info;
    }

    @lombok.Builder
    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @Getter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CuritibaNfseCancelRequestInfo {

        private final @XmlAttribute(name = "id") String id = UUID.randomUUID().toString().replaceAll("-", "");

        private final @XmlElement(name = "IdentificacaoNfse") @NotNull CuritibaNFSeIdentifier identifier;

        private final @XmlElement(name = "CodigoCancelamento") @NotNull CuritibaCancellationCode cancellationCode;

    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, CuritibaNfseCancelRequest.class).deserialize();
    }

    @Override
    public String getRootTagName() {
        return "CancelarNfseEnvio";
    }

    @Override
    public String getAssignableTagName() {
        return "InfPedidoCancelamento";
    }

    @Override
    public String getIdAttributeTagName() {
        return "id";
    }

}
