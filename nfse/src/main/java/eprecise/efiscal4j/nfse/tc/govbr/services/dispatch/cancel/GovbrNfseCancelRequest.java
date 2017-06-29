
package eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.cancel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.govbr.GovbrNFSeIdentifier;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNfseCancelRequest {

    private final @XmlElement(name = "InfPedidoCancelamento") @NotNull Info info;

    public static class Builder {

        private Info info;

        /**
         * @param info
         * @return
         */
        public Builder withInfo(final Info info) {
            this.info = info;
            return this;
        }

        public GovbrNfseCancelRequest build() {
            final GovbrNfseCancelRequest entity = new GovbrNfseCancelRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrNfseCancelRequest() {
        info = null;
    }

    public GovbrNfseCancelRequest(final Builder builder) {
        info = builder.info;
    }

    public Info getInfo() {
        return info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Info {

        private final @XmlElement(name = "IdentificacaoNfse") @NotNull GovbrNFSeIdentifier identifier;

        // TODO falta o atributo código de cancelamento

        public static class Builder {

            private GovbrNFSeIdentifier identifier;

            /**
             * @param identifier
             * @return
             */
            public Builder withIdentifier(final GovbrNFSeIdentifier identifier) {
                this.identifier = identifier;
                return this;
            }

            public Info build() {
                final Info entity = new Info(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public Info() {
            identifier = null;
        }

        public Info(final Builder builder) {
            identifier = builder.identifier;
        }

        public GovbrNFSeIdentifier getIdentifier() {
            return identifier;
        }

    }

}
