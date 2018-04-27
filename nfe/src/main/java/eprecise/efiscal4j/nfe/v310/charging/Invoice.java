
package eprecise.efiscal4j.nfe.v310.charging;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1302Optional;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Fatura da cobrança da NF-e
 * 
 * @author Felipe Bueno
 * 
 */
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nFat") @Size(min = 1, max = 60) @NFeString String number;

    private @XmlElement(name = "vOrig") @NFeDecimal1302Optional String originalValue;

    private @XmlElement(name = "vDesc") @NFeDecimal1302Optional String discountValue;

    private @XmlElement(name = "vLiq") @NFeDecimal1302Optional String netValue;

    public static class Builder {

        private String number;

        private String originalValue;

        private String discountValue;

        private String netValue;

        /**
         * Número da fatura
         * 
         * @param number
         * @return
         */
        public Builder withNumber(final String number) {
            this.number = number;
            return this;
        }

        /**
         * Valor original da fatura
         * 
         * @param originalValue
         * @return
         */
        public Builder withOriginalValue(final String originalValue) {
            this.originalValue = originalValue;
            return this;
        }

        /**
         * Valor do desconto da fatura
         * 
         * @param discountValue
         * @return
         */
        public Builder withDiscountValue(final String discountValue) {
            this.discountValue = discountValue;
            return this;
        }

        /**
         * Valor líquido da fatura
         * 
         * @param netValue
         * @return
         */
        public Builder withNetValue(final String netValue) {
            this.netValue = netValue;
            return this;
        }

        public Invoice build() {
            final Invoice entity = new Invoice(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Invoice() {
    }

    public Invoice(final Builder builder) {
        this.number = builder.number;
        this.originalValue = builder.originalValue;
        this.discountValue = builder.discountValue;
        this.netValue = builder.netValue;
    }

    public String getNumber() {
        return this.number;
    }

    public String getOriginalValue() {
        return this.originalValue;
    }

    public String getDiscountValue() {
        return this.discountValue;
    }

    public String getNetValue() {
        return this.netValue;
    }

}
