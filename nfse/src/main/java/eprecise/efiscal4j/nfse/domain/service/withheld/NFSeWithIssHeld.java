
package eprecise.efiscal4j.nfse.domain.service.withheld;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


public class NFSeWithIssHeld implements NFSeIssHeld {

    private static final long serialVersionUID = 1L;

    private final NFSeWithIssHeldSpecificData specificData;

    public static class Builder {

        private NFSeWithIssHeldSpecificData specificData;

        public Builder withSpecificData(final NFSeWithIssHeldSpecificData specificData) {
            this.specificData = specificData;
            return this;
        }

        public NFSeWithIssHeld build() {
            final NFSeWithIssHeld entity = new NFSeWithIssHeld(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeWithIssHeld() {
        specificData = null;
    }

    public NFSeWithIssHeld(final Builder builder) {
        specificData = builder.specificData;
    }

    public NFSeWithIssHeldSpecificData getSpecificData() {
        return specificData;
    }

}
