
package eprecise.efiscal4j.nfse.domain.service.emitter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.person.NFSePerson;
import eprecise.efiscal4j.nfse.domain.service.emitter.specificData.NFSeServiceEmitterSpecificData;


public class NFSeServiceEmitter extends NFSePerson {

    private static final long serialVersionUID = 1L;

    private final NFSeServiceEmitterSpecificData specificData;

    public static class Builder extends NFSePerson.Builder<Builder> {

        private NFSeServiceEmitterSpecificData specificData;

        public Builder withSpecificData(final NFSeServiceEmitterSpecificData specificData) {
            this.specificData = specificData;
            return this;
        }

        public NFSeServiceEmitter build() throws Exception {
            final NFSeServiceEmitter entity = new NFSeServiceEmitter(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeServiceEmitter() {
        specificData = null;
    }

    public NFSeServiceEmitter(final Builder builder) {
        super(builder);
        specificData = builder.specificData;
    }

    public NFSeServiceEmitterSpecificData getSpecificData() {
        return specificData;
    }

}
