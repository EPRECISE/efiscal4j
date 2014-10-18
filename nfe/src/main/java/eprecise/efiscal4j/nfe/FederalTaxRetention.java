
package eprecise.efiscal4j.nfe;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Retenção de Tributos Federais
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class FederalTaxRetention {

	private @XmlElement(name = "vRetPIS") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String pisRetainedValue;

	private @XmlElement(name = "vRetCOFINS") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String cofinsRetainedValue;

	private @XmlElement(name = "vRetCSLL") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String csllRetainedValue;

	private @XmlElement(name = "vBCIRRF") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String irrfCalculationBasis;

	private @XmlElement(name = "vIRRF") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String irrfRetainedValue;

	private @XmlElement(name = "vBCRetPrev") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String socialSecurityRetentionCalculationBasis;

	private @XmlElement(name = "vRetPrev") @Pattern(regexp = "0\\.[0-9]{1}[1-9]{1}|0\\.[1-9]{1}[0-9]{1}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") String socialSecurityRetentionValue;

	public static class Builder {

		private String pisRetainedValue;

		private String cofinsRetainedValue;

		private String csllRetainedValue;

		private String irrfCalculationBasis;

		private String irrfRetainedValue;

		private String socialSecurityRetentionCalculationBasis;

		private String socialSecurityRetentionValue;

		/**
		 * Valor Retido de PIS
		 * 
		 * @param pisRetainedValue
		 * @return
		 */
		public Builder withPISRetainedValue(String pisRetainedValue) {
			this.pisRetainedValue = pisRetainedValue;
			return this;
		}

		/**
		 * Valor Retido de COFINS
		 * 
		 * @param cofinsRetainedValue
		 * @return
		 */
		public Builder withCOFINSRetainedValue(String cofinsRetainedValue) {
			this.cofinsRetainedValue = cofinsRetainedValue;
			return this;
		}

		/**
		 * Valor Retido de CSLL
		 * 
		 * @param csllRetainedValue
		 * @return
		 */
		public Builder withCSLLRetainedValue(String csllRetainedValue) {
			this.csllRetainedValue = csllRetainedValue;
			return this;
		}

		/**
		 * Base de Cálculo do IRRF
		 * 
		 * @param irrfCalculationBasis
		 * @return
		 */
		public Builder withIRRFCalculationBasis(String irrfCalculationBasis) {
			this.irrfCalculationBasis = irrfCalculationBasis;
			return this;
		}

		/**
		 * Valor Retido de IRRF
		 * 
		 * @param irrfRetainedValue
		 * @return
		 */
		public Builder withIRRFRetainedValue(String irrfRetainedValue) {
			this.irrfRetainedValue = irrfRetainedValue;
			return this;
		}

		/**
		 * Base de Cálculo da Retenção da Previdência Social
		 * 
		 * @param socialSecurityRetentionCalculationBasis
		 * @return
		 */
		public Builder withSocialSecurityRetentionCalculationBasis(String socialSecurityRetentionCalculationBasis) {
			this.socialSecurityRetentionCalculationBasis = socialSecurityRetentionCalculationBasis;
			return this;
		}

		/**
		 * Valor da Retenção da Previdência Social
		 * 
		 * @param socialSecurityRetentionValue
		 * @return
		 */
		public Builder withSocialSecurityRetentionValue(String socialSecurityRetentionValue) {
			this.socialSecurityRetentionValue = socialSecurityRetentionValue;
			return this;
		}

		public FederalTaxRetention build() {
			FederalTaxRetention entity = new FederalTaxRetention(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public FederalTaxRetention() {
	}

	public FederalTaxRetention(Builder builder) {
		this.pisRetainedValue = builder.pisRetainedValue;
		this.cofinsRetainedValue = builder.cofinsRetainedValue;
		this.csllRetainedValue = builder.csllRetainedValue;
		this.irrfCalculationBasis = builder.irrfCalculationBasis;
		this.irrfRetainedValue = builder.irrfRetainedValue;
		this.socialSecurityRetentionCalculationBasis = builder.socialSecurityRetentionCalculationBasis;
		this.socialSecurityRetentionValue = builder.socialSecurityRetentionValue;
	}

}
