
package eprecise.efiscal4j.nfe.v400.fuel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1203;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Informações do grupo de encerrante
 *
 * @author Fernando C Glizt
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class FuelClosing implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nBico") @NotNull @NFeString @Pattern(regexp = "[0-9]{1,3}") final String nozzleNumber;

    private @XmlElement(name = "nBomba") @NFeString @Pattern(regexp = "[0-9]{1,3}") final String pumpNumber;

    private @XmlElement(name = "nTanque") @NotNull @NFeString @Pattern(regexp = "[0-9]{1,3}") final String tankNumber;

    private @XmlElement(name = "vEncIni") @NotNull @NFeDecimal1203 final String closingBeginValue;

    private @XmlElement(name = "vEncFin") @NotNull @NFeDecimal1203 final String closingEndValue;

    public static class Builder {

        private String nozzleNumber;

        private String pumpNumber;

        private String tankNumber;

        private String closingBeginValue;

        private String closingEndValue;

        /**
         * Numero de identificação do Bico utilizado no abastecimento
         *
         * @param nozzleNumber
         * @return
         */
        public Builder withNozzleNumber(final String nozzleNumber) {
            this.nozzleNumber = nozzleNumber;
            return this;
        }

        /**
         * Numero de identificação da bomba ao qual o bico está interligado
         *
         * @param pumpNumber
         * @return
         */
        public Builder withPumpNumber(final String pumpNumber) {
            this.pumpNumber = pumpNumber;
            return this;
        }

        /**
         * Numero de identificação do tanque ao qual o bico está interligado
         *
         * @param tankNumber
         * @return
         */
        public Builder withTankNumber(final String tankNumber) {
            this.tankNumber = tankNumber;
            return this;
        }

        /**
         * Valor do Encerrante no ínicio do abastecimento
         *
         * @param closingBeginValue
         * @return
         */
        public Builder withClosingBeginValue(final String closingBeginValue) {
            this.closingBeginValue = closingBeginValue;
            return this;
        }

        /**
         * Valor do Encerrante no final do abastecimento
         *
         * @param closingEndValue
         * @return
         */
        public Builder withClosingEndValue(final String closingEndValue) {
            this.closingEndValue = closingEndValue;
            return this;
        }

        public FuelClosing build() {
            final FuelClosing entity = new FuelClosing(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public FuelClosing() {
        this.nozzleNumber = null;
        this.pumpNumber = null;
        this.tankNumber = null;
        this.closingBeginValue = null;
        this.closingEndValue = null;
    }

    public FuelClosing(final Builder builder) {
        this.nozzleNumber = builder.nozzleNumber;
        this.pumpNumber = builder.pumpNumber;
        this.tankNumber = builder.tankNumber;
        this.closingBeginValue = builder.closingBeginValue;
        this.closingEndValue = builder.closingEndValue;
    }

    public String getNozzleNumber() {
        return nozzleNumber;
    }

    public String getPumpNumber() {
        return pumpNumber;
    }

    public String getTankNumber() {
        return tankNumber;
    }

    public String getClosingBeginValue() {
        return closingBeginValue;
    }

    public String getClosingEndValue() {
        return closingEndValue;
    }

}
