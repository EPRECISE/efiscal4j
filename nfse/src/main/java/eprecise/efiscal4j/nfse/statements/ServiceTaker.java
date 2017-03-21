
package eprecise.efiscal4j.nfse.statements;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.person.address.NFSeAddress;
import eprecise.efiscal4j.nfse.person.address.NFSeContact;
import eprecise.efiscal4j.nfse.person.documents.NFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceTaker {

    private final @XmlElement(name = "IdentificacaoTomador") Identifier identifier;

    private final @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 150) String socialName;

    private final @XmlElement(name = "Endereco") NFSeAddress address;

    private final @XmlElement(name = "Contato") NFSeContact contact;

    private final @XmlElement(name = "InscricaoEstadual") @Size(min = 1, max = 20) String stateRegistration;

    public static class Builder {

        private Identifier identifier;

        private String socialName;

        private NFSeAddress address;

        private NFSeContact contact;

        private String stateRegistration;

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
        this.identifier = null;
        this.socialName = null;
        this.address = null;
        this.contact = null;
        this.stateRegistration = null;
    }

    public ServiceTaker(final Builder builder) {
        this.identifier = builder.identifier;
        this.socialName = builder.socialName;
        this.address = builder.address;
        this.contact = builder.contact;
        this.stateRegistration = builder.stateRegistration;
    }

    public Identifier getIdentifier() {
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
