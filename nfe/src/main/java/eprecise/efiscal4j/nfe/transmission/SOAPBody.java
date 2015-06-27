
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nfeDadosMsg") NFeBody nFeBody;

    public static class Builder {

        private NFeBody nFeBody;

        /**
         * 
         * @param nFeBody
         * @return
         */
        public Builder withNfeBody(NFeBody nFeBody) {
            this.nFeBody = nFeBody;
            return this;
        }

        public SOAPBody build() {
            final SOAPBody entity = new SOAPBody(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public SOAPBody() {

    }

    public SOAPBody(Builder builder) {
        this.nFeBody = builder.nFeBody;
    }

}
