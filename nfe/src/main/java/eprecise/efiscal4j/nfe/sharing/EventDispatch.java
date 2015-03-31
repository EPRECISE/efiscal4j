
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeVersion;


/**
 * Tipo Lote de Envio de Evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "envEvento")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDispatch implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = NFeVersion.EVENT_VERSION;

    private @XmlElement(name = "idLote") @NotNull @Pattern(regexp = "[0-9]{1,15}") final String batchId;

    private @XmlElement(name = "evento") @NotNull @Size(max = 20) @Valid final ArrayList<Event> events;

    public static class Builder {

        private String batchId;

        private ArrayList<Event> events;

        /**
         * 
         * @param batchId
         * @return
         */
        public Builder withBatchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        /**
         * List of {@link Event}
         * 
         * @see Event
         * @param events
         * @return
         */
        public Builder withEvents(ArrayList<Event> events) {
            this.events = events;
            return this;
        }

        public EventDispatch build() {
            final EventDispatch entity = new EventDispatch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public EventDispatch() {
        this.batchId = null;
        this.events = null;
    }

    public EventDispatch(Builder builder) {
        this.batchId = builder.batchId;
        this.events = builder.events;
    }
}