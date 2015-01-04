
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.nfe.tax.icms.desoneration.ICMSDesonerationReason;


/**
 * Tributação pelo ICMS 41 - Não Tributada
 * 
 * @see BaseICMS40
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS41 extends BaseICMS40 {

	private static final long serialVersionUID = 1L;

	public static class Builder extends BaseICMS40.Builder implements ICMSBuilder {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Builder withOrigin(ProductOrigin origin) {
			return (ICMS41.Builder) super.withOrigin(origin);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Builder withIcmsDesonerationValue(String icmsDesonerationValue) {
			return (ICMS41.Builder) super.withIcmsDesonerationValue(icmsDesonerationValue);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Builder withIcmsDesonerationReason(ICMSDesonerationReason icmsDesonerationReason) {
			return (ICMS41.Builder) super.withIcmsDesonerationReason(icmsDesonerationReason);
		}

		@Override
		public ICMS41 build() {
			return new ICMS41(this);
		}

	}

	protected ICMS41(ICMS41.Builder builder) {
		super(builder, "41");
	}
}
