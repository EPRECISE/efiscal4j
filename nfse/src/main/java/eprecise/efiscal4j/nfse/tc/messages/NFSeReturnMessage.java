
package eprecise.efiscal4j.nfse.tc.messages;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFSeReturnMessage {

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

        public NFSeReturnMessage build() throws Exception {
            final NFSeReturnMessage entity = new NFSeReturnMessage(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeReturnMessage() {
        code = null;
        message = null;
        correction = null;
    }

    public NFSeReturnMessage(final Builder builder) {
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
