
package eprecise.efiscal4j.nfe.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo IPI 52 – Saída isenta
 * 
 * @see BaseIPITrib
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class IPI52 extends BaseIPINT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseIPINT.Builder implements IPIBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiFrameworkClass(String ipiFramework) {
            return (IPI52.Builder) super.withIpiFrameworkClass(ipiFramework);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(String producerCNPJ) {
            return (IPI52.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(String ipiSealCode) {
            return (IPI52.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(String ipiSealQuantity) {
            return (IPI52.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(String legalFramework) {
            return (IPI52.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI52 build() {
            return new IPI52(this);
        }
    }

    protected IPI52() {
        super();
    }

    protected IPI52(IPI52.Builder builder) {
        super(builder, "52");
    }
}
