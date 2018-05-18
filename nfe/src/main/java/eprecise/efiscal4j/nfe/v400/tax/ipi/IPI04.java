
package eprecise.efiscal4j.nfe.v400.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo IPI 04 – Entrada imune
 * 
 * @see BaseIPITrib
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class IPI04 extends BaseIPINT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseIPINT.Builder implements IPIBuilder {


        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(final String producerCNPJ) {
            return (IPI04.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(final String ipiSealCode) {
            return (IPI04.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(final String ipiSealQuantity) {
            return (IPI04.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(final String legalFramework) {
            return (IPI04.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI04 build() {
            return new IPI04(this);
        }
    }

    public IPI04() {
    }

    protected IPI04(final IPI04.Builder builder) {
        super(builder, "04");
    }
}
