
package eprecise.efiscal4j.nfe.v310;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1302Optional;


/**
 * Retenção de Tributos Federais
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class FederalTaxRetention {

	private @XmlElement(name = "vRetPIS") @NFeDecimal1302Optional String pisRetainedValue;

	private @XmlElement(name = "vRetCOFINS") @NFeDecimal1302Optional String cofinsRetainedValue;

	private @XmlElement(name = "vRetCSLL") @NFeDecimal1302Optional String csllRetainedValue;

	private @XmlElement(name = "vBCIRRF") @NFeDecimal1302Optional String irrfCalculationBasis;

	private @XmlElement(name = "vIRRF") @NFeDecimal1302Optional String irrfRetainedValue;

	private @XmlElement(name = "vBCRetPrev") @NFeDecimal1302Optional String socialSecurityRetentionCalculationBasis;

	private @XmlElement(name = "vRetPrev") @NFeDecimal1302Optional String socialSecurityRetentionValue;

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
