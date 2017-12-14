
package eprecise.efiscal4j.nfe.deliveryDFe.response;

import java.time.ZonedDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryByAccessKey;
import eprecise.efiscal4j.nfe.summaries.ProcessedNFeSummary;


@XmlRootElement(name = "resNFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDeliveryDFeProcessedNfeSummary extends ProcessedNFeSummary implements NFeDeliveryDfeDocumentContent {

    private static final long serialVersionUID = 1L;

    public NFeDeliveryDFeProcessedNfeSummary() {
        super();
    }

    private NFeDeliveryDFeProcessedNfeSummary(Builder builder) {
        super(builder);
    }

    public static class Builder extends ProcessedNFeSummary.Builder {

        /**
         * Chave de acesso da NF-e
         */
        @Override
        public Builder withAccessKey(NFeQueryByAccessKey accessKey) {
            return (Builder) super.withAccessKey(accessKey);
        }

        /**
         * CNPJ do Emitente
         */
        @Override
        public Builder withCnpj(String cnpj) {
            return (Builder) super.withCnpj(cnpj);
        }

        /**
         * Razão Social ou Nome do emitente
         */
        @Override
        public Builder withName(String name) {
            return (Builder) super.withName(name);
        }

        /**
         * Inscrição Estadual do Emitente
         */
        @Override
        public Builder withStateRegistration(String stateRegistration) {
            return (Builder) super.withStateRegistration(stateRegistration);
        }

        /**
         * Data e Hora de emissão do Documento Fiscal (AAAA-MM-DDThh:mm:ssTZD) ex.: 2012-09-01T13:00:00-03:00
         */
        @Override
        public Builder withEmissionDateTime(ZonedDateTime emissionDateTime) {
            return (Builder) super.withEmissionDateTime(emissionDateTime);
        }

        /**
         * Tipo do Documento Fiscal (0 - entrada; 1 - saída)
         */
        @Override
        public Builder withFiscalDocumentType(FiscalDocumentType fiscalDocumentType) {
            return (Builder) super.withFiscalDocumentType(fiscalDocumentType);
        }

        /**
         * Valor Total da NF-e
         */
        @Override
        public Builder withNfeTotalValue(String nfeTotalValue) {
            return (Builder) super.withNfeTotalValue(nfeTotalValue);
        }

        /**
         * Digest Value da NF-e processada. Utilizado para conferir a integridade da NF-e original
         */
        @Override
        public Builder withDigestValue(String digestValue) {
            return (Builder) super.withDigestValue(digestValue);
        }

        /**
         * Data e hora de autorização da NF-e, no formato AAAA-MM-DDTHH:MM:SSTZD
         */
        @Override
        public Builder withAuthorizationDateTime(ZonedDateTime authorizationDateTime) {
            return (Builder) super.withAuthorizationDateTime(authorizationDateTime);
        }

        /**
         * >Número do Protocolo de Status da NF-e. 1 posição (1 – Secretaria de Fazenda Estadual 2 – Receita Federal); 2 - códiga da UF - 2 posições ano; 10 seqüencial no ano
         */
        @Override
        public Builder withEventProtocolNumber(long eventProtocolNumber) {
            return (Builder) super.withEventProtocolNumber(eventProtocolNumber);
        }

        /**
         * Situação da NF-e: 1=Uso autorizado; 2=Uso denegado; 3=NF-e Cancelada;
         */
        @Override
        public Builder withNfeStatus(NFeDeliveryDfeNFeStatus nfeStatus) {
            return (Builder) super.withNfeStatus(nfeStatus);
        }

        @Override
        public NFeDeliveryDFeProcessedNfeSummary build() {
            final NFeDeliveryDFeProcessedNfeSummary entity = new NFeDeliveryDFeProcessedNfeSummary(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

}
