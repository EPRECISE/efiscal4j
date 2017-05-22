
package eprecise.efiscal4j.nfse.tc.messages;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.rps.RpsIdentifier;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFSeReturnMessageLot {

    private final @XmlElement(name = "IdentificacaoRps") @NotNull RpsIdentifier identifier;

    private final @XmlElement(name = "Codigo") @NotNull String code;

    private final @XmlElement(name = "Mensagem") @NotNull String message;

    public static class Builder {

        private RpsIdentifier identifier;

        private String code;

        private String message;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final RpsIdentifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * @param code
         * @return
         */
        public Builder withCode(final String code) {
            this.code = code;
            return this;
        }

        /**
         * @param message
         * @return
         */
        public Builder withMessage(final String message) {
            this.message = message;
            return this;
        }

        public NFSeReturnMessageLot build() throws Exception {
            final NFSeReturnMessageLot entity = new NFSeReturnMessageLot(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeReturnMessageLot() {
        code = null;
        message = null;
        identifier = null;
    }

    public NFSeReturnMessageLot(final Builder builder) {
        code = builder.code;
        message = builder.message;
        identifier = builder.identifier;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public RpsIdentifier getIdentifier() {
        return identifier;
    }

}
