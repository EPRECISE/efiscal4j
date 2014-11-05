
package eprecise.efiscal4j.nfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Totais referentes ao ISSQN
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class ISSQNTotal {

	private @XmlElement(name = "vServ") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String servicesTotalValue;

	private @XmlElement(name = "vBC") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String issqnCalculationBasis;

	private @XmlElement(name = "vISS") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String issqnTotalValue;

	private @XmlElement(name = "vPIS") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String pisTotalValue;

	private @XmlElement(name = "vCOFINS") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String cofinsTotalValue;

	private @XmlElement(name = "dCompet") @NotNull @Pattern(
			regexp = "(((20(([02468][048])|([13579][26]))-02-29))|(20[0-9][0-9])-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))") final String serviceProvisionDate;

	private @XmlElement(name = "vDeducao") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String calculationBasisReductionDeductionValue;

	private @XmlElement(name = "vOutro") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String otherRetentionsValue;

	private @XmlElement(name = "vDescIncond") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String unconditionalDiscountValue;

	private @XmlElement(name = "vDescCond") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String conditionalDiscountValue;

	private @XmlElement(name = "vDescCond") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String issRetentionTotalValue;

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
		 * Base de C�lculo do ISS
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
		 * Valor Total Reten��o ISS
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
