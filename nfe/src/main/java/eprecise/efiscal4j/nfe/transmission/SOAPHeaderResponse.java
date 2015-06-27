
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPHeaderResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns:env") @NotNull final String xmlnsEnv = "http://www.w3.org/2003/05/soap-envelope";

    private @XmlElement(name = "nfeCabecMsg") @NotNull final NFeHeader nFeHeader;

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

        public SOAPHeaderResponse build() {
            final SOAPHeaderResponse entity = new SOAPHeaderResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public SOAPHeaderResponse() {
        this.nFeHeader = null;
    }

    public SOAPHeaderResponse(Builder builder) {
        this.nFeHeader = builder.nFeHeader;
    }

    public String getXmlnsEnv() {
        return this.xmlnsEnv;
    }

    public NFeHeader getnFeHeader() {
        return this.nFeHeader;
    }

}
