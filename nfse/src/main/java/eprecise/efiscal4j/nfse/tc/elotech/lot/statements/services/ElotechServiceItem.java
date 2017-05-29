
package eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeValue;
import eprecise.efiscal4j.nfse.ts.elotech.types.NFSeUnitaryValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechServiceItem {

    private final @XmlElement(name = "ItemListaServico") @NotNull @Size(min = 1, max = 6) String itemServiceList;

    private final @XmlElement(name = "CodigoCnae") @NotNull @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cnaeCode;

    private final @XmlElement(name = "Descricao") @NotNull @Size(min = 1, max = 20) String description;

    private final @XmlElement(name = "Tributavel") @NotNull ElotechServiceItemTaxable taxable;

    private final @XmlElement(name = "Quantidade") @NotNull @NFSeValue String quantity;

    private final @XmlElement(name = "ValorUnitario") @NotNull @NFSeUnitaryValue String unitaryValue;

    private final @XmlElement(name = "ValorDesconto") @NFSeValue String discountValue;

    private final @XmlElement(name = "ValorLiquido") @NotNull @NFSeValue String netValue;

    private final @XmlElement(name = "DadosDeducao") ElotechDeductionData deductionData;

    public static class Builder {

        private String itemServiceList;

        private String cnaeCode;

        private String description;

        private ElotechServiceItemTaxable taxable;

        private String quantity;

        private String unitaryValue;

        private String discountValue;

        private String netValue;

        private ElotechDeductionData deductionData;

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
        public Builder withTaxable(final ElotechServiceItemTaxable taxable) {
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
        public Builder withDeductionData(final ElotechDeductionData deductionData) {
            this.deductionData = deductionData;
            return this;
        }

        public ElotechServiceItem build() {
            final ElotechServiceItem entity = new ElotechServiceItem(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechServiceItem() {
        itemServiceList = null;
        cnaeCode = null;
        description = null;
        taxable = null;
        quantity = null;
        unitaryValue = null;
        discountValue = null;
        netValue = null;
        deductionData = null;
    }

    public ElotechServiceItem(final Builder builder) {
        itemServiceList = builder.itemServiceList;
        cnaeCode = builder.cnaeCode;
        description = builder.description;
        taxable = builder.taxable;
        quantity = builder.quantity;
        unitaryValue = builder.unitaryValue;
        discountValue = builder.discountValue;
        netValue = builder.netValue;
        deductionData = builder.deductionData;
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

    public ElotechServiceItemTaxable getTaxable() {
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

    public ElotechDeductionData getDeductionData() {
        return deductionData;
    }

}
