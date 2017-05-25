
package eprecise.efiscal4j.nfse.domain.service.withheld;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeIssYesWithheldWithResponsible extends NFSeIssYesWithheld {

    private static final long serialVersionUID = 1L;

    private final NFSeResponsibleRetention responsibleRetention;

    public static class Builder {

        private NFSeResponsibleRetention responsibleRetention;

        public Builder withResponsibleRetention(final NFSeResponsibleRetention responsibleRetention) {
            this.responsibleRetention = responsibleRetention;
            return this;
        }

        public NFSeIssYesWithheldWithResponsible build() throws Exception {
            final NFSeIssYesWithheldWithResponsible entity = new NFSeIssYesWithheldWithResponsible(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeIssYesWithheldWithResponsible() {
        responsibleRetention = null;
    }

    public NFSeIssYesWithheldWithResponsible(final Builder builder) {
        responsibleRetention = builder.responsibleRetention;
    }

    public NFSeResponsibleRetention getResponsibleRetention() {
        return responsibleRetention;
    }

}
