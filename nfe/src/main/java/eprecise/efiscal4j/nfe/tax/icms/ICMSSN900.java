
package eprecise.efiscal4j.nfe.tax.icms;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04Optional;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Tributação do ICMS pelo SIMPLES NACIONAL, CRT=1 – Simples Nacional e CSOSN=900 - Outros
 * 
 * @see BaseICMSSN
 * @see ICMS
 * @author Clécius J. Martinkoski
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ICMSSN900 extends BaseICMSSN {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "modBC") @NotNull final BCModality bcModality;

	private @XmlElement(name = "pRedBC") @NFeDecimal0302a04Optional final String bcReductionPercent;

	private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

	private @XmlElement(name = "pICMS") @NotNull @NFeDecimal0302a04 final String icmsAliquot;

	private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsValue;

	private @XmlElement(name = "modBCST") @NotNull final BCModalityST bcModalitySt;

	private @XmlElement(name = "pMVAST") @NFeDecimal0302a04Optional final String valueMarginAddedStPercent;

	private @XmlElement(name = "pRedBCST") @NFeDecimal0302a04Optional final String bcReductionStPercent;

	private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String bcValueST;

	private @XmlElement(name = "pICMSST") @NotNull @NFeDecimal0302a04 final String icmsStAliquot;

	private @XmlElement(name = "vICMSST") @NotNull @NFeDecimal1302 final String icmsStValue;

	private @XmlElement(name = "pCredSN") @NotNull @NFeDecimal0302a04 final String creditSnAliquot;

	private @XmlElement(name = "vCredICMSSN") @NotNull @NFeDecimal1302 final String creditSnIcmsValue;

	public static class Builder extends BaseICMSSN.Builder implements ICMSBuilder {

		private BCModality bcModality;

		private String bcReductionPercent;

		private String bcValue;

		private String icmsAliquot;

		private String icmsValue;

		private BCModalityST bcModalitySt;

		private String valueMarginAddedStPercent;

		private String bcReductionStPercent;

		private String bcValueST;

		private String icmsStAliquot;

		private String icmsStValue;

		private String creditSnAliquot;

		private String creditSnIcmsValue;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Builder withOrigin(ProductOrigin origin) {
			return (ICMSSN900.Builder) super.withOrigin(origin);
		}

		/**
		 * @see BCModality
		 */
		public Builder withBcModality(BCModality bcModality) {
			this.bcModality = bcModality;
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

		/**
		 * Alíquota aplicável de cálculo do crédito (Simples Nacional). (v2.0)
		 * 
		 * @param creditSnAliquot
		 * @return
		 */
		public Builder withCreditSnAliquot(String creditSnAliquot) {
			this.creditSnAliquot = creditSnAliquot;
			return this;
		}

		/**
		 * Valor crédito do ICMS que pode ser aproveitado nos termos do art. 23 da LC 123 (Simples Nacional) (v2.0)
		 * 
		 * @param creditSnIcmsValue
		 * @return
		 */
		public Builder withCreditSnIcmsValue(String creditSnIcmsValue) {
			this.creditSnIcmsValue = creditSnIcmsValue;
			return this;
		}

		@Override
		public ICMSSN900 build() {
			return new ICMSSN900(this);
		}

	}

	protected ICMSSN900(ICMSSN900.Builder builder) {
		super(builder.origin, "900");
		this.bcModality = builder.bcModality;
		this.bcReductionPercent = builder.bcReductionPercent;
		this.bcValue = builder.bcValue;
		this.icmsAliquot = builder.icmsAliquot;
		this.icmsValue = builder.icmsValue;
		this.bcModalitySt = builder.bcModalitySt;
		this.valueMarginAddedStPercent = builder.valueMarginAddedStPercent;
		this.bcReductionStPercent = builder.bcReductionStPercent;
		this.bcValueST = builder.bcValueST;
		this.icmsStAliquot = builder.icmsStAliquot;
		this.icmsStValue = builder.icmsStValue;
		this.creditSnAliquot = builder.creditSnAliquot;
		this.creditSnIcmsValue = builder.creditSnIcmsValue;
	}
}
