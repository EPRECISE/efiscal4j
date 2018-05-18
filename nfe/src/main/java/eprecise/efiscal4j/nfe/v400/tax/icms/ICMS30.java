
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.tax.icms.desoneration.DesonerationGroup;
import eprecise.efiscal4j.nfe.v400.tax.icms.desoneration.ICMSDesonerationReason;
import eprecise.efiscal4j.nfe.v400.tax.icms.desoneration.ICMSDesonerationReason_6_7_9_Validation;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 30 - Isenta ou não tributada e com cobrança do ICMS por substituição tributária
 *
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@ICMSDesonerationReason_6_7_9_Validation
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS30 extends BaseICMS implements DesonerationGroup, IcmsWithST {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalityST;

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

        private BCModalityST bcModalityST;

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
            return (ICMS30.Builder) super.withOrigin(origin);
        }

        /**
         * @see BCModalityST
         */
        public Builder withBcModalityST(final BCModalityST bcModalityST) {
            this.bcModalityST = bcModalityST;
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
        public ICMS30 build() {
            return new ICMS30(this);
        }

    }

    public ICMS30() {
        this.bcModalityST = null;
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

    protected ICMS30(final ICMS30.Builder builder) {
        super(builder.origin, "30");
        this.bcModalityST = builder.bcModalityST;
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

    public BCModalityST getBcModalityST() {
        return this.bcModalityST;
    }

    public String getValueMarginAddedStPercent() {
        return this.valueMarginAddedStPercent;
    }

    public String getBcReductionStPercent() {
        return this.bcReductionStPercent;
    }

    public String getBcValueST() {
        return this.bcValueST;
    }

    public String getIcmsStAliquot() {
        return this.icmsStAliquot;
    }

    @Override
    public String getIcmsStValue() {
        return this.icmsStValue;
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

    @Override
    public String getIcmsDesonerationValue() {
        return this.icmsDesonerationValue;
    }

    @Override
    public ICMSDesonerationReason getIcmsDesonerationReason() {
        return this.icmsDesonerationReason;
    }
}
