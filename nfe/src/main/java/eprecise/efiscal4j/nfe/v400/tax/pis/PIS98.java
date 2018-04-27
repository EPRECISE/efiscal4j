
package eprecise.efiscal4j.nfe.v400.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 98 - Outras Operações de Entrada
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS98 extends BasePISOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISOther.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (PIS98.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(final String productAliquot) {
            return (PIS98.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(final String productQuantity) {
            return (PIS98.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(final String pisAliquot) {
            return (PIS98.Builder) super.withPisAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(final String pisValue) {
            return (PIS98.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS98 build() {
            return new PIS98(this);
        }
    }

    public PIS98() {
    }

    protected PIS98(final PIS98.Builder builder) {
        super(builder, "98");
    }

}
