
package eprecise.efiscal4j.nfse.tc.govbr.lot.rps;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeAddress;
import eprecise.efiscal4j.nfse.tc.commons.person.contacts.CommonsNFSeContact;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrServiceProvider {

    private final @XmlElement(name = "IdentificacaoPrestador") @NotNull GovbrServiceProviderIdentifier identifier;

    private final @XmlElement(name = "RazaoSocial") @NotNull @Size(min = 1, max = 115) String socialName;

    private final @XmlElement(name = "NomeFantasia") @Size(min = 1, max = 60) String fancyName;

    private final @XmlElement(name = "Endereco") @NotNull CommonsNFSeAddress address;

    private final @XmlElement(name = "Contato") CommonsNFSeContact contact;

    public static class Builder {

        private GovbrServiceProviderIdentifier identifier;

        private String socialName;

        private String fancyName;

        private CommonsNFSeAddress address;

        private CommonsNFSeContact contact;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final GovbrServiceProviderIdentifier identifier) {
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

        public GovbrServiceProvider build() throws Exception {
            final GovbrServiceProvider entity = new GovbrServiceProvider(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrServiceProvider() {
        identifier = null;
        socialName = null;
        fancyName = null;
        address = null;
        contact = null;
    }

    public GovbrServiceProvider(final Builder builder) {
        identifier = builder.identifier;
        socialName = builder.socialName;
        fancyName = builder.fancyName;
        address = builder.address;
        contact = builder.contact;
    }

    public GovbrServiceProviderIdentifier getIdentifier() {
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
    public static class GovbrServiceProviderIdentifier {

        private final @XmlElement(name = "Cnpj") @NotNull @CNPJ(formatted = false) @Size(max = 14) String cnpj;

        private final @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 15) String municipalRegistration;

        public static class Builder {

            private String cnpj;

            private String municipalRegistration;

            /**
             * @param cnpj
             * @return
             */
            public Builder withCnpj(final String cnpj) {
                this.cnpj = cnpj;
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

            public GovbrServiceProviderIdentifier build() {
                final GovbrServiceProviderIdentifier entity = new GovbrServiceProviderIdentifier(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public GovbrServiceProviderIdentifier() {
            cnpj = null;
            municipalRegistration = null;
        }

        public GovbrServiceProviderIdentifier(final Builder builder) {
            cnpj = builder.cnpj;
            municipalRegistration = builder.municipalRegistration;
        }

        public String getCnpj() {
            return cnpj;
        }

        public String getMunicipalRegistration() {
            return municipalRegistration;
        }
    }

}
