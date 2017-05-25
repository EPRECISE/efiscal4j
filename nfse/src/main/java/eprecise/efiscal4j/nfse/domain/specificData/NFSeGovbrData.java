
package eprecise.efiscal4j.nfse.domain.specificData;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrNatureOperation;


public class NFSeGovbrData implements NFSeSpecificData {

    private static final long serialVersionUID = 1L;

    private final GovbrNatureOperation natureOperation;

    private final Boolean simpleNational;

    private final Boolean culturalPromoter;

    public static class Builder {

        private GovbrNatureOperation natureOperation;

        private Boolean simpleNational;

        private Boolean culturalPromoter;

        public Builder withNatureOperation(final GovbrNatureOperation natureOperation) {
            this.natureOperation = natureOperation;
            return this;
        }

        public Builder withSimpleNational(final Boolean simpleNational) {
            this.simpleNational = simpleNational;
            return this;
        }

        public Builder withCulturalPromoter(final Boolean culturalPromoter) {
            this.culturalPromoter = culturalPromoter;
            return this;
        }

        public NFSeGovbrData build() {
            final NFSeGovbrData entity = new NFSeGovbrData(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeGovbrData() {
        natureOperation = null;
        simpleNational = null;
        culturalPromoter = null;
    }

    public NFSeGovbrData(final Builder builder) {
        natureOperation = builder.natureOperation;
        simpleNational = builder.simpleNational;
        culturalPromoter = builder.culturalPromoter;
    }

    public GovbrNatureOperation getNatureOperation() {
        return natureOperation;
    }

    public Boolean getSimpleNational() {
        return simpleNational;
    }

    public Boolean getCulturalPromoter() {
        return culturalPromoter;
    }

}
