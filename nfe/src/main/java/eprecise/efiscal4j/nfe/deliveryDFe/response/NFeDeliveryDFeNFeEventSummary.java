
package eprecise.efiscal4j.nfe.deliveryDFe.response;

import java.time.ZonedDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryByAccessKey;
import eprecise.efiscal4j.nfe.sharing.EventType;
import eprecise.efiscal4j.nfe.summaries.NFeEventSummary;


@XmlRootElement(name = "resEvento")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeNFeEventSummary extends NFeEventSummary implements NFeDeliveryDfeDocumentContent {

    private static final long serialVersionUID = 1L;

    public NFeDeliveryDFeNFeEventSummary() {
        super();
    }

    private NFeDeliveryDFeNFeEventSummary(Builder builder) {
        super(builder);
    }

    public static class Builder extends NFeEventSummary.Builder {

        /**
         * Código do órgão de recepção do Evento. Utilizar a Tabela do IBGE extendida, utilizar 91 para identificar o Ambiente Nacional
         */
        @Override
        public Builder withIbgeOrgan(IBGEOrgan ibgeOrgan) {
            return (Builder) super.withIbgeOrgan(ibgeOrgan);
        }

        /**
         * CNPJ do Emitente
         */
        @Override
        public Builder withCnpj(String cnpj) {
            return (Builder) super.withCnpj(cnpj);
        }

        /**
         * Chave de acesso da NF-e
         */
        @Override
        public Builder withAccessKey(NFeQueryByAccessKey accessKey) {
            return (Builder) super.withAccessKey(accessKey);
        }

        /**
         * Data e Hora do Evento, formato UTC (AAAA-MM-DDThh:mm:ssTZD, onde TZD = +hh:mm ou -hh:mm)
         */
        @Override
        public Builder withEventDateTime(ZonedDateTime eventDateTime) {
            return (Builder) super.withEventDateTime(eventDateTime);
        }

        /**
         * Tipo do Evento
         */
        @Override
        public Builder withEventType(EventType eventType) {
            return (Builder) super.withEventType(eventType);
        }

        /**
         * Seqüencial do evento para o mesmo tipo de evento. Para maioria dos eventos será 1, nos casos em que possa existir mais de um evento, como é o caso da carta de correção, o autor do evento
         * deve numerar de forma seqüencial
         */
        @Override
        public Builder withEventSequence(String eventSequence) {
            return (Builder) super.withEventSequence(eventSequence);
        }

        /**
         * Descrição do Evento
         */
        @Override
        public Builder withEventDescription(String eventDescription) {
            return (Builder) super.withEventDescription(eventDescription);
        }

        /**
         * Data e hora de autorização do evento no formato AAAA-MM-DDTHH:MM:SSTZD
         */
        @Override
        public Builder withAuthorizationDateTime(ZonedDateTime authorizationDateTime) {
            return (Builder) super.withAuthorizationDateTime(authorizationDateTime);
        }

        /**
         * Número do Protocolo do evento. 1 posição (1 – Secretaria de Fazenda Estadual 2 – Receita Federal); 2 - códiga da UF - 2 posições ano; 10 seqüencial no ano
         */
        @Override
        public Builder withEventProtocolNumber(long eventProtocolNumber) {
            return (Builder) super.withEventProtocolNumber(eventProtocolNumber);
        }

        @Override
        public NFeDeliveryDFeNFeEventSummary build() {
            final NFeDeliveryDFeNFeEventSummary entity = new NFeDeliveryDFeNFeEventSummary(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

}
