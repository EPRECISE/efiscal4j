
package eprecise.efiscal4j.nfse.domain.service;

import java.io.Serializable;
import java.math.BigDecimal;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String cnaeCode;

    private final String nationalServiceCode;

    private final String discrimination;

    private final BigDecimal unitaryValue;

    private final BigDecimal amount;

    private final BigDecimal discount;

    private final BigDecimal deduction;

    public static class Builder {

        private String cnaeCode;

        private String nationalServiceCode;

        private String discrimination;

        private BigDecimal unitaryValue;

        private BigDecimal amount;

        private BigDecimal discount;

        private BigDecimal deduction;

        public Builder withCnaeCode(final String cnaeCode) {
            this.cnaeCode = cnaeCode;
            return this;
        }

        public Builder withNationalServiceCode(final String nationalServiceCode) {
            this.nationalServiceCode = nationalServiceCode;
            return this;
        }

        public Builder withDiscrimination(final String discrimination) {
            this.discrimination = discrimination;
            return this;
        }

        public Builder withUnitaryValue(final BigDecimal unitaryValue) {
            this.unitaryValue = unitaryValue;
            return this;
        }

        public Builder withAmount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withDiscount(final BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public Builder withDeduction(final BigDecimal deduction) {
            this.deduction = deduction;
            return this;
        }

        public NFSeService build() {
            final NFSeService entity = new NFSeService(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeService() {
        cnaeCode = null;
        nationalServiceCode = null;
        discrimination = null;
        unitaryValue = null;
        amount = null;
        discount = null;
        deduction = null;
    }

    public NFSeService(final Builder builder) {
        cnaeCode = builder.cnaeCode;
        nationalServiceCode = builder.nationalServiceCode;
        discrimination = builder.discrimination;
        unitaryValue = builder.unitaryValue;
        amount = builder.amount;
        discount = builder.discount;
        deduction = builder.deduction;
    }

    public String getCnaeCode() {
        return cnaeCode;
    }

    public String getNationalServiceCode() {
        return nationalServiceCode;
    }

    public String getDiscrimination() {
        return discrimination;
    }

    public BigDecimal getUnitaryValue() {
        return unitaryValue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getDeduction() {
        return deduction;
    }

}
