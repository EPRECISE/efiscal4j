
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 75 - Operação de Aquisição por Substituição Tributária
 * 
 * @see BaseCOFINSOther
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS75 extends BaseCOFINSOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSOther.Builder implements COFINSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (COFINS75.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) {
            return (COFINS75.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (COFINS75.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(String cofinsAliquot) {
            return (COFINS75.Builder) super.withCofinsAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(String cofinsValue) {
            return (COFINS75.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS75 build() {
            return new COFINS75(this);
        }
    }

    protected COFINS75() {
        super(null, null);
    }

    protected COFINS75(COFINS75.Builder builder) {
        super(builder, "75");
    }

}
