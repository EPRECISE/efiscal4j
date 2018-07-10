
package eprecise.efiscal4j.nfe.v400.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo COFINS 67 - Crédito Presumido - Outras Operações
 * 
 * @see BaseCOFINSOther
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class COFINS67 extends BaseCOFINSOther {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseCOFINSOther.Builder implements COFINSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (COFINS67.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductAliquot(final String productAliquot) {
            return (COFINS67.Builder) super.withProductAliquot(productAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProductQuantity(final String productQuantity) {
            return (COFINS67.Builder) super.withProductQuantity(productQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsAliquot(final String cofinsAliquot) {
            return (COFINS67.Builder) super.withCofinsAliquot(cofinsAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withCofinsValue(final String cofinsValue) {
            return (COFINS67.Builder) super.withCofinsValue(cofinsValue);
        }

        @Override
        public COFINS67 build() {
            return new COFINS67(this);
        }
    }

    public COFINS67() {
        super();
    }

    protected COFINS67(final COFINS67.Builder builder) {
        super(builder, "67");
    }

}
