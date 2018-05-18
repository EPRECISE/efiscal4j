
package eprecise.efiscal4j.nfe.v400.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo IPI 03 – Entrada não-tributada
 * 
 * @see BaseIPITrib
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class IPI03 extends BaseIPINT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseIPINT.Builder implements IPIBuilder {


        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(final String producerCNPJ) {
            return (IPI03.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(final String ipiSealCode) {
            return (IPI03.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(final String ipiSealQuantity) {
            return (IPI03.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(final String legalFramework) {
            return (IPI03.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI03 build() {
            return new IPI03(this);
        }
    }

    public IPI03() {
    }

    protected IPI03(final IPI03.Builder builder) {
        super(builder, "03");
    }
}
