
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
import eprecise.efiscal4j.nfe.transmission.response.NFeAuthorizationResponse;
import eprecise.efiscal4j.nfe.v400.transmission.ObjectFactory;


/**
 * Método retornado após consumo do WS de autorização
 *
 * @author Felipe Bueno
 *
 */
@XmlRootElement(name = ObjectFactory.NFE_RESULT_MSG, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeAutorizacao4")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDispatchResponseMethod extends Receivable implements NFeAuthorizationResponse {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = ObjectFactory.RET_ENVI_NFE, namespace = "http://www.portalfiscal.inf.br/nfe") @NotNull final NFeDispatchResponse nFeDispatchResponse;

    public static class Builder {

        private NFeDispatchResponse nFeDispatchResponse;

        /**
         *
         * @param nFeDispatchResponse
         * @return
         */
        public Builder withNfeDispatchResponse(final NFeDispatchResponse nFeDispatchResponse) {
            this.nFeDispatchResponse = nFeDispatchResponse;
            return this;
        }

        public NFeDispatchResponseMethod build() {
            final NFeDispatchResponseMethod entity = new NFeDispatchResponseMethod(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeDispatchResponseMethod() {
        this.nFeDispatchResponse = null;
    }

    public NFeDispatchResponseMethod(final Builder builder) {
        this.nFeDispatchResponse = builder.nFeDispatchResponse;
    }

    public NFeDispatchResponse getnFeDispatchResponse() {
        return this.nFeDispatchResponse;
    }

    @Override
    public EventStatus getStatus() {
        return Optional.ofNullable(this.nFeDispatchResponse).map(response -> {
            return Optional.ofNullable(response).map(r -> r.getProcessingStatusProtocol()).map(psp -> psp.getProcessingStatusProtocolInfo()).map(info -> {
                return EventStatus.builder().statusCode(info.getStatusCode()).statusDescription(info.getStatusDescription()).build();
            }).orElse(EventStatus.builder().statusCode(response.getStatusCode()).statusDescription(response.getStatusDescription()).build());
        }).orElse(null);
    }

}
