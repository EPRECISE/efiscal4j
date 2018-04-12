
package eprecise.efiscal4j.nfse.tc.elotech.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.commons.person.documents.CommonsNFSeCnp;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;


@XmlRootElement(name = "IdentificacaoNfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechNFSeIdentifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

    private final @XmlElement(name = "CpfCnpj") CommonsNFSeCnp cnp;

    private final @XmlElement(name = "InscricaoMunicipal") String municipalRegistration;

    private final @NotNull @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

    public static class Builder {

        private String number;

        private CommonsNFSeCnp cnp;

        private String municipalRegistration;

        private String cityCode;

        /**
         * @param number
         * @return
         */
        public Builder withNumber(final String number) {
            this.number = number;
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

        /**
         * @param cityCode
         * @return
         */
        public Builder withCityCode(final String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        public ElotechNFSeIdentifier build() {
            final ElotechNFSeIdentifier entity = new ElotechNFSeIdentifier(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechNFSeIdentifier() {
        number = null;
        cnp = null;
        municipalRegistration = null;
        cityCode = null;
    }

    public ElotechNFSeIdentifier(final Builder builder) {
        number = builder.number;
        cnp = builder.cnp;
        municipalRegistration = builder.municipalRegistration;
        cityCode = builder.cityCode;
    }

    public String getNumber() {
        return number;
    }

    
    public CommonsNFSeCnp getCnp() {
        return cnp;
    }

    public String getMunicipalRegistration() {
        return municipalRegistration;
    }

    public String getCityCode() {
        return cityCode;
    }

}
