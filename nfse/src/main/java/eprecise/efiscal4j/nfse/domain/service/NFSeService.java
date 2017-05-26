
package eprecise.efiscal4j.nfse.domain.service;

import java.io.Serializable;
import java.math.BigDecimal;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.person.address.NFSeCity;


public class NFSeService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    private final String cnaeCode;

    private final String nationalServiceCode;

    private final NFSeCity cityService;

    private final String discrimination;

    private final BigDecimal unitaryValue;

    private final BigDecimal amount;

    private final BigDecimal discount;

    private final BigDecimal deduction;

    private final BigDecimal serviceValue;

    public static class Builder {

        private String name;

        private String cnaeCode;

        private String nationalServiceCode;

        private NFSeCity cityService;

        private String discrimination;

        private BigDecimal unitaryValue = BigDecimal.ZERO;

        private BigDecimal amount = BigDecimal.ZERO;

        private BigDecimal discount = BigDecimal.ZERO;

        private BigDecimal deduction = BigDecimal.ZERO;

        private BigDecimal serviceValue = BigDecimal.ZERO;

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

        public Builder withDiscount(final BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public Builder withDeduction(final BigDecimal deduction) {
            this.deduction = deduction;
            return this;
        }

        public Builder withServiceValue(final BigDecimal serviceValue) {
            this.serviceValue = serviceValue;
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
        cityService = null;
        discrimination = null;
        unitaryValue = null;
        amount = null;
        discount = null;
        deduction = null;
        serviceValue = null;
    }

    public NFSeService(final Builder builder) {
        name = builder.name;
        cnaeCode = builder.cnaeCode;
        nationalServiceCode = builder.nationalServiceCode;
        cityService = builder.cityService;
        discrimination = builder.discrimination;
        unitaryValue = builder.unitaryValue;
        amount = builder.amount;
        discount = builder.discount;
        deduction = builder.deduction;
        serviceValue = builder.serviceValue;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getServiceValue() {
        return serviceValue;
    }

    public BigDecimal getDeduction() {
        return deduction;
    }

}
