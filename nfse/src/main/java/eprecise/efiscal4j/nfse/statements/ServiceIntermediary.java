
package eprecise.efiscal4j.nfse.statements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.person.documents.NFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceIntermediary {

    private final @NotNull @XmlElement(name = "IdentificacaoIntermediario") Identifier identifier;

    private final @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 150) String socialName;

    public static class Builder {

        private Identifier identifier;

        private String socialName;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final Identifier identifier) {
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
        this.identifier = null;
        this.socialName = null;
    }

    public ServiceIntermediary(final Builder builder) {
        this.identifier = builder.identifier;
        this.socialName = builder.socialName;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getSocialName() {
        return socialName;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Identifier {

        private final @XmlElementWrapper(name = "CpfCnpj") NFSeCnp cnp;

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

            public Identifier build() throws Exception {
                final Identifier entity = new Identifier(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public Identifier() {
            this.cnp = null;
            this.municipalRegistration = null;
        }

        public Identifier(final Builder builder) {
            this.cnp = builder.cnp;
            this.municipalRegistration = builder.municipalRegistration;
        }

        public NFSeCnp getCnp() {
            return cnp;
        }

        public String getMunicipalRegistration() {
            return municipalRegistration;
        }
    }

}
