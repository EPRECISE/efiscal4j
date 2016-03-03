
package eprecise.efiscal4j.nfe.refdocuments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.types.NFeFiscalDocumentNumber;
import eprecise.efiscal4j.nfe.types.NFeFiscalDocumentSeries;


/**
 * Dados da NF modelo 1/1A referenciada
 * 
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferencedNF implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "cUF") @NotNull final UF emitterUf;

    private @XmlElement(name = "AAMM") @NotNull @Pattern(regexp = "[0-9]{2}[0]{1}[1-9]{1}|[0-9]{2}[1]{1}[0-2]{1}") final String emissionYearMonth;

    private @XmlElement(name = "CNPJ") @NotNull @NFeCNPJ final String emitterCnpj;

    private @XmlElement(name = "mod") @NotNull final String model = "01";

    private @XmlElement(name = "serie") @NotNull @NFeFiscalDocumentSeries String series = "0";

    private @XmlElement(name = "nNF") @NotNull @NFeFiscalDocumentNumber final String number;

    public static class Builder {

        private UF emitterUf;

        private String emissionYearMonth;

        private String emitterCnpj;

        private String series;

        private String number;

        /**
         * Código da UF do emitente do Documento Fiscal. Utilizar a Tabela do IBGE.
         * 
         * @param emitterUf
         * @return
         */
        public Builder withEmitterUf(UF emitterUf) {
            this.emitterUf = emitterUf;
            return this;
        }

        /**
         * 
         * @param emissionYearMonth
         * @return
         * 
         */
        public Builder withEmissionDate(String emissionYearMonth) {
            this.emissionYearMonth = emissionYearMonth;
            return this;
        }

        /**
         * CNPJ do emitente do documento fiscal referenciado
         * 
         * @param emitterCnpj
         * @return
         */
        public Builder withEmitterCnpj(String emitterCnpj) {
            this.emitterCnpj = emitterCnpj;
            return this;
        }

        /**
         * Série do Documento Fiscal, informar zero se inexistente
         * 
         * @param series
         * @return
         */
        public Builder withSeries(String series) {
            this.series = series;
            return this;
        }

        /**
         * Número do Documento Fiscal
         * 
         * @param number
         * @return
         */
        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public ReferencedNF build() {
            final ReferencedNF entity = new ReferencedNF(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ReferencedNF() {
        this.emitterUf = null;
        this.emissionYearMonth = null;
        this.emitterCnpj = null;
        this.series = null;
        this.number = null;
    }

    public ReferencedNF(Builder builder) {
        this.emitterUf = builder.emitterUf;
        this.emissionYearMonth = builder.emissionYearMonth;
        this.emitterCnpj = builder.emitterCnpj;
        this.series = builder.series;
        this.number = builder.number;
    }

    public UF getEmitterUf() {
        return this.emitterUf;
    }

    public String getSeries() {
        return this.series;
    }

    public String getEmissionYearMonth() {
        return this.emissionYearMonth;
    }

    public String getEmitterCnpj() {
        return this.emitterCnpj;
    }

    public String getModel() {
        return this.model;
    }

    public String getNumber() {
        return this.number;
    }

}
