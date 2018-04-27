
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;


/**
 * Detalhe do Evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlJavaTypeAdapter(EventDetailAdapter.class)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EventDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_00;

    private @XmlElement(name = "descEvento") @NotNull final String eventDescription;

    public EventDetail() {
        this.eventDescription = null;
    }

    public EventDetail(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getEventDescription() {
        return this.eventDescription;
    }

}
