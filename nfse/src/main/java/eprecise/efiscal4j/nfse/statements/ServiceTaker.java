
package eprecise.efiscal4j.nfse.statements;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.person.address.NFSeContact;
import eprecise.efiscal4j.nfse.person.documents.NFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceTaker {

    private final @XmlElement(name = "IdentificacaoTomador") ServiceTakerIdentifier identifier;

    private final @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 150) String socialName;

    private final @XmlElement(name = "Endereco") NFSeAddress address;

    private final @XmlElement(name = "Contato") NFSeContact contact;

    private final @XmlElement(name = "InscricaoEstadual") @Size(min = 1, max = 20) String stateRegistration;

    public static class Builder {

        private ServiceTakerIdentifier identifier;

        private String socialName;

        private NFSeAddress address;

        private NFSeContact contact;

        private String stateRegistration;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final ServiceTakerIdentifier identifier) {
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

        /**
         * @param address
         * @return
         */
        public Builder withAddress(final NFSeAddress address) {
            this.address = address;
            return this;
        }

        /**
         * @param contact
         * @return
         */
        public Builder withContact(final NFSeContact contact) {
            this.contact = contact;
            return this;
        }

        /**
         * @param stateRegistration
         * @return
         */
        public Builder withStateRegistration(final String stateRegistration) {
            this.stateRegistration = stateRegistration;
            return this;
        }

        public ServiceTaker build() throws Exception {
            final ServiceTaker entity = new ServiceTaker(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ServiceTaker() {
        identifier = null;
        socialName = null;
        address = null;
        contact = null;
        stateRegistration = null;
    }

    public ServiceTaker(final Builder builder) {
        identifier = builder.identifier;
        socialName = builder.socialName;
        address = builder.address;
        contact = builder.contact;
        stateRegistration = builder.stateRegistration;
    }

    public ServiceTakerIdentifier getIdentifier() {
        return identifier;
    }

    public String getSocialName() {
        return socialName;
    }

    public String getStateRegistration() {
        return stateRegistration;
    }

    public NFSeAddress getAddress() {
        return address;
    }

    public NFSeContact getContact() {
        return contact;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ServiceTakerIdentifier {

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

            public ServiceTakerIdentifier build() throws Exception {
                final ServiceTakerIdentifier entity = new ServiceTakerIdentifier(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public ServiceTakerIdentifier() {
            cnp = null;
            municipalRegistration = null;
        }

        public ServiceTakerIdentifier(final Builder builder) {
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
