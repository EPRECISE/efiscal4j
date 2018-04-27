
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


@XmlAccessorType(XmlAccessType.FIELD)
public class EventDetailRecipientManifestation extends EventDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "xJust") @Size(min = 15, max = 255) @NFeString final String justification;

    public static class Builder {

        private EventType eventType;

        private String justification;

        public Builder withEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        /**
         * Justificativa do evento (Min 15/Max 255)
         * 
         * @param justification
         * @return
         */
        public Builder withJustification(String justification) {
            this.justification = justification;
            return this;
        }

        public EventDetailRecipientManifestation build() {
            final EventDetailRecipientManifestation entity = new EventDetailRecipientManifestation(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventDetailRecipientManifestation() {
        super();
        this.justification = null;
    }

    public EventDetailRecipientManifestation(Builder builder) {
        super(builder.eventType.getFullDescriptionWithNoAccents());
        this.justification = builder.justification;

        if (!EventType.OPERACAO_NAO_REALIZADA.equals(builder.eventType) && !StringUtils.isEmpty(builder.justification)) {
            throw new IllegalStateException("Quando o tipo de operação é diferente de \"OPERACAO_NAO_REALIZADA\", a justificativa não é necessária");
        }
    }

    public String getJustification() {
        return this.justification;
    }

}
