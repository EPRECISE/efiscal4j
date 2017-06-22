
package eprecise.efiscal4j.nfse.tc.commons.person.address;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.ts.commons.types.NFSeNonNegativeInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class CommonsNFSeAddress {

    private final @XmlElement(name = "Endereco") @Size(min = 1, max = 125, message = "Endereço - rua: tamanho informado deve estar entre 1 e 125 caracteres.") String address;

    private final @XmlElement(name = "Numero") @Size(min = 1, max = 10, message = "Endereço - número: tamanho informado deve estar entre 1 e 10 caracteres.") String number;

    private final @XmlElement(name = "Complemento") @Size(min = 1, max = 60, message = "Endereço - complemento: tamanho informado deve estar entre 1 e 60 caracteres.") String complement;

    private final @XmlElement(name = "Bairro") @Size(min = 1, max = 60, message = "Endereço - bairro: tamanho informado deve estar entre 1 e 60 caracteres.") String district;

    private final @XmlElement(name = "CodigoMunicipio") @NFSeNonNegativeInteger @Size(
            min = 1, max = 7, message = "Endereço - código do município: tamanho informado deve estar entre 1 e 7 caracteres.") String cityCode;

    private final @XmlElement(name = "Uf") CommonsNFSeUF uf;

    private final @XmlElement(name = "Cep") @Size(min = 8, max = 8, message = "Endereço - cep: tamanho informado deve possuir exatamente 8 caracteres.") String cep;

    public static class Builder {

        private String address;

        private String number;

        private String complement;

        private String district;

        private String cityCode;

        private CommonsNFSeUF uf;

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
            this.district = district;
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
        public Builder withUf(final CommonsNFSeUF uf) {
            this.uf = uf;
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

        public CommonsNFSeAddress build() {
            final CommonsNFSeAddress entity = new CommonsNFSeAddress(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CommonsNFSeAddress() {
        address = null;
        number = null;
        complement = null;
        district = null;
        cityCode = null;
        uf = null;
        cep = null;
    }

    public CommonsNFSeAddress(final Builder builder) {
        address = builder.address;
        number = builder.number;
        complement = builder.complement;
        district = builder.district;
        cityCode = builder.cityCode;
        uf = builder.uf;
        cep = builder.cep;
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

    public CommonsNFSeUF getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }
}
