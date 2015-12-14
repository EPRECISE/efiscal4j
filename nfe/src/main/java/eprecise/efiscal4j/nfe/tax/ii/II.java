
package eprecise.efiscal4j.nfe.tax.ii;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.tax.MainTax;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;


/**
 * Dados do Imposto de Importação
 * 
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class II extends MainTax implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "vBC") @NotNull @NFeDecimal1302 final String bcValue;

    private @XmlElement(name = "vDespAdu") @NotNull @NFeDecimal1302 final String customsCharge;

    private @XmlElement(name = "vII") @NotNull @NFeDecimal1302 final String iiValue;

    private @XmlElement(name = "vIOF") @NotNull @NFeDecimal1302 final String iofValue;

    public static class Builder {

        private String bcValue;

        private String customsCharge;

        private String iiValue;

        private String iofValue;

        /**
         * Base da BC do Imposto de Importação
         * 
         * @param bcValue
         * @return
         */
        public Builder withBcValue(final String bcValue) {
            this.bcValue = bcValue;
            return this;
        }

        /**
         * Valor das despesas aduaneiras
         * 
         * @param customsCharge
         * @return
         */
        public Builder withCustomsCharge(final String customsCharge) {
            this.customsCharge = customsCharge;
            return this;
        }

        /**
         * Valor do Imposto de Importação
         * 
         * @param iiValue
         * @return
         */
        public Builder withIiValue(final String iiValue) {
            this.iiValue = iiValue;
            return this;
        }

        /**
         * Valor do Imposto sobre Operações Financeiras
         * 
         * @param iofValue
         * @return
         */
        public Builder withIofValue(final String iofValue) {
            this.iofValue = iofValue;
            return this;
        }

        public II build() {
            final II entity = new II(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public II() {
        this.bcValue = null;
        this.customsCharge = null;
        this.iiValue = null;
        this.iofValue = null;
    }

    protected II(final Builder builder) {
        this.bcValue = builder.bcValue;
        this.customsCharge = builder.customsCharge;
        this.iiValue = builder.iiValue;
        this.iofValue = builder.iofValue;
    }

    public String getBcValue() {
        return this.bcValue;
    }

    public String getCustomsCharge() {
        return this.customsCharge;
    }

    public String getIiValue() {
        return this.iiValue;
    }

    public String getIofValue() {
        return this.iofValue;
    }

}
