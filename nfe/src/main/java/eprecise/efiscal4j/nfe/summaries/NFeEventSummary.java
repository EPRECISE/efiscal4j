
package eprecise.efiscal4j.nfe.summaries;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryByAccessKey;
import eprecise.efiscal4j.nfe.sharing.EventType;
import eprecise.efiscal4j.nfe.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.types.NFeDeliveryDFeEventProtocolNumber;
import eprecise.efiscal4j.nfe.types.NFeDeliveryDFeEventSequence;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Schema da estrutura XML gerada pelo Ambiente Nacional com o conjunto de informações resumidas de um evento de NF-e
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeEventSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/deliveryDFe/resEvento_v1.01.xsd";

    private final NFeDateTimeUTC.Converter dateTimeConverter = new NFeDateTimeUTC.Converter();

    private final NFeDeliveryDFeEventProtocolNumber.Converter protocolNumberConverter = new NFeDeliveryDFeEventProtocolNumber.Converter();

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlAttribute(name = "versao") final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_01;

    private @XmlElement(name = "cOrgao") @NotNull final IBGEOrgan ibgeOrgan;

    private @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ final String cnpj; // Campo é uma xs:choice com CPF, não mapeado

    private @XmlElement(name = "consChNFe") @NotNull final NFeQueryByAccessKey accessKey;

    private @XmlElement(name = "dhEvento") @NotNull @NFeDateTimeUTC final String eventDateTime;

    private @XmlElement(name = "tpEvento") @NotNull final EventType eventType;

    private @XmlElement(name = "nSeqEvento") @NotNull @NFeDeliveryDFeEventSequence final String eventSequence;

    private @XmlElement(name = "xEvento") @NotNull @NFeString @Min(5) @Max(50) final String eventDescription;

    private @XmlElement(name = "dhRecbto") @NotNull @NFeDateTimeUTC final String authorizationDateTime;

    private @XmlElement(name = "nProt") @NotNull @NFeDeliveryDFeEventProtocolNumber final String eventProtocolNumber;

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

    protected NFeEventSummary(Builder builder) {
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

    public static class Builder {

        private IBGEOrgan ibgeOrgan;

        private String cnpj;

        private NFeQueryByAccessKey accessKey;

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
        public Builder withAccessKey(NFeQueryByAccessKey accessKey) {
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

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public IBGEOrgan getIbgeOrgan() {
        return this.ibgeOrgan;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public NFeQueryByAccessKey getAccessKey() {
        return this.accessKey;
    }

    public ZonedDateTime getEventDateTime() {
        return this.dateTimeConverter.parse(this.eventDateTime);
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
        return this.dateTimeConverter.parse(this.authorizationDateTime);
    }

    public long getEventProtocolNumber() {
        return this.protocolNumberConverter.parse(this.eventProtocolNumber);
    }

}
