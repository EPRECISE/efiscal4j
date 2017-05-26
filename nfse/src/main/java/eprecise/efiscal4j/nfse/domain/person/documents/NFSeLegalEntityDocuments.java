
package eprecise.efiscal4j.nfse.domain.person.documents;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.Formatter;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeLegalEntityDocuments implements NFSeDocuments {

    private static final long serialVersionUID = 1L;

    private final String corporateName;

    private final String cnpj;

    private final String im;

    private final String ie;

    public static class Builder {

        private final Formatter formatter = new CNPJFormatter();

        private String corporateName;

        private String cnpj;

        private String im;

        private String ie;

        public Builder withCorporateName(final String corporateName) {
            this.corporateName = corporateName;
            return this;
        }

        public Builder withCnpj(final String cnpj) {
            if (cnpj == null) {
                this.cnpj = null;
            } else {
                try {
                    this.cnpj = formatter.unformat(cnpj);
                } catch (final IllegalArgumentException e) {
                    this.cnpj = cnpj;
                }
            }
            return this;
        }

        public Builder withIe(final String ie) {
            this.ie = ie;
            return this;
        }

        public Builder withIm(final String im) {
            this.im = im;
            return this;
        }

        public NFSeLegalEntityDocuments build() {
            final NFSeLegalEntityDocuments entity = new NFSeLegalEntityDocuments(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeLegalEntityDocuments() {
        corporateName = null;
        cnpj = null;
        ie = null;
        im = null;
    }

    public NFSeLegalEntityDocuments(final Builder builder) {
        corporateName = builder.corporateName;
        cnpj = builder.cnpj;
        ie = builder.ie;
        im = builder.im;
    }

    @Override
    public String getCnp() {
        return cnpj;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getIm() {
        return im;
    }

    public String getIe() {
        return ie;
    }

}
