
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=202 - Tributada pelo Simples Nacional sem permissão de crédito e com cobrança do ICMS por Substituição Tributária
 * 
 * @see BaseICMSSN202
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN202 extends BaseICMSSN202 {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSSN202.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(ProductOrigin origin) {
            return (ICMSSN202.Builder) super.withOrigin(origin);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcModalityST(BCModalityST bcModalityST) {
            return (ICMSSN202.Builder) super.withBcModalityST(bcModalityST);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withValueMarginAddedStPercent(String valueMarginAddedStPercent) {
            return (ICMSSN202.Builder) super.withValueMarginAddedStPercent(valueMarginAddedStPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcReductionStPercent(String bcReductionStPercent) {
            return (ICMSSN202.Builder) super.withBcReductionStPercent(bcReductionStPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValueST(String bcValueST) {
            return (ICMSSN202.Builder) super.withBcValueST(bcValueST);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsStAliquot(String icmsStAliquot) {
            return (ICMSSN202.Builder) super.withIcmsStAliquot(icmsStAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsStValue(String icmsStValue) {
            return (ICMSSN202.Builder) super.withIcmsStValue(icmsStValue);
        }

        @Override
        public ICMSSN202 build() {
            return new ICMSSN202(this);
        }

    }

    protected ICMSSN202() {
        super(null, null);
    }

    protected ICMSSN202(ICMSSN202.Builder builder) {
        super(builder, "202");
    }
}
