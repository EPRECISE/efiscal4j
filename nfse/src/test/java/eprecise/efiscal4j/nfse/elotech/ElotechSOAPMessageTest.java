
package eprecise.efiscal4j.nfse.elotech;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSyncResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;


public class ElotechSOAPMessageTest implements Testable {

    /**
     * Teste do serviço de NFeAutorizacao
     *
     * @throws Exception
     */

    // TODO teste de transmissão - revisar
    // @Test
    public void validateNfseAuthorization() throws Exception {
        try {
            System.out.println("Testando NFSeAutorizacao...");

            final TransmissionResult transmissionResult = this.getTestDomain().geTransmissionChannel(NFSeTransmissor.ELOTECH).transmitAuthorization(this.getTestDomain().buildElotechLotRpsDispatch(), "4119905",
                    true);

            final ElotechLotRpsDispatchSyncResponse lotRpsDispatchResponse = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(), ElotechLotRpsDispatchSyncResponse.class)
                    .deserialize();

            System.out.println("Retorno convertido:");

            final String returnXml = new FiscalDocumentSerializer<>(lotRpsDispatchResponse).serialize();

            System.out.println(returnXml);

            System.out.println("NFSeAutorizacao - teste concluído");

        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(ElotechLotRpsDispatchSyncResponse.XSD);
    }

    @Override
    public ElotechLotRpsDispatchSync getBuiltEntity() throws Exception {
        return this.getTestDomain().buildElotechLotRpsDispatch();
    }

}
