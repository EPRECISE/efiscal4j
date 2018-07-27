
package eprecise.efiscal4j.nfe.v400.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação pelo IPI 55 – Saída com suspensão
 * 
 * @see BaseIPITrib
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IPI55 extends BaseIPINT {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseIPINT.Builder implements IPIBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(final String producerCNPJ) {
            return (IPI55.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(final String ipiSealCode) {
            return (IPI55.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(final String ipiSealQuantity) {
            return (IPI55.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(final String legalFramework) {
            return (IPI55.Builder) super.withLegalFramework(legalFramework);
        }

        @Override
        public IPI55 build() {
            return new IPI55(this);
        }
    }

    public IPI55() {
    }

    protected IPI55(final IPI55.Builder builder) {
        super(builder, "55");
    }
}
