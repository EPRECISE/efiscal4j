
package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.tax.ReturnedTax;
import eprecise.efiscal4j.nfe.tax.Tax;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Dados dos detalhes da NF-e
 * 
 * @author Felipe Bueno
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "nItem") @NotNull @Pattern(regexp = "[1-9]{1}[0-9]{0,1}|[1-8]{1}[0-9]{2}|[9]{1}[0-8]{1}[0-9]{1}|[9]{1}[9]{1}[0]{1}") final String itemOrder;

    private @XmlElement(name = "prod") @NotNull final NFeItem nFeItem;

    private @XmlElement(name = "imposto") @NotNull @Valid final Tax tax;

    private @XmlElement(name = "impostoDevol") ReturnedTax returnedTax;

    private @XmlElement(name = "infAdProd") @Size(min = 1, max = 500) @NFeString String additionalProductInfo;

    public static class Builder {

        private String itemOrder;

        private NFeItem nFeItem;

        private Tax tax;

        private String additionalProductInfo;

        private ReturnedTax returnedTax;

        /**
         * Número do item da NF-e
         */
        public Builder withItemOrder(String itemOrder) {
            this.itemOrder = itemOrder;
            return this;
        }

        /**
         * @see NFeItem
         * @param nFeItem
         * @return
         */
        public Builder withNFeItem(NFeItem nFeItem) {
            this.nFeItem = nFeItem;
            return this;
        }

        /**
         * @see Tax
         * @param tax
         * @return
         */
        public Builder withTax(Tax tax) {
            this.tax = tax;
            return this;
        }

        /**
         * Informações adicionais do produto (norma referenciada, informações complementares, etc)
         * 
         * @param additionalProductInfo
         * @return
         */
        public Builder withAdditionalProductInfo(String additionalProductInfo) {
            this.additionalProductInfo = additionalProductInfo;
            return this;
        }

        /**
         * @see ReturnedTax
         * @param returnedTax
         * @return
         */
        public Builder withReturnedTax(ReturnedTax returnedTax) {
            this.returnedTax = returnedTax;
            return this;
        }

        public NFeDetail build() {
            final NFeDetail entity = new NFeDetail(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDetail() {
        this.itemOrder = null;
        this.nFeItem = null;
        this.tax = null;
    }

    public NFeDetail(Builder builder) {
        this.itemOrder = builder.itemOrder;
        this.nFeItem = builder.nFeItem;
        this.tax = builder.tax;
        this.additionalProductInfo = builder.additionalProductInfo;
        this.returnedTax = builder.returnedTax;
    }

    public String getItemOrder() {
        return this.itemOrder;
    }

    public NFeItem getnFeItem() {
        return this.nFeItem;
    }

    public Tax getTax() {
        return this.tax;
    }

    public String getAdditionalProductInfo() {
        return this.additionalProductInfo;
    }

    public ReturnedTax getReturnedTax() {
        return this.returnedTax;
    }

}
