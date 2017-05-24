
package eprecise.efiscal4j.nfse.tc.govbr.lot.rps.service;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeAliquot;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrValues {

    private final @XmlElement(name = "ValorServicos") @NotNull @NFSeValue String serviceValue;

    private final @XmlElement(name = "ValorDeducoes") @NFSeValue String deductionValue;

    private final @XmlElement(name = "ValorPis") @NFSeValue String pisValue;

    private final @XmlElement(name = "ValorCofins") @NFSeValue String cofinsValue;

    private final @XmlElement(name = "ValorInss") @NFSeValue String inssValue;

    private final @XmlElement(name = "ValorIr") @NFSeValue String irValue;

    private final @XmlElement(name = "ValorCsll") @NFSeValue String csllValue;

    private final @XmlElement(name = "IssRetido") @NotNull CommonsNFSeBoolean issWithheld;

    private final @XmlElement(name = "ValorIss") @NFSeValue String issValue;

    private final @XmlElement(name = "ValorIssRetido") @NFSeValue String issWithhelValue;

    private final @XmlElement(name = "OutrasRetencoes") @NFSeValue String otherRetentionsValue;

    private final @XmlElement(name = "BaseCalculo") @NotNull @NFSeValue String bcValue;

    private final @XmlElement(name = "Aliquota") @NFSeAliquot String issAliquot;

    private final @XmlElement(name = "ValorLiquidoNfse") @NotNull @NFSeValue String netValue;

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

        private CommonsNFSeBoolean issWithheld;

        private String issValue;

        private String issWithhelValue;

        private String otherRetentionsValue;

        private String bcValue;

        private String issAliquot;

        private String netValue;

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
         * @param issWithheld
         * @return
         */
        public Builder withIssWithheld(final CommonsNFSeBoolean issWithheld) {
            this.issWithheld = issWithheld;
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
         * @param issWithhelValue
         * @return
         */
        public Builder withIssWithhelValue(final String issWithhelValue) {
            this.issWithhelValue = issWithhelValue;
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
         * @param netValue
         * @return
         */
        public Builder withNetValue(final String netValue) {
            this.netValue = netValue;
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

        public GovbrValues build() throws Exception {
            final GovbrValues entity = new GovbrValues(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrValues() {
        serviceValue = null;
        deductionValue = null;
        pisValue = null;
        cofinsValue = null;
        inssValue = null;
        irValue = null;
        csllValue = null;
        issWithheld = null;
        issValue = null;
        issWithhelValue = null;
        otherRetentionsValue = null;
        bcValue = null;
        issAliquot = null;
        netValue = null;
        discountUnconditionedValue = null;
        discountConditionedValue = null;
    }

    public GovbrValues(final Builder builder) {
        serviceValue = builder.serviceValue;
        deductionValue = builder.deductionValue;
        pisValue = builder.pisValue;
        cofinsValue = builder.cofinsValue;
        inssValue = builder.inssValue;
        irValue = builder.irValue;
        csllValue = builder.csllValue;
        issWithheld = builder.issWithheld;
        issValue = builder.issValue;
        issWithhelValue = builder.issWithhelValue;
        otherRetentionsValue = builder.otherRetentionsValue;
        bcValue = builder.bcValue;
        issAliquot = builder.issAliquot;
        netValue = builder.netValue;
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

    public CommonsNFSeBoolean getIssWithheld() {
        return issWithheld;
    }

    public String getIssValue() {
        return issValue;
    }

    public String getIssWithhelValue() {
        return issWithhelValue;
    }

    public String getOtherRetentionsValue() {
        return otherRetentionsValue;
    }

    public String getBcValue() {
        return bcValue;
    }

    public String getIssAliquot() {
        return issAliquot;
    }

    public String getNetValue() {
        return netValue;
    }

    public String getDiscountUnconditionedValue() {
        return discountUnconditionedValue;
    }

    public String getDiscountConditionedValue() {
        return discountConditionedValue;
    }

}
