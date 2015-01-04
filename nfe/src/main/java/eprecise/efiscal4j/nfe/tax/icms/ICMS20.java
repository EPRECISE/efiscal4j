
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.tax.icms.desoneration.DesonerationGroup;
import eprecise.efiscal4j.nfe.tax.icms.desoneration.ICMSDesonerationReason;
import eprecise.efiscal4j.nfe.tax.icms.desoneration.ICMSDesonerationReason_3_9_12_Validation;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 20 - Com redução de base de cálculo
 * 
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */

@ICMSDesonerationReason_3_9_12_Validation
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS20 extends BaseICMS implements DesonerationGroup {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

	private @XmlElement(name = "pRedBC") @NotNull @NFeDecimal0302a04 final String bcReductionPercent;

	private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

	private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

	private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

	private @XmlElement(name = "vICMSDeson") @NFeDecimal1302 final String icmsDesonerationValue;

	private @XmlElement(name = "motDesICMS") final ICMSDesonerationReason icmsDesonerationReason;

	public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

		private ProductOrigin origin;

		private BCModality bcModality;

		private String bcValue;

		private String bcReductionPercent;

		private String icmsAliquot;

		private String icmsValue;

		private String icmsDesonerationValue;

		private ICMSDesonerationReason icmsDesonerationReason;

		/**
		 * @see ProductOrigin
		 * @param origin
		 * @return
		 */
		@Override
		public Builder withOrigin(ProductOrigin origin) {
			this.origin = origin;
			return this;
		}

		/**
		 * @see BCModality
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
		 * Percentual de redução da BC
		 * 
		 * @param bcReductionPercent
		 * @return
		 */
		public Builder withBcReductionPercent(String bcReductionPercent) {
			this.bcReductionPercent = bcReductionPercent;
			return this;
		}

		/**
		 * Alíquota do ICMS
		 */
		public Builder withIcmsAliquot(String icmsAliquot) {
			this.icmsAliquot = icmsAliquot;
			return this;
		}

		/**
		 * Valor do ICMS
		 */
		public Builder withIcmsValue(String icmsValue) {
			this.icmsValue = icmsValue;
			return this;
		}

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
		public ICMS20 build() {
			return new ICMS20(this);
		}

	}

	protected ICMS20(ICMS20.Builder builder) {
		super(builder.origin, "20");
		this.bcModality = builder.bcModality;
		this.bcValue = builder.bcValue;
		this.bcReductionPercent = builder.bcReductionPercent;
		this.icmsAliquot = builder.icmsAliquot;
		this.icmsValue = builder.icmsValue;
		this.icmsDesonerationValue = builder.icmsDesonerationValue;
		this.icmsDesonerationReason = builder.icmsDesonerationReason;
	}

	public BCModality getBcModality() {
		return this.bcModality;
	}

	public String getBcReductionPercent() {
		return this.bcReductionPercent;
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

	@Override
	public String getIcmsDesonerationValue() {
		return this.icmsDesonerationValue;
	}

	@Override
	public ICMSDesonerationReason getIcmsDesonerationReason() {
		return this.icmsDesonerationReason;
	}

}
