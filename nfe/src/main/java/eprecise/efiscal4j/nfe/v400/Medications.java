
package eprecise.efiscal4j.nfe.v400;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Grupo do detalhamento de Medicamentos e de matérias-primas farmacêuticas
 *
 * @author Fernando C Glizt
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Medications implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "cProdANVISA") @NotNull @Size(max = 13) @NFeString final String anvisaProductCode;

    private @XmlElement(name = "vPMC") @NotNull @NFeDecimal1302 final String maxPriceConsumers;

    public static class Builder {

        private String anvisaProductCode;

        private String maxPriceConsumers;

        /**
         * Código de Produto da ANVISA. Utilizar o número do registro do produto da Câmara de Regulação do Mercado de Medicamento – CMED.
         *
         * @param anvisaProductCode
         * @return
         */
        public Builder withAnvisaProductCode(final String anvisaProductCode) {
            this.anvisaProductCode = anvisaProductCode;
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
        this.anvisaProductCode = null;
        this.maxPriceConsumers = null;
    }

    public Medications(final Builder builder) {
        this.anvisaProductCode = builder.anvisaProductCode;
        this.maxPriceConsumers = builder.maxPriceConsumers;
    }

    public String getAnvisaProductCode() {
        return anvisaProductCode;
    }

    public String getMaxPriceConsumers() {
        return maxPriceConsumers;
    }
}
