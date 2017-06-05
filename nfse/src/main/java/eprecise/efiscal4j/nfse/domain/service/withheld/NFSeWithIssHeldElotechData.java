
package eprecise.efiscal4j.nfse.domain.service.withheld;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechResponsibleRetention;


public class NFSeWithIssHeldElotechData implements NFSeWithIssHeldSpecificData {

    private static final long serialVersionUID = 1L;

    private final ElotechResponsibleRetention responsibleRetention;

    public static class Builder {

        private ElotechResponsibleRetention responsibleRetention;

        public Builder withResponsibleRetention(final ElotechResponsibleRetention responsibleRetention) {
            this.responsibleRetention = responsibleRetention;
            return this;
        }

        public NFSeWithIssHeldElotechData build() {
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

    public ElotechResponsibleRetention getResponsibleRetention() {
        return responsibleRetention;
    }

}
