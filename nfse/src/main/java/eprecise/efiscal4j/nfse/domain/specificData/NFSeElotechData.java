
package eprecise.efiscal4j.nfse.domain.specificData;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechIssRequirement;


public class NFSeElotechData implements NFSeSpecificData {

    private static final long serialVersionUID = 1L;

    private final ElotechIssRequirement issRequirement;

    private final String judicialProcessNumber;

    public static class Builder {

        private ElotechIssRequirement issRequirement;

        private String judicialProcessNumber;

        public Builder withIssRequirement(final ElotechIssRequirement issRequirement) {
            this.issRequirement = issRequirement;
            return this;
        }

        public Builder withJudicialProcessNumber(final String judicialProcessNumber) {
            this.judicialProcessNumber = judicialProcessNumber;
            return this;
        }

        public NFSeElotechData build() {
            final NFSeElotechData entity = new NFSeElotechData(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeElotechData() {
        super();
        issRequirement = null;
        judicialProcessNumber = null;
    }

    public NFSeElotechData(final Builder builder) {
        issRequirement = builder.issRequirement;
        judicialProcessNumber = builder.judicialProcessNumber;
    }

    public ElotechIssRequirement getIssRequirement() {
        return issRequirement;
    }

    public String getJudicialProcessNumber() {
        return judicialProcessNumber;
    }

}
