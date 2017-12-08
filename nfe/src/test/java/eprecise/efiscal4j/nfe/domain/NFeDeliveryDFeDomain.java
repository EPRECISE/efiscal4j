
package eprecise.efiscal4j.nfe.domain;

import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequestType;
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
}
