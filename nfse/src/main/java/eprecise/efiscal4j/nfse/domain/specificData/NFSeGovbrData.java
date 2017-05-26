
package eprecise.efiscal4j.nfse.domain.specificData;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrNatureOperation;
import eprecise.efiscal4j.nfse.tc.govbr.lot.rps.GovbrSpecialTaxationRegime;


public class NFSeGovbrData implements NFSeSpecificData {

    private static final long serialVersionUID = 1L;

    private final GovbrNatureOperation natureOperation;

    private final Boolean simpleNational;

    private final Boolean culturalPromoter;

    private final GovbrSpecialTaxationRegime specialTaxationRegime;

    public static class Builder {

        private GovbrNatureOperation natureOperation;

        private Boolean simpleNational;

        private Boolean culturalPromoter;

        private GovbrSpecialTaxationRegime specialTaxationRegime;

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

        public Builder withSpecialTaxationRegime(final GovbrSpecialTaxationRegime specialTaxationRegime) {
            this.specialTaxationRegime = specialTaxationRegime;
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
        specialTaxationRegime = null;
    }

    public NFSeGovbrData(final Builder builder) {
        natureOperation = builder.natureOperation;
        simpleNational = builder.simpleNational;
        culturalPromoter = builder.culturalPromoter;
        specialTaxationRegime = builder.specialTaxationRegime;
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

    public GovbrSpecialTaxationRegime getSpecialTaxationRegime() {
        return specialTaxationRegime;
    }

}
