
package eprecise.efiscal4j.nfe.v310.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPBodyResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns:env") @NotNull final String xmlnsEnv = "http://www.w3.org/2003/05/soap-envelope";

    //@formatter:off
    @XmlElementRefs({        
        @XmlElementRef(name = ObjectFactory.NFE_STAT_SERV_RESULT),
        @XmlElementRef(name = ObjectFactory.NFE_AUT_LOTE_RESULT),
        @XmlElementRef(name = ObjectFactory.NFE_RET_AUT_RESULT),
        @XmlElementRef(name = ObjectFactory.NFE_REC_EVENTO_RESULT),
    })
    @XmlJavaTypeAdapter(ReceivableAdapter.class)
    private final ReceivableWithQName receivable;
    //@formatter:on

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
