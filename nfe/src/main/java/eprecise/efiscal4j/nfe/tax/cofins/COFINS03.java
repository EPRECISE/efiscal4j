
package eprecise.efiscal4j.nfe.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 03 - Operação Tributável - Base de Calculo = Quantidade Vendida x Alíquota por Unidade de Produto
 * 
 * @see BaseCOFINSQuantity
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class COFINS03 extends BaseCOFINSQuantity {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSQuantity.Builder implements COFINSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (COFINS03.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String cofinsAliquot) {
            return (COFINS03.Builder) super.withProductAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(String cofinsValue) {
            return (COFINS03.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS03 build() {
            return new COFINS03(this);
        }
    }

    protected COFINS03() {
        super(null, null);
    }

    protected COFINS03(COFINS03.Builder builder) {
        super(builder, "03");
    }

}
