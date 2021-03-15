
package eprecise.efiscal4j.nfe.v400;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.types.NFeCNPJ;


/**
 * Grupo de Informações do Intermediador da Transação
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeBrokerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "CNPJ") @NFeCNPJ String cnpj;

    private final @XmlElement(name = "idCadIntTran") @Size(min = 2, max = 60) String identifier;

    public static class Builder {

        private String cnpj;

        private String identifier;

        /**
         * CNPJ do Intermediador da Transação (agenciador, plataforma de delivery, marketplace e similar) de serviços e de negócios.
         *
         * @param cnpj
         * @return
         */
        public Builder withCnpj(final String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        /**
         * Identificador cadastrado no intermediador
         *
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final String identifier) {
            this.identifier = identifier;
            return this;
        }

        public NFeBrokerInfo build() {
            final NFeBrokerInfo entity = new NFeBrokerInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeBrokerInfo() {
        this.cnpj = null;
        this.identifier = null;
    }

    public NFeBrokerInfo(final Builder builder) {
        this.cnpj = builder.cnpj;
        this.identifier = builder.identifier;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getIdentifier() {
        return this.identifier;
    }

}
