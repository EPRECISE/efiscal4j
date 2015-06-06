
package eprecise.efiscal4j.nfe.tax.pis;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.types.NFeDecimal0302a04;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Classe base para os PIS com CST de alíquota (01 e 02)
 * 
 * @see BasePIS
 * @see PIS
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
abstract class BasePISAliquot extends BasePIS {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "pPIS") @NotNull @NFeDecimal0302a04 final String pisAliquot;

    private @XmlElement(name = "vPIS") @NotNull @NFeDecimal1302 final String pisValue;

    static abstract class Builder extends BasePIS.Builder {

        private String bcValue;

        private String pisAliquot;

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
        abstract BasePISAliquot build();
    }

    protected BasePISAliquot() {
        super();
        this.bcValue = null;
        this.pisAliquot = null;
        this.pisValue = null;
    }

    protected BasePISAliquot(Builder builder, String cst) {
        super(cst);
        this.bcValue = builder.bcValue;
        this.pisAliquot = builder.pisAliquot;
        this.pisValue = builder.pisValue;
    }

    public String getBcValue() {
        return this.bcValue;
    }

    public String getPisAliquot() {
        return this.pisAliquot;
    }

    @Override
    public String getPisValue() {
        return this.pisValue;
    }

}
