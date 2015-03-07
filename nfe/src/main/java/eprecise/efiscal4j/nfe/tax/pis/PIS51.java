
package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 51 - Operação com Direito a Crédito – Vinculada Exclusivamente a Receita Não Tributada no Mercado Interno
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS51 extends BasePISOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISOther.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (PIS51.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) {
            return (PIS51.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (PIS51.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(String pisAliquot) {
            return (PIS51.Builder) super.withPisAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS51.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS51 build() {
            return new PIS51(this);
        }
    }

    protected PIS51() {
        super(null, null);
    }

    protected PIS51(PIS51.Builder builder) {
        super(builder, "51");
    }

}
