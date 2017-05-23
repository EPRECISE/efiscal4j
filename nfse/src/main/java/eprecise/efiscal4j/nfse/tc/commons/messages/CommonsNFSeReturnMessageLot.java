
package eprecise.efiscal4j.nfse.tc.commons.messages;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;


@XmlAccessorType(XmlAccessType.FIELD)
public class CommonsNFSeReturnMessageLot {

    private final @XmlElement(name = "IdentificacaoRps") @NotNull CommonsRpsIdentifier identifier;

    private final @XmlElement(name = "Codigo") @NotNull String code;

    private final @XmlElement(name = "Mensagem") @NotNull String message;

    public static class Builder {

        private CommonsRpsIdentifier identifier;

        private String code;

        private String message;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final CommonsRpsIdentifier identifier) {
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

        public CommonsNFSeReturnMessageLot build() throws Exception {
            final CommonsNFSeReturnMessageLot entity = new CommonsNFSeReturnMessageLot(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CommonsNFSeReturnMessageLot() {
        code = null;
        message = null;
        identifier = null;
    }

    public CommonsNFSeReturnMessageLot(final Builder builder) {
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

    public CommonsRpsIdentifier getIdentifier() {
        return identifier;
    }

}
