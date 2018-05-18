
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.tax.icms.desoneration.DesonerationGroup;
import eprecise.efiscal4j.nfe.v400.tax.icms.desoneration.ICMSDesonerationReason;
import eprecise.efiscal4j.nfe.v400.tax.icms.desoneration.ICMSDesonerationReason_3_9_12_Validation;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 70 - Com redução de base de cálculo e cobrança do ICMS por substituição tributária
 *
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@ICMSDesonerationReason_3_9_12_Validation
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS70 extends BaseICMS implements DesonerationGroup, IcmsWithValue, IcmsWithST {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

    private @XmlElement(name = "pRedBC") @NotNull @NFeDecimal0302a04 final String bcReductionPercent;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

    private @XmlElement(name = "vBCFCP") @NFeDecimal1302 final String bcFcpValue;

    private @XmlElement(name = "pFCP") @NFeDecimal0302a04Optional final String fcpAliquot;

    private @XmlElement(name = "vFCP") @NFeDecimal1302 final String fcpValue;

    private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalitySt;

    private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

    private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

    private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

    private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

    private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

    private @XmlElement(name = "vBCFCPST") @NFeDecimal1302 final String bcFcpValueST;

    private @XmlElement(name = "pFCPST") @NFeDecimal0302a04Optional final String fcpStAliquot;

    private @XmlElement(name = "vFCPST") @NFeDecimal1302 final String fcpStValue;

    private @XmlElement(name = "vICMSDeson") @NFeDecimal1302 final String icmsDesonerationValue;

    private @XmlElement(name = "motDesICMS") final ICMSDesonerationReason icmsDesonerationReason;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private BCModality bcModality;

        private String bcReductionPercent;

        private String bcValue;

        private String icmsAliquot;

        private String icmsValue;

        private String bcFcpValue;

        private String fcpAliquot;

        private String fcpValue;

        private BCModalityST bcModalitySt;

        private String valueMarginAddedStPercent;

        private String bcReductionStPercent;

        private String bcValueST;

        private String icmsStAliquot;

        private String icmsStValue;

        private String bcFcpValueST;

        private String fcpStAliquot;

        private String fcpStValue;

        private String icmsDesonerationValue;

        private ICMSDesonerationReason icmsDesonerationReason;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMS70.Builder) super.withOrigin(origin);
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
         * Valor do ICMS
         */
        public Builder withIcmsValue(final String icmsValue) {
            this.icmsValue = icmsValue;
            return this;
        }

        /**
         * Valor da Base de cálculo do FCP.
         * 
         * @param bcFcpValue
         */
        public Builder withBcFcpValue(final String bcFcpValue) {
            this.bcFcpValue = bcFcpValue;
            return this;
        }

        /**
         * Percentual de ICMS relativo ao Fundo de Combate à Pobreza (FCP)
         *
         * @param fcpAliquot
         * @return
         */
        public Builder withFcpAliquot(final String fcpAliquot) {
            this.fcpAliquot = fcpAliquot;
            return this;
        }

        /**
         * Valor do ICMS relativo ao Fundo de Combate à Pobreza (FCP)
         *
         * @param fcpValue
         * @return
         */
        public Builder withFcpValue(final String fcpValue) {
            this.fcpValue = fcpValue;
            return this;
        }

        /**
         * Percentual da Margem de Valor Adicionado ICMS ST
         */
        public Builder withValueMarginAddedStPercent(final String valueMarginAddedStPercent) {
            this.valueMarginAddedStPercent = valueMarginAddedStPercent;
            return this;
        }

        /**
         * Percentual de redução da BC ICMS ST
         */
        public Builder withBcReductionStPercent(final String bcReductionStPercent) {
            this.bcReductionStPercent = bcReductionStPercent;
            return this;
        }

        /**
         *
         * @see BCModalityST
         */
        public Builder withBcModalityST(final BCModalityST bcModalityST) {
            this.bcModalitySt = bcModalityST;
            return this;
        }

        /**
         * Valor da BC do ICMS ST
         */
        public Builder withBcValueST(final String bcValueST) {
            this.bcValueST = bcValueST;
            return this;
        }

        /**
         * Alíquota do ICMS ST
         */
        public Builder withIcmsStAliquot(final String icmsStAliquot) {
            this.icmsStAliquot = icmsStAliquot;
            return this;
        }

        /**
         * Valor do ICMS ST
         */
        public Builder withIcmsStValue(final String icmsStValue) {
            this.icmsStValue = icmsStValue;
            return this;
        }

        /**
         * Valor da Base de cálculo do FCP retido por substituicao tributaria
         * 
         * @param bcFcpValueST
         */
        public Builder withBcFcpValueST(final String bcFcpValueST) {
            this.bcFcpValueST = bcFcpValueST;
            return this;
        }

        /**
         * Percentual de FCP retido por substituição tributária
         *
         * @param fcpStAliquot
         * @return
         */
        public Builder withFcpStAliquot(final String fcpStAliquot) {
            this.fcpStAliquot = fcpStAliquot;
            return this;
        }

        /**
         * Valor do FCP retido por substituição tributária
         *
         * @param fcpStValue
         * @return
         */
        public Builder withFcpStValue(final String fcpStValue) {
            this.fcpStValue = fcpStValue;
            return this;
        }

        /**
         * Valor do ICMS de desoneração
         *
         * @param icmsDesonerationValue
         * @return
         */
        public Builder withIcmsDesonerationValue(final String icmsDesonerationValue) {
            this.icmsDesonerationValue = icmsDesonerationValue;
            return this;
        }

        /**
         * @see ICMSDesonerationReason
         * @param icmsDesonerationReason
         * @return
         */
        public Builder withIcmsDesonerationReason(final ICMSDesonerationReason icmsDesonerationReason) {
            this.icmsDesonerationReason = icmsDesonerationReason;
            return this;
        }

        @Override
        public ICMS70 build() {
            return new ICMS70(this);
        }
    }

    public ICMS70() {
        this.bcModality = null;
        this.bcReductionPercent = null;
        this.bcValue = null;
        this.icmsAliquot = null;
        this.icmsValue = null;
        this.bcFcpValue = null;
        this.fcpAliquot = null;
        this.fcpValue = null;
        this.bcModalitySt = null;
        this.valueMarginAddedStPercent = null;
        this.bcReductionStPercent = null;
        this.bcValueST = null;
        this.icmsStAliquot = null;
        this.icmsStValue = null;
        this.bcFcpValueST = null;
        this.fcpStAliquot = null;
        this.fcpStValue = null;
        this.icmsDesonerationValue = null;
        this.icmsDesonerationReason = null;
    }

    protected ICMS70(final ICMS70.Builder builder) {
        super(builder.origin, "70");
        this.bcModality = builder.bcModality;
        this.bcReductionPercent = builder.bcReductionPercent;
        this.bcValue = builder.bcValue;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsValue = builder.icmsValue;
        this.bcFcpValue = builder.bcFcpValue;
        this.fcpAliquot = builder.fcpAliquot;
        this.fcpValue = builder.fcpValue;
        this.bcModalitySt = builder.bcModalitySt;
        this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
        this.bcReductionStPercent = builder.bcReductionStPercent;
        this.bcValueST = builder.bcValueST;
        this.icmsStAliquot = builder.icmsStAliquot;
        this.icmsStValue = builder.icmsStValue;
        this.bcFcpValueST = builder.bcFcpValueST;
        this.fcpStAliquot = builder.fcpStAliquot;
        this.fcpStValue = builder.fcpStValue;
        this.icmsDesonerationValue = builder.icmsDesonerationValue;
        this.icmsDesonerationReason = builder.icmsDesonerationReason;
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

    public String getIcmsValue() {
        return icmsValue;
    }

    public String getBcFcpValue() {
        return bcFcpValue;
    }

    public String getFcpAliquot() {
        return fcpAliquot;
    }

    public String getFcpValue() {
        return fcpValue;
    }

    public BCModalityST getBcModalitySt() {
        return bcModalitySt;
    }

    public String getValueMarginAddedStPercent() {
        return valueMarginAddedStPercent;
    }

    public String getBcReductionStPercent() {
        return bcReductionStPercent;
    }

    public String getBcValueST() {
        return bcValueST;
    }

    public String getIcmsStAliquot() {
        return icmsStAliquot;
    }

    public String getIcmsStValue() {
        return icmsStValue;
    }

    public String getBcFcpValueST() {
        return bcFcpValueST;
    }

    public String getFcpStAliquot() {
        return fcpStAliquot;
    }

    public String getFcpStValue() {
        return fcpStValue;
    }

    public String getIcmsDesonerationValue() {
        return icmsDesonerationValue;
    }

    public ICMSDesonerationReason getIcmsDesonerationReason() {
        return icmsDesonerationReason;
    }

}
