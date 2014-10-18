
package eprecise.efiscal4j.nfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Dados dos produtos e serviços da NF-e
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class NFeItem {

	/**
	 * Código do produto ou serviço. Preencher com CFOP caso se trate de itens não relacionados com mercadorias/produto e que o contribuinte não possua codificação própria Formato ”CFOP9999”.
	 */
	private @XmlElement(name = "cProd") @NotNull @Size(min = 1, max = 60) @Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}") final String itemCode;

	/**
	 * GTIN (Global Trade Item Number) do produto, antigo código EAN ou código de barras
	 */
	private @XmlElement(name = "cEAN") @NotNull @Pattern(regexp = "[0-9]{0}|[0-9]{8}|[0-9]{12,14}") final String globalTradeItemNumber;

	/**
	 * Descrição do produto ou serviço
	 */
	private @XmlElement(name = "xProd") @NotNull @Size(min = 1, max = 120) @Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}") final String itemDescription;

	/**
	 * Código NCM (8 posições), será permitida a informação do gênero (posição do capítulo do NCM) quando a operação não for de comércio exterior (importação/exportação) ou o produto não seja
	 * tributado pelo IPI. Em caso de item de serviço ou item que não tenham produto (Ex. transferência de crédito, crédito do ativo imobilizado, etc.), informar o código 00 (zeros) (v2.0)
	 */
	private @XmlElement(name = "NCM") @NotNull @Pattern(regexp = "[0-9]{2}|[0-9]{8}") final String ncm;

	/**
	 * Código Fiscal de Operações e Prestações
	 */
	private @XmlElement(name = "CFOP") @NotNull final CFOP cfop;

	/**
	 * Unidade comercial
	 */
	private @XmlElement(name = "uCom") @NotNull @Size(min = 1, max = 6) @Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}") final String comercialUnit;

	/**
	 * Quantidade comercial
	 */
	private @XmlElement(name = "qCom") @NotNull @Pattern(
			regexp = "0\\.[1-9]{1}[0-9]{3}|0\\.[0-9]{3}[1-9]{1}|0\\.[0-9]{2}[1-9]{1}[0-9]{1}|0\\.[0-9]{1}[1-9]{1}[0-9]{2}|[1-9]{1}[0-9]{0,10}(\\.[0-9]{4})?") final String comercialQuantity;

	/**
	 * Valor unitário de comercialização
	 */
	private @XmlElement(name = "vUnCom") @NotNull @Pattern(regexp = "0|0\\.[0-9]{1,10}|[1-9]{1}[0-9]{0,10}|[1-9]{1}[0-9]{0,10}(\\.[0-9]{1,10})?") final String comercialUnitaryValue;

	/**
	 * Valor bruto do produto ou serviço
	 */
	private @XmlElement(name = "vProd") @NotNull @Pattern(regexp = "0|0\\.[0-9]{2}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") final String itemGrossValue;

	/**
	 * GTIN (Global Trade Item Number) da unidade tributável, antigo código EAN ou código de barras
	 */
	private @XmlElement(name = "cEANTrib") @NotNull @Pattern(regexp = "[0-9]{0}|[0-9]{8}|[0-9]{12,14}") final String taxableUnitGlobalTradeItemNumber;

	/**
	 * Unidade Tributável
	 */
	private @XmlElement(name = "uTrib") @NotNull @Size(min = 1, max = 6) @Pattern(regexp = "[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}") final String taxableUnit;

	/**
	 * Quantidade Tributável
	 */
	private @XmlElement(name = "qTrib") @NotNull @Pattern(regexp = "0|0\\.[0-9]{1,4}|[1-9]{1}[0-9]{0,10}|[1-9]{1}[0-9]{0,10}(\\.[0-9]{1,4})?") final String taxableQuantity;

	/**
	 * Valor unitário de tributação
	 */
	private @XmlElement(name = "vUnTrib") @NotNull @Pattern(regexp = "0|0\\.[0-9]{1,10}|[1-9]{1}[0-9]{0,10}|[1-9]{1}[0-9]{0,10}(\\.[0-9]{1,10})?") final String taxationUnitaryValue;

	private @XmlElement(name = "indTot") @NotNull final ItemValueComprisesTotal itemValueComprisesTotal;

	public static class Builder {

		private String itemCode;

		private String globalTradeItemNumber;

		private String itemDescription;

		private String ncm;

		private CFOP cfop;

		private String comercialUnit;

		private String comercialQuantity;

		private String comercialUnitaryValue;

		private String itemGrossValue;

		private String taxableUnitGlobalTradeItemNumber;

		private String taxableUnit;

		private String taxableQuantity;

		private String taxationUnitaryValue;

		private ItemValueComprisesTotal itemValueComprisesTotal;

		public Builder withItemCode(String itemCode) {
			this.itemCode = itemCode;
			return this;
		}

		public Builder withGlobalTradeItemNumber(String globalTradeItemNumber) {
			this.globalTradeItemNumber = globalTradeItemNumber;
			return this;
		}

		public Builder withItemDescription(String itemDescription) {
			this.itemDescription = itemDescription;
			return this;
		}

		public Builder withNCM(String ncm) {
			this.ncm = ncm;
			return this;
		}

		public Builder withCFOP(CFOP cfop) {
			this.cfop = cfop;
			return this;
		}

		public Builder withComercialUnit(String comercialUnit) {
			this.comercialUnit = comercialUnit;
			return this;
		}

		public Builder withComercialQuantity(String comercialQuantity) {
			this.comercialQuantity = comercialQuantity;
			return this;
		}

		public Builder withComercialUnitaryValue(String comercialUnitaryValue) {
			this.comercialUnitaryValue = comercialUnitaryValue;
			return this;
		}

		public Builder withItemGrossValue(String itemGrossValue) {
			this.itemGrossValue = itemGrossValue;
			return this;
		}

		public Builder withTaxableUnitGlobalTradeItemNumber(String taxableUnitGlobalTradeItemNumber) {
			this.taxableUnitGlobalTradeItemNumber = taxableUnitGlobalTradeItemNumber;
			return this;
		}

		public Builder withTaxableUnit(String taxableUnit) {
			this.taxableUnit = taxableUnit;
			return this;
		}

		public Builder withTaxableQuantity(String taxableQuantity) {
			this.taxableQuantity = taxableQuantity;
			return this;
		}

		public Builder withTaxationUnitaryValue(String taxationUnitaryValue) {
			this.taxationUnitaryValue = taxationUnitaryValue;
			return this;
		}

		public Builder withItemValueComprisesTotal(ItemValueComprisesTotal itemValueComprisesTotal) {
			this.itemValueComprisesTotal = itemValueComprisesTotal;
			return this;
		}

		public NFeItem build() {
			NFeItem entity = new NFeItem(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}
	}

	public NFeItem() {
		this.itemCode = null;
		this.globalTradeItemNumber = null;
		this.itemDescription = null;
		this.ncm = null;
		this.cfop = null;
		this.comercialUnit = null;
		this.comercialQuantity = null;
		this.comercialUnitaryValue = null;
		this.itemGrossValue = null;
		this.taxableUnitGlobalTradeItemNumber = null;
		this.taxableUnit = null;
		this.taxableQuantity = null;
		this.taxationUnitaryValue = null;
		this.itemValueComprisesTotal = null;
	}

	public NFeItem(Builder builder) {
		this.itemCode = builder.itemCode;
		this.globalTradeItemNumber = builder.globalTradeItemNumber;
		this.itemDescription = builder.itemDescription;
		this.ncm = builder.ncm;
		this.cfop = builder.cfop;
		this.comercialUnit = builder.comercialUnit;
		this.comercialQuantity = builder.comercialQuantity;
		this.comercialUnitaryValue = builder.comercialUnitaryValue;
		this.itemGrossValue = builder.itemGrossValue;
		this.taxableUnitGlobalTradeItemNumber = builder.taxableUnitGlobalTradeItemNumber;
		this.taxableUnit = builder.taxableUnit;
		this.taxableQuantity = builder.taxableQuantity;
		this.taxationUnitaryValue = builder.taxationUnitaryValue;
		this.itemValueComprisesTotal = builder.itemValueComprisesTotal;
	}

}
