
package eprecise.efiscal4j.nfe.v400.total;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Totais referentes ao ICMS
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class ICMSTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String icmsCalculationBasis;

    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 final String icmsTotalValue;

    private @XmlElement(name = "vICMSDeson") @NotNull @NFeDecimal1302 final String icmsTotalDesoneration;

    private @XmlElement(name = "vFCPUFDest") @NFeDecimal1302 final String receiverUfFCPTotalValue;

    private @XmlElement(name = "vICMSUFDest") @NFeDecimal1302 final String receiverUfIcmsShareTotalValue;

    private @XmlElement(name = "vICMSUFRemet") @NFeDecimal1302 final String emitterUfIcmsShareTotalValue;

    private @XmlElement(name = "vFCP") @NotNull @NFeDecimal1302 final String fcpTotalValue;

    private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 final String icmsSTCalculationBasis;

    private @XmlElement(name = "vST") @NotNull @NFeDecimal1302 final String icmsSTTotalValue;

    private @XmlElement(name = "vFCPST") @NotNull @NFeDecimal1302 final String fcpStTotalValue;

    private @XmlElement(name = "vFCPSTRet") @NotNull @NFeDecimal1302 final String fcpStRetainedTotalValue;

    private @XmlElement(name = "vProd") @NotNull @NFeDecimal1302 final String itemsTotalValue;

    private @XmlElement(name = "vFrete") @NotNull @NFeDecimal1302 final String shippingTotalValue;

    private @XmlElement(name = "vSeg") @NotNull @NFeDecimal1302 final String insuranceTotalValue;

    private @XmlElement(name = "vDesc") @NotNull @NFeDecimal1302 final String discountTotalValue;

    private @XmlElement(name = "vII") @NotNull @NFeDecimal1302 final String iiTotalValue;

    private @XmlElement(name = "vIPI") @NotNull @NFeDecimal1302 final String ipiTotalValue;

    private @XmlElement(name = "vIPIDevol") @NotNull @NFeDecimal1302 final String returnedIpiTotalValue;

    private @XmlElement(name = "vPIS") @NotNull @NFeDecimal1302 final String pisTotalValue;

    private @XmlElement(name = "vCOFINS") @NotNull @NFeDecimal1302 final String cofinsTotalValue;

    private @XmlElement(name = "vOutro") @NotNull @NFeDecimal1302 final String otherIncidentalCostsTotalValue;

    private @XmlElement(name = "vNF") @NotNull @NFeDecimal1302 final String nfeTotalValue;

    private @XmlElement(name = "vTotTrib") @NFeDecimal1302 final String taxTotalValue;

    public static class Builder {

        private String icmsCalculationBasis;

        private String icmsTotalValue;

        private String icmsTotalDesoneration;

        private String receiverUfFCPTotalValue;

        private String receiverUfIcmsShareTotalValue;

        private String emitterUfIcmsShareTotalValue;

        private String fcpTotalValue;

        private String icmsSTCalculationBasis;

        private String icmsSTTotalValue;

        private String fcpStTotalValue;

        private String fcpStRetainedTotalValue;

        private String itemsTotalValue;

        private String shippingTotalValue;

        private String insuranceTotalValue;

        private String discountTotalValue;

        private String iiTotalValue;

        private String ipiTotalValue;

        private String returnedIpiTotalValue;

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
         * Valor total do ICMS relativo ao Fundo de Combate à Pobreza (FCP) para a UF de destino.
         * 
         * @param receiverUfFCPTotalValue
         * @return
         */
        public Builder withReceiverUfFCPTotalValue(String receiverUfFCPTotalValue) {
            this.receiverUfFCPTotalValue = receiverUfFCPTotalValue;
            return this;
        }

        /**
         * Valor total do ICMS de partilha para a UF do destinatário
         * 
         * @param receiverUfIcmsShareTotalValue
         * @return
         */
        public Builder withReceiverUfIcmsShareTotalValue(String receiverUfIcmsShareTotalValue) {
            this.receiverUfIcmsShareTotalValue = receiverUfIcmsShareTotalValue;
            return this;
        }

        /**
         * Valor total do ICMS de partilha para a UF do remetente
         * 
         * @param emitterUfIcmsShareTotalValue
         * @return
         */
        public Builder withEmitterUfIcmsShareTotalValue(String emitterUfIcmsShareTotalValue) {
            this.emitterUfIcmsShareTotalValue = emitterUfIcmsShareTotalValue;
            return this;
        }

        /**
         * Valor Total do FCP (Fundo de Combate à Pobreza)
         * 
         * @param fcpTotalValue
         * @return
         */
        public Builder withFcpTotalValue(String fcpTotalValue) {
            this.fcpTotalValue = fcpTotalValue;
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
         * Valor Total do FCP (Fundo de Combate à Pobreza) retido por substituição tributária
         * 
         * @param fcpStTotalValue
         * @return
         */
        public Builder withFcpStTotalValue(String fcpStTotalValue) {
            this.fcpStTotalValue = fcpStTotalValue;
            return this;
        }

        /**
         * Valor Total do FCP (Fundo de Combate à Pobreza) retido anteriormente por substituição tributária
         * 
         * @param fcpStRetainedTotalValue
         * @return
         */
        public Builder withFcpStRetainedTotalValue(String fcpStRetainedTotalValue) {
            this.fcpStRetainedTotalValue = fcpStRetainedTotalValue;
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
         * Valor Total do IPI devolvido. Deve ser informado quando preenchido o Grupo Tributos Devolvidos na emissão de nota finNFe=4 (devolução) nas operações com não contribuintes do IPI.
         * Corresponde ao total da soma dos campos id: UA04
         * 
         * @param returnedIpiTotalValue
         * @return
         */
        public Builder withReturnedIpiTotalValue(String returnedIpiTotalValue) {
            this.returnedIpiTotalValue = returnedIpiTotalValue;
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
            return new ICMSTotal(this);
        }
    }

    public ICMSTotal() {
        this.icmsCalculationBasis = null;
        this.icmsTotalValue = null;
        this.icmsTotalDesoneration = null;
        this.receiverUfFCPTotalValue = null;
        this.receiverUfIcmsShareTotalValue = null;
        this.emitterUfIcmsShareTotalValue = null;
        this.fcpTotalValue = null;
        this.icmsSTCalculationBasis = null;
        this.icmsSTTotalValue = null;
        this.fcpStTotalValue = null;
        this.fcpStRetainedTotalValue = null;
        this.itemsTotalValue = null;
        this.shippingTotalValue = null;
        this.insuranceTotalValue = null;
        this.discountTotalValue = null;
        this.iiTotalValue = null;
        this.ipiTotalValue = null;
        this.returnedIpiTotalValue = null;
        this.pisTotalValue = null;
        this.cofinsTotalValue = null;
        this.otherIncidentalCostsTotalValue = null;
        this.nfeTotalValue = null;
        this.taxTotalValue = null;
    }

    public ICMSTotal(Builder builder) {
        this.icmsCalculationBasis = builder.icmsCalculationBasis;
        this.icmsTotalValue = builder.icmsTotalValue;
        this.icmsTotalDesoneration = builder.icmsTotalDesoneration;
        this.receiverUfFCPTotalValue = builder.receiverUfFCPTotalValue;
        this.receiverUfIcmsShareTotalValue = builder.receiverUfIcmsShareTotalValue;
        this.emitterUfIcmsShareTotalValue = builder.emitterUfIcmsShareTotalValue;
        this.fcpTotalValue = builder.fcpTotalValue;
        this.icmsSTCalculationBasis = builder.icmsSTCalculationBasis;
        this.icmsSTTotalValue = builder.icmsSTTotalValue;
        this.fcpStTotalValue = builder.fcpStTotalValue;
        this.fcpStRetainedTotalValue = builder.fcpStRetainedTotalValue;
        this.itemsTotalValue = builder.itemsTotalValue;
        this.shippingTotalValue = builder.shippingTotalValue;
        this.insuranceTotalValue = builder.insuranceTotalValue;
        this.discountTotalValue = builder.discountTotalValue;
        this.iiTotalValue = builder.iiTotalValue;
        this.ipiTotalValue = builder.ipiTotalValue;
        this.returnedIpiTotalValue = builder.returnedIpiTotalValue;
        this.pisTotalValue = builder.pisTotalValue;
        this.cofinsTotalValue = builder.cofinsTotalValue;
        this.otherIncidentalCostsTotalValue = builder.otherIncidentalCostsTotalValue;
        this.nfeTotalValue = builder.nfeTotalValue;
        this.taxTotalValue = builder.taxTotalValue;
    }

    public String getIcmsCalculationBasis() {
        return icmsCalculationBasis;
    }

    public String getIcmsTotalValue() {
        return icmsTotalValue;
    }

    public String getIcmsTotalDesoneration() {
        return icmsTotalDesoneration;
    }

    public String getReceiverUfFCPTotalValue() {
        return receiverUfFCPTotalValue;
    }

    public String getReceiverUfIcmsShareTotalValue() {
        return receiverUfIcmsShareTotalValue;
    }

    public String getEmitterUfIcmsShareTotalValue() {
        return emitterUfIcmsShareTotalValue;
    }

    public String getFcpTotalValue() {
        return fcpTotalValue;
    }

    public String getIcmsSTCalculationBasis() {
        return icmsSTCalculationBasis;
    }

    public String getIcmsSTTotalValue() {
        return icmsSTTotalValue;
    }

    public String getFcpStTotalValue() {
        return fcpStTotalValue;
    }

    public String getFcpStRetainedTotalValue() {
        return fcpStRetainedTotalValue;
    }

    public String getItemsTotalValue() {
        return itemsTotalValue;
    }

    public String getShippingTotalValue() {
        return shippingTotalValue;
    }

    public String getInsuranceTotalValue() {
        return insuranceTotalValue;
    }

    public String getDiscountTotalValue() {
        return discountTotalValue;
    }

    public String getIiTotalValue() {
        return iiTotalValue;
    }

    public String getIpiTotalValue() {
        return ipiTotalValue;
    }

    public String getReturnedIpiTotalValue() {
        return returnedIpiTotalValue;
    }

    public String getPisTotalValue() {
        return pisTotalValue;
    }

    public String getCofinsTotalValue() {
        return cofinsTotalValue;
    }

    public String getOtherIncidentalCostsTotalValue() {
        return otherIncidentalCostsTotalValue;
    }

    public String getNfeTotalValue() {
        return nfeTotalValue;
    }

    public String getTaxTotalValue() {
        return taxTotalValue;
    }

}
