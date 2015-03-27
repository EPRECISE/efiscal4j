
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
 * Protocolo de registro de evento da NF-e
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventProtocol implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = NFeVersion.EVENT_VERSION;

    private @XmlElement(name = "evento") @NotNull @Valid final Event event;

    private @XmlElement(name = "retEvento") @NotNull @Valid final EventResponse eventResponse;

    public static class Builder {

        private Event event;

        private EventResponse eventResponse;

        /**
         * @see Event
         * @param event
         * @return
         */
        public Builder withEvent(Event event) {
            this.event = event;
            return this;
        }

        /**
         * @see EventResponse
         * @param eventResponse
         * @return
         */
        public Builder withEventResponse(EventResponse eventResponse) {
            this.eventResponse = eventResponse;
            return this;
        }

        public EventProtocol build() {
            final EventProtocol entity = new EventProtocol(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public EventProtocol() {
        this.event = null;
        this.eventResponse = null;
    }

    public EventProtocol(Builder builder) {
        this.event = builder.event;
        this.eventResponse = builder.eventResponse;
    }

    public Event getEvent() {
        return this.event;
    }

    public EventResponse getEventResponse() {
        return this.eventResponse;
    }

}
