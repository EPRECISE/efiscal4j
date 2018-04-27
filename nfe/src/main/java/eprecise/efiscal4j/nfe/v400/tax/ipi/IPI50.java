
package eprecise.efiscal4j.nfe.v400.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo IPI 50 – Saída tributada
 * 
 * @see BaseIPITrib
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class IPI50 extends BaseIPITrib {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseIPITrib.Builder implements IPIBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (IPI50.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiAliquot(final String ipiAliquot) {
            return (IPI50.Builder) super.withIpiAliquot(ipiAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUnityQuantity(final String unityQuantity) {
            return (IPI50.Builder) super.withUnityQuantity(unityQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUnityValue(final String unityValue) {
            return (IPI50.Builder) super.withUnityValue(unityValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiValue(final String ipiValue) {
            return (IPI50.Builder) super.withIpiValue(ipiValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiFrameworkClass(final String ipiFramework) {
            return (IPI50.Builder) super.withIpiFrameworkClass(ipiFramework);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(final String producerCNPJ) {
            return (IPI50.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(final String ipiSealCode) {
            return (IPI50.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(final String ipiSealQuantity) {
            return (IPI50.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(final String legalFramework) {
            return (IPI50.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI50 build() {
            return new IPI50(this);
        }
    }

    public IPI50() {
    }

    protected IPI50(final IPI50.Builder builder) {
        super(builder, "50");
    }

}
