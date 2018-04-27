
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * Tipo retorno do Evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "retEvento")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_00;

    private @XmlElement(name = "infEvento") @NotNull @Valid final EventResponseInfo eventResponseInfo;

    public static class Builder {

        private EventResponseInfo eventResponseInfo;

        /**
         * @see EventResponseInfo
         * @param eventResponseInfo
         * @return
         */
        public Builder withEventResponseInfo(EventResponseInfo eventResponseInfo) {
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

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public EventResponseInfo getEventResponseInfo() {
        return this.eventResponseInfo;
    }
}
