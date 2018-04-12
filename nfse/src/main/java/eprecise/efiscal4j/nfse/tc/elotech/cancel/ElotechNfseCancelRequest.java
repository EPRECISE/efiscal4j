
package eprecise.efiscal4j.nfse.tc.elotech.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeAccessKey;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechNfseCancelRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "InfPedidoCancelamento") @NotNull ElotechNfseCancelRequestInfo info;

    public static class Builder {

        private ElotechNfseCancelRequestInfo info;

        /**
         * @param info
         * @return
         */
        public Builder withInfo(final ElotechNfseCancelRequestInfo info) {
            this.info = info;
            return this;
        }

        public ElotechNfseCancelRequest build() {
            final ElotechNfseCancelRequest entity = new ElotechNfseCancelRequest(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechNfseCancelRequest() {
        info = null;
    }

    public ElotechNfseCancelRequest(final Builder builder) {
        info = builder.info;
    }

    public ElotechNfseCancelRequestInfo getInfo() {
        return info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ElotechNfseCancelRequestInfo {

        private final @XmlElement(name = "IdentificacaoNfse") @NotNull ElotechNFSeIdentifier identifier;

        private final @NotNull @XmlElement(name = "ChaveAcesso") @NFSeAccessKey String accessKey;

        private final @XmlElement(name = "CodigoCancelamento") @NotNull ElotechCancellationCode cancellationCode;

        public static class Builder {

            private ElotechNFSeIdentifier identifier;

            private String accessKey;

            private ElotechCancellationCode cancellationCode;

            /**
             * @param identifier
             * @return
             */
            public Builder withIdentifier(final ElotechNFSeIdentifier identifier) {
                this.identifier = identifier;
                return this;
            }

            /**
             * @param accessKey
             * @return
             */
            public Builder withAccessKey(final String accessKey) {
                this.accessKey = accessKey;
                return this;
            }

            /**
             * @param cancellationCode
             * @return
             */
            public Builder withCancellationCode(final ElotechCancellationCode cancellationCode) {
                this.cancellationCode = cancellationCode;
                return this;
            }

            public ElotechNfseCancelRequestInfo build() {
                final ElotechNfseCancelRequestInfo entity = new ElotechNfseCancelRequestInfo(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public ElotechNfseCancelRequestInfo() {
            identifier = null;
            accessKey = null;
            cancellationCode = null;
        }

        public ElotechNfseCancelRequestInfo(final Builder builder) {
            identifier = builder.identifier;
            accessKey = builder.accessKey;
            cancellationCode = builder.cancellationCode;
        }

        
        public ElotechNFSeIdentifier getIdentifier() {
            return identifier;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public ElotechCancellationCode getCancellationCode() {
            return cancellationCode;
        }

    }

}
