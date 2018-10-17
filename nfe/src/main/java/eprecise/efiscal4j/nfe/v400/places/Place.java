
package eprecise.efiscal4j.nfe.v400.places;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.address.City;


/**
 * Tipo Dados do Local de Retirada ou Entrega // 24/10/08 - tamanho mínimo // v2.0
 *
 * @author Fernando C Glizt
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    private @NotNull final PlaceCnp cnp;

    private @XmlElement(name = "xLgr") @NotNull @Size(min = 2, max = 60) final String street;

    private @XmlElement(name = "nro") @NotNull @Size(min = 1, max = 60) final String number;

    private @XmlElement(name = "xCpl") @Size(min = 1, max = 60) String complement;

    private @XmlElement(name = "xBairro") @NotNull @Size(min = 2, max = 60) final String district;

    private @NotNull final City city;

    public static class Builder {

        private PlaceCnp cnp;

        private String street;

        private String number;

        private String complement;

        private String district;

        private City city;
        
        public Builder withCnp(PlaceCnp cnp) {
            this.cnp = cnp;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withComplement(String complement) {
            this.complement = complement;
            return this;
        }

        public Builder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder withCity(City city) {
            this.city = city;
            return this;
        }

        public Place build() {
            final Place entity = new Place(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Place() {
        this.cnp = null;
        this.street = null;
        this.number = null;
        this.district = null;
        this.city = null;
    }

    public Place(final Builder builder) {
        this.cnp = builder.cnp;
        this.street = builder.street;
        this.number = builder.number;
        this.complement = builder.complement;
        this.district = builder.district;
        this.city = builder.city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public PlaceCnp getCnp() {
        return cnp;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getDistrict() {
        return district;
    }

    public City getCity() {
        return city;
    }

}
