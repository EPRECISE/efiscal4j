
package eprecise.efiscal4j.nfe.tax.pis;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.tax.pis.validation.BasePISOtherStandard;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal1104;
import eprecise.efiscal4j.nfe.types.NFeDecimal1204;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302Optional;


/**
 * Dados do PIS Substituição Tributária
 * 
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PISST implements Serializable, BasePISOtherStandard {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBC") @NFeDecimal1302Optional final String bcValue;

    private @XmlElement(name = "pPIS") @NFeDecimal0302a04 final String pisAliquot;

    private @XmlElement(name = "qBCProd") @NFeDecimal1204 final String productQuantity;

    private @XmlElement(name = "vAliqProd") @NFeDecimal1104 final String productAliquot;

    private @XmlElement(name = "vPIS") @NotNull @NFeDecimal1302 final String pisValue;

    public static class Builder {

        private String bcValue;

        private String pisAliquot;

        private String productQuantity;

        private String productAliquot;

        private String pisValue;

        /**
         * Valor da BC do PIS ST
         * 
         * @param bcValue
         * @return
         */
        public Builder withBcValue(String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Alíquota do PIS ST (em percentual)
         * 
         * @param pisAliquot
         * @return
         */
        public Builder withPisAliquot(String pisAliquot) {
            this.pisAliquot = pisAliquot;
            return this;
        }

        /**
         * Quantidade Vendida
         * 
         * @param productQuantity
         * @return
         */
        public Builder withProductQuantity(String productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        /**
         * Alíquota do PIS ST (em reais)
         * 
         * @param productAliquot
         * @return
         */
        public Builder withProductAliquot(String productAliquot) {
            this.productAliquot = productAliquot;
            return this;
        }

        /**
         * Valor do PIS ST
         * 
         * @param pisValue
         * @return
         */
        public Builder withPisValue(String pisValue) {
            this.pisValue = pisValue;
            return this;
        }

        public PISST build() {
            final PISST entity = new PISST(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public PISST() {
        this.bcValue = null;
        this.pisAliquot = null;
        this.productQuantity = null;
        this.productAliquot = null;
        this.pisValue = null;
    }

    public PISST(Builder builder) {
        this.bcValue = builder.bcValue;
        this.pisAliquot = builder.pisAliquot;
        this.productQuantity = builder.productQuantity;
        this.productAliquot = builder.productAliquot;
        this.pisValue = builder.pisValue;
    }

    @Override
    public String getBcValue() {
        return this.bcValue;
    }

    @Override
    public String getPisAliquot() {
        return this.pisAliquot;
    }

    @Override
    public String getProductQuantity() {
        return this.productQuantity;
    }

    @Override
    public String getProductAliquot() {
        return this.productAliquot;
    }

    @Override
    public String getPisValue() {
        return this.pisValue;
    }

}
