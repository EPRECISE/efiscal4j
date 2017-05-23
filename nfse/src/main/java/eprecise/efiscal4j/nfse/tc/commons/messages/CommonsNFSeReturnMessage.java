
package eprecise.efiscal4j.nfse.tc.commons.messages;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class CommonsNFSeReturnMessage {

    private final @XmlElement(name = "Codigo") @NotNull String code;

    private final @XmlElement(name = "Mensagem") @NotNull String message;

    private final @XmlElement(name = "Correcao") @NotNull String correction;

    public static class Builder {

        private String code;

        private String message;

        private String correction;

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

        /**
         * @param correction
         * @return
         */
        public Builder withCorrection(final String correction) {
            this.correction = correction;
            return this;
        }

        public CommonsNFSeReturnMessage build() throws Exception {
            final CommonsNFSeReturnMessage entity = new CommonsNFSeReturnMessage(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CommonsNFSeReturnMessage() {
        code = null;
        message = null;
        correction = null;
    }

    public CommonsNFSeReturnMessage(final Builder builder) {
        code = builder.code;
        message = builder.message;
        correction = builder.correction;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getCorrection() {
        return correction;
    }

}
