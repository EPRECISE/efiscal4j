
package eprecise.efiscal4j.nfe.refdocuments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeAccessKey;


/**
 * NF-e referenciadas
 * 
 * @author Felipe Bueno
 *
 */
@XmlTransient
public class ReferencedNFe implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "refNFe") @NotNull @NFeAccessKey final String acessKey;

    public static class Builder {

        private String acessKey;

        /**
         * Chave de acesso das NF-e referenciadas
         * 
         * @param acessKey
         * @return
         */
        public Builder withAcessKey(String acessKey) {
            this.acessKey = acessKey;
            return this;
        }

        public ReferencedNFe build() {
            final ReferencedNFe entity = new ReferencedNFe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ReferencedNFe() {
        this.acessKey = null;

    }

    public ReferencedNFe(Builder builder) {
        this.acessKey = builder.acessKey;

    }

    public String getAcessKey() {
        return this.acessKey;
    }

}
