
package eprecise.efiscal4j.nfe.v400.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo IPI 00 – Entrada com recuperação de crédito
 * 
 * @see BaseIPITrib
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class IPI00 extends BaseIPITrib {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseIPITrib.Builder implements IPIBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValue(final String bcValue) {
            return (IPI00.Builder) super.withBcValue(bcValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiAliquot(final String ipiAliquot) {
            return (IPI00.Builder) super.withIpiAliquot(ipiAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUnityQuantity(final String unityQuantity) {
            return (IPI00.Builder) super.withUnityQuantity(unityQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withUnityValue(final String unityValue) {
            return (IPI00.Builder) super.withUnityValue(unityValue);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiValue(final String ipiValue) {
            return (IPI00.Builder) super.withIpiValue(ipiValue);
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(final String producerCNPJ) {
            return (IPI00.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(final String ipiSealCode) {
            return (IPI00.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(final String ipiSealQuantity) {
            return (IPI00.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(final String legalFramework) {
            return (IPI00.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI00 build() {
            return new IPI00(this);
        }
    }

    public IPI00() {
    }

    protected IPI00(final IPI00.Builder builder) {
        super(builder, "00");
    }

}
