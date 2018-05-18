
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.tax.icms.validation.ICMSSTRetained;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL,CRT=1 – Simples Nacional e CSOSN=500 - ICMS cobrado anteriormente por substituição tributária (substituído) ou por antecipação
 * 
 * @see BaseICMSSN
 * @see ICMSSTRetained
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN500 extends BaseICMSSN implements ICMSSTRetained {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBCSTRet") @NFeDecimal1302 final String bcRetainedValueST;

    private @XmlElement(name = "pST") @NFeDecimal0302a04Optional final String endConsumerSupportedAliquot;

    private @XmlElement(name = "vICMSSTRet") @NFeDecimal1302 final String icmsRetainedValueST;

    private @XmlElement(name = "vBCFCPSTRet") @NFeDecimal1302 final String bcFcpRetainedValueST;

    private @XmlElement(name = "pFCPSTRet") @NFeDecimal0302a04Optional final String fcpRetainedAliquotST;

    private @XmlElement(name = "vFCPSTRet") @NFeDecimal1302 final String fcpRetainedValueST;

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        private String bcRetainedValueST;

        private String endConsumerSupportedAliquot;

        private String icmsRetainedValueST;

        private String bcFcpRetainedValueST;

        private String fcpRetainedAliquotST;

        private String fcpRetainedValueST;

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMSSN500.Builder) super.withOrigin(origin);
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
        public ICMSSN500 build() {
            return new ICMSSN500(this);
        }
    }

    public ICMSSN500() {
        this.bcRetainedValueST = null;
        this.endConsumerSupportedAliquot = null;
        this.icmsRetainedValueST = null;
        this.bcFcpRetainedValueST = null;
        this.fcpRetainedAliquotST = null;
        this.fcpRetainedValueST = null;
    }

    protected ICMSSN500(final ICMSSN500.Builder builder) {
        super(builder.origin, "500");
        this.bcRetainedValueST = builder.bcRetainedValueST;
        this.endConsumerSupportedAliquot = builder.endConsumerSupportedAliquot;
        this.icmsRetainedValueST = builder.icmsRetainedValueST;
        this.bcFcpRetainedValueST = builder.bcFcpRetainedValueST;
        this.fcpRetainedAliquotST = builder.fcpRetainedAliquotST;
        this.fcpRetainedValueST = builder.fcpRetainedValueST;
    }

    public String getBcRetainedValueST() {
        return bcRetainedValueST;
    }

    public String getEndConsumerSupportedAliquot() {
        return endConsumerSupportedAliquot;
    }

    public String getIcmsRetainedValueST() {
        return icmsRetainedValueST;
    }

    public String getBcFcpRetainedValueST() {
        return bcFcpRetainedValueST;
    }

    public String getFcpRetainedAliquotST() {
        return fcpRetainedAliquotST;
    }

    public String getFcpRetainedValueST() {
        return fcpRetainedValueST;
    }

}
