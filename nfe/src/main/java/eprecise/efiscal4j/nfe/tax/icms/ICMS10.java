
package eprecise.efiscal4j.nfe.tax.icms;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Opc;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributação pelo ICMS 10 - Tributada e com cobrança do ICMS por substituição tributária
 * 
 * @see BaseICMS
 * @see ICMS
 * @author Clécius J. Martinkoski
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMS10 extends BaseICMS implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

	private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

	private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

	private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

	private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalitySt;

	private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Opc final String valueMarginAddedStPercent;

	private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Opc final String bcReductionStPercent;

	private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

	private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

	private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

	public static class Builder extends BaseICMS.Builder implements ICMSBuilder {

		private ProductOrigin origin;

		private BCModality bcModality;

		private String bcValue;

		private String icmsAliquot;

		private String icmsValue;

		private BCModalityST bcModalitySt;

		private String valueMarginAddedStPercent;

		private String bcReductionStPercent;

		private String bcValueST;

		private String icmsStAliquot;

		private String icmsStValue;

		/**
		 * @see ProductOrigin
		 * @param origin
		 * @return
		 */
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
		 */
		public Builder withBcValue(String bcValue) {
			this.bcValue = bcValue;
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
		 * 
		 * @see BCModalityST
		 */
		public Builder withBcModalityST(BCModalityST bcModalityST) {
			this.bcModalitySt = bcModalityST;
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

		@Override
		public ICMS10 build() {
			return new ICMS10(this);
		}

	}

	protected ICMS10(ICMS10.Builder builder) {
		super(builder.origin, "10");
		this.bcModality = builder.bcModality;
		this.bcValue = builder.bcValue;
		this.icmsAliquot = builder.icmsAliquot;
		this.icmsValue = builder.icmsValue;
		this.bcModalitySt = builder.bcModalitySt;
		this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
		this.bcReductionStPercent = builder.bcReductionStPercent;
		this.bcValueST = builder.bcValueST;
		this.icmsStAliquot = builder.icmsStAliquot;
		this.icmsStValue = builder.icmsStValue;
	}
}
