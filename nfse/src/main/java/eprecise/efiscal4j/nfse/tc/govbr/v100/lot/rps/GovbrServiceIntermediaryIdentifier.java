
package eprecise.efiscal4j.nfse.tc.govbr.v100.lot.rps;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;


@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrServiceIntermediaryIdentifier {

    private final @XmlElement(name = "RazaoSocial") @NotNull(message = "Nome/Razão Social: o valor é necessário") @Size(
            min = 1, max = 150, message = "Nome/Razão Social: tamanho informado deve estar entre 1 e 150 caracteres.") String socialName;

    private final @XmlElement(name = "CpfCnpj") @NotNull CommonsNFSeCnp cnp;

    private final @XmlElement(name = "InscricaoMunicipal") @Size(
            min = 1, max = 15, message = "Inscrição Municipal: tamanho informado deve estar entre 1 e 15 caracteres.") String municipalRegistration;

    public static class Builder {

        private String socialName;

        private CommonsNFSeCnp cnp;

        private String municipalRegistration;

        /**
         * @param socialName
         * @return
         */
        public Builder withSocialName(final String socialName) {
            this.socialName = socialName;
            return this;
        }

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

        public GovbrServiceIntermediaryIdentifier build() {
            final GovbrServiceIntermediaryIdentifier entity = new GovbrServiceIntermediaryIdentifier(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrServiceIntermediaryIdentifier() {
        socialName = null;
        cnp = null;
        municipalRegistration = null;
    }

    public GovbrServiceIntermediaryIdentifier(final Builder builder) {
        socialName = builder.socialName;
        cnp = builder.cnp;
        municipalRegistration = builder.municipalRegistration;
    }

    public String getSocialName() {
        return socialName;
    }

    public CommonsNFSeCnp getCnp() {
        return cnp;
    }

    public String getMunicipalRegistration() {
        return municipalRegistration;
    }

}
