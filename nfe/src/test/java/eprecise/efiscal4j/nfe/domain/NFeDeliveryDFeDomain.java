
package eprecise.efiscal4j.nfe.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.address.IBGEOrgan;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequestType;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryNSU;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryByAccessKey;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryNSU;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDFeResponse;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDFeSchemas;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDfeDocument;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDfeNFeStatus;
import eprecise.efiscal4j.nfe.sharing.EventProtocol;
import eprecise.efiscal4j.nfe.sharing.EventType;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.summaries.NFeEventSummary;
import eprecise.efiscal4j.nfe.summaries.ProcessedNFeSummary;


public class NFeDeliveryDFeDomain {

    public NFeDeliveryDFeRequest buildRequest(NFeDeliveryDFeRequestType type) {
        return new NFeDeliveryDFeRequest.Builder().withEnviroment(TransmissionEnvironment.HOMOLOGACAO).withCnpj("24804397000132").withType(type).build();
    }

    public NFeDeliveryDFeRequest buildQueryAccesKeyRequest() {
        return this.buildRequest(new NFeQueryByAccessKey.Builder().withAccessKey(TestDomain.randomFixedSizeNumber(44)).build());
    }

    public NFeDeliveryDFeRequest buildQueryByNsuRequest() {
        return this.buildRequest(new NFeQueryNSU.Builder().withNsu(TestDomain.randomFixedSizeNumber(15)).build());
    }

    public NFeDeliveryDFeRequest buildDeliveryNsuRequest() {
        return this.buildRequest(new NFeDeliveryNSU.Builder().withLastNsu(TestDomain.randomFixedSizeNumber(15)).build());
    }

    public NFeDeliveryDFeResponse buildResponse() throws Exception {
        //@formatter:off
        return new NFeDeliveryDFeResponse.Builder()
                .withEnviroment(TransmissionEnvironment.HOMOLOGACAO)
                .withAppVersion("1")
                .withStatusCode(1)
                .withStatusDescription("Cod 1")
                .withResponse(ZonedDateTime.now())
                .withLastNsu(1)
                .withMaxNsu(10)
                .withDocument(this.getProcessedNFeSummary())
//                .withDocument(this.getProcessedNFeDocument())
                .withDocument(this.getNFeEventSummaryDocument())
//                .withDocument(this.getEventProtocolDocument())
                .build();
        //@formatter:on
    }

    private NFeDeliveryDfeDocument getProcessedNFeSummary() {
        //@formatter:off
        final NFeQueryByAccessKey accessKey = new NFeQueryByAccessKey.Builder().withAccessKey("01234567890123456789012345678901234567891234").build();

        final ProcessedNFeSummary nfeSummary = new ProcessedNFeSummary.Builder()
                .withAccessKey(accessKey)
                .withAuthorizationDateTime(LocalDateTime.of(2017, 2, 1, 1, 0)
                .atZone(ZoneId.systemDefault()))
                .withCnpj("92387160000153")
                .withDigestValue("dmFpIGJhc2UgNjQ=")
                .withEmissionDateTime(LocalDateTime.of(2017, 1, 1, 1, 0).atZone(ZoneId.systemDefault()))
                .withEventProtocolNumber(1000)
                .withFiscalDocumentType(FiscalDocumentType.SAIDA)
                .withName("Meu nome")
                .withNfeStatus(NFeDeliveryDfeNFeStatus.AUTHORIZED)
                .withNfeTotalValue("10.20")
                .withStateRegistration("ISENTO")
                .build();

        return new NFeDeliveryDfeDocument.Builder().withContent(nfeSummary).withNsu(2L).withSchema(NFeDeliveryDFeSchemas.RES_NFE).build();
        //@formatter:on
    }

    private NFeDeliveryDfeDocument getProcessedNFeDocument() throws Exception {
        final ProcessedNFe processedNfe = new TestDomain().buildProcessedNFe();

        return new NFeDeliveryDfeDocument.Builder().withContent(processedNfe).withNsu(4L).withSchema(NFeDeliveryDFeSchemas.PROC_NFE).build();
    }

    private NFeDeliveryDfeDocument getNFeEventSummaryDocument() {
        //@formatter:off
        final NFeQueryByAccessKey accessKey = new NFeQueryByAccessKey.Builder().withAccessKey("01234567890123456789012345678901234567895678").build();

        final NFeEventSummary nfeEventSummary = new NFeEventSummary.Builder()
                .withAccessKey(accessKey)
                .withAuthorizationDateTime(LocalDateTime.of(2017, 2, 1, 1, 0).atZone(ZoneId.systemDefault()))
                .withCnpj("92387160000153")
                .withEventProtocolNumber(1001)
                .withEventDateTime(LocalDateTime.of(2017, 1, 1, 1, 0).atZone(ZoneId.systemDefault()))
                .withEventDescription("Minha descrição de evento")
                .withEventSequence("12")
                .withEventType(EventType.CONFIRMACAO_OPERACAO)
                .withIbgeOrgan(IBGEOrgan.PR).build();

        return new NFeDeliveryDfeDocument.Builder().withContent(nfeEventSummary).withNsu(4L).withSchema(NFeDeliveryDFeSchemas.RES_EVENT).build();
        //@formatter:on
    }

    private NFeDeliveryDfeDocument getEventProtocolDocument() {
        final EventProtocol eventProtocol = new EventProtocol.Builder().withEvent(null).withEventResponse(null).build();

        return new NFeDeliveryDfeDocument.Builder().withContent(eventProtocol).withNsu(5L).withSchema(NFeDeliveryDFeSchemas.PROC_EVENTO_NFE).build();
    }

    public NFeDeliveryDFeResponse buildQueryAccesKeyResponse() throws Exception {
        return this.buildResponse();
    }

    public NFeDeliveryDFeResponse buildQueryByNsuResponse() throws Exception {
        return this.buildResponse();
    }

    public NFeDeliveryDFeResponse buildDeliveryNsuResponse() throws Exception {
        return this.buildResponse();
    }
}
