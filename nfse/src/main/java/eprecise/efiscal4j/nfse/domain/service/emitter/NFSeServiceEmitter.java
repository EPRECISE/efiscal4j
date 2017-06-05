
package eprecise.efiscal4j.nfse.domain.service.emitter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.person.NFSePerson;


public class NFSeServiceEmitter extends NFSePerson {

    private static final long serialVersionUID = 1L;

    public static class Builder extends NFSePerson.Builder<Builder> {

        public NFSeServiceEmitter build() {
            final NFSeServiceEmitter entity = new NFSeServiceEmitter(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeServiceEmitter() {
    }

    public NFSeServiceEmitter(final Builder builder) {
        super(builder);
    }

}
