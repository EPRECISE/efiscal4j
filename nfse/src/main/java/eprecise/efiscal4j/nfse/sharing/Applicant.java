
package eprecise.efiscal4j.nfse.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.person.documents.NFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class Applicant implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "CpfCnpj") NFSeCnp cnp;

    private final @NotNull @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 10) String municipalRegistration;

    private final @NotNull @XmlElement(name = "Senha") @Size(min = 6, max = 30) String password;

    private final @NotNull @XmlElement(name = "Homologa") boolean homologation;

    public static class Builder {

        private NFSeCnp cnp;

        private String municipalRegistration;

        private String password;

        private boolean homologation;

        /**
         * @param cnp
         * @return
         */
        public Builder withCnp(final NFSeCnp cnp) {
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

        public Applicant build() throws Exception {
            final Applicant entity = new Applicant(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Applicant() {
        cnp = null;
        municipalRegistration = null;
        password = null;
        homologation = false;
    }

    public Applicant(final Builder builder) {
        cnp = builder.cnp;
        municipalRegistration = builder.municipalRegistration;
        password = builder.password;
        homologation = builder.homologation;
    }

    public NFSeCnp getCnp() {
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
