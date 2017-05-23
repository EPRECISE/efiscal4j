
package eprecise.efiscal4j.nfse.tc.elotech.statements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeAddress;
import eprecise.efiscal4j.nfse.tc.commons.person.contacts.CommonsNFSeContact;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechServiceProvider {

    private final @XmlElement(name = "IdentificacaoPrestador") @NotNull ServiceProviderIdentifier identifier;

    private final @XmlElement(name = "RazaoSocial") @NotNull @Size(min = 1, max = 150) String socialName;

    private final @XmlElement(name = "NomeFantasia") @Size(min = 1, max = 60) String fancyName;

    private final @XmlElement(name = "Endereco") @NotNull CommonsNFSeAddress address;

    private final @XmlElement(name = "Contato") CommonsNFSeContact contact;

    public static class Builder {

        private ServiceProviderIdentifier identifier;

        private String socialName;

        private String fancyName;

        private CommonsNFSeAddress address;

        private CommonsNFSeContact contact;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final ServiceProviderIdentifier identifier) {
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
         * @param fancyName
         * @return
         */
        public Builder withFancyName(final String fancyName) {
            this.fancyName = fancyName;
            return this;
        }

        /**
         * @param address
         * @return
         */
        public Builder withAddress(final CommonsNFSeAddress address) {
            this.address = address;
            return this;
        }

        /**
         * @param contact
         * @return
         */
        public Builder withContact(final CommonsNFSeContact contact) {
            this.contact = contact;
            return this;
        }

        public ElotechServiceProvider build() throws Exception {
            final ElotechServiceProvider entity = new ElotechServiceProvider(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechServiceProvider() {
        identifier = null;
        socialName = null;
        fancyName = null;
        address = null;
        contact = null;
    }

    public ElotechServiceProvider(final Builder builder) {
        identifier = builder.identifier;
        socialName = builder.socialName;
        fancyName = builder.fancyName;
        address = builder.address;
        contact = builder.contact;
    }

    public ServiceProviderIdentifier getIdentifier() {
        return identifier;
    }

    public String getSocialName() {
        return socialName;
    }

    public String getFancyName() {
        return fancyName;
    }

    public CommonsNFSeAddress getAddress() {
        return address;
    }

    public CommonsNFSeContact getContact() {
        return contact;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ServiceProviderIdentifier {

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

            public ServiceProviderIdentifier build() throws Exception {
                final ServiceProviderIdentifier entity = new ServiceProviderIdentifier(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public ServiceProviderIdentifier() {
            cnp = null;
            municipalRegistration = null;
        }

        public ServiceProviderIdentifier(final Builder builder) {
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
