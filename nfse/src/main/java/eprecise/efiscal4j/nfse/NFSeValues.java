
package eprecise.efiscal4j.nfse;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.types.NFSeAliquot;
import eprecise.efiscal4j.nfse.types.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFSeValues implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "BaseCalculo") @NFSeValue String bcValue;

    private final @XmlElement(name = "Aliquota") @NFSeAliquot String issAliquot;

    private final @XmlElement(name = "ValorIss") @NFSeValue String issValue;

    private final @NotNull @XmlElement(name = "ValorLiquidoNfse") @NFSeValue String issNetValue;

    public static class Builder {

        private String bcValue;

        private String issAliquot;

        private String issValue;

        private String issNetValue;

        /**
         * @param bcValue
         * @return
         */
        public Builder withBcValue(final String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * @param issAliquot
         * @return
         */
        public Builder withIssAliquot(final String issAliquot) {
            this.issAliquot = issAliquot;
            return this;
        }

        /**
         * @param issValue
         * @return
         */
        public Builder withIssValue(final String issValue) {
            this.issValue = issValue;
            return this;
        }

        /**
         * @param issNetValue
         * @return
         */
        public Builder withIssNetValue(final String issNetValue) {
            this.issNetValue = issNetValue;
            return this;
        }

        public NFSeValues build() throws Exception {
            final NFSeValues entity = new NFSeValues(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeValues() {
        bcValue = null;
        issAliquot = null;
        issValue = null;
        issNetValue = null;
    }

    public NFSeValues(final Builder builder) {
        bcValue = builder.bcValue;
        issAliquot = builder.issAliquot;
        issValue = builder.issValue;
        issNetValue = builder.issNetValue;
    }

    public String getBcValue() {
        return bcValue;
    }

    public String getIssAliquot() {
        return issAliquot;
    }

    public String getIssNetValue() {
        return issNetValue;
    }

    public String getIssValue() {
        return issValue;
    }

}
