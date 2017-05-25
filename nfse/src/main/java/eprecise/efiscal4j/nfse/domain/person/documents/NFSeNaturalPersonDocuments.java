
package eprecise.efiscal4j.nfse.domain.person.documents;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.format.Formatter;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeNaturalPersonDocuments implements NFSeDocuments {

    private static final long serialVersionUID = 1L;

    private final String cpf;

    public static class Builder {

        private final Formatter formatter = new CPFFormatter();

        private String cpf;

        public Builder withCpf(final String cpf) {
            try {
                this.cpf = formatter.unformat(cpf);
            } catch (final IllegalArgumentException e) {
                this.cpf = cpf;
            }
            return this;
        }

        public NFSeNaturalPersonDocuments build() {
            final NFSeNaturalPersonDocuments entity = new NFSeNaturalPersonDocuments(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeNaturalPersonDocuments() {
        cpf = null;
    }

    public NFSeNaturalPersonDocuments(final Builder builder) {
        cpf = builder.cpf;
    }

    public String getCpf() {
        return cpf;
    }

}
