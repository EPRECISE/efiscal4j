
package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeDecimal1104Variable;
import eprecise.efiscal4j.nfe.types.NFeDecimal1110Variable;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Dados dos produtos e serviços da NF-e
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class NFeItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "cProd") @NotNull @Size(min = 1, max = 60) @NFeString final String itemCode;

    private @XmlElement(name = "cEAN") @NotNull @Pattern(regexp = "[0-9]{0}|[0-9]{8}|[0-9]{12,14}") final String globalTradeItemNumber;

    private @XmlElement(name = "xProd") @NotNull @Size(min = 1, max = 120) @NFeString final String itemDescription;

    private @XmlElement(name = "NCM") @NotNull @Pattern(regexp = "[0-9]{2}|[0-9]{8}") final String ncm;

    private @XmlElement(name = "CFOP") @NotNull final CFOP cfop;

    private @XmlElement(name = "uCom") @NotNull @Size(min = 1, max = 6) @NFeString final String comercialUnit;

    private @XmlElement(name = "qCom") @NotNull @NFeDecimal1104Variable final String comercialQuantity;

    private @XmlElement(name = "vUnCom") @NotNull @NFeDecimal1110Variable final String comercialUnitaryValue;

    private @XmlElement(name = "vProd") @NotNull @NFeDecimal1302 final String itemGrossValue;

    private @XmlElement(name = "cEANTrib") @NotNull @Pattern(regexp = "[0-9]{0}|[0-9]{8}|[0-9]{12,14}") final String taxableUnitGlobalTradeItemNumber;

    private @XmlElement(name = "uTrib") @NotNull @Size(min = 1, max = 6) @NFeString final String taxableUnit;

    private @XmlElement(name = "qTrib") @NotNull @NFeDecimal1104Variable final String taxableQuantity;

    private @XmlElement(name = "vUnTrib") @NotNull @NFeDecimal1110Variable final String taxationUnitaryValue;

    private @XmlElement(name = "indTot") @NotNull final ItemValueComprisesTotal itemValueComprisesTotal;

    private @XmlElement(name = "vDesc") @NFeDecimal1302 final String discountValue;

    private @XmlElement(name = "vSeg") @NFeDecimal1302 final String insuranceValue;

    private @XmlElement(name = "vFrete") @NFeDecimal1302 final String freightValue;

    private @XmlElement(name = "vOutro") @NFeDecimal1302 final String othersValue;

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

        private String discountValue;

        private String insuranceValue;

        private String freightValue;

        private String othersValue;

        /**
         * Código do produto ou serviço. Preencher com CFOP caso se trate de itens não relacionados com mercadorias/produto e que o contribuinte não possua codificação própria Formato "CFOP9999".
         * 
         * @param itemCode
         * @return
         */
        public Builder withItemCode(final String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        /**
         * GTIN (Global Trade Item Number) do produto, antigo código EAN ou código de barras
         * 
         * @param globalTradeItemNumber
         * @return
         */
        public Builder withGlobalTradeItemNumber(final String globalTradeItemNumber) {
            this.globalTradeItemNumber = globalTradeItemNumber;
            return this;
        }

        /**
         * Descrição do produto ou serviço
         * 
         * @param itemDescription
         * @return
         */
        public Builder withItemDescription(final String itemDescription) {
            this.itemDescription = itemDescription;
            return this;
        }

        /**
         * Código NCM (8 posições), será permitida a informação do gênero (posição do capítulo do NCM) quando a Operação Não for de comércio exterior (importação/exportação) ou o produto Não seja
         * tributado pelo IPI. Em caso de item de serviço ou item que Não tenham produto (Ex. transferência de crédito, crédito do ativo imobilizado, etc.), informar o código 00 (zeros) (v2.0)
         * 
         * @param ncm
         * @return
         */
        public Builder withNCM(final String ncm) {
            this.ncm = ncm;
            return this;
        }

        /**
         * @see CFOP
         * @param cfop
         * @return
         */
        public Builder withCFOP(final CFOP cfop) {
            this.cfop = cfop;
            return this;
        }

        /**
         * Unidade comercial
         * 
         * @param comercialUnit
         * @return
         */
        public Builder withComercialUnit(final String comercialUnit) {
            this.comercialUnit = comercialUnit;
            return this;
        }

        /**
         * Quantidade comercial
         * 
         * @param comercialQuantity
         * @return
         */
        public Builder withComercialQuantity(final String comercialQuantity) {
            this.comercialQuantity = comercialQuantity;
            return this;
        }

        /**
         * Valor unitário de comercialização
         * 
         * @param comercialUnitaryValue
         * @return
         */
        public Builder withComercialUnitaryValue(final String comercialUnitaryValue) {
            this.comercialUnitaryValue = comercialUnitaryValue;
            return this;
        }

        /**
         * Valor bruto do produto ou serviço
         * 
         * @param itemGrossValue
         * @return
         */
        public Builder withItemGrossValue(final String itemGrossValue) {
            this.itemGrossValue = itemGrossValue;
            return this;
        }

        /**
         * GTIN (Global Trade Item Number) da unidade tributável, antigo código EAN ou código de barras
         * 
         * @param taxableUnitGlobalTradeItemNumber
         * @return
         */
        public Builder withTaxableUnitGlobalTradeItemNumber(final String taxableUnitGlobalTradeItemNumber) {
            this.taxableUnitGlobalTradeItemNumber = taxableUnitGlobalTradeItemNumber;
            return this;
        }

        /**
         * Unidade Tributável
         * 
         * @param taxableUnit
         * @return
         */
        public Builder withTaxableUnit(final String taxableUnit) {
            this.taxableUnit = taxableUnit;
            return this;
        }

        /**
         * Quantidade Tributável
         * 
         * @param taxableQuantity
         * @return
         */
        public Builder withTaxableQuantity(final String taxableQuantity) {
            this.taxableQuantity = taxableQuantity;
            return this;
        }

        /**
         * Valor unitário de tributação
         * 
         * @param taxationUnitaryValue
         * @return
         */
        public Builder withTaxationUnitaryValue(final String taxationUnitaryValue) {
            this.taxationUnitaryValue = taxationUnitaryValue;
            return this;
        }

        /**
         * @see ItemValueComprisesTotal
         * @param itemValueComprisesTotal
         * @return
         */
        public Builder withItemValueComprisesTotal(final ItemValueComprisesTotal itemValueComprisesTotal) {
            this.itemValueComprisesTotal = itemValueComprisesTotal;
            return this;
        }

        /**
         * Valor do Desconto
         * 
         * @param discountValue
         * @return
         */
        public Builder withDiscountValue(final String discountValue) {
            this.discountValue = discountValue;
            return this;
        }

        /**
         * Valor Total do Seguro
         * 
         * @param insuranceValue
         * @return
         */
        public Builder withInsuranceValue(final String insuranceValue) {
            this.insuranceValue = insuranceValue;
            return this;
        }

        /**
         * Valor Total do Frete
         * 
         * @param insuranceValue
         * @return
         */
        public Builder withFreightValue(final String freightValue) {
            this.freightValue = freightValue;
            return this;
        }

        /**
         * Outras despesas acessórias
         * 
         * @param othersValue
         * @return
         */
        public Builder withOthersValue(final String othersValue) {
            this.othersValue = othersValue;
            return this;
        }

        public NFeItem build() {
            final NFeItem entity = new NFeItem(this);
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
        this.discountValue = null;
        this.insuranceValue = null;
        this.freightValue = null;
        this.othersValue = null;
    }

    public NFeItem(final Builder builder) {
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
        this.discountValue = builder.discountValue;
        this.insuranceValue = builder.insuranceValue;
        this.freightValue = builder.freightValue;
        this.othersValue = builder.othersValue;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public String getGlobalTradeItemNumber() {
        return this.globalTradeItemNumber;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public String getNcm() {
        return this.ncm;
    }

    public CFOP getCfop() {
        return this.cfop;
    }

    public String getComercialUnit() {
        return this.comercialUnit;
    }

    public String getComercialQuantity() {
        return this.comercialQuantity;
    }

    public String getComercialUnitaryValue() {
        return this.comercialUnitaryValue;
    }

    public String getItemGrossValue() {
        return this.itemGrossValue;
    }

    public String getTaxableUnitGlobalTradeItemNumber() {
        return this.taxableUnitGlobalTradeItemNumber;
    }

    public String getTaxableUnit() {
        return this.taxableUnit;
    }

    public String getTaxableQuantity() {
        return this.taxableQuantity;
    }

    public String getTaxationUnitaryValue() {
        return this.taxationUnitaryValue;
    }

    public ItemValueComprisesTotal getItemValueComprisesTotal() {
        return this.itemValueComprisesTotal;
    }

    public String getDiscountValue() {
        return this.discountValue;
    }

    public String getInsuranceValue() {
        return this.insuranceValue;
    }

    public String getFreightValue() {
        return this.freightValue;
    }

    public String getOthersValue() {
        return this.othersValue;
    }

}
