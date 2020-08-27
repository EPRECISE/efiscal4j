
package eprecise.efiscal4j.nfse.domain.specificData.curitiba;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.specificData.NFSeSpecificData;
import eprecise.efiscal4j.nfse.tc.curitiba.lot.rps.CuritibaNatureOperation;


public class NFSeCuritibaData implements NFSeSpecificData {

    private static final long serialVersionUID = 1L;

    private final CuritibaNatureOperation natureOperation;

    private final Boolean simpleNational;

    private final Boolean culturalPromoter;

    public static class Builder {

        private CuritibaNatureOperation natureOperation;

        private Boolean simpleNational;

        private Boolean culturalPromoter;

        public Builder withNatureOperation(final CuritibaNatureOperation natureOperation) {
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

        public NFSeCuritibaData build() {
            final NFSeCuritibaData entity = new NFSeCuritibaData(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFSeCuritibaData() {
        natureOperation = null;
        simpleNational = null;
        culturalPromoter = null;
    }

    public NFSeCuritibaData(final Builder builder) {
        natureOperation = builder.natureOperation;
        simpleNational = builder.simpleNational;
        culturalPromoter = builder.culturalPromoter;
    }

    public CuritibaNatureOperation getNatureOperation() {
        return natureOperation;
    }

    public Boolean getSimpleNational() {
        return simpleNational;
    }

    public Boolean getCulturalPromoter() {
        return culturalPromoter;
    }

}
