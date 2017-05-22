
package eprecise.efiscal4j.nfse.tc.elotech.statements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.person.documents.NFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceIntermediary {

    private final @NotNull @XmlElement(name = "IdentificacaoIntermediario") ServiceIntermediaryIdentifier identifier;

    private final @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 150) String socialName;

    public static class Builder {

        private ServiceIntermediaryIdentifier identifier;

        private String socialName;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final ServiceIntermediaryIdentifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * @param socialName
         * @return
         */
        public Builder withSocialName(final String socialName) {
            this.socialName = socialName;
            return this;
        }

        public ServiceIntermediary build() throws Exception {
            final ServiceIntermediary entity = new ServiceIntermediary(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ServiceIntermediary() {
        identifier = null;
        socialName = null;
    }

    public ServiceIntermediary(final Builder builder) {
        identifier = builder.identifier;
        socialName = builder.socialName;
    }

    public ServiceIntermediaryIdentifier getIdentifier() {
        return identifier;
    }

    public String getSocialName() {
        return socialName;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ServiceIntermediaryIdentifier {

        private final @XmlElement(name = "CpfCnpj") NFSeCnp cnp;

        private final @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 10) String municipalRegistration;

        public static class Builder {

            private NFSeCnp cnp;

            private String municipalRegistration;

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

            public ServiceIntermediaryIdentifier build() throws Exception {
                final ServiceIntermediaryIdentifier entity = new ServiceIntermediaryIdentifier(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public ServiceIntermediaryIdentifier() {
            cnp = null;
            municipalRegistration = null;
        }

        public ServiceIntermediaryIdentifier(final Builder builder) {
            cnp = builder.cnp;
            municipalRegistration = builder.municipalRegistration;
        }

        public NFSeCnp getCnp() {
            return cnp;
        }

        public String getMunicipalRegistration() {
            return municipalRegistration;
        }
    }

}
