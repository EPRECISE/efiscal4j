
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.NaturalPersonDocuments;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.domain.SignatureType;


/**
 * Tipo Evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "evento")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "version", "eventInfo", "signature" })
public class Event extends Assignable implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = FiscalDocumentVersion.EVENT_VERSION;

    private @XmlElement(name = "infEvento") @NotNull @Valid final EventInfo eventInfo;

    public static class Builder {

        private EventInfo eventInfo;

        /**
         * Dados do evento
         * 
         * @param eventInfo
         * @return
         */
        public Builder withEventInfo(EventInfo eventInfo) {
            this.eventInfo = eventInfo;
            return this;
        }

        public Event build(Signer signer) throws Exception {
            Event entity = new Event(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (Event) signer.sign(entity);
            return entity;
        }

    }

    public Event() {
        this.eventInfo = null;
    }

    public Event(Builder builder) {
        this.eventInfo = builder.eventInfo;
    }

    public EventInfo getEventInfo() {
        return this.eventInfo;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).serialize();
    }

    @Override
    public String getRootTagName() {
        return "evento";
    }

    @Override
    public String getAssignableTagName() {
        return "infEvento";
    }

    @Override
    public String getIdAttributeTagName() {
        return "Id";
    }

    @Override
    public Assignable getAsEntity(String xml) {
        return new FiscalDocumentDeserializer<Event>(xml, Event.class).considering(Event.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(LegalEntityDocuments.class, NaturalPersonDocuments.class, SignatureType.class);
    }

}
