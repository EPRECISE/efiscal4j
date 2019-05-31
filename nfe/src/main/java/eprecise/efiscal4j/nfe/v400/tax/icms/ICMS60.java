
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.tax.icms.validation.ICMSSTRetained;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 60 - ICMS cobrado anteriormente por substituição tributária
 *
 * @see BaseICMS
 * @see ICMSSTRetained
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ICMS60 extends BaseICMS implements ICMSSTRetained {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBCSTRet") @NFeDecimal1302 final String bcRetainedValueST;

    private @XmlElement(name = "pST") @NFeDecimal0302a04Optional final String endConsumerSupportedAliquot;

    private @XmlElement(name = "vICMSSubstituto") @NFeDecimal1302 final String icmsSubstituteValue;

    private @XmlElement(name = "vICMSSTRet") @NFeDecimal1302 final String icmsRetainedValueST;

    private @XmlElement(name = "vBCFCPSTRet") @NFeDecimal1302 final String bcFcpRetainedValueST;

    private @XmlElement(name = "pFCPSTRet") @NFeDecimal0302a04Optional final String fcpRetainedAliquotST;

    private @XmlElement(name = "vFCPSTRet") @NFeDecimal1302 final String fcpRetainedValueST;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private String bcRetainedValueST;

        private String endConsumerSupportedAliquot;

        private String icmsSubstituteValue;

        private String icmsRetainedValueST;

        private String bcFcpRetainedValueST;

        private String fcpRetainedAliquotST;

        private String fcpRetainedValueST;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMS60.Builder) super.withOrigin(origin);
        }

        /**
         * Valor da BC do ICMS ST retido anteriormente
         *
         * @param bcRetainedValueST
         * @return
         */
        public Builder withBcRetainedValueST(final String bcRetainedValueST) {
            this.bcRetainedValueST = bcRetainedValueST;
            return this;
        }

        /**
         * Aliquota suportada pelo consumidor final
         *
         * @param endConsumerSupportedAliquot
         * @return
         */
        public Builder withEndConsumerSupportedAliquot(final String endConsumerSupportedAliquot) {
            this.endConsumerSupportedAliquot = endConsumerSupportedAliquot;
            return this;
        }

        /**
         * Valor do ICMS Próprio do Substituto cobrado em operação anterior
         *
         * @param icmsSubstituteValue
         * @return
         */
        public Builder withIcmsSubstituteValue(final String icmsSubstituteValue) {
            this.icmsSubstituteValue = icmsSubstituteValue;
            return this;
        }

        /**
         * Valor do ICMS ST retido anteriormente
         *
         * @param icmsRetainedValueST
         * @return
         */
        public Builder withIcmsRetainedValueST(final String icmsRetainedValueST) {
            this.icmsRetainedValueST = icmsRetainedValueST;
            return this;
        }

        /**
         * Valor da Base de cálculo do FCP retido anteriormente por ST
         *
         * @param bcFcpRetainedValueST
         */
        public Builder withBcFcpRetainedValueST(final String bcFcpRetainedValueST) {
            this.bcFcpRetainedValueST = bcFcpRetainedValueST;
            return this;
        }

        /**
         * Percentual de FCP retido anteriormente por substituição tributária
         *
         * @param fcpRetainedAliquotST
         * @return
         */
        public Builder withFcpRetainedAliquotST(final String fcpRetainedAliquotST) {
            this.fcpRetainedAliquotST = fcpRetainedAliquotST;
            return this;
        }

        /**
         * Valor do FCP retido por substituição tributária
         *
         * @param fcpRetainedValueST
         * @return
         */
        public Builder withFcpRetainedValueST(final String fcpRetainedValueST) {
            this.fcpRetainedValueST = fcpRetainedValueST;
            return this;
        }

        @Override
        public ICMS60 build() {
            return new ICMS60(this);
        }

    }

    public ICMS60() {
        this.bcRetainedValueST = null;
        this.endConsumerSupportedAliquot = null;
        this.icmsSubstituteValue = null;
        this.icmsRetainedValueST = null;
        this.bcFcpRetainedValueST = null;
        this.fcpRetainedAliquotST = null;
        this.fcpRetainedValueST = null;
    }

    protected ICMS60(final ICMS60.Builder builder) {
        super(builder.origin, "60");
        this.bcRetainedValueST = builder.bcRetainedValueST;
        this.endConsumerSupportedAliquot = builder.endConsumerSupportedAliquot;
        this.icmsSubstituteValue = builder.icmsSubstituteValue;
        this.icmsRetainedValueST = builder.icmsRetainedValueST;
        this.bcFcpRetainedValueST = builder.bcFcpRetainedValueST;
        this.fcpRetainedAliquotST = builder.fcpRetainedAliquotST;
        this.fcpRetainedValueST = builder.fcpRetainedValueST;
    }

    @Override
    public String getBcRetainedValueST() {
        return this.bcRetainedValueST;
    }

    public String getEndConsumerSupportedAliquot() {
        return this.endConsumerSupportedAliquot;
    }

    public String getIcmsSubstituteValue() {
        return this.icmsSubstituteValue;
    }

    @Override
    public String getIcmsRetainedValueST() {
        return this.icmsRetainedValueST;
    }

    public String getBcFcpRetainedValueST() {
        return this.bcFcpRetainedValueST;
    }

    public String getFcpRetainedAliquotST() {
        return this.fcpRetainedAliquotST;
    }

    public String getFcpRetainedValueST() {
        return this.fcpRetainedValueST;
    }
}
