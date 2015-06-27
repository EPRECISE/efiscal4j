
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;


/**
 * Método retornado após consumo do WS de recepção de evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.NFE_REC_EVENTO_RESULT)
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDispatchResponseMethod extends Receivable implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = ObjectFactory.ENV_EVENTO) @NotNull final EventDispatchResponse eventDispatchResponse;

    private @XmlTransient QName qName = new QName(ObjectFactory.NFE_REC_EVENTO_RESULT);

    public static class Builder {

        private EventDispatchResponse eventDispatchResponse;

        /**
         * 
         * @param eventDispatchResponse
         * @return
         */
        public Builder withEventDispatchResponse(EventDispatchResponse eventDispatchResponse) {
            this.eventDispatchResponse = eventDispatchResponse;
            return this;
        }

        public EventDispatchResponseMethod build() {
            final EventDispatchResponseMethod entity = new EventDispatchResponseMethod(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventDispatchResponseMethod() {
        this.eventDispatchResponse = null;
    }

    public EventDispatchResponseMethod(Builder builder) {
        this.eventDispatchResponse = builder.eventDispatchResponse;
    }

    @Override
    public void setQName(QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

}
