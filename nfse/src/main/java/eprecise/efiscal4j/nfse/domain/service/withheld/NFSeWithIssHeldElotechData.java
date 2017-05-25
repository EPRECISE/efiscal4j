
package eprecise.efiscal4j.nfse.domain.service.withheld;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeWithIssHeldElotechData implements NFSeWithIssHeldSpecificData {

    private static final long serialVersionUID = 1L;

    private final NFSeResponsibleRetention responsibleRetention;

    public static class Builder {

        private NFSeResponsibleRetention responsibleRetention;

        public Builder withResponsibleRetention(final NFSeResponsibleRetention responsibleRetention) {
            this.responsibleRetention = responsibleRetention;
            return this;
        }

        public NFSeWithIssHeldElotechData build() throws Exception {
            final NFSeWithIssHeldElotechData entity = new NFSeWithIssHeldElotechData(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeWithIssHeldElotechData() {
        responsibleRetention = null;
    }

    public NFSeWithIssHeldElotechData(final Builder builder) {
        responsibleRetention = builder.responsibleRetention;
    }

    public NFSeResponsibleRetention getResponsibleRetention() {
        return responsibleRetention;
    }

    public static enum NFSeResponsibleRetention {

                                                 TAKER,
                                                 INTERMEDIARY

    }

}
