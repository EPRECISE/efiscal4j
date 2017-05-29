
package eprecise.efiscal4j.nfse.domain.service.taker;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.person.NFSePerson;


public class NFSeServiceTaker extends NFSePerson {

    private static final long serialVersionUID = 1L;

    public static class Builder extends NFSePerson.Builder<Builder> {

        public NFSeServiceTaker build() throws Exception {
            final NFSeServiceTaker entity = new NFSeServiceTaker(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeServiceTaker() {
    }

    public NFSeServiceTaker(final Builder builder) {
        super(builder);
    }

}
