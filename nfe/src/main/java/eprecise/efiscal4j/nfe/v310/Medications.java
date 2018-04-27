
package eprecise.efiscal4j.nfe.v310;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeDate;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal0803Variable;
import eprecise.efiscal4j.nfe.v310.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Grupo do detalhamento de Medicamentos e de matérias-primas farmacêuticas
 *
 * @author Fernando C Glizt
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Medications implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nLote") @NotNull @Size(min = 1, max = 20) @NFeString final String batchNumber;

    private @XmlElement(name = "qLote") @NotNull @NFeDecimal0803Variable final String batchQuantity;

    private @XmlElement(name = "dFab") @NotNull @NFeDate final String manufacturingDate;

    private @XmlElement(name = "dVal") @NotNull @NFeDate final String expirationDate;

    private @XmlElement(name = "vPMC") @NotNull @NFeDecimal1302 final String maxPriceConsumers;

    public static class Builder {

        private String batchNumber;

        private String batchQuantity;

        private String manufacturingDate;

        private String expirationDate;

        private String maxPriceConsumers;

        /**
         * Número do lote do medicamento
         *
         * @param batchNumber
         * @return
         */
        public Builder withBatchNumber(final String batchNumber) {
            this.batchNumber = batchNumber;
            return this;
        }

        /**
         * Quantidade de produtos no lote
         *
         * @param batchQuantity
         * @return
         */
        public Builder withBatchQuantity(final String batchQuantity) {
            this.batchQuantity = batchQuantity;
            return this;
        }

        /**
         * Data de Fabricação do medicamento (AAAA-MM-DD)
         *
         * @param manufacturingDate
         * @return
         */
        public Builder withManufacturingDate(final String manufacturingDate) {
            this.manufacturingDate = manufacturingDate;
            return this;
        }

        /**
         * Data de validade do medicamento (AAAA-MM-DD)
         *
         * @param expirationDate
         * @return
         */
        public Builder withExpirationDate(final String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        /**
         * Preço Máximo ao Consumidor
         *
         * @param maxPriceConsumers
         * @return
         */
        public Builder withMaxPriceConsumers(final String maxPriceConsumers) {
            this.maxPriceConsumers = maxPriceConsumers;
            return this;
        }

        public Medications build() {
            final Medications entity = new Medications(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Medications() {
        this.batchNumber = null;
        this.batchQuantity = null;
        this.manufacturingDate = null;
        this.expirationDate = null;
        this.maxPriceConsumers = null;
    }

    public Medications(final Builder builder) {
        this.batchNumber = builder.batchNumber;
        this.batchQuantity = builder.batchQuantity;
        this.manufacturingDate = builder.manufacturingDate;
        this.expirationDate = builder.expirationDate;
        this.maxPriceConsumers = builder.maxPriceConsumers;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public String getBatchQuantity() {
        return batchQuantity;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getMaxPriceConsumers() {
        return maxPriceConsumers;
    }
}
