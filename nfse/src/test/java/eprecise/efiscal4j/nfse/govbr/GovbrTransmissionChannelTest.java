
package eprecise.efiscal4j.nfse.govbr;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.GovbrLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.GovbrLotRpsDispatchAsyncResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;


public class GovbrTransmissionChannelTest implements Testable {

    /**
     * Teste do serviço de NFeAutorizacao
     *
     * @throws Exception
     */
    @Test
    public void validateNfseAuthorization() throws Exception {
        try {
            System.out.println("Testando NFSeAutorizacao...");

            final TransmissionResult transmissionResult = getTestDomain().geTransmissionChannel(NFSeTransmissor.GOVBR).transmitAuthorization(getTestDomain().buildGovbrLotRpsDispatch(), "4118501",
                    true);

            final GovbrLotRpsDispatchAsyncResponse lotRpsDispatchResponse = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(), GovbrLotRpsDispatchAsyncResponse.class).deserialize();

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
        return new TestDomain(GovbrLotRpsDispatchAsync.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return getTestDomain().buildGovbrLotRpsDispatch();
    }

}
