
package eprecise.efiscal4j.nfe.v310.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v310.tax.icms.desoneration.DesonerationGroup;
import eprecise.efiscal4j.nfe.v310.tax.icms.desoneration.ICMSDesonerationReason;
import eprecise.efiscal4j.nfe.v310.tax.icms.desoneration.ICMSDesonerationReason_3_9_12_Validation;
import eprecise.efiscal4j.nfe.v310.tax.icms.validation.ICMS90Standard;
import eprecise.efiscal4j.nfe.v310.tax.icms.validation.ICMSST90Standard;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 90 - Outras
 * 
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@ICMSDesonerationReason_3_9_12_Validation
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS90 extends BaseICMS implements DesonerationGroup, ICMS90Standard, ICMSST90Standard {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pRedBC") @NFeDecimal0302a04Optional final String bcReductionPercent;

    private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

    private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalitySt;

    private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

    private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

    private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

    private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

    private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

    private @XmlElement(name = "vICMSDeson") @NFeDecimal1302 final String icmsDesonerationValue;

    private @XmlElement(name = "motDesICMS") final ICMSDesonerationReason icmsDesonerationReason;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private BCModality bcModality;

        private String bcReductionPercent;

        private String bcValue;

        private String icmsAliquot;

        private String icmsValue;

        private BCModalityST bcModalitySt;

        private String valueMarginAddedStPercent;

        private String bcReductionStPercent;

        private String bcValueST;

        private String icmsStAliquot;

        private String icmsStValue;

        private String icmsDesonerationValue;

        private ICMSDesonerationReason icmsDesonerationReason;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMS90.Builder) super.withOrigin(origin);
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
        public ICMS90 build() {
            return new ICMS90(this);
        }

    }

    public ICMS90() {
        this.bcModality = null;
        this.bcReductionPercent = null;
        this.bcValue = null;
        this.icmsAliquot = null;
        this.icmsValue = null;
        this.bcModalitySt = null;
        this.valueMarginAddedStPercent = null;
        this.bcReductionStPercent = null;
        this.bcValueST = null;
        this.icmsStAliquot = null;
        this.icmsStValue = null;
        this.icmsDesonerationValue = null;
        this.icmsDesonerationReason = null;
    }

    protected ICMS90(final ICMS90.Builder builder) {
        super(builder.origin, "90");
        this.bcModality = builder.bcModality;
        this.bcReductionPercent = builder.bcReductionPercent;
        this.bcValue = builder.bcValue;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsValue = builder.icmsValue;
        this.bcModalitySt = builder.bcModalitySt;
        this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
        this.bcReductionStPercent = builder.bcReductionStPercent;
        this.bcValueST = builder.bcValueST;
        this.icmsStAliquot = builder.icmsStAliquot;
        this.icmsStValue = builder.icmsStValue;
        this.icmsDesonerationValue = builder.icmsDesonerationValue;
        this.icmsDesonerationReason = builder.icmsDesonerationReason;
    }

    @Override
    public BCModality getBcModality() {
        return this.bcModality;
    }

    @Override
    public String getBcReductionPercent() {
        return this.bcReductionPercent;
    }

    @Override
    public String getBcValue() {
        return this.bcValue;
    }

    @Override
    public String getIcmsAliquot() {
        return this.icmsAliquot;
    }

    @Override
    public String getIcmsValue() {
        return this.icmsValue;
    }

    @Override
    public BCModalityST getBcModalitySt() {
        return this.bcModalitySt;
    }

    @Override
    public String getValueMarginAddedStPercent() {
        return this.valueMarginAddedStPercent;
    }

    @Override
    public String getBcReductionStPercent() {
        return this.bcReductionStPercent;
    }

    @Override
    public String getBcValueST() {
        return this.bcValueST;
    }

    @Override
    public String getIcmsStAliquot() {
        return this.icmsStAliquot;
    }

    @Override
    public String getIcmsStValue() {
        return this.icmsStValue;
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
