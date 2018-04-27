
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventResponseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") @Pattern(regexp = "ID[0-9]{15}") final String id;

    private @XmlElement(name = "tpAmb") @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "cOrgao") @NotNull final IBGEOrgan ibgeOrgan;

    private @XmlElement(name = "cStat") @NotNull @Size(max = 3) @Pattern(regexp = "[0-9]{3}") final String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull @Size(min = 1, max = 255) @NFeString final String statusDescription;

    private @XmlElement(name = "chNFe") @NFeAccessKey final String acessKey;

    private @XmlElement(name = "tpEvento") final EventType eventType;

    private @XmlElement(name = "xEvento") @Size(min = 5, max = 60) @NFeString final String eventDescription;

    private @XmlElement(name = "nSeqEvento") @Pattern(regexp = "[1-9][0-9]{0,1}") final String eventSeqNumber;

    private @XmlElement(name = "cOrgaoAutor") final IBGEOrgan authorOrgan;

    private @XmlElement(name = "CNPJDest") @NFeCNPJOptional final String receiverCnpj;

    private @XmlElement(name = "CPFDest") @NFeCPF final String receiverCpf;

    private @XmlElement(name = "emailDest") @Size(min = 1, max = 60) @NFeString final String receiverEmail;

    private @XmlElement(name = "dhRegEvento") @NotNull @NFeDateTimeUTC final String eventRegisterDateTime;

    private @XmlElement(name = "nProt") @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String protocolNumber;

    public static class Builder {

        private String id;

        private TransmissionEnvironment transmissionEnvironment;

        private String applicationVersion;

        private IBGEOrgan ibgeOrgan;

        private String statusCode;

        private String statusDescription;

        private String acessKey;

        private EventType eventType;

        private String eventDescription;

        private String eventSeqNumber;

        private IBGEOrgan authorOrgan;

        private String receiverCnpj;

        private String receiverCpf;

        private String receiverEmail;

        private String eventRegisterDateTime;

        private String protocolNumber;

        /**
         * 
         * @param id
         * @return
         */
        public Builder withId(String id) {
            this.id = id;
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
         * Versão do Aplicativo que recebeu o Evento
         * 
         * @param applicationVersion
         * @return
         */
        public Builder withApplicationVersion(String applicationVersion) {
            this.applicationVersion = applicationVersion;
            return this;
        }

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
         * Código do status do registro do Evento
         * 
         * @param statusCode
         * @return
         */
        public Builder withStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        /**
         * Descrição literal do status do registro do Evento
         * 
         * @param statusDescription
         * @return
         */
        public Builder withStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
            return this;
        }

        /**
         * Chave de Acesso NF-e vinculada
         * 
         * @see NFeAccessKey
         * @param acessKey
         * @return
         */
        public Builder withAcessKey(String acessKey) {
            this.acessKey = acessKey;
            return this;
        }

        /**
         * Tipo do Evento vinculado
         * 
         * @see EventType
         * @param eventType
         * @return
         */
        public Builder withEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

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
         * Seqüencial do evento
         * 
         * @param eventSeqNumber
         * @return
         */
        public Builder withEventSeqNumber(String eventSeqNumber) {
            this.eventSeqNumber = eventSeqNumber;
            return this;
        }

        /**
         * Código do órgão de autor do Evento. Utilizar a Tabela do IBGE extendida, utilizar 90 para identificar o Ambiente Nacional
         * 
         * @param authorOrgan
         * @return
         */
        public Builder withAuthorOrgan(IBGEOrgan authorOrgan) {
            this.authorOrgan = authorOrgan;
            return this;
        }

        /**
         * Identificação do destinatário da NF-e - CNPJ
         * 
         * @param receiverCnpj
         * @return
         */
        public Builder withReceiverCnpj(String receiverCnpj) {
            this.receiverCnpj = receiverCnpj;
            return this;
        }

        /**
         * Identificação do destinatário da NF-e - CPF
         * 
         * @param receiverCpf
         * @return
         */
        public Builder withReceiverCpf(String receiverCpf) {
            this.receiverCpf = receiverCpf;
            return this;
        }

        /**
         * Email do destinatário
         * 
         * @param receiverEmail
         * @return
         */
        public Builder withReceiverEmail(String receiverEmail) {
            this.receiverEmail = receiverEmail;
            return this;
        }

        /**
         * Data e Hora de registro do evento
         * 
         * @param eventRegisterDateTime
         * @return
         */
        public Builder withEventRegisterDateTime(String eventRegisterDateTime) {
            this.eventRegisterDateTime = eventRegisterDateTime;
            return this;
        }

        /**
         * Número do Protocolo do registro do Evento
         * 
         * @param protocolNumber
         * @return
         */
        public Builder withProtocolNumber(String protocolNumber) {
            this.protocolNumber = protocolNumber;
            return this;
        }

        public EventResponseInfo build() {
            final EventResponseInfo entity = new EventResponseInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public EventResponseInfo() {
        this.id = null;
        this.transmissionEnvironment = null;
        this.applicationVersion = null;
        this.ibgeOrgan = null;
        this.statusCode = null;
        this.statusDescription = null;
        this.acessKey = null;
        this.eventType = null;
        this.eventDescription = null;
        this.eventSeqNumber = null;
        this.authorOrgan = null;
        this.receiverCnpj = null;
        this.receiverCpf = null;
        this.receiverEmail = null;
        this.eventRegisterDateTime = null;
        this.protocolNumber = null;
    }

    public EventResponseInfo(Builder builder) {
        this.id = builder.id;
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.ibgeOrgan = builder.ibgeOrgan;
        this.statusCode = builder.statusCode;
        this.statusDescription = builder.statusDescription;
        this.acessKey = builder.acessKey;
        this.eventType = builder.eventType;
        this.eventDescription = builder.eventDescription;
        this.eventSeqNumber = builder.eventSeqNumber;
        this.authorOrgan = builder.authorOrgan;
        this.receiverCnpj = builder.receiverCnpj;
        this.receiverCpf = builder.receiverCpf;
        this.receiverEmail = builder.receiverEmail;
        this.eventRegisterDateTime = builder.eventRegisterDateTime;
        this.protocolNumber = builder.protocolNumber;
    }

    public String getId() {
        return this.id;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public IBGEOrgan getIbgeOrgan() {
        return this.ibgeOrgan;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public String getAcessKey() {
        return this.acessKey;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public String getEventDescription() {
        return this.eventDescription;
    }

    public String getEventSeqNumber() {
        return this.eventSeqNumber;
    }

    public IBGEOrgan getAuthorOrgan() {
        return this.authorOrgan;
    }

    public String getReceiverCnpj() {
        return this.receiverCnpj;
    }

    public String getReceiverCpf() {
        return this.receiverCpf;
    }

    public String getReceiverEmail() {
        return this.receiverEmail;
    }

    public String getEventRegisterDateTime() {
        return this.eventRegisterDateTime;
    }

    public String getProtocolNumber() {
        return this.protocolNumber;
    }

}
