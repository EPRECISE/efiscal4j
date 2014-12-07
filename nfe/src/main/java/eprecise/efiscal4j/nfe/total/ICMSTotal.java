
package eprecise.efiscal4j.nfe.total;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Totais referentes ao ICMS
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSTotal {

	private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String icmsCalculationBasis;

	private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsTotalValue;

	private @XmlElement(name = "vICMSDeson") @NotNull @NFeDecimal1302 final String icmsTotalDesoneration;

	private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String icmsSTCalculationBasis;

	private @XmlElement(name = "vST") @NotNull @NFeDecimal1302 final String icmsSTTotalValue;

	private @XmlElement(name = "vProd") @NotNull @NFeDecimal1302 final String itemsTotalValue;

	private @XmlElement(name = "vFrete") @NotNull @NFeDecimal1302 final String shippingTotalValue;

	private @XmlElement(name = "vSeg") @NotNull @NFeDecimal1302 final String insuranceTotalValue;

	private @XmlElement(name = "vDesc") @NotNull @NFeDecimal1302 final String discountTotalValue;

	private @XmlElement(name = "vII") @NotNull @NFeDecimal1302 final String iiTotalValue;

	private @XmlElement(name = "vIPI") @NotNull @NFeDecimal1302 final String ipiTotalValue;

	private @XmlElement(name = "vPIS") @NotNull @NFeDecimal1302 final String pisTotalValue;

	private @XmlElement(name = "vCOFINS") @NotNull @NFeDecimal1302 final String cofinsTotalValue;

	private @XmlElement(name = "vOutro") @NotNull @NFeDecimal1302 final String otherIncidentalCostsTotalValue;

	private @XmlElement(name = "vNF") @NotNull @NFeDecimal1302 final String nfeTotalValue;

	private @XmlElement(name = "vTotTrib") @NFeDecimal1302 String taxTotalValue;

	public static class Builder {

		private String icmsCalculationBasis;

		private String icmsTotalValue;

		private String icmsTotalDesoneration;

		private String icmsSTCalculationBasis;

		private String icmsSTTotalValue;

		private String itemsTotalValue;

		private String shippingTotalValue;

		private String insuranceTotalValue;

		private String discountTotalValue;

		private String iiTotalValue;

		private String ipiTotalValue;

		private String pisTotalValue;

		private String cofinsTotalValue;

		private String otherIncidentalCostsTotalValue;

		private String nfeTotalValue;

		private String taxTotalValue;

		/**
		 * BC do ICMS
		 * 
		 * @param calculationBasis
		 * @return
		 */
		public Builder withICMSCalculationBasis(String icmsCalculationBasis) {
			this.icmsCalculationBasis = icmsCalculationBasis;
			return this;
		}

		/**
		 * Valor Total do ICMS
		 * 
		 * @param icmsTotalValue
		 * @return
		 */
		public Builder withICMSTotalValue(String icmsTotalValue) {
			this.icmsTotalValue = icmsTotalValue;
			return this;
		}

		/**
		 * Valor Total do ICMS desonerado
		 * 
		 * @param icmsTotalDesoneration
		 * @return
		 */
		public Builder withICMSTotalDesoneration(String icmsTotalDesoneration) {
			this.icmsTotalDesoneration = icmsTotalDesoneration;
			return this;
		}

		/**
		 * BC do ICMS ST
		 * 
		 * @param icmsSTCalculationBasis
		 * @return
		 */
		public Builder withICMSSTCalculationBasis(String icmsSTCalculationBasis) {
			this.icmsSTCalculationBasis = icmsSTCalculationBasis;
			return this;
		}

		/**
		 * Valor Total do ICMS ST
		 * 
		 * @param icmsSTTotalValue
		 * @return
		 */
		public Builder withICMSSTTotalValue(String icmsSTTotalValue) {
			this.icmsSTTotalValue = icmsSTTotalValue;
			return this;
		}

		/**
		 * Valor Total dos produtos e serviços
		 * 
		 * @param itemsTotalValue
		 * @return
		 */
		public Builder withItemsTotalValue(String itemsTotalValue) {
			this.itemsTotalValue = itemsTotalValue;
			return this;
		}

		/**
		 * Valor Total do Frete
		 * 
		 * @param shippingTotalValue
		 * @return
		 */
		public Builder withShippingTotalValue(String shippingTotalValue) {
			this.shippingTotalValue = shippingTotalValue;
			return this;
		}

		/**
		 * Valor Total do Seguro
		 * 
		 * @param insuranceTotalValue
		 * @return
		 */
		public Builder withInsuranceTotalValue(String insuranceTotalValue) {
			this.insuranceTotalValue = insuranceTotalValue;
			return this;
		}

		/**
		 * Valor Total do Desconto
		 * 
		 * @param discountTotalValue
		 * @return
		 */
		public Builder withDiscountTotalValue(String discountTotalValue) {
			this.discountTotalValue = discountTotalValue;
			return this;
		}

		/**
		 * Valor Total do II
		 * 
		 * @param iiTotalValue
		 * @return
		 */
		public Builder withIITotalValue(String iiTotalValue) {
			this.iiTotalValue = iiTotalValue;
			return this;
		}

		/**
		 * Valor Total do IPI
		 * 
		 * @param ipiTotalValue
		 * @return
		 */
		public Builder withIPITotalValue(String ipiTotalValue) {
			this.ipiTotalValue = ipiTotalValue;
			return this;
		}

		/**
		 * Valor do PIS
		 * 
		 * @param pisTotalValue
		 * @return
		 */
		public Builder withPISTotalValue(String pisTotalValue) {
			this.pisTotalValue = pisTotalValue;
			return this;
		}

		/**
		 * Valor do COFINS
		 * 
		 * @param cofinsTotalValue
		 * @return
		 */
		public Builder withCOFINSTotalValue(String cofinsTotalValue) {
			this.cofinsTotalValue = cofinsTotalValue;
			return this;
		}

		/**
		 * Outras Despesas acessórias
		 * 
		 * @param otherIncidentalCostsTotalValue
		 * @return
		 */
		public Builder withOtherIncidentalCostsTotalValue(String otherIncidentalCostsTotalValue) {
			this.otherIncidentalCostsTotalValue = otherIncidentalCostsTotalValue;
			return this;
		}

		/**
		 * Valor Total da NF-e
		 * 
		 * @param nfeTotalValue
		 * @return
		 */
		public Builder withNFeTotalValue(String nfeTotalValue) {
			this.nfeTotalValue = nfeTotalValue;
			return this;
		}

		/**
		 * Valor estimado total de impostos federais, estaduais e municipais
		 * 
		 * @param taxTotalValue
		 * @return
		 */
		public Builder withTaxTotalValue(String taxTotalValue) {
			this.taxTotalValue = taxTotalValue;
			return this;
		}

		public ICMSTotal build() {
			ICMSTotal entity = new ICMSTotal(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public ICMSTotal() {
		this.icmsCalculationBasis = null;
		this.icmsTotalValue = null;
		this.icmsTotalDesoneration = null;
		this.icmsSTCalculationBasis = null;
		this.icmsSTTotalValue = null;
		this.itemsTotalValue = null;
		this.shippingTotalValue = null;
		this.insuranceTotalValue = null;
		this.discountTotalValue = null;
		this.iiTotalValue = null;
		this.ipiTotalValue = null;
		this.pisTotalValue = null;
		this.cofinsTotalValue = null;
		this.otherIncidentalCostsTotalValue = null;
		this.nfeTotalValue = null;
	}

	public ICMSTotal(Builder builder) {
		this.icmsCalculationBasis = builder.icmsCalculationBasis;
		this.icmsTotalValue = builder.icmsTotalValue;
		this.icmsTotalDesoneration = builder.icmsTotalDesoneration;
		this.icmsSTCalculationBasis = builder.icmsSTCalculationBasis;
		this.icmsSTTotalValue = builder.icmsSTTotalValue;
		this.itemsTotalValue = builder.itemsTotalValue;
		this.shippingTotalValue = builder.shippingTotalValue;
		this.insuranceTotalValue = builder.insuranceTotalValue;
		this.discountTotalValue = builder.discountTotalValue;
		this.iiTotalValue = builder.iiTotalValue;
		this.ipiTotalValue = builder.ipiTotalValue;
		this.pisTotalValue = builder.pisTotalValue;
		this.cofinsTotalValue = builder.cofinsTotalValue;
		this.otherIncidentalCostsTotalValue = builder.otherIncidentalCostsTotalValue;
		this.nfeTotalValue = builder.nfeTotalValue;
		this.taxTotalValue = builder.taxTotalValue;
	}
}
