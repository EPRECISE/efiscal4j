
package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "cMun") @NotNull @Pattern(regexp = "[0-9]{7}") final String ibgeCode;

    private @XmlElement(name = "xMun") @NotNull @Size(min = 2, max = 60) final String description;

    private @XmlElement(name = "UF") final UF uf;

    private final Country country;

    public static class Builder {

        private Country country;

        private UF uf;

        private String ibgeCode;

        private String description;

        public Builder withCountry(Country country) {
            this.country = country;
            return this;
        }

        public Builder withUF(UF uf) {
            this.uf = uf;
            return this;
        }

        public Builder withIbgeCode(String ibgeCode) {
            this.ibgeCode = ibgeCode;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public City build() {
            City entity = new City(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public City() {
        this.ibgeCode = null;
        this.description = null;
        this.country = null;
        this.uf = null;
    }

    public City(Builder builder) {
        this.country = builder.country;
        this.uf = builder.uf;
        this.ibgeCode = builder.ibgeCode;
        this.description = builder.description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIbgeCode() {
        return this.ibgeCode;
    }

    public Country getCountry() {
        return this.country;
    }

    public UF getUf() {
        return this.uf;
    }

}
