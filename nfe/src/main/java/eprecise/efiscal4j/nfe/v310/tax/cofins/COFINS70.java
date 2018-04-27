
package eprecise.efiscal4j.nfe.v310.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 70 - Operação de Aquisição sem Direito a Crédito
 * 
 * @see BaseCOFINSOther
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS70 extends BaseCOFINSOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSOther.Builder implements COFINSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (COFINS70.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(final String productAliquot) {
            return (COFINS70.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(final String productQuantity) {
            return (COFINS70.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(final String cofinsAliquot) {
            return (COFINS70.Builder) super.withCofinsAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(final String cofinsValue) {
            return (COFINS70.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS70 build() {
            return new COFINS70(this);
        }
    }

    public COFINS70() {
        super();
    }

    protected COFINS70(final COFINS70.Builder builder) {
        super(builder, "70");
    }

}
