
package eprecise.efiscal4j.nfe.tax.pis;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal1104Variable;
import eprecise.efiscal4j.nfe.types.NFeDecimal1204Variable;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Classe base para os PIS com CST de quantidade (03)
 * 
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BasePISQuantity extends BasePIS {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "qBCProd") @NotNull @NFeDecimal1204Variable final String productQuantity;

    private @XmlElement(name = "vAliqProd") @NotNull @NFeDecimal1104Variable final String productAliquot;

    private @XmlElement(name = "vPIS") @NotNull @NFeDecimal1302 final String pisValue;

    static abstract class Builder extends BasePIS.Builder {

        private String productQuantity;

        private String productAliquot;

        private String pisValue;

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
        abstract BasePISQuantity build();
    }

    protected BasePISQuantity() {
        super(null);
        this.productQuantity = null;
        this.productAliquot = null;
        this.pisValue = null;
    }

    protected BasePISQuantity(Builder builder, String cst) {
        super(cst);
        this.productQuantity = builder.productQuantity;
        this.productAliquot = builder.productAliquot;
        this.pisValue = builder.pisValue;
    }

    public String getProductQuantity() {
        return this.productQuantity;
    }

    public String getProductAliquot() {
        return this.productAliquot;
    }

    @Override
    public String getPisValue() {
        return this.pisValue;
    }
}
