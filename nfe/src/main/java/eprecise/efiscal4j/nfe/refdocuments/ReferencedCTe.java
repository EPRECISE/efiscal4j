
package eprecise.efiscal4j.nfe.refdocuments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeAccessKey;


/**
 * CT-e referenciado emitido anteriormente, vinculado à NF-e atual
 * 
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferencedCTe implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "refCTe") @NotNull @NFeAccessKey final String acessKey;

    public static class Builder {

        private String acessKey;

        /**
         * Chave de acesso dos CT-e referenciados
         * 
         * @param acessKey
         * @return
         */
        public Builder withAcessKey(String acessKey) {
            this.acessKey = acessKey;
            return this;
        }

        public ReferencedCTe build() {
            final ReferencedCTe entity = new ReferencedCTe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ReferencedCTe() {
        this.acessKey = null;

    }

    public ReferencedCTe(Builder builder) {
        this.acessKey = builder.acessKey;

    }

    public String getAcessKey() {
        return this.acessKey;
    }
}
