
package eprecise.efiscal4j.nfse.domain.service.emitter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.person.NFSePerson;
import eprecise.efiscal4j.nfse.domain.specialTaxationRegime.NFSeSpecialTaxationRegime;


public class NFSeServiceEmitter extends NFSePerson {

    private static final long serialVersionUID = 1L;

    private final NFSeSpecialTaxationRegime specialTaxationRegime;

    public static class Builder extends NFSePerson.Builder<Builder> {

        private NFSeSpecialTaxationRegime specialTaxationRegime;

        public Builder withSpecialTaxationRegime(final NFSeSpecialTaxationRegime specialTaxationRegime) {
            this.specialTaxationRegime = specialTaxationRegime;
            return this;
        }

        public NFSeServiceEmitter build() {
            final NFSeServiceEmitter entity = new NFSeServiceEmitter(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeServiceEmitter() {
        specialTaxationRegime = null;
    }

    public NFSeServiceEmitter(final Builder builder) {
        super(builder);
        specialTaxationRegime = builder.specialTaxationRegime;
    }

    public NFSeSpecialTaxationRegime getSpecialTaxationRegime() {
        return specialTaxationRegime;
    }

}
