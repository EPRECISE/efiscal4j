
package eprecise.efiscal4j.nfe.v400.tax.cofins;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.tax.cofins.validation.BaseCOFINSOtherStandard;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1104Variable;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1204Variable;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Classe base para os COFINS com os outros CST (49, 50, 51, 52, 53, 54, 55, 56, 60, 61, 62, 63, 64, 65, 66, 67, 70, 71, 72, 73, 74, 75, 98, 99)
 *
 * @see BaseCOFINSOtherStandard
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BaseCOFINSOther extends BaseCOFINS implements BaseCOFINSOtherStandard {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBC") @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pCOFINS") @NFeDecimal0302a04 final String cofinsAliquot;

    private @XmlElement(name = "qBCProd") @NFeDecimal1204Variable final String productQuantity;

    private @XmlElement(name = "vAliqProd") @NFeDecimal1104Variable final String productAliquot;

    private @XmlElement(name = "vCOFINS") @NFeDecimal1302 final String cofinsValue;

    static abstract class Builder extends BaseCOFINS.Builder {

        private String bcValue;

        private String cofinsAliquot;

        private String productQuantity;

        private String productAliquot;

        private String cofinsValue;

        /**
         * Valor da BC do COFINS
         *
         * @param bcValue
         * @return
         */
        public Builder withBcValue(final String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Alíquota do COFINS (em percentual)
         *
         * @param cofinsAliquot
         * @return
         */
        public Builder withCofinsAliquot(final String cofinsAliquot) {
            this.cofinsAliquot = cofinsAliquot;
            return this;
        }

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
        abstract BaseCOFINSOther build();
    }

    public BaseCOFINSOther() {
        super();
        this.bcValue = null;
        this.cofinsAliquot = null;
        this.productQuantity = null;
        this.productAliquot = null;
        this.cofinsValue = null;
    }

    protected BaseCOFINSOther(final Builder builder, final String cst) {
        super(cst);
        this.bcValue = builder.bcValue;
        this.cofinsAliquot = builder.cofinsAliquot;
        this.productQuantity = builder.productQuantity;
        this.productAliquot = builder.productAliquot;
        this.cofinsValue = builder.cofinsValue;
    }

    @Override
    public String getBcValue() {
        return this.bcValue;
    }

    @Override
    public String getCofinsAliquot() {
        return this.cofinsAliquot;
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
    public String getCofinsValue() {
        return this.cofinsValue;
    }
}
