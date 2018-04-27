
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

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 String icmsCalculationBasis;

    private @XmlElement(name = "vICMS") @NotNull @NFeDecimal1302 String icmsTotalValue;

    private @XmlElement(name = "vICMSDeson") @NotNull @NFeDecimal1302 String icmsTotalDesoneration;

    private @XmlElement(name = "vFCPUFDest") @NFeDecimal1302 String receiverUfFCPTotalValue;

    private @XmlElement(name = "vICMSUFDest") @NFeDecimal1302 String receiverUfIcmsShareTotalValue;

    private @XmlElement(name = "vICMSUFRemet") @NFeDecimal1302 String emitterUfIcmsShareTotalValue;

    private @XmlElement(name = "vBCST") @NotNull @NFeDecimal1302 String icmsSTCalculationBasis;

    private @XmlElement(name = "vST") @NotNull @NFeDecimal1302 String icmsSTTotalValue;

    private @XmlElement(name = "vProd") @NotNull @NFeDecimal1302 String itemsTotalValue;

    private @XmlElement(name = "vFrete") @NotNull @NFeDecimal1302 String shippingTotalValue;

    private @XmlElement(name = "vSeg") @NotNull @NFeDecimal1302 String insuranceTotalValue;

    private @XmlElement(name = "vDesc") @NotNull @NFeDecimal1302 String discountTotalValue;

    private @XmlElement(name = "vII") @NotNull @NFeDecimal1302 String iiTotalValue;

    private @XmlElement(name = "vIPI") @NotNull @NFeDecimal1302 String ipiTotalValue;

    private @XmlElement(name = "vPIS") @NotNull @NFeDecimal1302 String pisTotalValue;

    private @XmlElement(name = "vCOFINS") @NotNull @NFeDecimal1302 String cofinsTotalValue;

    private @XmlElement(name = "vOutro") @NotNull @NFeDecimal1302 String otherIncidentalCostsTotalValue;

    private @XmlElement(name = "vNF") @NotNull @NFeDecimal1302 String nfeTotalValue;

    private @XmlElement(name = "vTotTrib") @NFeDecimal1302 String taxTotalValue;

    public static class Builder {

        private String icmsCalculationBasis;

        private String icmsTotalValue;

        private String icmsTotalDesoneration;

        private String receiverUfFCPTotalValue;

        private String receiverUfIcmsShareTotalValue;

        private String emitterUfIcmsShareTotalValue;

        private String icmsSTCalculationBasis;

        private String icmsSTTotalValue;

        private String itemsTotalValue;

        private String shippingTotalValue;

        private String insuranceTotalValue;

        private String discountTotalValue;

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
        // public Builder withIITotalValue(String iiTotalValue) {
        // this.iiTotalValue = iiTotalValue;
        // return this;
        // }

        /**
         * Valor Total do IPI
         * 
         * @param ipiTotalValue
         * @return
         */
        // public Builder withIPITotalValue(String ipiTotalValue) {
        // this.ipiTotalValue = ipiTotalValue;
        // return this;
        // }

        /**
         * Valor do PIS
         * 
         * @param pisTotalValue
         * @return
         */
        // public Builder withPISTotalValue(String pisTotalValue) {
        // this.pisTotalValue = pisTotalValue;
        // return this;
        // }

        /**
         * Valor do COFINS
         * 
         * @param cofinsTotalValue
         * @return
         */
        // public Builder withCOFINSTotalValue(String cofinsTotalValue) {
        // this.cofinsTotalValue = cofinsTotalValue;
        // return this;
        // }

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
    }

    public ICMSTotal(Builder builder) {
        this.icmsCalculationBasis = builder.icmsCalculationBasis;
        this.icmsTotalValue = builder.icmsTotalValue;
        this.icmsTotalDesoneration = builder.icmsTotalDesoneration;
        this.receiverUfFCPTotalValue = builder.receiverUfFCPTotalValue;
        this.receiverUfIcmsShareTotalValue = builder.receiverUfIcmsShareTotalValue;
        this.emitterUfIcmsShareTotalValue = builder.emitterUfIcmsShareTotalValue;
        this.icmsSTCalculationBasis = builder.icmsSTCalculationBasis;
        this.icmsSTTotalValue = builder.icmsSTTotalValue;
        this.itemsTotalValue = builder.itemsTotalValue;
        this.shippingTotalValue = builder.shippingTotalValue;
        this.insuranceTotalValue = builder.insuranceTotalValue;
        this.discountTotalValue = builder.discountTotalValue;
        this.otherIncidentalCostsTotalValue = builder.otherIncidentalCostsTotalValue;
        this.nfeTotalValue = builder.nfeTotalValue;
        this.taxTotalValue = builder.taxTotalValue;
    }

    public String getIcmsCalculationBasis() {
        return this.icmsCalculationBasis;
    }

    public void setIcmsCalculationBasis(String icmsCalculationBasis) {
        this.icmsCalculationBasis = icmsCalculationBasis;
    }

    public String getIcmsTotalValue() {
        return this.icmsTotalValue;
    }

    public void setIcmsTotalValue(String icmsTotalValue) {
        this.icmsTotalValue = icmsTotalValue;
    }

    public String getIcmsTotalDesoneration() {
        return this.icmsTotalDesoneration;
    }

    public void setIcmsTotalDesoneration(String icmsTotalDesoneration) {
        this.icmsTotalDesoneration = icmsTotalDesoneration;
    }

    public String getReceiverUfFCPTotalValue() {
        return this.receiverUfFCPTotalValue;
    }

    public void setReceiverUfFCPTotalValue(String receiverUfFCPTotalValue) {
        this.receiverUfFCPTotalValue = receiverUfFCPTotalValue;
    }

    public String getReceiverUfIcmsShareTotalValue() {
        return this.receiverUfIcmsShareTotalValue;
    }

    public void setReceiverUfIcmsShareTotalValue(String receiverUfIcmsShareTotalValue) {
        this.receiverUfIcmsShareTotalValue = receiverUfIcmsShareTotalValue;
    }

    public String getEmitterUfIcmsShareTotalValue() {
        return this.emitterUfIcmsShareTotalValue;
    }

    public void setEmitterUfIcmsShareTotalValue(String emitterUfIcmsShareTotalValue) {
        this.emitterUfIcmsShareTotalValue = emitterUfIcmsShareTotalValue;
    }

    public String getIcmsSTCalculationBasis() {
        return this.icmsSTCalculationBasis;
    }

    public void setIcmsSTCalculationBasis(String icmsSTCalculationBasis) {
        this.icmsSTCalculationBasis = icmsSTCalculationBasis;
    }

    public String getIcmsSTTotalValue() {
        return this.icmsSTTotalValue;
    }

    public void setIcmsSTTotalValue(String icmsSTTotalValue) {
        this.icmsSTTotalValue = icmsSTTotalValue;
    }

    public String getItemsTotalValue() {
        return this.itemsTotalValue;
    }

    public void setItemsTotalValue(String itemsTotalValue) {
        this.itemsTotalValue = itemsTotalValue;
    }

    public String getShippingTotalValue() {
        return this.shippingTotalValue;
    }

    public void setShippingTotalValue(String shippingTotalValue) {
        this.shippingTotalValue = shippingTotalValue;
    }

    public String getInsuranceTotalValue() {
        return this.insuranceTotalValue;
    }

    public void setInsuranceTotalValue(String insuranceTotalValue) {
        this.insuranceTotalValue = insuranceTotalValue;
    }

    public String getDiscountTotalValue() {
        return this.discountTotalValue;
    }

    public void setDiscountTotalValue(String discountTotalValue) {
        this.discountTotalValue = discountTotalValue;
    }

    public String getIiTotalValue() {
        return this.iiTotalValue;
    }

    public void setIiTotalValue(String iiTotalValue) {
        this.iiTotalValue = iiTotalValue;
    }

    public String getIpiTotalValue() {
        return this.ipiTotalValue;
    }

    public void setIpiTotalValue(String ipiTotalValue) {
        this.ipiTotalValue = ipiTotalValue;
    }

    public String getPisTotalValue() {
        return this.pisTotalValue;
    }

    public void setPisTotalValue(String pisTotalValue) {
        this.pisTotalValue = pisTotalValue;
    }

    public String getCofinsTotalValue() {
        return this.cofinsTotalValue;
    }

    public void setCofinsTotalValue(String cofinsTotalValue) {
        this.cofinsTotalValue = cofinsTotalValue;
    }

    public String getOtherIncidentalCostsTotalValue() {
        return this.otherIncidentalCostsTotalValue;
    }

    public void setOtherIncidentalCostsTotalValue(String otherIncidentalCostsTotalValue) {
        this.otherIncidentalCostsTotalValue = otherIncidentalCostsTotalValue;
    }

    public String getNfeTotalValue() {
        return this.nfeTotalValue;
    }

    public void setNfeTotalValue(String nfeTotalValue) {
        this.nfeTotalValue = nfeTotalValue;
    }

    public String getTaxTotalValue() {
        return this.taxTotalValue;
    }

    public void setTaxTotalValue(String taxTotalValue) {
        this.taxTotalValue = taxTotalValue;
    }

}
