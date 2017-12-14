
package eprecise.efiscal4j.nfe.deliveryDFe.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.sharing.Event;
import eprecise.efiscal4j.nfe.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.sharing.EventResponse;


@XmlRootElement(name = "procEventoNFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeEventProtocol extends EventProtocol implements NFeDeliveryDfeDocumentContent {

    private static final long serialVersionUID = 1L;

    public NFeDeliveryDFeEventProtocol() {
        super();
    }

    private NFeDeliveryDFeEventProtocol(Builder builder) {
        super(builder);
    }

    public static class Builder extends EventProtocol.Builder {

        /**
         * @see Event
         * @param event
         * @return
         */
        @Override
        public Builder withEvent(Event event) {
            return (Builder) super.withEvent(event);
        }

        /**
         * @see EventResponse
         * @param eventResponse
         * @return
         */
        @Override
        public Builder withEventResponse(EventResponse eventResponse) {
            return (Builder) super.withEventResponse(eventResponse);
        }

        @Override
        public NFeDeliveryDFeEventProtocol build() {
            final NFeDeliveryDFeEventProtocol entity = new NFeDeliveryDFeEventProtocol(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

}
