
package eprecise.efiscal4j.nfe.v310.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.nfe.v310.tax.icms.desoneration.ICMSDesonerationReason;


/**
 * Tributação pelo ICMS 50 - Suspensão
 * 
 * @see BaseICMS40
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS50 extends BaseICMS40 {

    private static final long serialVersionUID = 1L;

    public static class Builder extends BaseICMS40.Builder implements ICMSBuilder {

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder withOrigin(final ProductOrigin origin) {
            return (ICMS50.Builder) super.withOrigin(origin);
        }

        @Override
        public Builder withIcmsDesonerationValue(final String icmsDesonerationValue) {
            return (ICMS50.Builder) super.withIcmsDesonerationValue(icmsDesonerationValue);
        }

        @Override
        public Builder withIcmsDesonerationReason(final ICMSDesonerationReason icmsDesonerationReason) {
            return (ICMS50.Builder) super.withIcmsDesonerationReason(icmsDesonerationReason);
        }

        @Override
        public ICMS50 build() {
            return new ICMS50(this);
        }

    }

    public ICMS50() {
    }

    protected ICMS50(final ICMS50.Builder builder) {
        super(builder, "50");
    }
}
