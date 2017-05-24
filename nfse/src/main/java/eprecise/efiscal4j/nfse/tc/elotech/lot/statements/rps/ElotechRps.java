
package eprecise.efiscal4j.nfse.tc.elotech.lot.statements.rps;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.rps.CommonsRpsIdentifier;
import eprecise.efiscal4j.nfse.ts.commons.rps.CommonsRpsStatus;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeDate;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechRps {

    private final @XmlElement(name = "IdentificacaoRps") @NotNull CommonsRpsIdentifier identifier;

    private final @XmlElement(name = "DataEmissao") @NotNull @NFSeDate String emissionDate;

    private final @XmlElement(name = "Status") @NotNull CommonsRpsStatus status;

    public static class Builder {

        private CommonsRpsIdentifier identifier;

        private String emissionDate;

        private CommonsRpsStatus status;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final CommonsRpsIdentifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * @param emissionDate
         * @return
         */
        public Builder withEmissionDate(final String emissionDate) {
            this.emissionDate = emissionDate;
            return this;
        }

        /**
         * @param status
         * @return
         */
        public Builder withStatus(final CommonsRpsStatus status) {
            this.status = status;
            return this;
        }

        public ElotechRps build() throws Exception {
            final ElotechRps entity = new ElotechRps(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechRps() {
        this.identifier = null;
        this.emissionDate = null;
        this.status = null;
    }

    public ElotechRps(final Builder builder) {
        this.identifier = builder.identifier;
        this.emissionDate = builder.emissionDate;
        this.status = builder.status;
    }

    public CommonsRpsIdentifier getIdentifier() {
        return identifier;
    }

    public String getEmissionDate() {
        return emissionDate;
    }

    public CommonsRpsStatus getStatus() {
        return status;
    }

}
