
package eprecise.efiscal4j.nfse.domain.service.intermediary;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.person.NFSePerson;


public class NFSeServiceIntermediary extends NFSePerson {

    private static final long serialVersionUID = 1L;

    public static class Builder extends NFSePerson.Builder {

        public NFSeServiceIntermediary build() throws Exception {
            final NFSeServiceIntermediary entity = new NFSeServiceIntermediary(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFSeServiceIntermediary() {
    }

    public NFSeServiceIntermediary(final Builder builder) {
        super(builder);
    }

}
