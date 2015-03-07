
package eprecise.efiscal4j.nfe.tax.pis;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo PIS 55 - Operação com Direito a Crédito - Vinculada a Receitas Não-Tributadas no Mercado Interno e de Exportação
 * 
 * @see BasePISOther
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PIS55 extends BasePISOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BasePISOther.Builder implements PISBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (PIS55.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(String productAliquot) {
            return (PIS55.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(String productQuantity) {
            return (PIS55.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisAliquot(String pisAliquot) {
            return (PIS55.Builder) super.withPisAliquot(pisAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withPisValue(String pisValue) {
            return (PIS55.Builder) super.withPisValue(pisValue);
        }

        @Override
        public PIS55 build() {
            return new PIS55(this);
        }
    }

    protected PIS55() {
        super(null, null);
    }

    protected PIS55(PIS55.Builder builder) {
        super(builder, "55");
    }

}
