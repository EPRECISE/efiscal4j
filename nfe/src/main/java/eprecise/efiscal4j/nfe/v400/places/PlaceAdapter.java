
package eprecise.efiscal4j.nfe.v400.places;

import java.io.Serializable;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.v400.address.City;


public class PlaceAdapter extends XmlAdapter<PlaceAdapter.AdaptedPlace, Place> {

    @Override
    public Place unmarshal(PlaceAdapter.AdaptedPlace adaptedPlace) throws Exception {
        if (adaptedPlace == null) {
            return null;
        }

        Place place;

        //@formatter:off
        if (adaptedPlace.getCpf() != null) {
            place = new Place.Builder()
            .withCnp(new PlaceCpf.Builder().withCpf(adaptedPlace.getCpf()).build())
            .withStreet(adaptedPlace.getStreet())
            .withNumber(adaptedPlace.getNumber())
            .withComplement(adaptedPlace.getComplement())
            .withDistrict(adaptedPlace.getDistrict())
            .withCity(new City.Builder()
                    .withIbgeCode(adaptedPlace.getCityIbgeCode())
                    .withDescription(adaptedPlace.getCityDescription())
                    .withUF(UF.findByAcronym(adaptedPlace.getUf()))
                    .build())
            .build();
        }else if(adaptedPlace.getCnpj() != null){
            place = new Place.Builder()
                    .withCnp(new PlaceCnpj.Builder().withCnpj(adaptedPlace.getCnpj()).build())
                    .withStreet(adaptedPlace.getStreet())
                    .withNumber(adaptedPlace.getNumber())
                    .withComplement(adaptedPlace.getComplement())
                    .withDistrict(adaptedPlace.getDistrict())
                    .withCity(new City.Builder()
                            .withIbgeCode(adaptedPlace.getCityIbgeCode())
                            .withDescription(adaptedPlace.getCityDescription())
                            .withUF(UF.findByAcronym(adaptedPlace.getUf()))
                            .build())
                    .build();
        }else{
            throw new UnsupportedOperationException();            
        }
        //@formatter:on

        return place;
    }

    @Override
    public PlaceAdapter.AdaptedPlace marshal(Place place) throws Exception {
        if (place == null) {
            return null;
        }

        return new AdaptedPlace(Optional.ofNullable(place.getCnp()).filter(PlaceCnpj.class::isInstance).map(PlaceCnp::getCnp).orElse(null),
                Optional.ofNullable(place.getCnp()).filter(PlaceCpf.class::isInstance).map(PlaceCnp::getCnp).orElse(null), place.getStreet(), place.getNumber(), place.getComplement(),
                place.getDistrict(), Optional.ofNullable(place.getCity()).map(City::getIbgeCode).orElse(null), Optional.ofNullable(place.getCity()).map(City::getDescription).orElse(null),
                Optional.ofNullable(place.getCity()).map(City::getUf).map(uf -> uf.getAcronym()).orElse(null));
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "cnpj", "cpf", "street", "number", "complement", "district", "cityIbgeCode", "cityDescription", "uf" })
    protected static class AdaptedPlace implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlElement(name = "CNPJ") final String cnpj;

        private @XmlElement(name = "CPF") final String cpf;

        private @XmlElement(name = "xLgr") final String street;

        private @XmlElement(name = "nro") final String number;

        private @XmlElement(name = "xCpl") final String complement;

        private @XmlElement(name = "xBairro") final String district;

        private @XmlElement(name = "cMun") String cityIbgeCode;

        private @XmlElement(name = "xMun") String cityDescription;

        private @XmlElement(name = "UF") String uf;

        public AdaptedPlace() {
            this.cnpj = null;
            this.cpf = null;
            this.street = null;
            this.number = null;
            this.complement = null;
            this.district = null;
            this.cityIbgeCode = null;
            this.cityDescription = null;
            this.uf = null;
        }

        public AdaptedPlace(String cnpj, String cpf, String street, String number, String complement, String district, String cityIbgeCode, String cityDescription, String uf) {
            this.cnpj = cnpj;
            this.cpf = cpf;
            this.street = street;
            this.number = number;
            this.complement = complement;
            this.district = district;
            this.cityIbgeCode = cityIbgeCode;
            this.cityDescription = cityDescription;
            this.uf = uf;
        }

        public String getCityIbgeCode() {
            return cityIbgeCode;
        }

        public void setCityIbgeCode(String cityIbgeCode) {
            this.cityIbgeCode = cityIbgeCode;
        }

        public String getCityDescription() {
            return cityDescription;
        }

        public void setCityDescription(String cityDescription) {
            this.cityDescription = cityDescription;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }

        public String getCnpj() {
            return cnpj;
        }

        public String getCpf() {
            return cpf;
        }

        public String getStreet() {
            return street;
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

    }

}
