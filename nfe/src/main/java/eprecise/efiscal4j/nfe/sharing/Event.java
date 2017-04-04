
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
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
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
public class Event extends DefaultAssignable implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_00;

    private @XmlElement(name = "infEvento") @NotNull @Valid final EventInfo eventInfo;

    public static class Builder {

        private EventInfo eventInfo;

        /**
         * Dados do evento
         *
         * @param eventInfo
         * @return
         */
        public Builder withEventInfo(final EventInfo eventInfo) {
            this.eventInfo = eventInfo;
            return this;
        }

        public Event build(final Signer signer) throws Exception {
            Event entity = new Event(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (Event) signer.sign(entity);
            return entity;
        }

    }

    public Event() {
        eventInfo = null;
    }

    public Event(final Builder builder) {
        eventInfo = builder.eventInfo;
    }

    public FiscalDocumentVersion getVersion() {
        return version;
    }

    public EventInfo getEventInfo() {
        return eventInfo;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).considering(Event.getValidationConsideringClasses()).serialize();
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
    public DefaultAssignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, Event.class).considering(Event.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(EventDetailCancellation.class, EventDetailCCe.class, SignatureType.class);
    }

}
