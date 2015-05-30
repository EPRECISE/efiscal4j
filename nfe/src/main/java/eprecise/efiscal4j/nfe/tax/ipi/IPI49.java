
package eprecise.efiscal4j.nfe.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo IPI 49 – Outras entradas
 * 
 * @see BaseIPITrib
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class IPI49 extends BaseIPITrib {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseIPITrib.Builder implements IPIBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(String bcValue) {
            return (IPI49.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiAliquot(String ipiAliquot) {
            return (IPI49.Builder) super.withIpiAliquot(ipiAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUnityQuantity(String unityQuantity) {
            return (IPI49.Builder) super.withUnityQuantity(unityQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUnityValue(String unityValue) {
            return (IPI49.Builder) super.withUnityValue(unityValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiValue(String ipiValue) {
            return (IPI49.Builder) super.withIpiValue(ipiValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiFrameworkClass(String ipiFrameworkClass) {
            return (IPI49.Builder) super.withIpiFrameworkClass(ipiFrameworkClass);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(String producerCNPJ) {
            return (IPI49.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(String ipiSealCode) {
            return (IPI49.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(String ipiSealQuantity) {
            return (IPI49.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(String legalFramework) {
            return (IPI49.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI49 build() {
            return new IPI49(this);
        }
    }

    protected IPI49() {
        super();
    }

    protected IPI49(IPI49.Builder builder) {
        super(builder, "49");
    }

}
