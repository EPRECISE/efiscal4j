
package eprecise.efiscal4j.nfe.v400.summaries;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.v310.sharing.EventType;
import eprecise.efiscal4j.nfe.v310.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.v310.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.v310.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.v310.types.NFeDeliveryDFeEventProtocolNumber;
import eprecise.efiscal4j.nfe.v310.types.NFeDeliveryDFeEventSequence;
import eprecise.efiscal4j.nfe.v310.types.NFeString;


/**
 * Schema da estrutura XML gerada pelo Ambiente Nacional com o conjunto de informações resumidas de um evento de NF-e
 */

@XmlRootElement(name = "resEvento")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeEventSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/deliveryDFe/resEvento_v1.01.xsd";

    private @XmlTransient final NFeDateTimeUTC.Converter dateTimeConverter = new NFeDateTimeUTC.Converter();

    private @XmlTransient final NFeDeliveryDFeEventProtocolNumber.Converter protocolNumberConverter = new NFeDeliveryDFeEventProtocolNumber.Converter();

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlAttribute(name = "versao") final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_01;

    private @XmlElement(name = "cOrgao") @NotNull final IBGEOrgan ibgeOrgan;

    private @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ final String cnpj; // Campo é uma xs:choice com CPF, não mapeado

    private @XmlElement(name = "chNFe") @NotNull @NFeAccessKey final String accessKey;

    private @XmlElement(name = "dhEvento") @NotNull @NFeDateTimeUTC final String eventDateTime;

    private @XmlElement(name = "tpEvento") @NotNull final EventType eventType;

    private @XmlElement(name = "nSeqEvento") @NotNull @NFeDeliveryDFeEventSequence final String eventSequence;

    private @XmlElement(name = "xEvento") @NotNull @NFeString @Size(min = 5, max = 60) final String eventDescription;

    private @XmlElement(name = "dhRecbto") @NotNull @NFeDateTimeUTC final String authorizationDateTime;

    private @XmlElement(name = "nProt") @NotNull @NFeDeliveryDFeEventProtocolNumber final String eventProtocolNumber;

    public static class Builder {

        private IBGEOrgan ibgeOrgan;

        private String cnpj;

        private String accessKey;

        private ZonedDateTime eventDateTime;

        private EventType eventType;

        private String eventSequence;

        private String eventDescription;

        private ZonedDateTime authorizationDateTime;

        private long eventProtocolNumber;

        /**
         * Código do órgão de recepção do Evento. Utilizar a Tabela do IBGE extendida, utilizar 91 para identificar o Ambiente Nacional
         */
        public Builder withIbgeOrgan(IBGEOrgan ibgeOrgan) {
            this.ibgeOrgan = ibgeOrgan;
            return this;
        }

        /**
         * CNPJ do Emitente
         */
        public Builder withCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        /**
         * Chave de acesso da NF-e
         */
        public Builder withAccessKey(String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        /**
         * Data e Hora do Evento, formato UTC (AAAA-MM-DDThh:mm:ssTZD, onde TZD = +hh:mm ou -hh:mm)
         */
        public Builder withEventDateTime(ZonedDateTime eventDateTime) {
            this.eventDateTime = eventDateTime;
            return this;
        }

        /**
         * Tipo do Evento
         */
        public Builder withEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        /**
         * Seqüencial do evento para o mesmo tipo de evento. Para maioria dos eventos será 1, nos casos em que possa existir mais de um evento, como é o caso da carta de correção, o autor do evento
         * deve numerar de forma seqüencial
         */
        public Builder withEventSequence(String eventSequence) {
            this.eventSequence = eventSequence;
            return this;
        }

        /**
         * Descrição do Evento
         */
        public Builder withEventDescription(String eventDescription) {
            this.eventDescription = eventDescription;
            return this;
        }

        /**
         * Data e hora de autorização do evento no formato AAAA-MM-DDTHH:MM:SSTZD
         */
        public Builder withAuthorizationDateTime(ZonedDateTime authorizationDateTime) {
            this.authorizationDateTime = authorizationDateTime;
            return this;
        }

        /**
         * Número do Protocolo do evento. 1 posição (1 – Secretaria de Fazenda Estadual 2 – Receita Federal); 2 - códiga da UF - 2 posições ano; 10 seqüencial no ano
         */
        public Builder withEventProtocolNumber(long eventProtocolNumber) {
            this.eventProtocolNumber = eventProtocolNumber;
            return this;
        }

        public NFeEventSummary build() {
            final NFeEventSummary entity = new NFeEventSummary(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeEventSummary() {
        this.ibgeOrgan = null;
        this.cnpj = null;
        this.accessKey = null;
        this.eventDateTime = null;
        this.eventType = null;
        this.eventSequence = null;
        this.eventDescription = null;
        this.authorizationDateTime = null;
        this.eventProtocolNumber = null;
    }

    private NFeEventSummary(Builder builder) {
        this.ibgeOrgan = builder.ibgeOrgan;
        this.cnpj = builder.cnpj;
        this.accessKey = builder.accessKey;
        this.eventDateTime = this.dateTimeConverter.serialize(builder.eventDateTime);
        this.eventType = builder.eventType;
        this.eventSequence = builder.eventSequence;
        this.eventDescription = builder.eventDescription;
        this.authorizationDateTime = this.dateTimeConverter.serialize(builder.authorizationDateTime);
        this.eventProtocolNumber = this.protocolNumberConverter.serialize(builder.eventProtocolNumber);
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public IBGEOrgan getIbgeOrgan() {
        return this.ibgeOrgan;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public ZonedDateTime getEventDateTime() {
        return this.eventDateTime == null ? null : this.dateTimeConverter.parse(this.eventDateTime);
    }

    public boolean isOwnerManifestation() {
        return EventType.CANC_NFE.equals(this.eventType) || EventType.CCE.equals(this.eventType);
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public String getEventSequence() {
        return this.eventSequence;
    }

    public String getEventDescription() {
        return this.eventDescription;
    }

    public ZonedDateTime getAuthorizationDateTime() {
        return this.authorizationDateTime == null ? null : this.dateTimeConverter.parse(this.authorizationDateTime);
    }

    public long getEventProtocolNumber() {
        return this.eventProtocolNumber == null ? 0L : this.protocolNumberConverter.parse(this.eventProtocolNumber);
    }

}
