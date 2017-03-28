
package eprecise.efiscal4j.nfse.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    public static class Builder {

        public SOAPHeader build() {
            final SOAPHeader entity = new SOAPHeader(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public SOAPHeader() {

    }

    public SOAPHeader(final Builder builder) {
    }

}
