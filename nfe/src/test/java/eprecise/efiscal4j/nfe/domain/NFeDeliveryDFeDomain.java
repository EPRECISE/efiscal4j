
package eprecise.efiscal4j.nfe.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import eprecise.efiscal4j.nfe.FiscalDocumentType;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequestType;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryNSU;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryByAccessKey;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryNSU;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDFeProcessedNfeSummary;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDFeResponse;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDfeDocument;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDfeNFeStatus;
import eprecise.efiscal4j.nfe.deliveryDFe.response.NFeDeliveryDfeSchema;


public class NFeDeliveryDFeDomain {

    public NFeDeliveryDFeRequest buildRequest(NFeDeliveryDFeRequestType type) {
        return new NFeDeliveryDFeRequest.Builder().withEnviroment(TransmissionEnvironment.PRODUCAO).withCnpj("24804397000132").withType(type).build();
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

    public NFeDeliveryDFeResponse buildResponse() {

        //@formatter:off
        final NFeDeliveryDFeProcessedNfeSummary nfeSummary = new NFeDeliveryDFeProcessedNfeSummary.Builder()
                .withAccessKey(null)
                .withAuthorizationDateTime(LocalDateTime.of(2017, 2, 1, 1, 0).atZone(ZoneId.systemDefault()))
                .withCnpj("")
                .withDigestValue("")
                .withEmissionDateTime(LocalDateTime.of(2017, 1, 1, 1, 0).atZone(ZoneId.systemDefault()))
                .withEventProtocolNumber(1000)
                .withFiscalDocumentType(FiscalDocumentType.SAIDA)
                .withName("Meu nome")
                .withNfeStatus(NFeDeliveryDfeNFeStatus.AUTHORIZED)
                .withNfeTotalValue("")
                .withStateRegistration("").build();

        final NFeDeliveryDfeDocument doc1 = new NFeDeliveryDfeDocument.Builder()
                .withContent(nfeSummary)
                .withNsu(1L)
                .withSchema(NFeDeliveryDfeSchema.RES_NFE).build();
        //@formatter:on

        return new NFeDeliveryDFeResponse.Builder().withEnviroment(TransmissionEnvironment.PRODUCAO).withAppVersion("1").withStatusCode(1).withStatusDescription("Cod 1")
                .withResponse(ZonedDateTime.now()).withLastNsu(1).withMaxNsu(10).withDocument(doc1).build();
    }

    public NFeDeliveryDFeResponse buildQueryAccesKeyResponse() {
        return this.buildResponse();
    }

    public NFeDeliveryDFeResponse buildQueryByNsuResponse() {
        return this.buildResponse();
    }

    public NFeDeliveryDFeResponse buildDeliveryNsuResponse() {
        return this.buildResponse();
    }
}
