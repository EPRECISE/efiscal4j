
package eprecise.efiscal4j.nfse.domain.tax;

import java.io.Serializable;
import java.math.BigDecimal;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeTax implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BigDecimal bcValue;

    private final BigDecimal issAliquot;

    private final BigDecimal issValue;

    private final BigDecimal pisValue;

    private final BigDecimal cofinsValue;

    private final BigDecimal inssValue;

    private final BigDecimal irValue;

    private final BigDecimal csllValue;

    private final BigDecimal cppValue;

    private final BigDecimal issRetentionValue;

    private final BigDecimal otherRetentionsValue;

    public static class Builder {

        private BigDecimal bcValue;

        private BigDecimal issAliquot;

        private BigDecimal issValue;

        private BigDecimal pisValue;

        private BigDecimal cofinsValue;

        private BigDecimal inssValue;

        private BigDecimal irValue;

        private BigDecimal csllValue;

        private BigDecimal cppValue;

        private BigDecimal issRetentionValue;

        private BigDecimal otherRetentionsValue;

        public Builder withBcValue(final BigDecimal bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        public Builder withIssAliquot(final BigDecimal issAliquot) {
            this.issAliquot = issAliquot;
            return this;
        }

        public Builder withIssValue(final BigDecimal issValue) {
            this.issValue = issValue;
            return this;
        }

        public Builder withPisValue(final BigDecimal pisValue) {
            this.pisValue = pisValue;
            return this;
        }

        public Builder withCofinsValue(final BigDecimal cofinsValue) {
            this.cofinsValue = cofinsValue;
            return this;
        }

        public Builder withInssValue(final BigDecimal inssValue) {
            this.inssValue = inssValue;
            return this;
        }

        public Builder withIrValue(final BigDecimal irValue) {
            this.irValue = irValue;
            return this;
        }

        public Builder withCsllValue(final BigDecimal csllValue) {
            this.csllValue = csllValue;
            return this;
        }

        public Builder withCppValue(final BigDecimal cppValue) {
            this.cppValue = cppValue;
            return this;
        }

        public Builder withIssRetentionValue(final BigDecimal issRetentionValue) {
            this.issRetentionValue = issRetentionValue;
            return this;
        }

        public Builder withOtherRetentionsValue(final BigDecimal otherRetentionsValue) {
            this.otherRetentionsValue = otherRetentionsValue;
            return this;
        }

        public NFSeTax build() {
            final NFSeTax entity = new NFSeTax(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeTax() {
        bcValue = null;
        issAliquot = null;
        issValue = null;
        pisValue = null;
        cofinsValue = null;
        inssValue = null;
        irValue = null;
        csllValue = null;
        cppValue = null;
        issRetentionValue = null;
        otherRetentionsValue = null;
    }

    public NFSeTax(final Builder builder) {
        bcValue = builder.bcValue;
        issAliquot = builder.issAliquot;
        issValue = builder.issValue;
        pisValue = builder.pisValue;
        cofinsValue = builder.cofinsValue;
        inssValue = builder.inssValue;
        irValue = builder.irValue;
        csllValue = builder.csllValue;
        cppValue = builder.cppValue;
        issRetentionValue = builder.issRetentionValue;
        otherRetentionsValue = builder.otherRetentionsValue;
    }

    public BigDecimal getBcValue() {
        return bcValue;
    }

    public BigDecimal getIssAliquot() {
        return issAliquot;
    }

    public BigDecimal getIssValue() {
        return issValue;
    }

    public BigDecimal getPisValue() {
        return pisValue;
    }

    public BigDecimal getCofinsValue() {
        return cofinsValue;
    }

    public BigDecimal getInssValue() {
        return inssValue;
    }

    public BigDecimal getIrValue() {
        return irValue;
    }

    public BigDecimal getCsllValue() {
        return csllValue;
    }

    public BigDecimal getCppValue() {
        return cppValue;
    }

    public BigDecimal getIssRetentionValue() {
        return issRetentionValue;
    }

    public BigDecimal getOtherRetentionsValue() {
        return otherRetentionsValue;
    }

}
