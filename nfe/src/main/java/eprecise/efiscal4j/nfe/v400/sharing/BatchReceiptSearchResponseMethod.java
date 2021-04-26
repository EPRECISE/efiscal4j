
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;
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
import eprecise.efiscal4j.nfe.transmission.response.NFeBatchReceiptSearchResponse;
import eprecise.efiscal4j.nfe.v400.transmission.ObjectFactory;


/**
 * Método retornado após consumo do WS de retorno de autorização
 *
 * @author Felipe Bueno
 *
 */
@XmlRootElement(name = ObjectFactory.NFE_RESULT_MSG, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeRetAutorizacao4")
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchReceiptSearchResponseMethod extends Receivable implements NFeBatchReceiptSearchResponse, NFeAuthorizationResponse, Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = ObjectFactory.RET_CONS_RECI_NFE) @NotNull final BatchReceiptSearchResponse batchReceiptSearchResponse;

    public static class Builder {

        private BatchReceiptSearchResponse batchReceiptSearchResponse;

        /**
         *
         * @param batchReceiptSearchResponse
         * @return
         */
        public Builder withNfeDispatchResponse(final BatchReceiptSearchResponse batchReceiptSearchResponse) {
            this.batchReceiptSearchResponse = batchReceiptSearchResponse;
            return this;
        }

        public BatchReceiptSearchResponseMethod build() {
            final BatchReceiptSearchResponseMethod entity = new BatchReceiptSearchResponseMethod(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public BatchReceiptSearchResponseMethod() {
        this.batchReceiptSearchResponse = null;
    }

    public BatchReceiptSearchResponseMethod(final Builder builder) {
        this.batchReceiptSearchResponse = builder.batchReceiptSearchResponse;
    }

    @Override
    public EventStatus getStatus() {
        return Optional.ofNullable(this.batchReceiptSearchResponse).map(response -> Optional.ofNullable(response).map(BatchReceiptSearchResponse::getProcessingStatusProtocol).map(ProcessingStatusProtocol::getProcessingStatusProtocolInfo).map(info -> EventStatus.builder().statusCode(info.getStatusCode()).statusDescription(info.getStatusDescription()).build()).orElse(EventStatus.builder().statusCode(response.getStatusCode()).statusDescription(response.getStatusDescription()).build())).orElse(null);
    }


}
