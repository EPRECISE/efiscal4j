
package eprecise.efiscal4j.nfe.domain;

import java.time.ZonedDateTime;

import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequestType;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeResponse;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDfeDocument;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryNSU;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryByAccessKey;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeQueryNSU;


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
        final NFeDeliveryDfeDocument doc = new NFeDeliveryDfeDocument.Builder().withContent("dmFtb3MgbMOhIG5hIGNhc2EgZGEgbWFyaWE=").withNsu(0L).withSchema("").build();

        return new NFeDeliveryDFeResponse.Builder().withEnviroment(TransmissionEnvironment.PRODUCAO).withAppVersion("1").withStatusCode(1).withStatusDescription("Cod 1")
                .withResponse(ZonedDateTime.now()).withLastNsu(1).withMaxNsu(10).withDocument(doc).build();
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

    public NFeDeliveryDfeDocument buildDfeDocumentWithInvalidBase64() {
        return new NFeDeliveryDfeDocument.Builder().withContent("dmFtb3MgbMOhIG5%%hIGNhc2EgZGEgbWFyaWE=").withNsu(0L).withSchema("").build();
    }
}
