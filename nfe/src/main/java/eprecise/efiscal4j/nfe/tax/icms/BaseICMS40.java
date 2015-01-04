
package eprecise.efiscal4j.nfe.tax.icms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.tax.icms.desoneration.DesonerationGroup;
import eprecise.efiscal4j.nfe.tax.icms.desoneration.ICMSDesonerationReason;
import eprecise.efiscal4j.nfe.tax.icms.desoneration.ICMSDesonerationReasonAllBut_12_Validation;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Classe base para os ICMS com CST 40 41 e 50
 * 
 * @see BaseICMS
 * @see DesonerationGroup
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 * 
 */
@ICMSDesonerationReasonAllBut_12_Validation
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BaseICMS40 extends BaseICMS implements DesonerationGroup {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "vICMSDeson") @NFeDecimal1302 final String icmsDesonerationValue;

	private @XmlElement(name = "motDesICMS") final ICMSDesonerationReason icmsDesonerationReason;

	static abstract class Builder extends BaseICMS.Builder {

		private String icmsDesonerationValue;

		private ICMSDesonerationReason icmsDesonerationReason;

		/**
		 * Valor do ICMS de desoneração
		 * 
		 * @param icmsDesonerationValue
		 * @return
		 */
		public Builder withIcmsDesonerationValue(String icmsDesonerationValue) {
			this.icmsDesonerationValue = icmsDesonerationValue;
			return this;
		}

		/**
		 * @see ICMSDesonerationReason
		 * @param icmsDesonerationReason
		 * @return
		 */
		public Builder withIcmsDesonerationReason(ICMSDesonerationReason icmsDesonerationReason) {
			this.icmsDesonerationReason = icmsDesonerationReason;
			return this;
		}

		@Override
		abstract BaseICMS40 build();
	}

	protected BaseICMS40(Builder builder, String cst) {
		super(builder.origin, cst);
		this.icmsDesonerationValue = builder.icmsDesonerationValue;
		this.icmsDesonerationReason = builder.icmsDesonerationReason;
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
