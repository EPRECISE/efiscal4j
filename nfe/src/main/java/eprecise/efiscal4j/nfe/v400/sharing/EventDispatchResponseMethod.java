
package eprecise.efiscal4j.nfe.v400.sharing;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.transmission.response.NFeEventDispatchResponse;
import eprecise.efiscal4j.nfe.v400.transmission.ObjectFactory;


/**
 * Método retornado após consumo do WS de recepção de evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.NFE_RESULT_MSG, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeRecepcaoEvento4")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDispatchResponseMethod extends Receivable implements NFeEventDispatchResponse {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = ObjectFactory.RET_ENV_EVENTO) @NotNull final EventDispatchResponse eventDispatchResponse;

    public static class Builder {

        private EventDispatchResponse eventDispatchResponse;

        /**
         * 
         * @param eventDispatchResponse
         * @return
         */
        public Builder withEventDispatchResponse(final EventDispatchResponse eventDispatchResponse) {
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

    public EventDispatchResponseMethod(final Builder builder) {
        this.eventDispatchResponse = builder.eventDispatchResponse;
    }

    public EventDispatchResponse getEventDispatchResponse() {
        return this.eventDispatchResponse;
    }

    @Override
    public EventStatus getStatus() {
        return Optional.ofNullable(this.eventDispatchResponse).map(response -> {
            return Optional.ofNullable(response).map(r -> r.getEventResponses().stream().findFirst().orElse(null)).map(er -> er.getEventResponseInfo()).map(info -> {
                return EventStatus.builder().statusCode(info.getStatusCode()).statusDescription(info.getStatusDescription()).build();
            }).orElse(EventStatus.builder().statusCode(response.getStatusCode()).statusDescription(response.getStatusDescription()).build());
        }).orElse(null);
    }

}
