
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.types.NFeVersion;
import eprecise.efiscal4j.signer.Assignable;


/**
 * Tipo Evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Event implements Serializable, Assignable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = NFeVersion.EVENT_VERSION;

    private @XmlElement(name = "infEvento") @NotNull @Valid final EventInfo eventInfo;

    private @XmlTransient String signedXml;

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

        public Event build() {
            final Event entity = new Event(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
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
    public String getSignedXml() {
        return this.signedXml;
    }

    @Override
    public void setSignedXml(String signedXml) {
        this.signedXml = signedXml;
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

}
