
package eprecise.efiscal4j.nfse.domain.service.emitter.specificData;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeServiceEmitterElotechData implements NFSeServiceEmitterSpecificData {

    private static final long serialVersionUID = 1L;

    private final String password;

    public static class Builder {

        private String password;

        public Builder withPassword(final String password) {
            this.password = password;
            return this;
        }

        public NFSeServiceEmitterElotechData build() {
            final NFSeServiceEmitterElotechData entity = new NFSeServiceEmitterElotechData(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeServiceEmitterElotechData() {
        password = null;
    }

    public NFSeServiceEmitterElotechData(final Builder builder) {
        password = builder.password;
    }

    public String getPassword() {
        return password;
    }

}
