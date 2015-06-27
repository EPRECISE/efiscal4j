
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nfeCabecMsg") NFeHeader nFeHeader;

    public static class Builder {

        private NFeHeader nFeHeader;

        /**
         * 
         * @param nFeHeader
         * @return
         */
        public Builder withNfeHeader(NFeHeader nFeHeader) {
            this.nFeHeader = nFeHeader;
            return this;
        }

        public SOAPHeader build() {
            final SOAPHeader entity = new SOAPHeader(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public SOAPHeader() {

    }

    public SOAPHeader(Builder builder) {
        this.nFeHeader = builder.nFeHeader;
    }

}
