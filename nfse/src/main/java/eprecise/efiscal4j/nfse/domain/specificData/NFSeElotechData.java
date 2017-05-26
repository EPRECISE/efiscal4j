
package eprecise.efiscal4j.nfse.domain.specificData;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.elotech.lot.statements.services.ElotechIssRequirement;


public class NFSeElotechData implements NFSeSpecificData {

    private static final long serialVersionUID = 1L;

    private final ElotechIssRequirement issRequirement;

    private final String judicialProcessNumber;

    private final boolean homologation;

    private final boolean taxIncentive;

    public static class Builder {

        private ElotechIssRequirement issRequirement;

        private String judicialProcessNumber;

        private boolean homologation;

        private boolean taxIncentive;

        public Builder withIssRequirement(final ElotechIssRequirement issRequirement) {
            this.issRequirement = issRequirement;
            return this;
        }

        public Builder withJudicialProcessNumber(final String judicialProcessNumber) {
            this.judicialProcessNumber = judicialProcessNumber;
            return this;
        }

        public Builder withHomologation(final boolean homologation) {
            this.homologation = homologation;
            return this;
        }

        public Builder withTaxIncentive(final boolean taxIncentive) {
            this.taxIncentive = taxIncentive;
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
        homologation = false;
        taxIncentive = false;
    }

    public NFSeElotechData(final Builder builder) {
        issRequirement = builder.issRequirement;
        judicialProcessNumber = builder.judicialProcessNumber;
        homologation = builder.homologation;
        taxIncentive = builder.taxIncentive;
    }

    public ElotechIssRequirement getIssRequirement() {
        return issRequirement;
    }

    public String getJudicialProcessNumber() {
        return judicialProcessNumber;
    }

    public boolean isHomologation() {
        return homologation;
    }

    public boolean isTaxIncentive() {
        return taxIncentive;
    }

}
