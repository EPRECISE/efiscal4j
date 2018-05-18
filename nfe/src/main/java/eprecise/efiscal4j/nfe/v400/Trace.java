
package eprecise.efiscal4j.nfe.v400;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeDate;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal0803Variable;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Grupo para permitir a que os itens da nota sejam rastreados.
 *
 * @author Fernando C Glizt
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Trace implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nLote") @NotNull @Size(min = 1, max = 20) @NFeString final String batchNumber;

    private @XmlElement(name = "qLote") @NotNull @NFeDecimal0803Variable final String batchQuantity;

    private @XmlElement(name = "dFab") @NotNull @NFeDate final String manufacturingDate;

    private @XmlElement(name = "dVal") @NotNull @NFeDate final String expirationDate;

    private @XmlElement(name = "cAgreg") @NFeString @Pattern(regexp = "[0-9]{1,20}") final String aggregationCode;

    public static class Builder {

        private String batchNumber;

        private String batchQuantity;

        private String manufacturingDate;

        private String expirationDate;

        private String aggregationCode;

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
         * Código de Agregação
         *
         * @param aggregationCode
         * @return
         */
        public Builder withAggregationCode(final String aggregationCode) {
            this.aggregationCode = aggregationCode;
            return this;
        }

        public Trace build() {
            final Trace entity = new Trace(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Trace() {
        this.batchNumber = null;
        this.batchQuantity = null;
        this.manufacturingDate = null;
        this.expirationDate = null;
        this.aggregationCode = null;
    }

    public Trace(final Builder builder) {
        this.batchNumber = builder.batchNumber;
        this.batchQuantity = builder.batchQuantity;
        this.manufacturingDate = builder.manufacturingDate;
        this.expirationDate = builder.expirationDate;
        this.aggregationCode = builder.aggregationCode;
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

    public String getAggregationCode() {
        return aggregationCode;
    }
}
