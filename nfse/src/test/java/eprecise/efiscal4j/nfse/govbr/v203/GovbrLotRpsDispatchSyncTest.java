
package eprecise.efiscal4j.nfse.govbr.v203;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSyncResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;


public class GovbrLotRpsDispatchSyncTest implements Testable {

    private String protocol = "767778";

    private final String cityCode = "4118501";

    private final boolean homologation = true;

    @Test
    public void transmitDispatch() throws Exception {
        try {
            System.out.println("Testando EnviarLoteRpsSincrono");

            final GovbrLotRpsDispatchSync dispatchEntity = this.getBuiltEntity();

            final String requestXml = new FiscalDocumentSerializer<>(dispatchEntity).serialize();

            System.out.println("Request XML EnviarLoteRpsSincrono");
            System.out.println(requestXml);

            final TransmissionResult transmissionResult = this.getTestDomain().geTransmissionChannel(NFSeTransmissor.GOVBR_V203)
                    .transmitAuthorization(dispatchEntity, cityCode, homologation);

            final GovbrLotRpsDispatchSyncResponse dispatchResponse = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(),
                    GovbrLotRpsDispatchSyncResponse.class).deserialize();

            System.out.println("Retorno EnviarLoteRpsSincrono:");

            protocol = dispatchResponse.getProtocolNumber();

            final String returnXml = new FiscalDocumentSerializer<>(dispatchResponse).serialize();

            System.out.println(returnXml);

            System.out.println("RecepcionarLoteRps - teste concluído");
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }

    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(GovbrLotRpsDispatchSync.XSD);
    }

    @Override
    public GovbrLotRpsDispatchSync getBuiltEntity() throws Exception {
        return this.getTestDomain().buildGovbrV203LotRpsDispatch();
    }

}
