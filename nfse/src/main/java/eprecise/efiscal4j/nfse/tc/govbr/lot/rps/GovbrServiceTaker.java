
package eprecise.efiscal4j.nfse.tc.govbr.lot.rps;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.address.CommonsNFSeAddress;
import eprecise.efiscal4j.nfse.tc.commons.person.contacts.CommonsNFSeContact;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrServiceTaker {

    private final @XmlElement(name = "IdentificacaoTomador") GovbrServiceTakerIdentifier identifier;

    private final @XmlElement(name = "RazaoSocial") @Size(min = 1, max = 115) String socialName;

    private final @XmlElement(name = "Endereco") CommonsNFSeAddress address;

    private final @XmlElement(name = "Contato") CommonsNFSeContact contact;

    public static class Builder {

        private GovbrServiceTakerIdentifier identifier;

        private String socialName;

        private CommonsNFSeAddress address;

        private CommonsNFSeContact contact;

        /**
         * @param identifier
         * @return
         */
        public Builder withIdentifier(final GovbrServiceTakerIdentifier identifier) {
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

        public GovbrServiceTaker build() {
            final GovbrServiceTaker entity = new GovbrServiceTaker(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrServiceTaker() {
        identifier = null;
        socialName = null;
        address = null;
        contact = null;
    }

    public GovbrServiceTaker(final Builder builder) {
        identifier = builder.identifier;
        socialName = builder.socialName;
        address = builder.address;
        contact = builder.contact;
    }

    public GovbrServiceTakerIdentifier getIdentifier() {
        return identifier;
    }

    public String getSocialName() {
        return socialName;
    }

    public CommonsNFSeAddress getAddress() {
        return address;
    }

    public CommonsNFSeContact getContact() {
        return contact;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GovbrServiceTakerIdentifier {

        private final @XmlElement(name = "CpfCnpj") CommonsNFSeCnp cnp;

        private final @XmlElement(name = "InscricaoMunicipal") @Size(min = 1, max = 15) String municipalRegistration;

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

            public GovbrServiceTakerIdentifier build() {
                final GovbrServiceTakerIdentifier entity = new GovbrServiceTakerIdentifier(this);
                ValidationBuilder.from(entity).validate().throwIfViolate();
                return entity;
            }
        }

        public GovbrServiceTakerIdentifier() {
            cnp = null;
            municipalRegistration = null;
        }

        public GovbrServiceTakerIdentifier(final Builder builder) {
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
