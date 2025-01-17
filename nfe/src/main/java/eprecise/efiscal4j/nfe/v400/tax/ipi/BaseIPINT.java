
package eprecise.efiscal4j.nfe.v400.tax.ipi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Classe base para os IPI com CST não tributados (01, 02, 03, 04, 05, 51, 52, 53, 54 e 55)
 * 
 * @see BaseIPI
 * @see IPI
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BaseIPINT extends BaseIPI {

    private static final long serialVersionUID = 1L;

    static abstract class Builder extends BaseIPI.Builder {

        @Override
        abstract BaseIPINT build();


        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withProducerCNPJ(final String producerCNPJ) {
            return (BaseIPINT.Builder) super.withProducerCNPJ(producerCNPJ);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealCode(final String ipiSealCode) {
            return (BaseIPINT.Builder) super.withIpiSealCode(ipiSealCode);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIpiSealQuantity(final String ipiSealQuantity) {
            return (BaseIPINT.Builder) super.withIpiSealQuantity(ipiSealQuantity);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withLegalFramework(final String legalFramework) {
            return (BaseIPINT.Builder) super.withLegalFramework(legalFramework);
        }
    }

    public BaseIPINT() {
    }

    protected BaseIPINT(final Builder builder, final String cst) {
        super(builder, cst);
    }

    @Override
    public String getIpiValue() {
        return "0";
    }
}
