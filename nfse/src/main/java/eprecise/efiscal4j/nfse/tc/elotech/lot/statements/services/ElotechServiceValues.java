
package eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.commons.CommonsNFSeBoolean;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeAliquot;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechServiceValues {

    private final @XmlElement(name = "ValorServicos") @NotNull @NFSeValue String serviceValue;

    private final @XmlElement(name = "ValorDeducoes") @NFSeValue String deductionValue;

    private final @XmlElement(name = "AliquotaPis") @NFSeAliquot String pisAliquot;

    private final @XmlElement(name = "RetidoPis") CommonsNFSeBoolean pisWithheld;

    private final @XmlElement(name = "ValorPis") @NFSeValue String pisValue;

    private final @XmlElement(name = "AliquotaCofins") @NFSeAliquot String cofinsAliquot;

    private final @XmlElement(name = "RetidoCofins") CommonsNFSeBoolean cofinsWithheld;

    private final @XmlElement(name = "ValorCofins") @NFSeValue String cofinsValue;

    private final @XmlElement(name = "AliquotaInss") @NFSeAliquot String inssAliquot;

    private final @XmlElement(name = "RetidoInss") CommonsNFSeBoolean inssWithheld;

    private final @XmlElement(name = "ValorInss") @NFSeValue String inssValue;

    private final @XmlElement(name = "AliquotaIr") @NFSeAliquot String irAliquot;

    private final @XmlElement(name = "RetidoIr") CommonsNFSeBoolean irWithheld;

    private final @XmlElement(name = "ValorIr") @NFSeValue String irValue;

    private final @XmlElement(name = "AliquotaCsll") @NFSeAliquot String csllAliquot;

    private final @XmlElement(name = "RetidoCsll") CommonsNFSeBoolean csllWithheld;

    private final @XmlElement(name = "ValorCsll") @NFSeValue String csllValue;

    private final @XmlElement(name = "AliquotaCpp") @NFSeAliquot String cppAliquot;

    private final @XmlElement(name = "RetidoCpp") CommonsNFSeBoolean cppWithheld;

    private final @XmlElement(name = "ValorCpp") @NFSeValue String cppValue;

    private final @XmlElement(name = "OutrasRetencoes") @NFSeValue String otherRetentionsValue;

    private final @XmlElement(name = "RetidoOutrasRetencoes") @NFSeValue String otherRetentionsWithheldValue;

    private final @XmlElement(name = "AliquotaTotTributos") @NFSeAliquot String totalTaxAliquot;

    private final @XmlElement(name = "ValorTotTributos") @NFSeValue String totalTaxValue;

    private final @XmlElement(name = "ValorIss") @NFSeValue String issValue;

    private final @XmlElement(name = "Aliquota") @NFSeAliquot String issAliquot;

    private final @XmlElement(name = "DescontoIncondicionado") @NFSeValue String discountUnconditionedValue;

    private final @XmlElement(name = "DescontoCondicionado") @NFSeValue String discountConditionedValue;

    public static class Builder {

        private String serviceValue;

        private String deductionValue;

        private String pisAliquot;

        private CommonsNFSeBoolean pisWithheld;

        private String pisValue;

        private String cofinsAliquot;

        private CommonsNFSeBoolean cofinsWithheld;

        private String cofinsValue;

        private String inssAliquot;

        private CommonsNFSeBoolean inssWithheld;

        private String inssValue;

        private String irAliquot;

        private CommonsNFSeBoolean irWithheld;

        private String irValue;

        private String csllAliquot;

        private CommonsNFSeBoolean csllWithheld;

        private String csllValue;

        private String cppAliquot;

        private CommonsNFSeBoolean cppWithheld;

        private String cppValue;

        private String otherRetentionsValue;

        private String otherRetentionsWithheldValue;

        private String totalTaxAliquot;

        private String totalTaxValue;

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
         * @param pisAliquot
         * @return
         */
        public Builder withPisAliquot(final String pisAliquot) {
            this.pisAliquot = pisAliquot;
            return this;
        }

        /**
         * @param pisWithheld
         * @return
         */
        public Builder withPisWithheld(final CommonsNFSeBoolean pisWithheld) {
            this.pisWithheld = pisWithheld;
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
         * @param cofinsAliquot
         * @return
         */
        public Builder withCofinsAliquot(final String cofinsAliquot) {
            this.cofinsAliquot = cofinsAliquot;
            return this;
        }

        /**
         * @param cofinsWithheld
         * @return
         */
        public Builder withCofinsWithheld(final CommonsNFSeBoolean cofinsWithheld) {
            this.cofinsWithheld = cofinsWithheld;
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
         * @param inssAliquot
         * @return
         */
        public Builder withInssAliquot(final String inssAliquot) {
            this.inssAliquot = inssAliquot;
            return this;
        }

        /**
         * @param inssWithheld
         * @return
         */
        public Builder withInssWithheld(final CommonsNFSeBoolean inssWithheld) {
            this.inssWithheld = inssWithheld;
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
         * @param irAliquot
         * @return
         */
        public Builder withIrAliquot(final String irAliquot) {
            this.irAliquot = irAliquot;
            return this;
        }

        /**
         * @param irWithheld
         * @return
         */
        public Builder withIrWithheld(final CommonsNFSeBoolean irWithheld) {
            this.irWithheld = irWithheld;
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
         * @param csllAliquot
         * @return
         */
        public Builder withCsllAliquot(final String csllAliquot) {
            this.csllAliquot = csllAliquot;
            return this;
        }

        /**
         * @param csllWithheld
         * @return
         */
        public Builder withCsllWithheld(final CommonsNFSeBoolean csllWithheld) {
            this.csllWithheld = csllWithheld;
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
         * @param cppAliquot
         * @return
         */
        public Builder withCppAliquot(final String cppAliquot) {
            this.cppAliquot = cppAliquot;
            return this;
        }

        /**
         * @param cppWithheld
         * @return
         */
        public Builder withCppWithheld(final CommonsNFSeBoolean cppWithheld) {
            this.cppWithheld = cppWithheld;
            return this;
        }

        /**
         * @param cppValue
         * @return
         */
        public Builder withCppValue(final String cppValue) {
            this.cppValue = cppValue;
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
         * @param otherRetentionsWithheldValue
         * @return
         */
        public Builder withOtherRetentionsWithheldValue(final String otherRetentionsWithheldValue) {
            this.otherRetentionsWithheldValue = otherRetentionsWithheldValue;
            return this;
        }

        /**
         * @param totalTaxAliquot
         * @return
         */
        public Builder withTotalTaxAliquot(final String totalTaxAliquot) {
            this.totalTaxAliquot = totalTaxAliquot;
            return this;
        }

        /**
         * @param totalTaxValue
         * @return
         */
        public Builder withTotalTaxValue(final String totalTaxValue) {
            this.totalTaxValue = totalTaxValue;
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

        public ElotechServiceValues build() {
            final ElotechServiceValues entity = new ElotechServiceValues(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechServiceValues() {
        this.serviceValue = null;
        this.deductionValue = null;
        this.pisAliquot = null;
        this.pisWithheld = null;
        this.pisValue = null;
        this.cofinsAliquot = null;
        this.cofinsWithheld = null;
        this.cofinsValue = null;
        this.inssAliquot = null;
        this.inssWithheld = null;
        this.inssValue = null;
        this.irAliquot = null;
        this.irWithheld = null;
        this.irValue = null;
        this.csllAliquot = null;
        this.csllWithheld = null;
        this.csllValue = null;
        this.cppAliquot = null;
        this.cppWithheld = null;
        this.cppValue = null;
        this.otherRetentionsValue = null;
        this.otherRetentionsWithheldValue = null;
        this.totalTaxAliquot = null;
        this.totalTaxValue = null;
        this.issValue = null;
        this.issAliquot = null;
        this.discountUnconditionedValue = null;
        this.discountConditionedValue = null;
    }

    public ElotechServiceValues(Builder builder) {
        this.serviceValue = builder.serviceValue;
        this.deductionValue = builder.deductionValue;
        this.pisAliquot = builder.pisAliquot;
        this.pisWithheld = builder.pisWithheld;
        this.pisValue = builder.pisValue;
        this.cofinsAliquot = builder.cofinsAliquot;
        this.cofinsWithheld = builder.cofinsWithheld;
        this.cofinsValue = builder.cofinsValue;
        this.inssAliquot = builder.inssAliquot;
        this.inssWithheld = builder.inssWithheld;
        this.inssValue = builder.inssValue;
        this.irAliquot = builder.irAliquot;
        this.irWithheld = builder.irWithheld;
        this.irValue = builder.irValue;
        this.csllAliquot = builder.csllAliquot;
        this.csllWithheld = builder.csllWithheld;
        this.csllValue = builder.csllValue;
        this.cppAliquot = builder.cppAliquot;
        this.cppWithheld = builder.cppWithheld;
        this.cppValue = builder.cppValue;
        this.otherRetentionsValue = builder.otherRetentionsValue;
        this.otherRetentionsWithheldValue = builder.otherRetentionsWithheldValue;
        this.totalTaxAliquot = builder.totalTaxAliquot;
        this.totalTaxValue = builder.totalTaxValue;
        this.issValue = builder.issValue;
        this.issAliquot = builder.issAliquot;
        this.discountUnconditionedValue = builder.discountUnconditionedValue;
        this.discountConditionedValue = builder.discountConditionedValue;
    }

    public String getServiceValue() {
        return serviceValue;
    }

    public String getDeductionValue() {
        return deductionValue;
    }

    public String getPisAliquot() {
        return pisAliquot;
    }

    public CommonsNFSeBoolean getPisWithheld() {
        return pisWithheld;
    }

    public String getPisValue() {
        return pisValue;
    }

    public String getCofinsAliquot() {
        return cofinsAliquot;
    }

    public CommonsNFSeBoolean getCofinsWithheld() {
        return cofinsWithheld;
    }

    public String getCofinsValue() {
        return cofinsValue;
    }

    public String getInssAliquot() {
        return inssAliquot;
    }

    public CommonsNFSeBoolean getInssWithheld() {
        return inssWithheld;
    }

    public String getInssValue() {
        return inssValue;
    }

    public String getIrAliquot() {
        return irAliquot;
    }

    public CommonsNFSeBoolean getIrWithheld() {
        return irWithheld;
    }

    public String getIrValue() {
        return irValue;
    }

    public String getCsllAliquot() {
        return csllAliquot;
    }

    public CommonsNFSeBoolean getCsllWithheld() {
        return csllWithheld;
    }

    public String getCsllValue() {
        return csllValue;
    }

    public String getCppAliquot() {
        return cppAliquot;
    }

    public CommonsNFSeBoolean getCppWithheld() {
        return cppWithheld;
    }

    public String getCppValue() {
        return cppValue;
    }

    public String getOtherRetentionsValue() {
        return otherRetentionsValue;
    }

    public String getOtherRetentionsWithheldValue() {
        return otherRetentionsWithheldValue;
    }

    public String getTotalTaxAliquot() {
        return totalTaxAliquot;
    }

    public String getTotalTaxValue() {
        return totalTaxValue;
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
