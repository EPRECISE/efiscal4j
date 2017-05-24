
package eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeAliquot;
import eprecise.efiscal4j.nfse.ts.elotech.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechServiceValues {

    private final @XmlElement(name = "ValorServicos") @NotNull @NFSeValue String serviceValue;

    private final @XmlElement(name = "ValorDeducoes") @NFSeValue String deductionValue;

    private final @XmlElement(name = "ValorPis") @NFSeValue String pisValue;

    private final @XmlElement(name = "ValorCofins") @NFSeValue String cofinsValue;

    private final @XmlElement(name = "ValorInss") @NFSeValue String inssValue;

    private final @XmlElement(name = "ValorIr") @NFSeValue String irValue;

    private final @XmlElement(name = "ValorCsll") @NFSeValue String csllValue;

    private final @XmlElement(name = "OutrasRetencoes") @NFSeValue String otherRetentionsValue;

    private final @XmlElement(name = "ValorIss") @NFSeValue String issValue;

    private final @XmlElement(name = "Aliquota") @NFSeAliquot String issAliquot;

    private final @XmlElement(name = "DescontoIncondicionado") @NFSeValue String discountUnconditionedValue;

    private final @XmlElement(name = "DescontoCondicionado") @NFSeValue String discountConditionedValue;

    public static class Builder {

        private String serviceValue;

        private String deductionValue;

        private String pisValue;

        private String cofinsValue;

        private String inssValue;

        private String irValue;

        private String csllValue;

        private String otherRetentionsValue;

        private String issValue;

        private String issAliquot;

        private String discountUnconditionedValue;

        private String discountConditionedValue;

        /**
         * @param serviceValue
         * @return
         */
        public Builder withServiceValue(final String serviceValue) {
            this.serviceValue = serviceValue;
            return this;
        }

        /**
         * @param deductionValue
         * @return
         */
        public Builder withDeductionValue(final String deductionValue) {
            this.deductionValue = deductionValue;
            return this;
        }

        /**
         * @param pisValue
         * @return
         */
        public Builder withPisValue(final String pisValue) {
            this.pisValue = pisValue;
            return this;
        }

        /**
         * @param cofinsValue
         * @return
         */
        public Builder withCofinsValue(final String cofinsValue) {
            this.cofinsValue = cofinsValue;
            return this;
        }

        /**
         * @param inssValue
         * @return
         */
        public Builder withInssValue(final String inssValue) {
            this.inssValue = inssValue;
            return this;
        }

        /**
         * @param irValue
         * @return
         */
        public Builder withIrValue(final String irValue) {
            this.irValue = irValue;
            return this;
        }

        /**
         * @param csllValue
         * @return
         */
        public Builder withCsllValue(final String csllValue) {
            this.csllValue = csllValue;
            return this;
        }

        /**
         * @param otherRetentionsValue
         * @return
         */
        public Builder withOtherRetentionsValue(final String otherRetentionsValue) {
            this.otherRetentionsValue = otherRetentionsValue;
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
         * @param issAliquot
         * @return
         */
        public Builder withIssAliquot(final String issAliquot) {
            this.issAliquot = issAliquot;
            return this;
        }

        /**
         * @param discountUnconditionedValue
         * @return
         */
        public Builder withDiscountUnconditionedValue(final String discountUnconditionedValue) {
            this.discountUnconditionedValue = discountUnconditionedValue;
            return this;
        }

        /**
         * @param discountConditionedValue
         * @return
         */
        public Builder withDiscountConditionedValue(final String discountConditionedValue) {
            this.discountConditionedValue = discountConditionedValue;
            return this;
        }

        public ElotechServiceValues build() throws Exception {
            final ElotechServiceValues entity = new ElotechServiceValues(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechServiceValues() {
        serviceValue = null;
        deductionValue = null;
        pisValue = null;
        cofinsValue = null;
        inssValue = null;
        irValue = null;
        csllValue = null;
        otherRetentionsValue = null;
        issValue = null;
        issAliquot = null;
        discountUnconditionedValue = null;
        discountConditionedValue = null;
    }

    public ElotechServiceValues(final Builder builder) {
        serviceValue = builder.serviceValue;
        deductionValue = builder.deductionValue;
        pisValue = builder.pisValue;
        cofinsValue = builder.cofinsValue;
        inssValue = builder.inssValue;
        irValue = builder.irValue;
        csllValue = builder.csllValue;
        otherRetentionsValue = builder.otherRetentionsValue;
        issValue = builder.issValue;
        issAliquot = builder.issAliquot;
        discountUnconditionedValue = builder.discountUnconditionedValue;
        discountConditionedValue = builder.discountConditionedValue;
    }

    public String getServiceValue() {
        return serviceValue;
    }

    public String getDeductionValue() {
        return deductionValue;
    }

    public String getPisValue() {
        return pisValue;
    }

    public String getCofinsValue() {
        return cofinsValue;
    }

    public String getInssValue() {
        return inssValue;
    }

    public String getIrValue() {
        return irValue;
    }

    public String getCsllValue() {
        return csllValue;
    }

    public String getOtherRetentionsValue() {
        return otherRetentionsValue;
    }

    public String getIssValue() {
        return issValue;
    }

    public String getIssAliquot() {
        return issAliquot;
    }

    public String getDiscountUnconditionedValue() {
        return discountUnconditionedValue;
    }

    public String getDiscountConditionedValue() {
        return discountConditionedValue;
    }

}
