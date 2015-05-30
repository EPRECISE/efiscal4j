
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 98 - Outras Operações de Entrada
 * 
 * @see BaseCOFINSOther
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS98 extends BaseCOFINSOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSOther.Builder implements COFINSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (COFINS98.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) {
            return (COFINS98.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (COFINS98.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(String cofinsAliquot) {
            return (COFINS98.Builder) super.withCofinsAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(String cofinsValue) {
            return (COFINS98.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS98 build() {
            return new COFINS98(this);
        }
    }

    protected COFINS98() {
        super();
    }

    protected COFINS98(COFINS98.Builder builder) {
        super(builder, "98");
    }

}
