
package eprecise.efiscal4j.nfe.tax.ipi;

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
        public Builder withBcValue(String bcValue) {
            return (IPI50.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiAliquot(String ipiAliquot) {
            return (IPI50.Builder) super.withIpiAliquot(ipiAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUnityQuantity(String unityQuantity) {
            return (IPI50.Builder) super.withUnityQuantity(unityQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUnityValue(String unityValue) {
            return (IPI50.Builder) super.withUnityValue(unityValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiValue(String ipiValue) {
            return (IPI50.Builder) super.withIpiValue(ipiValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiFrameworkClass(String ipiFramework) {
            return (IPI50.Builder) super.withIpiFrameworkClass(ipiFramework);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(String producerCNPJ) {
            return (IPI50.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(String ipiSealCode) {
            return (IPI50.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(String ipiSealQuantity) {
            return (IPI50.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(String legalFramework) {
            return (IPI50.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI50 build() {
            return new IPI50(this);
        }
    }

    protected IPI50() {
        super();
    }

    protected IPI50(IPI50.Builder builder) {
        super(builder, "50");
    }

}
