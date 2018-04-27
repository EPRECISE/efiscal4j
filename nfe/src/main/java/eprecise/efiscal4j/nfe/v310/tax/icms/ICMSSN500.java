
package eprecise.efiscal4j.nfe.v310.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v310.tax.icms.validation.ICMSSTRetained;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1302;


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

    private @XmlElement(name = "vICMSSTRet") @NFeDecimal1302 final String icmsRetainedValueST;

    public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

        private String bcRetainedValueST;

        private String icmsRetainedValueST;

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
         * Valor do ICMS ST retido anteriormente
         * 
         * @param icmsRetainedValueST
         * @return
         */
        public Builder withIcmsRetainedValueST(final String icmsRetainedValueST) {
            this.icmsRetainedValueST = icmsRetainedValueST;
            return this;
        }

        @Override
        public ICMSSN500 build() {
            return new ICMSSN500(this);
        }
    }

    public ICMSSN500() {
        this.bcRetainedValueST = null;
        this.icmsRetainedValueST = null;
    }

    protected ICMSSN500(final ICMSSN500.Builder builder) {
        super(builder.origin, "500");
        this.bcRetainedValueST = builder.bcRetainedValueST;
        this.icmsRetainedValueST = builder.icmsRetainedValueST;
    }

    @Override
    public String getBcRetainedValueST() {
        return this.bcRetainedValueST;
    }

    @Override
    public String getIcmsRetainedValueST() {
        return this.icmsRetainedValueST;
    }
}
