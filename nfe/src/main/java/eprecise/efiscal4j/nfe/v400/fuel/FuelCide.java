
package eprecise.efiscal4j.nfe.v400.fuel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1104;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1204Variable;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * CIDE Combustíveis
 *
 * @author Fernando C Glizt
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class FuelCide implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "qBCProd") @NotNull @NFeDecimal1204Variable final String bcCideQuantity;

    private @XmlElement(name = "vAliqProd") @NotNull @NFeDecimal1104 final String cideAliquotValue;

    private @XmlElement(name = "vCIDE") @NotNull @NFeDecimal1302 final String cideValue;

    public static class Builder {

        private String bcCideQuantity;

        private String cideAliquotValue;

        private String cideValue;

        /**
         * BC do CIDE (Quantidade comercializada)
         *
         * @param bcCideQuantity
         * @return
         */
        public Builder withBcCideQuantity(final String bcCideQuantity) {
            this.bcCideQuantity = bcCideQuantity;
            return this;
        }

        /**
         * Alíquota do CIDE (em reais)
         *
         * @param cideValue
         * @return
         */
        public Builder withCideValue(final String cideValue) {
            this.cideValue = cideValue;
            return this;
        }

        /**
         * Valor do CIDE
         *
         * @param cideAliquotValue
         * @return
         */
        public Builder withCideAliquotValue(final String cideAliquotValue) {
            this.cideAliquotValue = cideAliquotValue;
            return this;
        }

        public FuelCide build() {
            final FuelCide entity = new FuelCide(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public FuelCide() {
        this.bcCideQuantity = null;
        this.cideAliquotValue = null;
        this.cideValue = null;
    }

    public FuelCide(final Builder builder) {
        this.bcCideQuantity = builder.bcCideQuantity;
        this.cideAliquotValue = builder.cideAliquotValue;
        this.cideValue = builder.cideValue;
    }

    public String getBcCideQuantity() {
        return bcCideQuantity;
    }

    public String getCideAliquotValue() {
        return cideAliquotValue;
    }

    public String getCideValue() {
        return cideValue;
    }

}
