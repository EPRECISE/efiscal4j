
package eprecise.efiscal4j.nfe.v310.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 99 - Outras Operações
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS99 extends BasePISOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISOther.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (PIS99.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(final String productAliquot) {
            return (PIS99.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(final String productQuantity) {
            return (PIS99.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(final String pisAliquot) {
            return (PIS99.Builder) super.withPisAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(final String pisValue) {
            return (PIS99.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS99 build() {
            return new PIS99(this);
        }
    }

    public PIS99() {
    }

    protected PIS99(final PIS99.Builder builder) {
        super(builder, "99");
    }

}
