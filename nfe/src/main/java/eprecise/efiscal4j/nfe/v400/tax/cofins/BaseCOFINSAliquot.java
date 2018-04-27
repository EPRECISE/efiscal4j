
package eprecise.efiscal4j.nfe.v400.tax.cofins;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;


/**
 * Classe base para os COFINS com CST de alíquota (01 e 02)
 * 
 * @see BaseCOFINS
 * @see COFINS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BaseCOFINSAliquot extends BaseCOFINS {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pCOFINS") @NotNull @NFeDecimal0302a04 final String cofinsAliquot;

    private @XmlElement(name = "vCOFINS") @NotNull @NFeDecimal1302 final String cofinsValue;

    static abstract class Builder extends BaseCOFINS.Builder {

        private String bcValue;

        private String cofinsAliquot;

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
        abstract BaseCOFINSAliquot build();
    }

    public BaseCOFINSAliquot() {
        super();
        this.bcValue = null;
        this.cofinsAliquot = null;
        this.cofinsValue = null;
    }

    protected BaseCOFINSAliquot(final Builder builder, final String cst) {
        super(cst);
        this.bcValue = builder.bcValue;
        this.cofinsAliquot = builder.cofinsAliquot;
        this.cofinsValue = builder.cofinsValue;
    }

    public String getBcValue() {
        return this.bcValue;
    }

    public String getCofinsAliquot() {
        return this.cofinsAliquot;
    }

    @Override
    public String getCofinsValue() {
        return this.cofinsValue;
    }

}
