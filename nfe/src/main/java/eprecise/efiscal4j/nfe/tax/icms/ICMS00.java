
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 00 - Tributada integralmente
 * 
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS00 extends BaseICMS {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private BCModality bcModality;

        private String bcValue;

        private String icmsAliquot;

        private String icmsValue;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(ProductOrigin origin) {
            return (ICMS00.Builder) super.withOrigin(origin);
        }

        /**
         * @see BCModality
         * @param bcModality
         * @return
         */
        public Builder withBcModality(BCModality bcModality) {
            this.bcModality = bcModality;
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
         * 
         * @param icmsAliquot
         * @return
         */
        public Builder withIcmsAliquot(String icmsAliquot) {
            this.icmsAliquot = icmsAliquot;
            return this;
        }

        /**
         * Valor do ICMS
         * 
         * @param icmsValue
         * @return
         */
        public Builder withIcmsValue(String icmsValue) {
            this.icmsValue = icmsValue;
            return this;
        }

        @Override
        public ICMS00 build() {
            return new ICMS00(this);
        }
    }

    protected ICMS00() {
        super(null, null);
        this.bcModality = null;
        this.bcValue = null;
        this.icmsAliquot = null;
        this.icmsValue = null;
    }

    protected ICMS00(ICMS00.Builder builder) {
        super(builder.origin, "00");
        this.bcModality = builder.bcModality;
        this.bcValue = builder.bcValue;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsValue = builder.icmsValue;
    }

    public BCModality getBcModality() {
        return this.bcModality;
    }

    public String getBcValue() {
        return this.bcValue;
    }

    public String getIcmsAliquot() {
        return this.icmsAliquot;
    }

    public String getIcmsValue() {
        return this.icmsValue;
    }

}
