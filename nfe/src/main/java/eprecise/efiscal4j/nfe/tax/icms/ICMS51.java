
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Max100;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributção pelo ICMS 51 - Diferimento
 *
 * A exigência do preenchimento das informações do ICMS diferido fica à critério de cada UF.
 *
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS51 extends BaseICMS {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBC") final BCModality bcModality;

    private @XmlElement(name = "pRedBC") @NFeDecimal0302a04 final String bcReductionPercent;

    private @XmlElement(name = "vBC") @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pICMS") @NFeDecimal0302a04 final String icmsAliquot;

    private @XmlElement(name = "vICMSOp") @NFeDecimal1302 final String icmsOperationValue;

    private @XmlElement(name = "pDif") @NFeDecimal0302a04Max100 final String deferralPercent;

    private @XmlElement(name = "vICMSDif") @NFeDecimal1302 final String icmsDeferralValue;

    private @XmlElement(name = "vICMS") @NFeDecimal1302 final String icmsValue;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private BCModality bcModality;

        private String bcReductionPercent;

        private String bcValue;

        private String icmsAliquot;

        private String icmsOperationValue;

        private String deferralPercent;

        private String icmsDeferralValue;

        private String icmsValue;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(ProductOrigin origin) {
            return (ICMS51.Builder) super.withOrigin(origin);
        }

        /**
         * @see BCModality
         */
        public Builder withBcModality(BCModality bcModality) {
            this.bcModality = bcModality;
            return this;
        }

        /**
         * Percentual de redução da BC
         *
         * @param bcReductionPercent
         * @return
         */
        public Builder withBcReductionPercent(String bcReductionPercent) {
            this.bcReductionPercent = bcReductionPercent;
            return this;
        }

        /**
         * Valor da BC do ICMS
         *
         * @param bcValue
         * @return
         */
        public Builder withBcValue(String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Alíquota do ICMS
         */
        public Builder withIcmsAliquot(String icmsAliquot) {
            this.icmsAliquot = icmsAliquot;
            return this;
        }

        /**
         * Valor do ICMS da Operação
         *
         * @param icmsOperationValue
         * @return
         */
        public Builder withIcmsOperationValue(String icmsOperationValue) {
            this.icmsOperationValue = icmsOperationValue;
            return this;
        }

        /**
         * Percentual do diferimento
         *
         * @param deferralPercent
         * @return
         */
        public Builder withDeferralPercent(String deferralPercent) {
            this.deferralPercent = deferralPercent;
            return this;
        }

        /**
         *
         * @param icmsDeferralValue
         * @return
         */
        public Builder withIcmsDeferralValue(String icmsDeferralValue) {
            this.icmsDeferralValue = icmsDeferralValue;
            return this;
        }

        /**
         * Valor do ICMS
         */
        public Builder withIcmsValue(String icmsValue) {
            this.icmsValue = icmsValue;
            return this;
        }

        @Override
        public ICMS51 build() {
            return new ICMS51(this);
        }

    }

    protected ICMS51() {
        super(null, null);
        bcModality = null;
        bcReductionPercent = null;
        bcValue = null;
        icmsAliquot = null;
        icmsOperationValue = null;
        deferralPercent = null;
        icmsDeferralValue = null;
        icmsValue = null;
    }

    protected ICMS51(ICMS51.Builder builder) {
        super(builder.origin, "51");
        bcModality = builder.bcModality;
        bcReductionPercent = builder.bcReductionPercent;
        bcValue = builder.bcValue;
        icmsAliquot = builder.icmsAliquot;
        icmsOperationValue = builder.icmsOperationValue;
        deferralPercent = builder.deferralPercent;
        icmsDeferralValue = builder.icmsDeferralValue;
        icmsValue = builder.icmsValue;
    }

    public BCModality getBcModality() {
        return bcModality;
    }

    public String getBcReductionPercent() {
        return bcReductionPercent;
    }

    public String getBcValue() {
        return bcValue;
    }

    public String getIcmsAliquot() {
        return icmsAliquot;
    }

    public String getIcmsOperationValue() {
        return icmsOperationValue;
    }

    public String getDeferralPercent() {
        return deferralPercent;
    }

    public String getIcmsDeferralValue() {
        return icmsDeferralValue;
    }

    public String getIcmsValue() {
        return icmsValue;
    }
}
