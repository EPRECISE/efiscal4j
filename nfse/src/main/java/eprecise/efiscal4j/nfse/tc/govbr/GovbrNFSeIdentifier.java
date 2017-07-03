
package eprecise.efiscal4j.nfse.tc.govbr;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;


@XmlRootElement(name = "IdentificacaoNfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GovbrNFSeIdentifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull @XmlElement(name = "Numero") @NFSeNonNegativeInteger @Size(max = 15) String number;

    private final @NotNull @XmlElement(name = "Cnpj") @CNPJ(formatted = false) String cnpj;

    private final @XmlElement(name = "InscricaoMunicipal") String municipalRegistration;

    private final @NotNull @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

    public static class Builder {

        private String number;

        private String cnpj;

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

        /**
         * @param cityCode
         * @return
         */
        public Builder withCityCode(final String cityCode) {
            this.cityCode = cityCode;
            return this;
        }

        public GovbrNFSeIdentifier build() {
            final GovbrNFSeIdentifier entity = new GovbrNFSeIdentifier(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public GovbrNFSeIdentifier() {
        number = null;
        cnpj = null;
        municipalRegistration = null;
        cityCode = null;
    }

    public GovbrNFSeIdentifier(final Builder builder) {
        number = builder.number;
        cnpj = builder.cnpj;
        municipalRegistration = builder.municipalRegistration;
        cityCode = builder.cityCode;
    }

    public String getNumber() {
        return number;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getMunicipalRegistration() {
        return municipalRegistration;
    }

    public String getCityCode() {
        return cityCode;
    }

}
