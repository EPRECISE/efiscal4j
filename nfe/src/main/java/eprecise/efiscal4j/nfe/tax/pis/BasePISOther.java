
package eprecise.efiscal4j.nfe.tax.pis;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.tax.pis.validation.BasePISOtherStandard;
import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal1104Variable;
import eprecise.efiscal4j.nfe.types.NFeDecimal1204Variable;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Classe base para os PIS com os outros CST (49, 50, 51, 52, 53, 54, 55, 56, 60, 61, 62, 63, 64, 65, 66, 67, 70, 71, 72, 73, 74, 75, 98, 99)
 * 
 * @see BasePISOtherStandard
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BasePISOther extends BasePIS implements BasePISOtherStandard {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBC") @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pPIS") @NFeDecimal0302a04 final String pisAliquot;

    private @XmlElement(name = "qBCProd") @NFeDecimal1204Variable final String productQuantity;

    private @XmlElement(name = "vAliqProd") @NFeDecimal1104Variable final String productAliquot;

    private @XmlElement(name = "vPIS") @NotNull @NFeDecimal1302 final String pisValue;

    static abstract class Builder extends BasePIS.Builder {

        private String bcValue;

        private String pisAliquot;

        private String productQuantity;

        private String productAliquot;

        private String pisValue;

        /**
         * Valor da BC do PIS
         * 
         * @param bcValue
         * @return
         */
        public Builder withBcValue(String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Alíquota do PIS (em percentual)
         * 
         * @param pisAliquot
         * @return
         */
        public Builder withPisAliquot(String pisAliquot) {
            this.pisAliquot = pisAliquot;
            return this;
        }

        /**
         * Quantidade Vendida (NT2011/004)
         * 
         * @param productQuantity
         * @return
         */
        public Builder withProductQuantity(String productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        /**
         * Alíquota do PIS (em reais) (NT2011/004)
         * 
         * @param productAliquot
         * @return
         */
        public Builder withProductAliquot(String productAliquot) {
            this.productAliquot = productAliquot;
            return this;
        }

        /**
         * Valor do PIS
         * 
         * @param pisValue
         * @return
         */
        public Builder withPisValue(String pisValue) {
            this.pisValue = pisValue;
            return this;
        }

        @Override
        abstract BasePISOther build();
    }

    protected BasePISOther() {
        super(null);
        this.bcValue = null;
        this.pisAliquot = null;
        this.productQuantity = null;
        this.productAliquot = null;
        this.pisValue = null;
    }

    protected BasePISOther(Builder builder, String cst) {
        super(cst);
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
