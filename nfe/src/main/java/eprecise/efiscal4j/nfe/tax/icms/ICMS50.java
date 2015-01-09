
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.nfe.tax.icms.desoneration.ICMSDesonerationReason;


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
		public Builder withOrigin(ProductOrigin origin) {
			return (ICMS50.Builder) super.withOrigin(origin);
		}

		@Override
		public Builder withIcmsDesonerationValue(String icmsDesonerationValue) {
			return (ICMS50.Builder) super.withIcmsDesonerationValue(icmsDesonerationValue);
		}

		@Override
		public Builder withIcmsDesonerationReason(ICMSDesonerationReason icmsDesonerationReason) {
			return (ICMS50.Builder) super.withIcmsDesonerationReason(icmsDesonerationReason);
		}

		@Override
		public ICMS50 build() {
			return new ICMS50(this);
		}

	}

	protected ICMS50(ICMS50.Builder builder) {
		super(builder, "50");
	}
}
