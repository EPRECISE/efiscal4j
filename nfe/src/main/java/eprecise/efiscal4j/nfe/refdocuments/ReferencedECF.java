
package eprecise.efiscal4j.nfe.refdocuments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Grupo do Cupom Fiscal vinculado à NF-e
 * 
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferencedECF implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "mod") @NotNull final ReferecedECFModel model;

    private @XmlElement(name = "nECF") @NotNull @Pattern(regexp = "[0-9]{1,3}") final String ecfNumber;

    private @XmlElement(name = "nCOO") @NotNull @Pattern(regexp = "[0-9]{1,6}") final String cooNumber;

    public static class Builder {

        private ReferecedECFModel model;

        private String ecfNumber;

        private String cooNumber;

        /**
         * Código do modelo do Documento Fiscal
         * 
         * @param model
         * @return
         */
        public Builder withModel(ReferecedECFModel model) {
            this.model = model;
            return this;
        }

        /**
         * Informar o número de ordem seqüencial do ECF que emitiu o Cupom Fiscal vinculado à NF-e
         * 
         * @param ecfNumber
         * @return
         */
        public Builder withEcfNumber(String ecfNumber) {
            this.ecfNumber = ecfNumber;
            return this;
        }

        /**
         * Informar o Número do Contador de Ordem de Operação - COO vinculado à NF-e
         * 
         * @param cooNumber
         * @return
         */
        public Builder withCooNumber(String cooNumber) {
            this.cooNumber = cooNumber;
            return this;
        }

        public ReferencedECF build() {
            final ReferencedECF entity = new ReferencedECF(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ReferencedECF() {
        this.model = null;
        this.ecfNumber = null;
        this.cooNumber = null;

    }

    public ReferencedECF(Builder builder) {
        this.model = builder.model;
        this.ecfNumber = builder.ecfNumber;
        this.cooNumber = builder.cooNumber;
    }

    public ReferecedECFModel getModel() {
        return this.model;
    }

    public String getEcfNumber() {
        return this.ecfNumber;
    }

    public String getCooNumber() {
        return this.cooNumber;
    }

    @XmlType
    @XmlEnum(String.class)
    public enum ReferecedECFModel {
                                   @XmlEnumValue("2B") NAO_ECF("2B", "Cupom emitido por registradora (não ECF)"),
                                   @XmlEnumValue("2C") ECF_PDV("2C", "Cupom fiscal PDV"),
                                   @XmlEnumValue("2D") ECF("2D", "Cupom fiscal ECF");

        private final String value;

        private final String description;

        private ReferecedECFModel(String value, String description) {
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
