
package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 03 - Operação Tributável - Base de Calculo = Quantidade Vendida x Alíquota por Unidade de Produto
 * 
 * @see BasePISQuantity
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS03 extends BasePISQuantity {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISQuantity.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (PIS03.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String pisAliquot) {
            return (PIS03.Builder) super.withProductAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS03.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS03 build() {
            return new PIS03(this);
        }
    }

    protected PIS03() {
        super(null, null);
    }

    protected PIS03(PIS03.Builder builder) {
        super(builder, "03");
    }

}
