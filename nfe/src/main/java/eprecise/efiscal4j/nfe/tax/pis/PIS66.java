
package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 66 - Crédito Presumido - Operação de Aquisição Vinculada a Receitas Tributadas e Não-Tributadas no Mercado Interno, e de Exportação
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class PIS66 extends BasePISOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISOther.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (PIS66.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) {
            return (PIS66.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (PIS66.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(String pisAliquot) {
            return (PIS66.Builder) super.withPisAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS66.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS66 build() {
            return new PIS66(this);
        }
    }

    protected PIS66() {
        super(null, null);
    }

    protected PIS66(PIS66.Builder builder) {
        super(builder, "66");
    }

}
