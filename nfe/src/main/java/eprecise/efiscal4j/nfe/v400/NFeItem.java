
package eprecise.efiscal4j.nfe.v400;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.fuel.Fuel;
import eprecise.efiscal4j.nfe.v400.guns.Gun;
import eprecise.efiscal4j.nfe.v400.item.di.ImportDeclaration;
import eprecise.efiscal4j.nfe.v400.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1104Variable;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1110Variable;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302Optional;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


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

    private @XmlElement(name = "cEAN") @NotNull @Pattern(regexp = "SEM GTIN|[0-9]{0}|[0-9]{8}|[0-9]{12,14}") final String globalTradeItemNumber;

    private @XmlElement(name = "xProd") @NotNull @Size(min = 1, max = 120) @NFeString String itemDescription;

    private @XmlElement(name = "NCM") @NotNull @Pattern(regexp = "[0-9]{2}|[0-9]{8}") final String ncm;

    private @XmlElement(name = "CEST") @Pattern(regexp = "[0-9]{7}") final String cest;

    private @XmlElement(name = "indEscala") final NFeItemScaleIndication scaleIndication;

    private @XmlElement(name = "CNPJFab") @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ String manufacturerCnpj;

    private @XmlElement(name = "cBenef") @Size(max = 10) String beneficiaryCode;

    private @XmlElement(name = "EXTIPI") @Pattern(regexp = "[0-9]{2,3}") String exTipi;

    private @XmlElement(name = "CFOP") @NotNull final CFOP cfop;

    private @XmlElement(name = "uCom") @NotNull @Size(min = 1, max = 6) @NFeString final String comercialUnit;

    private @XmlElement(name = "qCom") @NotNull @NFeDecimal1104Variable final String comercialQuantity;

    private @XmlElement(name = "vUnCom") @NotNull @NFeDecimal1110Variable final String comercialUnitaryValue;

    private @XmlElement(name = "vProd") @NotNull @NFeDecimal1302 final String itemGrossValue;

    private @XmlElement(name = "cEANTrib") @NotNull @Pattern(regexp = "SEM GTIN|[0-9]{0}|[0-9]{8}|[0-9]{12,14}") final String taxableUnitGlobalTradeItemNumber;

    private @XmlElement(name = "uTrib") @NotNull @Size(min = 1, max = 6) @NFeString final String taxableUnit;

    private @XmlElement(name = "qTrib") @NotNull @NFeDecimal1104Variable final String taxableQuantity;

    private @XmlElement(name = "vUnTrib") @NotNull @NFeDecimal1110Variable final String taxationUnitaryValue;

    private @XmlElement(name = "vFrete") @NFeDecimal1302Optional final String freightValue;

    private @XmlElement(name = "vSeg") @NFeDecimal1302Optional final String insuranceValue;

    private @XmlElement(name = "vDesc") @NFeDecimal1302Optional final String discountValue;

    private @XmlElement(name = "vOutro") @NFeDecimal1302Optional final String othersValue;

    private @XmlElement(name = "indTot") @NotNull final ItemValueComprisesTotal itemValueComprisesTotal;

    private @XmlElement(name = "med") @Valid final Medications medications;

    private @XmlElement(name = "arma") @Size(max = 500) @Valid final List<Gun> guns;

    private @XmlElement(name = "comb") @Valid final Fuel fuel;

    private @XmlElement(name = "DI") @Size(max = 100) @Valid final List<ImportDeclaration> importDeclarations;

    private @XmlElement(name = "xPed") @Size(min = 1, max = 15) @NFeString final String purchaseOrderDescription;

    private @XmlElement(name = "nItemPed") @Pattern(regexp = "[0-9]{1,6}") final String purchaseOrderNumber;

    private @XmlElement(name = "nFCI") @NotNull @Pattern(regexp = "[A-F0-9]{8}-[A-F0-9]{4}-[A-F0-9]{4}-[A-F0-9]{4}-[A-F0-9]{12}") final String fciNumber;

    private @XmlElement(name = "rastro") @Size(max = 500) @Valid final List<Trace> traces;

    public static class Builder {

        private String itemCode;

        private String globalTradeItemNumber;

        private String itemDescription;

        private String ncm;

        private String cest;

        private NFeItemScaleIndication scaleIndication;

        private String manufacturerCnpj;

        private String beneficiaryCode;

        private String exTipi;

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

        private Medications medications;

        private List<Gun> guns;

        private Fuel fuel;

        private List<ImportDeclaration> importDeclarations;

        private String purchaseOrderDescription;

        private String purchaseOrderNumber;

        private String fciNumber;

        private List<Trace> traces;

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
         * Código NCM (8 posições), será permitida a informação do gênero (posição do capítulo do NCM)<br>
         * quando a Operação Não for de comércio exterior (importação/exportação)<br>
         * ou o produto Não seja tributado pelo IPI. Em caso de item de serviço ou item que<br>
         * não tenham produto (Ex. transferência de crédito, crédito do ativo imobilizado, etc.), <br>
         * informar o código 00 (zeros) (v2.0)
         *
         * @param ncm
         * @return
         */
        public Builder withNCM(final String ncm) {
            this.ncm = ncm;
            return this;
        }

        /**
         * Codigo especificador da Substuicao Tributaria - CEST, que identifica a<br>
         * mercadoria sujeita aos regimes de substituicao tributária e de<br>
         * antecipação do recolhimento do imposto
         * 
         * @param cest
         * @return
         */
        public Builder withCest(String cest) {
            this.cest = cest;
            return this;
        }

        /**
         * @param scaleIndication
         * @see NFeItemScaleIndication
         * @return
         */
        public Builder withScaleIndication(NFeItemScaleIndication scaleIndication) {
            this.scaleIndication = scaleIndication;
            return this;
        }

        /**
         * CNPJ do Fabricante da Mercadoria, obrigatório para produto em escala NÃO relevante
         *
         * @param manufacturerCnpj
         * @return
         */
        public Builder withManufacturerCnpj(final String manufacturerCnpj) {
            this.manufacturerCnpj = manufacturerCnpj;
            return this;
        }

        /**
         * Código de Benefício Fiscal na UF aplicado ao item. Permite informar por produto o mesmo código do benefício utilizados na EFD e outras declarações e obrigações acessórias que as UF exigem
         *
         * @param beneficiaryCode
         * @return
         */
        public Builder withBeneficiaryCode(final String beneficiaryCode) {
            this.beneficiaryCode = beneficiaryCode;
            return this;
        }

        /**
         * Código EX TIPI (3 posições)
         *
         * @param exTipi
         * @return
         */
        public Builder withExTipi(final String exTipi) {
            this.exTipi = exTipi;
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

        /**
         * 
         * @see Medications
         * @param medications
         * @return
         */
        public Builder withMedications(final Medications medications) {
            this.medications = medications;
            return this;
        }

        /**
         * 
         * @see Gun
         * @param guns
         * @return
         */
        public Builder withGuns(final List<Gun> guns) {
            this.guns = guns;
            return this;
        }

        /**
         * 
         * @see Fuel
         * @param fuel
         * @return
         */
        public Builder witFuel(final Fuel fuel) {
            this.fuel = fuel;
            return this;
        }

        /**
         * 
         * @see ImportDeclaration
         * @param importDeclarations
         * @return
         */
        public Builder withImportDeclarations(final List<ImportDeclaration> importDeclarations) {
            this.importDeclarations = importDeclarations;
            return this;
        }

        /**
         * pedido de compra - Informação de interesse do emissor para controle do B2B.
         *
         * @param purchaseOrderDescription
         * @return
         */
        public Builder withPurchaseOrderDescription(final String purchaseOrderDescription) {
            this.purchaseOrderDescription = purchaseOrderDescription;
            return this;
        }

        /**
         * Número do Item do Pedido de Compra - Identificação do número do item do pedido de Compra
         *
         * @param purchaseOrderNumber
         * @return
         */
        public Builder withPurchaseOrderNumber(final String purchaseOrderNumber) {
            this.purchaseOrderNumber = purchaseOrderNumber;
            return this;
        }

        /**
         * Número de controle da FCI - Ficha de Conteúdo de Importação
         *
         * @param fciNumber
         * @return
         */
        public Builder withFciNumber(final String fciNumber) {
            this.fciNumber = fciNumber;
            return this;
        }

        /**
         * 
         * @see Trace
         * @param traces
         * @return
         */
        public Builder withTraces(final List<Trace> traces) {
            this.traces = traces;
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
        this.cest = null;
        this.scaleIndication = null;
        this.manufacturerCnpj = null;
        this.beneficiaryCode = null;
        this.exTipi = null;
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
        this.medications = null;
        this.guns = null;
        this.fuel = null;
        this.importDeclarations = null;
        this.purchaseOrderDescription = null;
        this.purchaseOrderNumber = null;
        this.fciNumber = null;
        this.traces = null;
    }

    public NFeItem(final Builder builder) {
        this.itemCode = builder.itemCode;
        this.globalTradeItemNumber = builder.globalTradeItemNumber;
        this.itemDescription = builder.itemDescription;
        this.ncm = builder.ncm;
        this.cest = builder.cest;
        this.scaleIndication = builder.scaleIndication;
        this.manufacturerCnpj = builder.manufacturerCnpj;
        this.beneficiaryCode = builder.beneficiaryCode;
        this.exTipi = builder.exTipi;
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
        this.medications = builder.medications;
        this.guns = builder.guns;
        this.fuel = builder.fuel;
        this.importDeclarations = builder.importDeclarations;
        this.purchaseOrderDescription = builder.purchaseOrderDescription;
        this.purchaseOrderNumber = builder.purchaseOrderNumber;
        this.fciNumber = builder.fciNumber;
        this.traces = builder.traces;
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

    public void setItemDescription(final String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getNcm() {
        return this.ncm;
    }

    public String getCest() {
        return this.cest;
    }

    public NFeItemScaleIndication getScaleIndication() {
        return scaleIndication;
    }

    public String getManufacturerCnpj() {
        return manufacturerCnpj;
    }

    public String getBeneficiaryCode() {
        return beneficiaryCode;
    }

    public String getExTipi() {
        return exTipi;
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

    public Medications getMedications() {
        return medications;
    }

    public List<Gun> getGuns() {
        return guns;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public List<ImportDeclaration> getImportDeclarations() {
        return this.importDeclarations;
    }

    public String getPurchaseOrderDescription() {
        return purchaseOrderDescription;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public String getFciNumber() {
        return fciNumber;
    }

    public List<Trace> getTraces() {
        return traces;
    }

}
