
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.tax.icms.desoneration.DesonerationGroup;
import eprecise.efiscal4j.nfe.tax.icms.desoneration.ICMSDesonerationReason;
import eprecise.efiscal4j.nfe.tax.icms.desoneration.ICMSDesonerationReason_6_7_9_Validation;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 30 - Isenta ou não tributada e com cobrança do ICMS por substituição tributária
 * 
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@ICMSDesonerationReason_6_7_9_Validation
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS30 extends BaseICMS implements DesonerationGroup {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalityST;

	private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

	private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

	private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

	private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

	private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

	private @XmlElement(name = "vICMSDeson") @NFeDecimal1302 final String icmsDesonerationValue;

	private @XmlElement(name = "motDesICMS") final ICMSDesonerationReason icmsDesonerationReason;

	public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

		private BCModalityST bcModalityST;

		private String valueMarginAddedStPercent;

		private String bcReductionStPercent;

		private String bcValueST;

		private String icmsStAliquot;

		private String icmsStValue;

		private String icmsDesonerationValue;

		private ICMSDesonerationReason icmsDesonerationReason;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Builder withOrigin(ProductOrigin origin) {
			return (ICMS30.Builder) super.withOrigin(origin);
		}

		/**
		 * @see BCModalityST
		 */
		public Builder withBcModalityST(BCModalityST bcModalityST) {
			this.bcModalityST = bcModalityST;
			return this;
		}

		/**
		 * Percentual da Margem de Valor Adicionado ICMS ST
		 */
		public Builder withValueMarginAddedStPercent(String valueMarginAddedStPercent) {
			this.valueMarginAddedStPercent = valueMarginAddedStPercent;
			return this;
		}

		/**
		 * Percentual de redução da BC ICMS ST
		 */
		public Builder withBcReductionStPercent(String bcReductionStPercent) {
			this.bcReductionStPercent = bcReductionStPercent;
			return this;
		}

		/**
		 * Valor da BC do ICMS ST
		 */
		public Builder withBcValueST(String bcValueST) {
			this.bcValueST = bcValueST;
			return this;
		}

		/**
		 * Alíquota do ICMS ST
		 */
		public Builder withIcmsStAliquot(String icmsStAliquot) {
			this.icmsStAliquot = icmsStAliquot;
			return this;
		}

		/**
		 * Valor do ICMS ST
		 */
		public Builder withIcmsStValue(String icmsStValue) {
			this.icmsStValue = icmsStValue;
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
		public ICMS30 build() {
			return new ICMS30(this);
		}

	}

	protected ICMS30(ICMS30.Builder builder) {
		super(builder.origin, "30");
		this.bcModalityST = builder.bcModalityST;
		this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
		this.bcReductionStPercent = builder.bcReductionStPercent;
		this.bcValueST = builder.bcValueST;
		this.icmsStAliquot = builder.icmsStAliquot;
		this.icmsStValue = builder.icmsStValue;
		this.icmsDesonerationValue = builder.icmsDesonerationValue;
		this.icmsDesonerationReason = builder.icmsDesonerationReason;

	}

	public BCModalityST getBcModalityST() {
		return this.bcModalityST;
	}

	public String getValueMarginAddedStPercent() {
		return this.valueMarginAddedStPercent;
	}

	public String getBcReductionStPercent() {
		return this.bcReductionStPercent;
	}

	public String getBcValueST() {
		return this.bcValueST;
	}

	public String getIcmsStAliquot() {
		return this.icmsStAliquot;
	}

	public String getIcmsStValue() {
		return this.icmsStValue;
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
