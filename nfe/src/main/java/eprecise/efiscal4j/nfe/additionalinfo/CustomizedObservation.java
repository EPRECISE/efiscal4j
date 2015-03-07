
package eprecise.efiscal4j.nfe.additionalinfo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Informar o nome do campo no atributo xCampo e o conteúdo do campo no xTexto.
 * 
 * @author Felipe Bueno
 * 
 */
public class CustomizedObservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "xTexto") @Size(min = 1, max = 60) @NFeString @NotNull final String text;

    private @XmlAttribute(name = "xCampo") @Size(min = 1, max = 20) @NFeString @NotNull final String field;

    public static class Builder {

        private String text;

        private String field;

        /**
         * Conteúdo do campo customizado
         * 
         * @param text
         * @return
         */
        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Nome do campo customizado
         * 
         * @param field
         * @return
         */
        public Builder withField(String field) {
            this.field = field;
            return this;
        }

        public CustomizedObservation build() {
            final CustomizedObservation entity = new CustomizedObservation(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public CustomizedObservation() {
        this.text = null;
        this.field = null;
    }

    public CustomizedObservation(Builder builder) {
        this.text = builder.text;
        this.field = builder.field;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getText() {
        return this.text;
    }

    public String getField() {
        return this.field;
    }

}
