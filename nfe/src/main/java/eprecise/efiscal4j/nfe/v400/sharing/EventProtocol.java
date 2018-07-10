
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.event.cancel.FiscalDocumentCancel.Processed;
import eprecise.efiscal4j.nfe.transmission.request.NFeEventDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.response.NFeEventDispatchResponse;
import eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument.ProcessedFiscalDocumentCancelAdapter;
import eprecise.efiscal4j.nfe.version.ProcessedEventVersion;


/**
 * Evento Processado da NF-e
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "procEventoNFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventProtocol implements Serializable, ProcessedEventVersion {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_00;

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

    private EventProtocol(Builder builder) {
        this.event = builder.event;
        this.eventResponse = builder.eventResponse;
    }
    
    public EventProtocol(final NFeEventDispatchRequest request, final NFeEventDispatchResponse response) {
    	this.event = Optional.ofNullable(request).filter(EventDispatch.class::isInstance).map(EventDispatch.class::cast).filter(e -> !e.getEvents().isEmpty()).map(e -> e.getEvents().iterator().next()).orElse(null);
    	this.eventResponse = Optional.ofNullable(response).filter(EventDispatchResponseMethod.class::isInstance).map(EventDispatchResponseMethod.class::cast).map(e -> e.getEventDispatchResponse()).filter(e -> !e.getEventResponses().isEmpty()).map(e -> e.getEventResponses().iterator().next()).orElse(null);
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public Event getEvent() {
        return this.event;
    }

    public EventResponse getEventResponse() {
        return this.eventResponse;
    }

	@Override
	public Processed buildProcessedFiscalDocumentCancel() {
		return new ProcessedFiscalDocumentCancelAdapter(this).buildProcessedFiscalDocumentCancel();
	}

}
