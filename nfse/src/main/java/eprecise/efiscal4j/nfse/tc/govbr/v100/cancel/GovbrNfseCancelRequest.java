
package eprecise.efiscal4j.nfse.tc.govbr.v100.cancel;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.govbr.v100.GovbrNFSeIdentifier;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNfseCancelRequest extends DefaultAssignable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "InfPedidoCancelamento") @NotNull GovbrNfseCancelRequestInfo info;

    public static class Builder {

        private GovbrNfseCancelRequestInfo info;

        /**
         * @param info
         * @return
         */
        public Builder withInfo(final GovbrNfseCancelRequestInfo info) {
            this.info = info;
            return this;
        }

        public GovbrNfseCancelRequest build() {
            final GovbrNfseCancelRequest entity = new GovbrNfseCancelRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

        public GovbrNfseCancelRequest build(final Signer signer) throws Exception {
            GovbrNfseCancelRequest entity = new GovbrNfseCancelRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (GovbrNfseCancelRequest) signer.sign(entity);
            return entity;
        }
    }

    public GovbrNfseCancelRequest() {
        info = null;
    }

    public GovbrNfseCancelRequest(final Builder builder) {
        info = builder.info;
    }

    public GovbrNfseCancelRequestInfo getInfo() {
        return info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GovbrNfseCancelRequestInfo {

        private @XmlAttribute(name = "id") final String id = UUID.randomUUID().toString().replaceAll("-", "");

        private final @XmlElement(name = "IdentificacaoNfse") @NotNull GovbrNFSeIdentifier identifier;

        private final @XmlElement(name = "CodigoCancelamento") @NotNull GovbrCancellationCode cancellationCode;

        // TODO falta o atributo código de cancelamento

        public static class Builder {

            private GovbrNFSeIdentifier identifier;

            private GovbrCancellationCode cancellationCode;

            /**
             * @param identifier
             * @return
             */
            public Builder withIdentifier(final GovbrNFSeIdentifier identifier) {
                this.identifier = identifier;
                return this;
            }

            /**
             * @param cancellationCode
             * @return
             */
            public Builder withCancellationCode(final GovbrCancellationCode cancellationCode) {
                this.cancellationCode = cancellationCode;
                return this;
            }

            public GovbrNfseCancelRequestInfo build() {
                final GovbrNfseCancelRequestInfo entity = new GovbrNfseCancelRequestInfo(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public GovbrNfseCancelRequestInfo() {
            identifier = null;
            cancellationCode = null;
        }

        public GovbrNfseCancelRequestInfo(final Builder builder) {
            identifier = builder.identifier;
            cancellationCode = builder.cancellationCode;
        }

        public GovbrNFSeIdentifier getIdentifier() {
            return identifier;
        }

        public GovbrCancellationCode getCancellationCode() {
            return cancellationCode;
        }

    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, GovbrNfseCancelRequest.class).deserialize();
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
