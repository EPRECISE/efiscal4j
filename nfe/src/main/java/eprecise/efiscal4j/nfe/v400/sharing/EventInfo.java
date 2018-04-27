
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.v400.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.v400.types.NFeCNPJOptional;
import eprecise.efiscal4j.nfe.v400.types.NFeCPF;
import eprecise.efiscal4j.nfe.v400.types.NFeDateTimeUTC;


/**
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") @NotNull @Pattern(regexp = "ID[0-9]{52}") final String id;

    private @XmlElement(name = "cOrgao") @NotNull final IBGEOrgan ibgeOrgan;

    private @XmlElement(name = "tpAmb") @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "CNPJ") @NFeCNPJOptional final String authorCnpj;

    private @XmlElement(name = "CPF") @NFeCPF final String authorCpf;

    private @XmlElement(name = "chNFe") @NotNull @NFeAccessKey final String acessKey;

    private @XmlElement(name = "dhEvento") @NotNull @NFeDateTimeUTC final String eventDateTime;

    private @XmlElement(name = "tpEvento") @NotNull final EventType eventType;

    private @XmlElement(name = "nSeqEvento") @NotNull @Pattern(regexp = "[1-9][0-9]{0,1}") final String eventSeqNumber;

    private @XmlElement(name = "verEvento") @NotNull final String eventVersion;

    private @XmlElement(name = "detEvento") @NotNull final EventDetail eventDetail;

    public static class Builder {

        private IBGEOrgan ibgeOrgan;

        private TransmissionEnvironment transmissionEnvironment;

        private String authorCnpj;

        private String authorCpf;

        private String acessKey;

        private String eventDateTime;

        private EventType eventType;

        private String eventSeqNumber;

        private String eventVersion;

        private EventDetail eventDetail;

        /**
         * Código do órgão de recepção do Evento. Utilizar a Tabela do IBGE extendida, utilizar 90 para identificar o Ambiente Nacional
         * 
         * @see IBGEOrgan
         * @param ibgeOrgan
         * @return
         */
        public Builder withIbgeOrgan(IBGEOrgan ibgeOrgan) {
            this.ibgeOrgan = ibgeOrgan;
            return this;
        }

        /**
         * @see TransmissionEnvironment
         * @param transmissionEnvironment
         * @return
         */
        public Builder withTransmissionEnvironment(TransmissionEnvironment transmissionEnvironment) {
            this.transmissionEnvironment = transmissionEnvironment;
            return this;
        }

        /**
         * 
         * @param authorCnpj
         * @return
         */
        public Builder withAuthorCnpj(String authorCnpj) {
            this.authorCnpj = authorCnpj;
            return this;
        }

        /**
         * 
         * @param authorCpf
         * @return
         */
        public Builder withAuthorCpf(String authorCpf) {
            this.authorCpf = authorCpf;
            return this;
        }

        /**
         * @see NFeAccessKey
         * @param acessKey
         * @return
         */
        public Builder withAcessKey(String acessKey) {
            this.acessKey = acessKey;
            return this;
        }

        /**
         * Data e hora do evento
         * 
         * @param eventDateTime
         * @return
         */
        public Builder withEventDateTime(String eventDateTime) {
            this.eventDateTime = eventDateTime;
            return this;
        }

        /**
         * @see EventType
         * @param eventType
         * @return
         */
        public Builder withEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        /**
         * Seqüencial do evento para o mesmo tipo de evento. <br>
         * Para maioria dos eventos será 1, nos casos em que possa existir mais de um evento, <br>
         * como é o caso da carta de correção, o autor do evento deve numerar de forma seqüencial.
         * 
         * @param eventSeqNumber
         * @return
         */
        public Builder withEventSeqNumber(String eventSeqNumber) {
            this.eventSeqNumber = eventSeqNumber;
            return this;
        }

        /**
         * Versão do Tipo do Evento
         * 
         * @param eventVersion
         * @return
         */
        public Builder withEventVersion(String eventVersion) {
            this.eventVersion = eventVersion;
            return this;
        }

        /**
         * @see EventDetail
         * @param eventDetail
         * @return
         */
        public Builder withEventDetail(EventDetail eventDetail) {
            this.eventDetail = eventDetail;
            return this;
        }

        public EventInfo build() {
            final EventInfo entity = new EventInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventInfo() {
        this.id = null;
        this.ibgeOrgan = null;
        this.transmissionEnvironment = null;
        this.authorCnpj = null;
        this.authorCpf = null;
        this.acessKey = null;
        this.eventDateTime = null;
        this.eventType = null;
        this.eventSeqNumber = null;
        this.eventVersion = null;
        this.eventDetail = null;
    }

    public EventInfo(Builder builder) {
        this.ibgeOrgan = builder.ibgeOrgan;
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.authorCnpj = builder.authorCnpj;
        this.authorCpf = builder.authorCpf;
        this.acessKey = builder.acessKey;
        this.eventDateTime = builder.eventDateTime;
        this.eventType = builder.eventType;
        this.eventSeqNumber = builder.eventSeqNumber;
        this.eventVersion = builder.eventVersion;
        this.eventDetail = builder.eventDetail;
        this.id = this.generateEventId();
    }

    /**
     * Gera o ID do Evento a ser assinado, que é composto por:
     *
     * <ul>
     * <li>ID - Texto contendo ID</li>
     * <li>tpEvento - Código do tipo de evento</li>
     * <li>chaveNFe - Chave da NF-e</li>
     * <li>nSeqEvento - Número sequencial do evento</li>
     * </ul>
     *
     * @param builder
     * @throws ParseException
     */
    private String generateEventId() {
        final StringBuilder eventId = new StringBuilder();

        eventId.append("ID");
        eventId.append(this.getEventType().getCode());
        eventId.append(this.getAcessKey());
        eventId.append(new DecimalFormat("00").format(Integer.valueOf(this.getEventSeqNumber())));

        return eventId.toString();
    }

    public String getId() {
        return this.id;
    }

    public IBGEOrgan getIbgeOrgan() {
        return this.ibgeOrgan;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getAuthorCnpj() {
        return this.authorCnpj;
    }

    public String getAuthorCpf() {
        return this.authorCpf;
    }

    public String getAcessKey() {
        return this.acessKey;
    }

    public String getEventDateTime() {
        return this.eventDateTime;
    }

    public boolean isOwnerManifestation() {
        return EventType.CANC_NFE.equals(this.getEventType()) || EventType.CCE.equals(this.getEventType());
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public String getEventSeqNumber() {
        return this.eventSeqNumber;
    }

    public String getEventVersion() {
        return this.eventVersion;
    }

    public EventDetail getEventDetail() {
        return this.eventDetail;
    }

}
