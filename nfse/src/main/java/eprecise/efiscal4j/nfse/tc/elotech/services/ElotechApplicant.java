
package eprecise.efiscal4j.nfse.tc.elotech.services;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechApplicant implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull(message = "Cnpj do Prestador: o valor é necessário") @XmlElement(name = "CpfCnpj") CommonsNFSeCnp cnp;

    private final @NotNull(message = "Inscrição Municipal do Prestador: o valor é necessário") @XmlElement(name = "InscricaoMunicipal") @Size(
            min = 1, max = 10, message = "Inscrição Municipal do Prestador: tamanho informado deve estar entre 1 e 10 caracteres.") String municipalRegistration;

    private final @NotNull(message = "Senha do Prestador: o valor é necessário") @XmlElement(name = "Senha") @Size(
            min = 6, max = 30, message = "Senha do Prestador: tamanho informado deve estar entre 6 e 30 caracteres.") String password;

    private final @NotNull @XmlElement(name = "Homologa") boolean homologation;

    public static class Builder {

        private CommonsNFSeCnp cnp;

        private String municipalRegistration;

        private String password;

        private boolean homologation;

        /**
         * @param cnp
         * @return
         */
        public Builder withCnp(final CommonsNFSeCnp cnp) {
            this.cnp = cnp;
            return this;
        }

        /**
         * @param municipalRegistration
         * @return
         */
        public Builder withMunicipalRegistration(final String municipalRegistration) {
            this.municipalRegistration = municipalRegistration;
            return this;
        }

        /**
         * @param password
         * @return
         */
        public Builder withPassword(final String password) {
            this.password = password;
            return this;
        }

        /**
         * @param homologation
         * @return
         */
        public Builder withHomologation(final boolean homologation) {
            this.homologation = homologation;
            return this;
        }

        public ElotechApplicant build() {
            final ElotechApplicant entity = new ElotechApplicant(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechApplicant() {
        cnp = null;
        municipalRegistration = null;
        password = null;
        homologation = false;
    }

    public ElotechApplicant(final Builder builder) {
        cnp = builder.cnp;
        municipalRegistration = builder.municipalRegistration;
        password = builder.password;
        homologation = builder.homologation;
    }

    public CommonsNFSeCnp getCnp() {
        return cnp;
    }

    public String getMunicipalRegistration() {
        return municipalRegistration;
    }

    public String getPassword() {
        return password;
    }

    public boolean isHomologation() {
        return homologation;
    }

}
