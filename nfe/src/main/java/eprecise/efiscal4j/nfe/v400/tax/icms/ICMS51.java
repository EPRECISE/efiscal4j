
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Max100;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


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
class ICMS51 extends BaseICMS implements IcmsWithValue {

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
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMS51.Builder) super.withOrigin(origin);
        }

        /**
         * @see BCModality
         */
        public Builder withBcModality(final BCModality bcModality) {
            this.bcModality = bcModality;
            return this;
        }

        /**
         * Percentual de redução da BC
         *
         * @param bcReductionPercent
         * @return
         */
        public Builder withBcReductionPercent(final String bcReductionPercent) {
            this.bcReductionPercent = bcReductionPercent;
            return this;
        }

        /**
         * Valor da BC do ICMS
         *
         * @param bcValue
         * @return
         */
        public Builder withBcValue(final String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Alíquota do ICMS
         */
        public Builder withIcmsAliquot(final String icmsAliquot) {
            this.icmsAliquot = icmsAliquot;
            return this;
        }

        /**
         * Valor do ICMS da Operação
         *
         * @param icmsOperationValue
         * @return
         */
        public Builder withIcmsOperationValue(final String icmsOperationValue) {
            this.icmsOperationValue = icmsOperationValue;
            return this;
        }

        /**
         * Percentual do diferimento
         *
         * @param deferralPercent
         * @return
         */
        public Builder withDeferralPercent(final String deferralPercent) {
            this.deferralPercent = deferralPercent;
            return this;
        }

        /**
         *
         * @param icmsDeferralValue
         * @return
         */
        public Builder withIcmsDeferralValue(final String icmsDeferralValue) {
            this.icmsDeferralValue = icmsDeferralValue;
            return this;
        }

        /**
         * Valor do ICMS
         */
        public Builder withIcmsValue(final String icmsValue) {
            this.icmsValue = icmsValue;
            return this;
        }

        @Override
        public ICMS51 build() {
            return new ICMS51(this);
        }

    }

    public ICMS51() {
        this.bcModality = null;
        this.bcReductionPercent = null;
        this.bcValue = null;
        this.icmsAliquot = null;
        this.icmsOperationValue = null;
        this.deferralPercent = null;
        this.icmsDeferralValue = null;
        this.icmsValue = null;
    }

    protected ICMS51(final ICMS51.Builder builder) {
        super(builder.origin, "51");
        this.bcModality = builder.bcModality;
        this.bcReductionPercent = builder.bcReductionPercent;
        this.bcValue = builder.bcValue;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsOperationValue = builder.icmsOperationValue;
        this.deferralPercent = builder.deferralPercent;
        this.icmsDeferralValue = builder.icmsDeferralValue;
        this.icmsValue = builder.icmsValue;
    }

    public BCModality getBcModality() {
        return this.bcModality;
    }

    public String getBcReductionPercent() {
        return this.bcReductionPercent;
    }

    public String getBcValue() {
        return this.bcValue;
    }

    public String getIcmsAliquot() {
        return this.icmsAliquot;
    }

    public String getIcmsOperationValue() {
        return this.icmsOperationValue;
    }

    public String getDeferralPercent() {
        return this.deferralPercent;
    }

    public String getIcmsDeferralValue() {
        return this.icmsDeferralValue;
    }

    @Override
    public String getIcmsValue() {
        return this.icmsValue;
    }
}
