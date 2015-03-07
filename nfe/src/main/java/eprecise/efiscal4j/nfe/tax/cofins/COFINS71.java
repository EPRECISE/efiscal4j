
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 71 - Operação de Aquisição com Isenção
 * 
 * @see BaseCOFINSOther
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS71 extends BaseCOFINSOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSOther.Builder implements COFINSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (COFINS71.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) {
            return (COFINS71.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (COFINS71.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(String cofinsAliquot) {
            return (COFINS71.Builder) super.withCofinsAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(String cofinsValue) {
            return (COFINS71.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS71 build() {
            return new COFINS71(this);
        }
    }

    protected COFINS71() {
        super(null, null);
    }

    protected COFINS71(COFINS71.Builder builder) {
        super(builder, "71");
    }

}
