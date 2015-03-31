
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeVersion;


/**
 * Tipo retorno do Evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = NFeVersion.EVENT_VERSION;

    private @XmlElement(name = "infEvento") @NotNull @Valid final EventResponseInfo eventResponseInfo;

    public static class Builder {

        private EventResponseInfo eventResponseInfo;

        /**
         * @see EventResponseInfo
         * @param eventResponseInfo
         * @return
         */
        public Builder witheventResponseInfo(EventResponseInfo eventResponseInfo) {
            this.eventResponseInfo = eventResponseInfo;
            return this;
        }

        public EventResponse build() {
            final EventResponse entity = new EventResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventResponse() {
        this.eventResponseInfo = null;
    }

    public EventResponse(Builder builder) {
        this.eventResponseInfo = builder.eventResponseInfo;
    }

    public EventResponseInfo getEventResponseInfo() {
        return this.eventResponseInfo;
    }
}