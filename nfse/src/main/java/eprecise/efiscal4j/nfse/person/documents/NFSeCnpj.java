
package eprecise.efiscal4j.nfse.person.documents;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeCnpj extends NFSeCnp {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "Cnpj") @NotNull @CNPJ(formatted = false) @Size(max = 14) String cnpj;

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

        public NFSeCnpj build() throws Exception {
            final NFSeCnpj entity = new NFSeCnpj(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeCnpj() {
        this.cnpj = null;
    }

    public NFSeCnpj(final Builder builder) {
        this.cnpj = builder.cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getCnp() {
        return this.cnpj;
    }

}
