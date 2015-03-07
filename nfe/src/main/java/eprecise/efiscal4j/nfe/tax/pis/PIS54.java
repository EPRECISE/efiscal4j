
package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 54 - Operação com Direito a Crédito - Vinculada a Receitas Tributadas no Mercado Interno e de Exportação
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS54 extends BasePISOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISOther.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (PIS54.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) {
            return (PIS54.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (PIS54.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(String pisAliquot) {
            return (PIS54.Builder) super.withPisAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS54.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS54 build() {
            return new PIS54(this);
        }
    }

    protected PIS54() {
        super(null, null);
    }

    protected PIS54(PIS54.Builder builder) {
        super(builder, "54");
    }

}
