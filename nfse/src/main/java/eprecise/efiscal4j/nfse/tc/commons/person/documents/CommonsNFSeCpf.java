
package eprecise.efiscal4j.nfse.tc.commons.person.documents;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CPF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class CommonsNFSeCpf extends CommonsNFSeCnp {

    private static final long serialVersionUID = 1L;

    private final @XmlElement(name = "Cpf") @NotNull(message = "Cpf: o valor é necessário") @Size(max = 11) @CPF(formatted = false, ignoreRepeated = true) String cpf;

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

        public CommonsNFSeCpf build() {
            final CommonsNFSeCpf entity = new CommonsNFSeCpf(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CommonsNFSeCpf() {
        cpf = null;
    }

    public CommonsNFSeCpf(final Builder builder) {
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
