
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 00 - Tributada integralmente
 *
 * @see BaseICMS
 * @see ICMS
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMS00 extends BaseICMS implements IcmsWithValue {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

    private @XmlElement(name = "pFCP") @NFeDecimal0302a04Optional final String fcpAliquot;

    private @XmlElement(name = "vFCP") @NFeDecimal1302 final String fcpValue;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private BCModality bcModality;

        private String bcValue;

        private String icmsAliquot;

        private String icmsValue;

        private String fcpAliquot;

        private String fcpValue;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMS00.Builder) super.withOrigin(origin);
        }

        /**
         * @see BCModality
         * @param bcModality
         * @return
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
         * Alíquota do ICMS
         *
         * @param icmsAliquot
         * @return
         */
        public Builder withIcmsAliquot(final String icmsAliquot) {
            this.icmsAliquot = icmsAliquot;
            return this;
        }

        /**
         * Valor do ICMS
         *
         * @param icmsValue
         * @return
         */
        public Builder withIcmsValue(final String icmsValue) {
            this.icmsValue = icmsValue;
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

        @Override
        public ICMS00 build() {
            return new ICMS00(this);
        }
    }

    public ICMS00() {
        this.bcModality = null;
        this.bcValue = null;
        this.icmsAliquot = null;
        this.icmsValue = null;
        this.fcpAliquot = null;
        this.fcpValue = null;
    }

    protected ICMS00(final ICMS00.Builder builder) {
        super(builder.origin, "00");
        this.bcModality = builder.bcModality;
        this.bcValue = builder.bcValue;
        this.icmsAliquot = builder.icmsAliquot;
        this.icmsValue = builder.icmsValue;
        this.fcpAliquot = builder.fcpAliquot;
        this.fcpValue = builder.fcpValue;
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

    @Override
    public String getIcmsValue() {
        return this.icmsValue;
    }

    public String getFcpAliquot() {
        return fcpAliquot;
    }

    public String getFcpValue() {
        return fcpValue;
    }

}
