
package eprecise.efiscal4j.nfe.v310.sharing;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.transmission.response.NFeAuthorizationResponse;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v310.transmission.ReceivableWithQName;


/**
 * Método retornado após consumo do WS de autorização
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.NFE_AUT_LOTE_RESULT, namespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao3")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDispatchResponseMethod extends ReceivableWithQName implements NFeAuthorizationResponse {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = ObjectFactory.RET_ENVI_NFE) @NotNull final NFeDispatchResponse nFeDispatchResponse;

    private @XmlTransient QName qName = new QName(ObjectFactory.NFE_AUT_LOTE_RESULT);

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

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
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
    
    @Override
    public String getProtocol() {
        return Optional.ofNullable(this.nFeDispatchResponse).map(NFeDispatchResponse::getProcessingStatusProtocol).map(ProcessingStatusProtocol::getProcessingStatusProtocolInfo)
                .map(ProcessingStatusProtocolInfo::getProtocolNumber).orElse(" - ");
    }

}
