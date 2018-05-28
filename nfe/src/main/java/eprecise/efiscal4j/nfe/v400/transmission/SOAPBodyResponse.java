
package eprecise.efiscal4j.nfe.v400.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.transmission.ReceivableWithQName;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPBodyResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns:env") @NotNull final String xmlnsEnv = "http://www.w3.org/2003/05/soap-envelope";

    @XmlElement(name = ObjectFactory.RET_ENVI_NFE) private final ReceivableWithQName receivable;

    public static class Builder {

        private ReceivableWithQName receivable;

        /**
         * 
         * @param receivable
         * @return
         */
        public Builder withReceivable(ReceivableWithQName receivable) {
            this.receivable = receivable;
            return this;
        }

        public SOAPBodyResponse build() {
            final SOAPBodyResponse entity = new SOAPBodyResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public SOAPBodyResponse() {
        this.receivable = null;
    }

    public SOAPBodyResponse(Builder builder) {
        this.receivable = builder.receivable;
    }

    public String getXmlnsEnv() {
        return this.xmlnsEnv;
    }

    public ReceivableWithQName getServiceMethodReturn() {
        return this.receivable;
    }
}
