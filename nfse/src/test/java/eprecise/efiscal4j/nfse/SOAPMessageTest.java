
package eprecise.efiscal4j.nfse;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatchResponse;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel.TransmissionResult;


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

            final LotRpsDispatchResponse lotRpsDispatchResponse = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(), LotRpsDispatchResponse.class).deserialize();

            System.out.println("Retorno convertido:");

            final String returnXml = new FiscalDocumentSerializer<>(lotRpsDispatchResponse).serialize();

            System.out.println(returnXml);

            System.out.println("NFSeAutorizacao - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(LotRpsDispatchResponse.XSD);
    }

    @Override
    public LotRpsDispatch getBuiltEntity() throws Exception {
        return getTestDomain().buildLotRpsDispatch();
    }

}
