
package eprecise.efiscal4j.nfe.v310.tax.cofins;

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
        public Builder withProductQuantity(final String productQuantity) {
            return (COFINS03.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(final String cofinsAliquot) {
            return (COFINS03.Builder) super.withProductAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(final String cofinsValue) {
            return (COFINS03.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS03 build() {
            return new COFINS03(this);
        }
    }

    public COFINS03() {
        super();
    }

    protected COFINS03(final COFINS03.Builder builder) {
        super(builder, "03");
    }

}
