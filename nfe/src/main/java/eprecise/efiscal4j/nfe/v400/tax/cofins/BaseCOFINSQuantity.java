
package eprecise.efiscal4j.nfe.v400.tax.cofins;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1104Variable;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1204Variable;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Classe base para os COFINS com CST de quantidade (03)
 * 
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BaseCOFINSQuantity extends BaseCOFINS {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "qBCProd") @NotNull @NFeDecimal1204Variable final String productQuantity;

    private @XmlElement(name = "vAliqProd") @NotNull @NFeDecimal1104Variable final String productAliquot;

    private @XmlElement(name = "vCOFINS") @NotNull @NFeDecimal1302 final String cofinsValue;

    static abstract class Builder extends BaseCOFINS.Builder {

        private String productQuantity;

        private String productAliquot;

        private String cofinsValue;

        /**
         * Quantidade Vendida (NT2011/004)
         * 
         * @param productQuantity
         * @return
         */
        public Builder withProductQuantity(final String productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        /**
         * Alíquota do COFINS (em reais) (NT2011/004)
         * 
         * @param productAliquot
         * @return
         */
        public Builder withProductAliquot(final String productAliquot) {
            this.productAliquot = productAliquot;
            return this;
        }

        /**
         * Valor do COFINS
         * 
         * @param cofinsValue
         * @return
         */
        public Builder withCofinsValue(final String cofinsValue) {
            this.cofinsValue = cofinsValue;
            return this;
        }

        @Override
        abstract BaseCOFINSQuantity build();
    }

    public BaseCOFINSQuantity() {
        super();
        this.productQuantity = null;
        this.productAliquot = null;
        this.cofinsValue = null;
    }

    protected BaseCOFINSQuantity(final Builder builder, final String cst) {
        super(cst);
        this.productQuantity = builder.productQuantity;
        this.productAliquot = builder.productAliquot;
        this.cofinsValue = builder.cofinsValue;
    }

    public String getProductQuantity() {
        return this.productQuantity;
    }

    public String getProductAliquot() {
        return this.productAliquot;
    }

    @Override
    public String getCofinsValue() {
        return this.cofinsValue;
    }
}
