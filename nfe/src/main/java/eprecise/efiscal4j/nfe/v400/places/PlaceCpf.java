
package eprecise.efiscal4j.nfe.v400.places;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CPF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class PlaceCpf extends PlaceCnp {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "Cpf") @NotNull(message = "Cpf: o valor é necessário") @Size(max = 11) @CPF(formatted = false) String cpf;

    public static class Builder {

        private String cpf;

        /**
         * @param cpf
         * @return
         */
        public Builder withCpf(final String cpf) {
            this.cpf = cpf;
            return this;
        }

        public PlaceCpf build() {
            final PlaceCpf entity = new PlaceCpf(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public PlaceCpf() {
        cpf = null;
    }

    public PlaceCpf(final Builder builder) {
        cpf = builder.cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getCnp() {
        return cpf;
    }

}
