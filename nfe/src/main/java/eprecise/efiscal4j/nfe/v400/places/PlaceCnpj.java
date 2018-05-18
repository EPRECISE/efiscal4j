
package eprecise.efiscal4j.nfe.v400.places;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class PlaceCnpj extends PlaceCnp {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "CNPJ") @Pattern(regexp = "[0-9]{0}|[0-9]{14}") @Size(max = 14) String cnpj;

    public static class Builder {

        private String cnpj;

        /**
         * @param cnpj
         * @return
         */
        public Builder withCnpj(final String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public PlaceCnpj build() {
            final PlaceCnpj entity = new PlaceCnpj(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public PlaceCnpj() {
        cnpj = null;
    }

    public PlaceCnpj(final Builder builder) {
        cnpj = builder.cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getCnp() {
        return cnpj;
    }

}
