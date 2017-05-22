
package eprecise.efiscal4j.nfse;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.LotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.LotRpsDispatchSyncResponse;


public class SOAPMessageTest implements Testable {

    /**
     * Teste do serviço de NFeAutorizacao
     *
     * @throws Exception
     */
    @Test
    public void validateNfseAuthorization() throws Exception {
        try {
            System.out.println("Testando NFSeAutorizacao...");

            final TransmissionResult transmissionResult = getTestDomain().getTransmissionChannel().transmitAuthorization(getTestDomain().buildLotRpsDispatch());

            final LotRpsDispatchSyncResponse lotRpsDispatchResponse = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(), LotRpsDispatchSyncResponse.class).deserialize();

            System.out.println("Retorno convertido:");

            final String returnXml = new FiscalDocumentSerializer<>(lotRpsDispatchResponse).serialize();

            System.out.println(returnXml);

            System.out.println("NFSeAutorizacao - teste concluído");

        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(LotRpsDispatchSyncResponse.XSD);
    }

    @Override
    public LotRpsDispatchSync getBuiltEntity() throws Exception {
        return getTestDomain().buildLotRpsDispatch();
    }

}
