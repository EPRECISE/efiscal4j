
package eprecise.efiscal4j.nfe.item.di;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302Optional;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Adições (NT 2011/004)
 *
 * @author Felipe Bueno
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Addition implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nAdicao") @NotNull @Pattern(regexp = "[1-9]{1}[0-9]{0,2}") final String number;

    private @XmlElement(name = "nSeqAdic") @NotNull @Pattern(regexp = "[1-9]{1}[0-9]{0,2}") final String sequence;

    private @XmlElement(name = "cFabricante") @NotNull @Size(min = 1, max = 60) @NFeString final String manufacturerCode;

    private @XmlElement(name = "vDescDI") @NFeDecimal1302Optional final String discountValue;

    private @XmlElement(name = "nDraw") @Pattern(regexp = "[0-9]{0,11}") final String drawbackNumber;

    public static class Builder {

        private String number;

        private String sequence;

        private String manufacturerCode;

        private String discountValue;

        private String drawbackNumber;

        /**
         * Número da Adição
         * 
         * @param number
         * @return
         */
        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        /**
         * Número seqüencial do item dentro da Adição
         * 
         * @param sequence
         * @return
         */
        public Builder withSequence(String sequence) {
            this.sequence = sequence;
            return this;
        }

        /**
         * Código do fabricante estrangeiro (usado nos sistemas internos de informação do emitente da NF-e)
         * 
         * @param manufacturerCode
         * @return
         */
        public Builder withManufacturerCode(String manufacturerCode) {
            this.manufacturerCode = manufacturerCode;
            return this;
        }

        /**
         * Valor do desconto do item da DI – adição
         * 
         * @param discountValue
         * @return
         */
        public Builder withDiscountValue(String discountValue) {
            this.discountValue = discountValue;
            return this;
        }

        /**
         * Número do ato concessório de Drawback
         * 
         * @param drawbackNumber
         * @return
         */
        public Builder withDrawbackNumber(String drawbackNumber) {
            this.drawbackNumber = drawbackNumber;
            return this;
        }

        public Addition build() {
            final Addition entity = new Addition(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public Addition() {
        this.number = null;
        this.sequence = null;
        this.manufacturerCode = null;
        this.discountValue = null;
        this.drawbackNumber = null;
    }

    public Addition(Builder builder) {
        this.number = builder.number;
        this.sequence = builder.sequence;
        this.manufacturerCode = builder.manufacturerCode;
        this.discountValue = builder.discountValue;
        this.drawbackNumber = builder.drawbackNumber;
    }

    public String getNumber() {
        return this.number;
    }

    public String getSequence() {
        return this.sequence;
    }

    public String getManufacturerCode() {
        return this.manufacturerCode;
    }

    public String getDiscountValue() {
        return this.discountValue;
    }

    public String getDrawbackNumber() {
        return this.drawbackNumber;
    }

}
