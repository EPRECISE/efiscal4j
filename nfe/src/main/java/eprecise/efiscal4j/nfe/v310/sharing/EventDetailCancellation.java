
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Detalhe do Evento - Cancelamento
 * 
 * @see EventDetail
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventDetailCancellation extends EventDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nProt") @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String protocolNumber;

    private @XmlElement(name = "xJust") @NotNull @Size(min = 15, max = 255) @NFeString final String justification;

    public static class Builder {

        private String protocolNumber;

        private String justification;

        /**
         * Número do Protocolo do registro do Evento.
         * 
         * <ul>
         * <li>1 posição - (1 – Secretaria de Fazenda Estadual 2 – Receita Federal)</li>
         * <li>2 posições - Ano</li>
         * <li>10 posições - Seqüencial no ano</li>
         * </ul>
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

        public EventDetailCancellation build() {
            final EventDetailCancellation entity = new EventDetailCancellation(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventDetailCancellation() {
        super();
        this.protocolNumber = null;
        this.justification = null;
    }

    public EventDetailCancellation(Builder builder) {
        super(EventType.CANC_NFE.getDescription());
        this.protocolNumber = builder.protocolNumber;
        this.justification = builder.justification;
    }

    public String getProtocolNumber() {
        return this.protocolNumber;
    }

    public String getJustification() {
        return this.justification;
    }
}
