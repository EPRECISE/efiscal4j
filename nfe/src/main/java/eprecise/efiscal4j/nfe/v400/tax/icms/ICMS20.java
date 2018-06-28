
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
 * Tributação pelo ICMS 20 - Com redução de base de cálculo
 *
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */

@ICMSDesonerationReason_3_9_12_Validation
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMS20 extends BaseICMS implements DesonerationGroup, IcmsWithValue {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

    private @XmlElement(name = "pRedBC") @NotNull @NFeDecimal0302a04 final String bcReductionPercent;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;
    
    private @XmlElement(name = "vBCFCP") @NFeDecimal1302 final String bcFcpValue;

    private @XmlElement(name = "pFCP") @NFeDecimal0302a04Optional final String fcpAliquot;

    private @XmlElement(name = "vFCP") @NFeDecimal1302 final String fcpValue;

    private @XmlElement(name = "vICMSDeson") @NFeDecimal1302 final String icmsDesonerationValue;

    private @XmlElement(name = "motDesICMS") final ICMSDesonerationReason icmsDesonerationReason;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private ProductOrigin origin;

        private BCModality bcModality;

        private String bcValue;

        private String bcReductionPercent;

        private String icmsAliquot;

        private String icmsValue;
        
        private String bcFcpValue;

        private String fcpAliquot;

        private String fcpValue;

        private String icmsDesonerationValue;

        private ICMSDesonerationReason icmsDesonerationReason;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            this.origin = origin;
            return this;
        }

        /**
         * @see BCModality
         */
        public Builder withBcModality(final BCModality bcModality) {
            this.bcModality = bcModality;
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
        public ICMS20 build() {
            return new ICMS20(this);
        }

    }

    public ICMS20() {
        this.bcModality = null;
        this.bcValue = null;
        this.bcReductionPercent = null;
        this.icmsAliquot = null;
        this.icmsValue = null;
        this.bcFcpValue = null;
        this.fcpAliquot = null;
        this.fcpValue = null;
        this.icmsDesonerationValue = null;
        this.icmsDesonerationReason = null;
    }

    protected ICMS20(final ICMS20.Builder builder) {
        super(builder.origin, "20");
        this.bcModality = builder.bcModality;
        this.bcValue = builder.bcValue;
        this.bcReductionPercent = builder.bcReductionPercent;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsValue = builder.icmsValue;
        this.bcFcpValue = builder.bcFcpValue;
        this.fcpAliquot = builder.fcpAliquot;
        this.fcpValue = builder.fcpValue;
        this.icmsDesonerationValue = builder.icmsDesonerationValue;
        this.icmsDesonerationReason = builder.icmsDesonerationReason;
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

    @Override
    public String getIcmsValue() {
        return this.icmsValue;
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

    @Override
    public String getIcmsDesonerationValue() {
        return this.icmsDesonerationValue;
    }

    @Override
    public ICMSDesonerationReason getIcmsDesonerationReason() {
        return this.icmsDesonerationReason;
    }

}
