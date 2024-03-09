
package eprecise.efiscal4j.nfe.v400.transmission;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPSVRSBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nfe:nfeDadosMsg") NFeBody nFeBody;

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

        public SOAPSVRSBody build() {
            final SOAPSVRSBody entity = new SOAPSVRSBody(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public SOAPSVRSBody(Builder builder) {
        this.nFeBody = builder.nFeBody;
    }

}