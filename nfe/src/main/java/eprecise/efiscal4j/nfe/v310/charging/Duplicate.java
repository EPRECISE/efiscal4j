
package eprecise.efiscal4j.nfe.v310.charging;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeDate;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1302Optional;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Dados das duplicatas NT 2011/004
 *
 * @author Felipe Bueno
 *
 */
public class Duplicate implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nDup") @Size(min = 1, max = 60) @NFeString String number;

    private @XmlElement(name = "dVenc") @NFeDate String dueDate;

    private @XmlElement(name = "vDup") @NotNull @NFeDecimal1302Optional final String value;

    public static class Builder {

        private String number;

        private String dueDate;

        private String value;

        /**
         * Número da duplicata
         * 
         * @param number
         * @return
         */
        public Builder withNumber(final String number) {
            this.number = number;
            return this;
        }

        /**
         * Data de vencimento da duplicata (AAAA-MM-DD)
         * 
         * @param dueDate
         * @return
         */
        public Builder withDueDate(final String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        /**
         * Valor da duplicata
         * 
         * @param value
         * @return
         */
        public Builder withValue(final String value) {
            this.value = value;
            return this;
        }

        public Duplicate build() {
            final Duplicate entity = new Duplicate(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Duplicate() {
        this.value = null;
    }

    public Duplicate(final Builder builder) {
        this.number = builder.number;
        this.dueDate = builder.dueDate;
        this.value = builder.value;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public String getValue() {
        return this.value;
    }

}
