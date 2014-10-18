
package eprecise.efiscal4j.nfe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


/**
 * Dados dos produtos e servi�os da NF-e
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class NFeItem {

	/**
	 * C�digo do produto ou servi�o. Preencher com CFOP caso se trate de itens n�o relacionados com mercadorias/produto e que o contribuinte n�o possua codifica��o pr�pria Formato �CFOP9999�.
	 */
	private @XmlElement(name = "cProd") @NotNull @Size(min = 1, max = 60) @Pattern(regexp = "[!-�]{1}[ -�]{0,}[!-�]{1}|[!-�]{1}") final String itemCode;

	/**
	 * GTIN (Global Trade Item Number) do produto, antigo c�digo EAN ou c�digo de barras
	 */
	private @XmlElement(name = "cEAN") @NotNull @Pattern(regexp = "[0-9]{0}|[0-9]{8}|[0-9]{12,14}") final String globalTradeItemNumber;

	/**
	 * Descri��o do produto ou servi�o
	 */
	private @XmlElement(name = "xProd") @NotNull @Size(min = 1, max = 120) @Pattern(regexp = "[!-�]{1}[ -�]{0,}[!-�]{1}|[!-�]{1}") final String itemDescription;

	/**
	 * C�digo NCM (8 posi��es), ser� permitida a informa��o do g�nero (posi��o do cap�tulo do NCM) quando a opera��o n�o for de com�rcio exterior (importa��o/exporta��o) ou o produto n�o seja
	 * tributado pelo IPI. Em caso de item de servi�o ou item que n�o tenham produto (Ex. transfer�ncia de cr�dito, cr�dito do ativo imobilizado, etc.), informar o c�digo 00 (zeros) (v2.0)
	 */
	private @XmlElement(name = "NCM") @NotNull @Pattern(regexp = "[0-9]{2}|[0-9]{8}") final String ncm;

	/**
	 * C�digo Fiscal de Opera��es e Presta��es
	 */
	private @XmlElement(name = "CFOP") @NotNull final CFOP cfop;

	/**
	 * Unidade comercial
	 */
	private @XmlElement(name = "uCom") @NotNull @Size(min = 1, max = 6) @Pattern(regexp = "[!-�]{1}[ -�]{0,}[!-�]{1}|[!-�]{1}") final String comercialUnit;

	/**
	 * Quantidade comercial
	 */
	private @XmlElement(name = "qCom") @NotNull @Pattern(
			regexp = "0\\.[1-9]{1}[0-9]{3}|0\\.[0-9]{3}[1-9]{1}|0\\.[0-9]{2}[1-9]{1}[0-9]{1}|0\\.[0-9]{1}[1-9]{1}[0-9]{2}|[1-9]{1}[0-9]{0,10}(\\.[0-9]{4})?") final String comercialQuantity;

	/**
	 * Valor unit�rio de comercializa��o
	 */
	private @XmlElement(name = "vUnCom") @NotNull @Pattern(regexp = "0|0\\.[0-9]{1,10}|[1-9]{1}[0-9]{0,10}|[1-9]{1}[0-9]{0,10}(\\.[0-9]{1,10})?") final String comercialUnitaryValue;

	/**
	 * Valor bruto do produto ou servi�o
	 */
	private @XmlElement(name = "vProd") @NotNull @Pattern(regexp = "0|0\\.[0-9]{2}|[1-9]{1}[0-9]{0,12}(\\.[0-9]{2})?") final String itemGrossValue;

	/**
	 * GTIN (Global Trade Item Number) da unidade tribut�vel, antigo c�digo EAN ou c�digo de barras
	 */
	private @XmlElement(name = "cEANTrib") @NotNull @Pattern(regexp = "[0-9]{0}|[0-9]{8}|[0-9]{12,14}") final String taxableUnitGlobalTradeItemNumber;

	/**
	 * Unidade Tribut�vel
	 */
	private @XmlElement(name = "uTrib") @NotNull @Size(min = 1, max = 6) @Pattern(regexp = "[!-�]{1}[ -�]{0,}[!-�]{1}|[!-�]{1}") final String taxableUnit;

	/**
	 * Quantidade Tribut�vel
	 */
	private @XmlElement(name = "qTrib") @NotNull @Pattern(regexp = "0|0\\.[0-9]{1,4}|[1-9]{1}[0-9]{0,10}|[1-9]{1}[0-9]{0,10}(\\.[0-9]{1,4})?") final String taxableQuantity;

	/**
	 * Valor unit�rio de tributa��o
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
