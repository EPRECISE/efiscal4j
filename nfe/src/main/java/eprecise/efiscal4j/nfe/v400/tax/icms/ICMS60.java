
package eprecise.efiscal4j.nfe.v400.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.tax.icms.validation.ICMSSTRetained;
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
class ICMS60 extends BaseICMS implements ICMSSTRetained {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBCSTRet") @NFeDecimal1302 final String bcRetainedValueST;

    private @XmlElement(name = "vICMSSTRet") @NFeDecimal1302 final String icmsRetainedValueST;

    public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

        private String bcRetainedValueST;

        private String icmsRetainedValueST;

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
        public ICMS60 build() {
            return new ICMS60(this);
        }

    }

    public ICMS60() {
        this.bcRetainedValueST = null;
        this.icmsRetainedValueST = null;
    }

    protected ICMS60(final ICMS60.Builder builder) {
        super(builder.origin, "60");
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
