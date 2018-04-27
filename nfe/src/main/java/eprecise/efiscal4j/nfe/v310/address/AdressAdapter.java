
package eprecise.efiscal4j.nfe.v310.address;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.commons.domain.adress.UF;


public class AdressAdapter extends XmlAdapter<AdressAdapter.AdaptedAdress, Address> {

    @Override
    public Address unmarshal(final AdaptedAdress adaptedAdress) throws Exception {
        City city = null;
        Country country = null;

        //@formatter:off        
        if (adaptedAdress.getCountryIbgeCode() != null || adaptedAdress.getCountryDescription() != null) {
            country = new Country.Builder()
                       .withIbgeCode(adaptedAdress.getCountryIbgeCode())
                       .withDescription(adaptedAdress.getCountryDescription())
                       .build();
        }
        
        city = new City.Builder()
                .withCountry(country)
                .withIbgeCode(adaptedAdress.getCityIbgeCode())
                .withDescription(adaptedAdress.getCityDescription())
                .withUF(UF.findByAcronym(adaptedAdress.getUf()))
                .build();                          

        return new Address.Builder()
                   .withStreet(adaptedAdress.getStreet())
                   .withNumber(adaptedAdress.getNumber())
                   .withComplement(adaptedAdress.getComplement())
                   .withDistrict(adaptedAdress.getDistrict())
                   .withCep(adaptedAdress.getCep())
                   .withCity(city)
                   .withPhone(adaptedAdress.getPhone())
                   .build();
        //@formatter:on       
    }

    @Override
    public AdaptedAdress marshal(final Address adress) throws Exception {
        if (adress == null) {
            return null;
        }

        final AdaptedAdress adaptedAdress = new AdaptedAdress();

        adaptedAdress.setStreet(adress.getStreet());
        adaptedAdress.setNumber(adress.getNumber());
        adaptedAdress.setComplement(adress.getComplement());
        adaptedAdress.setDistrict(adress.getDistrict());
        adaptedAdress.setCep(adress.getCep());
        if (adress.getCity() != null) {
            adaptedAdress.setCityIbgeCode(adress.getCity().getIbgeCode());
            adaptedAdress.setCityDescription(adress.getCity().getDescription());

            if (adress.getCity().getUf() != null) {
                adaptedAdress.setUf(adress.getCity().getUf().getAcronym());
            }

            if (adress.getCity().getCountry() != null) {
                adaptedAdress.setCountryIbgeCode(adress.getCity().getCountry().getIbgeCode());
                adaptedAdress.setCountryDescription(adress.getCity().getCountry().getDescription());
            }
        }
        adaptedAdress.setPhone(adress.getPhone());

        return adaptedAdress;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    protected static class AdaptedAdress implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlElement(name = "xLgr") String street;

        private @XmlElement(name = "nro") String number;

        private @XmlElement(name = "xCpl") String complement;

        private @XmlElement(name = "xBairro") String district;

        private @XmlElement(name = "cMun") String cityIbgeCode;

        private @XmlElement(name = "xMun") String cityDescription;

        private @XmlElement(name = "UF") String uf;

        private @XmlElement(name = "CEP") String cep;

        private @XmlElement(name = "cPais") String countryIbgeCode;

        private @XmlElement(name = "xPais") String countryDescription;

        private @XmlElement(name = "fone") String phone;

        public AdaptedAdress() {
            this.street = null;
            this.number = null;
            this.complement = null;
            this.district = null;
            this.cep = null;
            this.cityIbgeCode = null;
            this.cityDescription = null;
            this.uf = null;
            this.countryIbgeCode = null;
            this.countryDescription = null;
            this.phone = null;
        }

        public AdaptedAdress(String street, String number, String complement, String district, String cep, String cityIbgeCode, String cityDescription, String uf, String countryIbgeCode,
                String countryDescription, String phone) {
            this.street = street;
            this.number = number;
            this.complement = complement;
            this.district = district;
            this.cep = cep;
            this.cityIbgeCode = cityIbgeCode;
            this.cityDescription = cityDescription;
            this.uf = uf;
            this.countryIbgeCode = countryIbgeCode;
            this.countryDescription = countryDescription;
            this.phone = phone;
        }

        public String getStreet() {
            return this.street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getComplement() {
            return this.complement;
        }

        public void setComplement(String complement) {
            this.complement = complement;
        }

        public String getDistrict() {
            return this.district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getCep() {
            return this.cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getCityIbgeCode() {
            return this.cityIbgeCode;
        }

        public void setCityIbgeCode(String cityIbgeCode) {
            this.cityIbgeCode = cityIbgeCode;
        }

        public String getCityDescription() {
            return this.cityDescription;
        }

        public void setCityDescription(String cityDescription) {
            this.cityDescription = cityDescription;
        }

        public String getUf() {
            return this.uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }

        public String getCountryIbgeCode() {
            return this.countryIbgeCode;
        }

        public void setCountryIbgeCode(String countryIbgeCode) {
            this.countryIbgeCode = countryIbgeCode;
        }

        public String getCountryDescription() {
            return this.countryDescription;
        }

        public void setCountryDescription(String countryDescription) {
            this.countryDescription = countryDescription;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

    }

}
