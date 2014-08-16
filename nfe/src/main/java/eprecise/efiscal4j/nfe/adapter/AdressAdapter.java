
package eprecise.efiscal4j.nfe.adapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.nfe.Adress;
import eprecise.efiscal4j.nfe.City;
import eprecise.efiscal4j.nfe.Country;
import eprecise.efiscal4j.nfe.UF;


public class AdressAdapter extends XmlAdapter<AdressAdapter.AdaptedAdress, Adress> {

    @Override
    public Adress unmarshal(AdaptedAdress adaptedAdress) throws Exception {
        //@formatter:off
        Adress adress = new Adress.Builder()
                            .withStreet(adaptedAdress.getStreet())
                            .withNumber(adaptedAdress.getNumber())
                            .withComplement(adaptedAdress.getComplement())
                            .withDistrict(adaptedAdress.getDistrict())
                            .withCep(adaptedAdress.getCep())
                            .withCity(new City.Builder()
                                          .withCountry(new Country.Builder()
                                                           .withIbgeCode(adaptedAdress.getCountryIbgeCode())
                                                           .withDescription(adaptedAdress.getCountryDescription())
                                                           .build())
                                          .withIbgeCode(adaptedAdress.getCityIbgeCode())
                                          .withDescription(adaptedAdress.getCityDescription())
                                          .withUF(UF.findByAcronym(adaptedAdress.getUf()))
                                          .build())
                            .build();
        //@formatter:on
        return adress;
    }

    @Override
    public AdaptedAdress marshal(Adress adress) throws Exception {
        //@formatter:off
        AdaptedAdress adaptedAdress = new AdaptedAdress(adress.getStreet()
                                                      , adress.getNumber()
                                                      , adress.getComplement()
                                                      , adress.getDistrict()
                                                      , adress.getCep()
                                                      , adress.getCity().getIbgeCode()
                                                      , adress.getCity().getDescription()
                                                      , adress.getCity().getUf().getAcronym()
                                                      , adress.getCity().getCountry().getIbgeCode()
                                                      , adress.getCity().getCountry().getDescription()
                                                      , adress.getPhone());
        //@formatter:on        
        return adaptedAdress;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    protected static class AdaptedAdress {

        private @XmlElement(name = "xLgr") final String street;

        private @XmlElement(name = "nro") final String number;

        private @XmlElement(name = "xCpl") final String complement;

        private @XmlElement(name = "xBairro") final String district;

        private @XmlElement(name = "cMun") final String cityIbgeCode;

        private @XmlElement(name = "xMun") final String cityDescription;

        private @XmlElement(name = "UF") final String uf;

        private @XmlElement(name = "CEP") final String cep;

        private @XmlElement(name = "cPais") final String countryIbgeCode;

        private @XmlElement(name = "xPais") final String countryDescription;

        private @XmlElement(name = "fone") final String phone;

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

        public String getNumber() {
            return this.number;
        }

        public String getComplement() {
            return this.complement;
        }

        public String getDistrict() {
            return this.district;
        }

        public String getCep() {
            return this.cep;
        }

        public String getCityIbgeCode() {
            return this.cityIbgeCode;
        }

        public String getCityDescription() {
            return this.cityDescription;
        }

        public String getUf() {
            return this.uf;
        }

        public String getCountryIbgeCode() {
            return this.countryIbgeCode;
        }

        public String getCountryDescription() {
            return this.countryDescription;
        }

        public String getPhone() {
            return this.phone;
        }

    }

}
