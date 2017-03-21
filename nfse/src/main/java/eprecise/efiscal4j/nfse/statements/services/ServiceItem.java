
package eprecise.efiscal4j.nfse.statements.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.types.NFSeUnitaryValue;
import eprecise.efiscal4j.nfse.types.NFSeValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceItem {

    private final @XmlElement(name = "ItemListaServico") @NotNull @Size(min = 1, max = 6) String itemServiceList;

    private final @XmlElement(name = "CodigoCnae") @NotNull @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cnaeCode;

    private final @XmlElement(name = "Descricao") @NotNull @Size(min = 1, max = 20) String description;

    private final @XmlElement(name = "Tributavel") @NotNull ServiceItemTaxable taxable;

    private final @XmlElement(name = "Quantidade") @NotNull @NFSeValue String quantity;

    private final @XmlElement(name = "ValorUnitario") @NotNull @NFSeUnitaryValue String unitaryValue;

    private final @XmlElement(name = "ValorDesconto") @NFSeValue String discountValue;

    private final @XmlElement(name = "ValorLiquido") @NotNull @NFSeValue String netValue;

    private final @XmlElement(name = "DadosDeducao") DeductionData deductionData;

    public static class Builder {

        private String itemServiceList;

        private String cnaeCode;

        private String description;

        private ServiceItemTaxable taxable;

        private String quantity;

        private String unitaryValue;

        private String discountValue;

        private String netValue;

        private DeductionData deductionData;

        /**
         * @param itemServiceList
         * @return
         */
        public Builder withItemServiceList(final String itemServiceList) {
            this.itemServiceList = itemServiceList;
            return this;
        }

        /**
         * @param cnaeCode
         * @return
         */
        public Builder withCnaeCode(final String cnaeCode) {
            this.cnaeCode = cnaeCode;
            return this;
        }

        /**
         * @param description
         * @return
         */
        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @param taxable
         * @return
         */
        public Builder withTaxable(final ServiceItemTaxable taxable) {
            this.taxable = taxable;
            return this;
        }

        /**
         * @param quantity
         * @return
         */
        public Builder withQuantity(final String quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * @param unitaryValue
         * @return
         */
        public Builder withUnitaryValue(final String unitaryValue) {
            this.unitaryValue = unitaryValue;
            return this;
        }

        /**
         * @param discountValue
         * @return
         */
        public Builder withDiscountValue(final String discountValue) {
            this.discountValue = discountValue;
            return this;
        }

        /**
         * @param netValue
         * @return
         */
        public Builder withNetValue(final String netValue) {
            this.netValue = netValue;
            return this;
        }

        /**
         * @param deductionData
         * @return
         */
        public Builder withDeductionData(final DeductionData deductionData) {
            this.deductionData = deductionData;
            return this;
        }

        public ServiceItem build() throws Exception {
            final ServiceItem entity = new ServiceItem(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ServiceItem() {
        this.itemServiceList = null;
        this.cnaeCode = null;
        this.description = null;
        this.taxable = null;
        this.quantity = null;
        this.unitaryValue = null;
        this.discountValue = null;
        this.netValue = null;
        this.deductionData = null;
    }

    public ServiceItem(final Builder builder) {
        this.itemServiceList = builder.itemServiceList;
        this.cnaeCode = builder.cnaeCode;
        this.description = builder.description;
        this.taxable = builder.taxable;
        this.quantity = builder.quantity;
        this.unitaryValue = builder.unitaryValue;
        this.discountValue = builder.discountValue;
        this.netValue = builder.netValue;
        this.deductionData = builder.deductionData;
    }

    public String getItemServiceList() {
        return itemServiceList;
    }

    public String getCnaeCode() {
        return cnaeCode;
    }

    public String getDescription() {
        return description;
    }

    public ServiceItemTaxable getTaxable() {
        return taxable;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnitaryValue() {
        return unitaryValue;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public String getNetValue() {
        return netValue;
    }

    public DeductionData getDeductionData() {
        return deductionData;
    }

}
