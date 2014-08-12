
package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfe.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "cPais") @NotNull final String ibgeCode;

    private @XmlElement(name = "xPais") @NotNull @Size(min = 2, max = 60) final String description;

    public static class Builder {

        private String ibgeCode;

        private String description;

        public Builder withIbgeCode(String ibgeCode) {
            this.ibgeCode = ibgeCode;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Country build() {
            Country entity = new Country(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public Country() {
        this.ibgeCode = null;
        this.description = null;
    }

    public Country(Builder builder) {
        this.ibgeCode = builder.ibgeCode;
        this.description = builder.description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIbgeCode() {
        return this.ibgeCode;
    }
}
