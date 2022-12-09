
package eprecise.efiscal4j.nfse.domain.service;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeCity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;


public class NFSeService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    private final String cnaeCode;

    private final String nationalServiceCode;

    private final String assessmentCityCode;

    private final NFSeCity cityService;

    private final String discrimination;

    private final BigDecimal unitaryValue;

    private final BigDecimal amount;

    private final NFSeDiscount discount;

    private final BigDecimal deduction;

    public static class Builder {

        private String name;

        private String cnaeCode;

        private String nationalServiceCode;

        private String assessmentCityCode;

        private NFSeCity cityService;

        private String discrimination;

        private BigDecimal unitaryValue = BigDecimal.ZERO;

        private BigDecimal amount = BigDecimal.ZERO;

        private NFSeDiscount discount = NFSeDiscount.builder().build();

        private BigDecimal deduction = BigDecimal.ZERO;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withCnaeCode(final String cnaeCode) {
            this.cnaeCode = cnaeCode;
            return this;
        }

        public Builder withNationalServiceCode(final String nationalServiceCode) {
            this.nationalServiceCode = nationalServiceCode;
            return this;
        }

        public Builder withAssessmentCityCode(final String assessmentCityCode) {
            this.assessmentCityCode = assessmentCityCode;
            return this;
        }

        public Builder withCityService(final NFSeCity cityService) {
            this.cityService = cityService;
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

        public Builder withDiscount(final BigDecimal unconditionedValue) {
            this.discount = NFSeDiscount.builder().unconditionedValue(unconditionedValue).conditionedValue(Optional.ofNullable(this.discount.getConditionedValue()).orElse(BigDecimal.ZERO)).build();
            return this;
        }

        public Builder withDiscount(final NFSeDiscount discount) {
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
        name = null;
        cnaeCode = null;
        nationalServiceCode = null;
        assessmentCityCode = null;
        cityService = null;
        discrimination = null;
        unitaryValue = null;
        amount = null;
        discount = null;
        deduction = null;
    }

    public NFSeService(final Builder builder) {
        name = builder.name;
        cnaeCode = builder.cnaeCode;
        nationalServiceCode = builder.nationalServiceCode;
        assessmentCityCode = builder.assessmentCityCode;
        cityService = builder.cityService;
        discrimination = builder.discrimination;
        unitaryValue = builder.unitaryValue;
        amount = builder.amount;
        discount = builder.discount;
        deduction = builder.deduction;
    }

    public String getName() {
        return name;
    }

    public String getCnaeCode() {
        return cnaeCode;
    }

    public String getNationalServiceCode() {
        return nationalServiceCode;
    }

    public String getAssessmentCityCode() {
        return assessmentCityCode;
    }

    public NFSeCity getCityService() {
        return cityService;
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

    public NFSeDiscount getDiscount() {
        return discount;
    }

    public BigDecimal getDeduction() {
        return deduction;
    }

    public BigDecimal getGrossValue() {
        return Optional.ofNullable(getAmount()).orElse(BigDecimal.ZERO).multiply(Optional.ofNullable(getUnitaryValue()).orElse(BigDecimal.ZERO));
    }

    public BigDecimal getNetValue() {
        return getGrossValue().subtract(Optional.ofNullable(getDiscount().getTotal()).orElse(BigDecimal.ZERO));
    }

}
