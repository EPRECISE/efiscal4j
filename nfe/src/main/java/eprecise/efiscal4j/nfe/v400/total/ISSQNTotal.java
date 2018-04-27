
package eprecise.efiscal4j.nfe.v400.total;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.SpecialTaxRegime;
import eprecise.efiscal4j.nfe.v400.types.NFeDate;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302Optional;


/**
 * Totais referentes ao ISSQN
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class ISSQNTotal {

	private @XmlElement(name = "vServ") @NFeDecimal1302Optional String servicesTotalValue;

	private @XmlElement(name = "vBC") @NFeDecimal1302Optional String issqnCalculationBasis;

	private @XmlElement(name = "vISS") @NFeDecimal1302Optional String issqnTotalValue;

	private @XmlElement(name = "vPIS") @NFeDecimal1302Optional String pisTotalValue;

	private @XmlElement(name = "vCOFINS") @NFeDecimal1302Optional String cofinsTotalValue;

	private @XmlElement(name = "dCompet") @NotNull @NFeDate final String serviceProvisionDate;

	private @XmlElement(name = "vDeducao") @NFeDecimal1302Optional String calculationBasisReductionDeductionValue;

	private @XmlElement(name = "vOutro") @NFeDecimal1302Optional String otherRetentionsValue;

	private @XmlElement(name = "vDescIncond") @NFeDecimal1302Optional String unconditionalDiscountValue;

	private @XmlElement(name = "vDescCond") @NFeDecimal1302Optional String conditionalDiscountValue;

	private @XmlElement(name = "vISSRet") @NFeDecimal1302Optional String issRetentionTotalValue;

	private @XmlElement(name = "cRegTrib") SpecialTaxRegime specialTaxRegime;

	public static class Builder {

		private String servicesTotalValue;

		private String issqnCalculationBasis;

		private String issqnTotalValue;

		private String pisTotalValue;

		private String cofinsTotalValue;

		private String serviceProvisionDate;

		private String calculationBasisReductionDeductionValue;

		private String otherRetentionsValue;

		private String unconditionalDiscountValue;

		private String conditionalDiscountValue;

		private String issRetentionTotalValue;

		private SpecialTaxRegime specialTaxRegime;

		/**
		 * Valor Total dos Servi�os sob Não-incid�ncia ou Não tributados pelo ICMS
		 * 
		 * @param issqnTotalValue
		 * @return
		 */
		public Builder withServicesTotalValue(String servicesTotalValue) {
			this.servicesTotalValue = servicesTotalValue;
			return this;
		}

		/**
		 * Base de Cálculo do ISS
		 * 
		 * @param issqnCalculationBasis
		 * @return
		 */
		public Builder withISSQNCalculationBasis(String issqnCalculationBasis) {
			this.issqnCalculationBasis = issqnCalculationBasis;
			return this;
		}

		/**
		 * Valor Total do ISS
		 * 
		 * @param issqnTotalValue
		 * @return
		 */
		public Builder withISSQNTotalValue(String issqnTotalValue) {
			this.issqnTotalValue = issqnTotalValue;
			return this;
		}

		/**
		 * Valor do PIS sobre servi�os
		 * 
		 * @param pisTotalValue
		 * @return
		 */
		public Builder withPISTotalValue(String pisTotalValue) {
			this.pisTotalValue = pisTotalValue;
			return this;
		}

		/**
		 * Valor do COFINS sobre servi�os
		 * 
		 * @param cofinsTotalValue
		 * @return
		 */
		public Builder withCOFINSTotalValue(String cofinsTotalValue) {
			this.cofinsTotalValue = cofinsTotalValue;
			return this;
		}

		/**
		 * Data da presta��o do servi�o (AAAA-MM-DD)
		 * 
		 * @param serviceProvisionDate
		 * @return
		 */
		public Builder withServiceProvisionDate(String serviceProvisionDate) {
			this.serviceProvisionDate = serviceProvisionDate;
			return this;
		}

		/**
		 * Valor dedu��o para redu��o da base de c�lculo
		 * 
		 * @param calculationBasisReductionDeductionValue
		 * @return
		 */
		public Builder withCalculationBasisReductionDeductionValue(String calculationBasisReductionDeductionValue) {
			this.calculationBasisReductionDeductionValue = calculationBasisReductionDeductionValue;
			return this;
		}

		/**
		 * Valor outras reten��es
		 * 
		 * @param otherRetentionsValue
		 * @return
		 */
		public Builder withOtherRetentionsValue(String otherRetentionsValue) {
			this.otherRetentionsValue = otherRetentionsValue;
			return this;
		}

		/**
		 * Valor desconto incondicionado
		 * 
		 * @param unconditionalDiscountValue
		 * @return
		 */
		public Builder withUnconditionalDiscountValue(String unconditionalDiscountValue) {
			this.unconditionalDiscountValue = unconditionalDiscountValue;
			return this;
		}

		/**
		 * Valor desconto condicionado
		 * 
		 * @param conditionalDiscountValue
		 * @return
		 */
		public Builder withConditionalDiscountValue(String conditionalDiscountValue) {
			this.conditionalDiscountValue = conditionalDiscountValue;
			return this;
		}

		/**
		 * Valor Total Retenção ISS
		 * 
		 * @param issRetentionTotalValue
		 * @return
		 */
		public Builder withISSRetentionTotalValue(String issRetentionTotalValue) {
			this.issRetentionTotalValue = issRetentionTotalValue;
			return this;
		}

		/**
		 * C�digo do regime especial de tributa��o
		 * 
		 * @param specialTaxRegime
		 * @return
		 */
		public Builder withSpecialTaxRegime(SpecialTaxRegime specialTaxRegime) {
			this.specialTaxRegime = specialTaxRegime;
			return this;
		}

		public ISSQNTotal build() {
			ISSQNTotal entity = new ISSQNTotal(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public ISSQNTotal() {
		this.serviceProvisionDate = null;
	}

	public ISSQNTotal(Builder builder) {
		this.servicesTotalValue = builder.servicesTotalValue;
		this.issqnCalculationBasis = builder.issqnCalculationBasis;
		this.issqnTotalValue = builder.issqnTotalValue;
		this.pisTotalValue = builder.pisTotalValue;
		this.cofinsTotalValue = builder.cofinsTotalValue;
		this.serviceProvisionDate = builder.serviceProvisionDate;
		this.calculationBasisReductionDeductionValue = builder.calculationBasisReductionDeductionValue;
		this.otherRetentionsValue = builder.otherRetentionsValue;
		this.unconditionalDiscountValue = builder.unconditionalDiscountValue;
		this.conditionalDiscountValue = builder.conditionalDiscountValue;
		this.issRetentionTotalValue = builder.issRetentionTotalValue;
		this.specialTaxRegime = builder.specialTaxRegime;
	}
}
