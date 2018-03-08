
package eprecise.efiscal4j.nfse.domain.tax;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeTax implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BigDecimal bcValue;

    private final BigDecimal issAliquot;

    private final BigDecimal issValue;

    private final BigDecimal pisAliquot;

    private final boolean pisWithheld;

    private final BigDecimal pisValue;

    private final BigDecimal cofinsAliquot;

    private final boolean cofinsWithheld;

    private final BigDecimal cofinsValue;

    private final BigDecimal inssAliquot;

    private final boolean inssWithheld;

    private final BigDecimal inssValue;

    private final BigDecimal irAliquot;

    private final boolean irWithheld;

    private final BigDecimal irValue;

    private final BigDecimal csllAliquot;

    private final boolean csllWithheld;

    private final BigDecimal csllValue;

    private final BigDecimal cppAliquot;

    private final boolean cppWithheld;

    private final BigDecimal cppValue;

    private final BigDecimal issRetentionValue;

    private final BigDecimal otherRetentionsValue;

    public static class Builder {

        private BigDecimal bcValue;

        private BigDecimal issAliquot;

        private BigDecimal issValue;

        private BigDecimal pisAliquot;

        private boolean pisWithheld;

        private BigDecimal pisValue;

        private BigDecimal cofinsAliquot;

        private boolean cofinsWithheld;

        private BigDecimal cofinsValue;

        private BigDecimal inssAliquot;

        private boolean inssWithheld;

        private BigDecimal inssValue;

        private BigDecimal irAliquot;

        private boolean irWithheld;

        private BigDecimal irValue;

        private BigDecimal csllAliquot;

        private boolean csllWithheld;

        private BigDecimal csllValue;

        private BigDecimal cppAliquot;

        private boolean cppWithheld;

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

        public Builder withPisAliquot(final BigDecimal pisAliquot) {
            this.pisAliquot = pisAliquot;
            return this;
        }

        public Builder withPisWithheld(final boolean pisWithheld) {
            this.pisWithheld = pisWithheld;
            return this;
        }

        public Builder withPisValue(final BigDecimal pisValue) {
            this.pisValue = pisValue;
            return this;
        }

        public Builder withCofinsAliquot(final BigDecimal cofinsAliquot) {
            this.cofinsAliquot = cofinsAliquot;
            return this;
        }

        public Builder withCofinsWithheld(final boolean cofinsWithheld) {
            this.cofinsWithheld = cofinsWithheld;
            return this;
        }

        public Builder withCofinsValue(final BigDecimal cofinsValue) {
            this.cofinsValue = cofinsValue;
            return this;
        }

        public Builder withInssAliquot(final BigDecimal inssAliquot) {
            this.inssAliquot = inssAliquot;
            return this;
        }

        public Builder withInssWithheld(final boolean inssWithheld) {
            this.inssWithheld = inssWithheld;
            return this;
        }

        public Builder withInssValue(final BigDecimal inssValue) {
            this.inssValue = inssValue;
            return this;
        }

        public Builder withIrAliquot(final BigDecimal irAliquot) {
            this.irAliquot = irAliquot;
            return this;
        }

        public Builder withIrWithheld(final boolean irWithheld) {
            this.irWithheld = irWithheld;
            return this;
        }

        public Builder withIrValue(final BigDecimal irValue) {
            this.irValue = irValue;
            return this;
        }

        public Builder withCsllAliquot(final BigDecimal csllAliquot) {
            this.csllAliquot = csllAliquot;
            return this;
        }

        public Builder withCsllWithheld(final boolean csllWithheld) {
            this.csllWithheld = csllWithheld;
            return this;
        }

        public Builder withCsllValue(final BigDecimal csllValue) {
            this.csllValue = csllValue;
            return this;
        }

        public Builder withCppAliquot(final BigDecimal cppAliquot) {
            this.cppAliquot = cppAliquot;
            return this;
        }

        public Builder withCppWithheld(final boolean cppWithheld) {
            this.cppWithheld = cppWithheld;
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

    public BigDecimal getTotal() {
        return Optional.ofNullable(getIssValue()).orElse(BigDecimal.ZERO).add(Optional.ofNullable(getPisValue()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(getCofinsValue()).orElse(BigDecimal.ZERO)).add(Optional.ofNullable(getInssValue()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(getIrValue()).orElse(BigDecimal.ZERO)).add(Optional.ofNullable(getCsllValue()).orElse(BigDecimal.ZERO))
                .add(Optional.ofNullable(getCppValue()).orElse(BigDecimal.ZERO)).add(Optional.ofNullable(getOtherRetentionsValue()).orElse(BigDecimal.ZERO));
    }

    public NFSeTax() {
        bcValue = null;
        issAliquot = null;
        issValue = null;
        pisAliquot = null;
        pisWithheld = false;
        pisValue = null;
        cofinsAliquot = null;
        cofinsWithheld = false;
        cofinsValue = null;
        inssAliquot = null;
        inssWithheld = false;
        inssValue = null;
        irAliquot = null;
        irWithheld = false;
        irValue = null;
        csllAliquot = null;
        csllWithheld = false;
        csllValue = null;
        cppAliquot = null;
        cppWithheld = false;
        cppValue = null;
        issRetentionValue = null;
        otherRetentionsValue = null;
    }

    public NFSeTax(final Builder builder) {
        bcValue = builder.bcValue;
        issAliquot = builder.issAliquot;
        issValue = builder.issValue;
        pisAliquot = builder.pisAliquot;
        pisWithheld = builder.pisWithheld;
        pisValue = builder.pisValue;
        cofinsAliquot = builder.cofinsAliquot;
        cofinsWithheld = builder.cofinsWithheld;
        cofinsValue = builder.cofinsValue;
        inssAliquot = builder.inssAliquot;
        inssWithheld = builder.inssWithheld;
        inssValue = builder.inssValue;
        irAliquot = builder.irAliquot;
        irWithheld = builder.irWithheld;
        irValue = builder.irValue;
        csllAliquot = builder.csllAliquot;
        csllWithheld = builder.csllWithheld;
        csllValue = builder.csllValue;
        cppAliquot = builder.cppAliquot;
        cppWithheld = builder.cppWithheld;
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

    public BigDecimal getPisAliquot() {
        return pisAliquot;
    }

    public boolean isPisWithheld() {
        return pisWithheld;
    }

    public BigDecimal getPisValue() {
        return pisValue;
    }

    public BigDecimal getCofinsAliquot() {
        return cofinsAliquot;
    }

    public boolean isCofinsWithheld() {
        return cofinsWithheld;
    }

    public BigDecimal getCofinsValue() {
        return cofinsValue;
    }

    public BigDecimal getInssAliquot() {
        return inssAliquot;
    }

    public boolean isInssWithheld() {
        return inssWithheld;
    }

    public BigDecimal getInssValue() {
        return inssValue;
    }

    public BigDecimal getIrAliquot() {
        return irAliquot;
    }

    public boolean isIrWithheld() {
        return irWithheld;
    }

    public BigDecimal getIrValue() {
        return irValue;
    }

    public BigDecimal getCsllAliquot() {
        return csllAliquot;
    }

    public boolean isCsllWithheld() {
        return csllWithheld;
    }

    public BigDecimal getCsllValue() {
        return csllValue;
    }

    public BigDecimal getCppAliquot() {
        return cppAliquot;
    }

    public boolean isCppWithheld() {
        return cppWithheld;
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
