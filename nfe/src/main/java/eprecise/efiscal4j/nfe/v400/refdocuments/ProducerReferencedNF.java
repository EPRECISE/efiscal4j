
package eprecise.efiscal4j.nfe.v400.refdocuments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.v400.types.NFeCPF;
import eprecise.efiscal4j.nfe.v400.types.NFeFiscalDocumentNumber;
import eprecise.efiscal4j.nfe.v400.types.NFeFiscalDocumentSeries;


/**
 * Grupo com as informações NF de produtor referenciada
 * 
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ProducerReferencedNF implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "cUF") @NotNull final UF emitterUf;

    private @XmlElement(name = "AAMM") @NotNull @Pattern(regexp = "[0-9]{2}[0]{1}[1-9]{1}|[0-9]{2}[1]{1}[0-2]{1}") final String emissionYearMonth;

    private @XmlElement(name = "CNPJ") @NFeCNPJ final String emitterCnpj;

    private @XmlElement(name = "CPF") @NFeCPF final String emitterCpf;

    private @XmlElement(name = "IE") @NotNull final String stateRegistration;

    private @XmlElement(name = "mod") @NotNull final ProducerReferencedNFModel model;

    private @XmlElement(name = "serie") @NotNull @NFeFiscalDocumentSeries String series = "0";

    private @XmlElement(name = "nNF") @NotNull @NFeFiscalDocumentNumber final String number;

    public static class Builder {

        private UF emitterUf;

        private String emissionYearMonth;

        private String emitterCnpj;

        private String emitterCpf;

        private String stateRegistration;

        private ProducerReferencedNFModel model;

        private String series;

        private String number;

        /**
         * Código da UF do emitente do Documento FiscalUtilizar a Tabela do IBGE (Anexo IV - Tabela de UF, Município e País)
         * 
         * @param emitterUf
         * @return
         */
        public Builder withEmitterUf(UF emitterUf) {
            this.emitterUf = emitterUf;
            return this;
        }

        /**
         * Ano e mês de emissão - AAMM
         * 
         * @param emissionYearMonth
         * @return
         */
        public Builder withEmissionYearMonth(String emissionYearMonth) {
            this.emissionYearMonth = emissionYearMonth;
            return this;
        }

        /**
         * CNPJ do emitente da NF de produtor
         * 
         * @param emitterCnpj
         * @return
         */
        public Builder withEmitterCnpj(String emitterCnpj) {
            this.emitterCnpj = emitterCnpj;
            return this;
        }

        /**
         * CPF do emitente da NF de produtor
         * 
         * @param emitterCpf
         * @return
         */
        public Builder withEmitterCpf(String emitterCpf) {
            this.emitterCpf = emitterCpf;
            return this;
        }

        /**
         * IE do emitente da NF de Produtor
         * 
         * @param stateRegistration
         * @return
         */
        public Builder withStateRegistration(String stateRegistration) {
            this.stateRegistration = stateRegistration;
            return this;
        }

        /**
         * Código do modelo do Documento Fiscal
         * 
         * @param model
         * @return
         */
        public Builder withModel(ProducerReferencedNFModel model) {
            this.model = model;
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

        public ProducerReferencedNF build() {
            final ProducerReferencedNF entity = new ProducerReferencedNF(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ProducerReferencedNF() {
        this.emitterUf = null;
        this.emissionYearMonth = null;
        this.emitterCnpj = null;
        this.emitterCpf = null;
        this.stateRegistration = null;
        this.model = null;
        this.series = null;
        this.number = null;
    }

    public ProducerReferencedNF(Builder builder) {
        this.emitterUf = builder.emitterUf;
        this.emissionYearMonth = builder.emissionYearMonth;
        this.emitterCnpj = builder.emitterCnpj;
        this.emitterCpf = builder.emitterCpf;
        this.stateRegistration = builder.stateRegistration;
        this.model = builder.model;
        this.series = builder.series;
        this.number = builder.number;
    }

    public UF getEmitterUf() {
        return this.emitterUf;
    }

    public String getEmissionYearMonth() {
        return this.emissionYearMonth;
    }

    public String getEmitterCnpj() {
        return this.emitterCnpj;
    }

    public String getEmitterCpf() {
        return this.emitterCpf;
    }

    public String getStateRegistration() {
        return this.stateRegistration;
    }

    public ProducerReferencedNFModel getModel() {
        return this.model;
    }

    public String getSeries() {
        return this.series;
    }

    public String getNumber() {
        return this.number;
    }

    @XmlType
    @XmlEnum(String.class)
    public enum ProducerReferencedNFModel implements Serializable {
                                                                   @XmlEnumValue("04") PRODUCER_NF("04", "NF de produtor"),
                                                                   @XmlEnumValue("01") SPARE_NF("01", "NF Avulsa");

        private static final long serialVersionUID = 1L;

        private final String value;

        private final String description;

        private ProducerReferencedNFModel(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return this.value;
        }

        public String getDescription() {
            return this.description;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

}
