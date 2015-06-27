
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Detalhe do Evento
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_00;

    private @XmlElement(name = "descEvento") @NotNull final String eventDescription;

    private @XmlElement(name = "nProt") @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String protocolNumber;

    private @XmlElement(name = "xJust") @NotNull @Size(min = 15, max = 255) @NFeString final String justification;

    public static class Builder {

        private String eventDescription;

        private String protocolNumber;

        private String justification;

        /**
         * Descrição do Evento
         * 
         * @param eventDescription
         * @return
         */
        public Builder withEventDescription(String eventDescription) {
            this.eventDescription = eventDescription;
            return this;
        }

        /**
         * Número do Protocolo do registro do Evento. 1 posição (1 – Secretaria de Fazenda Estadual 2 – Receita Federal); 2 posições ano; 10 seqüencial no ano.
         * 
         * @param protocolNumber
         * @return
         */
        public Builder withProtocolNumber(String protocolNumber) {
            this.protocolNumber = protocolNumber;
            return this;
        }

        /**
         * Justificativa do evento
         * 
         * @param justification
         * @return
         */
        public Builder withJustification(String justification) {
            this.justification = justification;
            return this;
        }

        public EventDetail build() {
            final EventDetail entity = new EventDetail(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventDetail() {
        this.eventDescription = null;
        this.protocolNumber = null;
        this.justification = null;
    }

    public EventDetail(Builder builder) {
        this.eventDescription = builder.eventDescription;
        this.protocolNumber = builder.protocolNumber;
        this.justification = builder.justification;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getEventDescription() {
        return this.eventDescription;
    }

    public String getProtocolNumber() {
        return this.protocolNumber;
    }

    public String getJustification() {
        return this.justification;
    }
}
