
package eprecise.efiscal4j.nfse.tc.elotech.statements.rps;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.rps.RpsIdentifier;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeDate;
import eprecise.efiscal4j.nfse.ts.rps.RpsStatus;


@XmlAccessorType(XmlAccessType.FIELD)
public class Rps {

    private final @XmlElement(name = "IdentificacaoRps") @NotNull RpsIdentifier identifier;

    private final @XmlElement(name = "DataEmissao") @NotNull @NFSeDate String emissionDate;

    private final @XmlElement(name = "Status") @NotNull RpsStatus status;

    public static class Builder {

        private RpsIdentifier identifier;

        private String emissionDate;

        private RpsStatus status;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final RpsIdentifier identifier) {
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
        public Builder withStatus(final RpsStatus status) {
            this.status = status;
            return this;
        }

        public Rps build() throws Exception {
            final Rps entity = new Rps(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Rps() {
        this.identifier = null;
        this.emissionDate = null;
        this.status = null;
    }

    public Rps(final Builder builder) {
        this.identifier = builder.identifier;
        this.emissionDate = builder.emissionDate;
        this.status = builder.status;
    }

    public RpsIdentifier getIdentifier() {
        return identifier;
    }

    public String getEmissionDate() {
        return emissionDate;
    }

    public RpsStatus getStatus() {
        return status;
    }

}
