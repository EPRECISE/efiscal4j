
package eprecise.efiscal4j.nfse.domain.person.address;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeCity {

    private final String name;

    private final String ibgeCode;

    private final NFSeUF uf;

    public static class Builder {

        private String name;

        private String ibgeCode;

        private NFSeUF uf;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withIbgeCode(final String ibgeCode) {
            this.ibgeCode = ibgeCode;
            return this;
        }

        public Builder withUf(final NFSeUF uf) {
            this.uf = uf;
            return this;
        }

        public NFSeCity build() throws Exception {
            final NFSeCity entity = new NFSeCity(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeCity(final Builder builder) {
        name = builder.name;
        ibgeCode = builder.ibgeCode;
        uf = builder.uf;
    }

    public String getName() {
        return name;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public NFSeUF getUf() {
        return uf;
    }

}
