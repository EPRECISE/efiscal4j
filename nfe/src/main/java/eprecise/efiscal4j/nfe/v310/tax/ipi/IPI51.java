
package eprecise.efiscal4j.nfe.v310.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo IPI 51 – Saída tributada com alíquota zero
 * 
 * @see BaseIPITrib
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class IPI51 extends BaseIPINT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseIPINT.Builder implements IPIBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiFrameworkClass(final String ipiFramework) {
            return (IPI51.Builder) super.withIpiFrameworkClass(ipiFramework);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(final String producerCNPJ) {
            return (IPI51.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(final String ipiSealCode) {
            return (IPI51.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(final String ipiSealQuantity) {
            return (IPI51.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(final String legalFramework) {
            return (IPI51.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI51 build() {
            return new IPI51(this);
        }
    }

    public IPI51() {
    }

    protected IPI51(final IPI51.Builder builder) {
        super(builder, "51");
    }
}
