
package eprecise.efiscal4j.nfse.tc.elotech.lot.statements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechServiceIntermediary {

    private final @NotNull @XmlElement(name = "IdentificacaoIntermediario") ElotechServiceIntermediaryIdentifier identifier;

    private final @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 150) String socialName;

    public static class Builder {

        private ElotechServiceIntermediaryIdentifier identifier;

        private String socialName;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final ElotechServiceIntermediaryIdentifier identifier) {
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

        public ElotechServiceIntermediary build() {
            final ElotechServiceIntermediary entity = new ElotechServiceIntermediary(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechServiceIntermediary() {
        identifier = null;
        socialName = null;
    }

    public ElotechServiceIntermediary(final Builder builder) {
        identifier = builder.identifier;
        socialName = builder.socialName;
    }

    public ElotechServiceIntermediaryIdentifier getIdentifier() {
        return identifier;
    }

    public String getSocialName() {
        return socialName;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ElotechServiceIntermediaryIdentifier {

        private final @XmlElement(name = "CpfCnpj") CommonsNFSeCnp cnp;

        private final @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 10) String municipalRegistration;

        public static class Builder {

            private CommonsNFSeCnp cnp;

            private String municipalRegistration;

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

            public ElotechServiceIntermediaryIdentifier build() {
                final ElotechServiceIntermediaryIdentifier entity = new ElotechServiceIntermediaryIdentifier(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public ElotechServiceIntermediaryIdentifier() {
            cnp = null;
            municipalRegistration = null;
        }

        public ElotechServiceIntermediaryIdentifier(final Builder builder) {
            cnp = builder.cnp;
            municipalRegistration = builder.municipalRegistration;
        }

        public CommonsNFSeCnp getCnp() {
            return cnp;
        }

        public String getMunicipalRegistration() {
            return municipalRegistration;
        }
    }

}
