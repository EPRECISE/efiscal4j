
package eprecise.efiscal4j.nfse.person.address;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.types.NFSeNonNegativeInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFSeAddress {

    private final @XmlElement(name = "Endereco") @Size(min = 1, max = 125) String address;

    private final @XmlElement(name = "Numero") @Size(min = 1, max = 10) String number;

    private final @XmlElement(name = "Complemento") @Size(min = 1, max = 60) String complement;

    private final @XmlElement(name = "Bairro") @Size(min = 1, max = 60) String district;

    private final @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(min = 1, max = 7) String cityCode;

    private final @XmlElement(name = "CidadeNome") @Size(min = 1, max = 125) String cityName;

    private final @XmlElement(name = "Uf") NFSeUF uf;

    private final @XmlElement(name = "CodigoPais") @Size(min = 4, max = 4) String countryCode;

    private final @XmlElement(name = "Cep") @Size(min = 8, max = 8) String cep;

    public static class Builder {

        private String address;

        private String number;

        private String complement;

        private String district;

        private String cityCode;

        private String cityName;

        private NFSeUF uf;

        private String countryCode;

        private String cep;

        /**
         * @param address
         * @return
         */
        public Builder withAddress(final String address) {
            this.address = address;
            return this;
        }

        /**
         * @param number
         * @return
         */
        public Builder withNumber(final String number) {
            this.number = number;
            return this;
        }

        /**
         * @param complement
         * @return
         */
        public Builder withComplement(final String complement) {
            this.complement = complement;
            return this;
        }

        /**
         * @param district
         * @return
         */
        public Builder withDistrict(final String district) {
            this.complement = district;
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

        /**
         * @param uf
         * @return
         */
        public Builder withUf(final NFSeUF uf) {
            this.uf = uf;
            return this;
        }

        /**
         * @param countryCode
         * @return
         */
        public Builder withCountryCode(final String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        /**
         * @param cep
         * @return
         */
        public Builder withCep(final String cep) {
            this.cep = cep;
            return this;
        }

        public NFSeAddress build() throws Exception {
            final NFSeAddress entity = new NFSeAddress(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeAddress() {
        this.address = null;
        this.number = null;
        this.complement = null;
        this.district = null;
        this.cityCode = null;
        this.cityName = null;
        this.uf = null;
        this.countryCode = null;
        this.cep = null;
    }

    public NFSeAddress(final Builder builder) {
        this.address = builder.address;
        this.number = builder.number;
        this.complement = builder.complement;
        this.district = builder.district;
        this.cityCode = builder.cityCode;
        this.cityName = builder.cityName;
        this.uf = builder.uf;
        this.countryCode = builder.countryCode;
        this.cep = builder.cep;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public NFSeUF getUf() {
        return uf;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCep() {
        return cep;
    }

}
