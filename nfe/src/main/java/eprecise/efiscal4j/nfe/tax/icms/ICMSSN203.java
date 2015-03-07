
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL e CSOSN=203 - Isenção do ICMS nos Simples Nacional para faixa de receita bruta e com cobrança do ICMS por Substituição Tributária
 * 
 * @see BaseICMSSN202
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN203 extends BaseICMSSN202 {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMSSN202.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(ProductOrigin origin) {
            return (ICMSSN203.Builder) super.withOrigin(origin);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcModalityST(BCModalityST bcModalityST) {
            return (ICMSSN203.Builder) super.withBcModalityST(bcModalityST);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withValueMarginAddedStPercent(String valueMarginAddedStPercent) {
            return (ICMSSN203.Builder) super.withValueMarginAddedStPercent(valueMarginAddedStPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcReductionStPercent(String bcReductionStPercent) {
            return (ICMSSN203.Builder) super.withBcReductionStPercent(bcReductionStPercent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withBcValueST(String bcValueST) {
            return (ICMSSN203.Builder) super.withBcValueST(bcValueST);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsStAliquot(String icmsStAliquot) {
            return (ICMSSN203.Builder) super.withIcmsStAliquot(icmsStAliquot);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withIcmsStValue(String icmsStValue) {
            return (ICMSSN203.Builder) super.withIcmsStValue(icmsStValue);
        }

        @Override
        public ICMSSN203 build() {
            return new ICMSSN203(this);
        }

    }

    protected ICMSSN203() {
        super(null, null);
    }

    protected ICMSSN203(ICMSSN203.Builder builder) {
        super(builder, "203");
    }
}
