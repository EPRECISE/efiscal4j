
package eprecise.efiscal4j.nfse.govbr.v100;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.GovbrLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.GovbrLotRpsDispatchAsyncResponse;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.GovbrLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.GovbrLotRpsDispatchConsultResponse;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.state.GovbrLotRpsDispatchConsultState;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.state.GovbrLotRpsDispatchConsultStateResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;


public class GovbrTransmissionChannelTest implements Testable {

    private static Optional<String> protocol = Optional.of("767778");

    /**
     *
     * @throws Exception
     */
    @Test
    public void transmitAuthorization() throws Exception {
        try {
            System.out.println("Testando RecepcionarLoteRps...");

            final GovbrLotRpsDispatchAsync buildGovbrLotRpsDispatch = getTestDomain().buildGovbrV100LotRpsDispatch();

            final String requestXml = new FiscalDocumentSerializer<>(buildGovbrLotRpsDispatch).serialize();

            System.out.println("Request XML RecepcionarLoteRps");
            System.out.println(requestXml);

            final TransmissionResult transmissionResult = getTestDomain().geTransmissionChannel(NFSeTransmissor.GOVBR_V100)
                    .transmitAuthorization(buildGovbrLotRpsDispatch, "4118501", true);

            final GovbrLotRpsDispatchAsyncResponse lotRpsDispatchResponse = new FiscalDocumentDeserializer<>(
                    transmissionResult.getResponseXml(), GovbrLotRpsDispatchAsyncResponse.class).deserialize();

            System.out.println("Retorno RecepcionarLoteRps:");

            protocol = Optional.ofNullable(lotRpsDispatchResponse.getProtocol());

            final String returnXml = new FiscalDocumentSerializer<>(lotRpsDispatchResponse).serialize();

            System.out.println(returnXml);

            System.out.println("RecepcionarLoteRps - teste concluído");

        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

    // @Test
    public void transmitConsultState() {

        try {
            System.out.println("Testando ConsultarSituacaoLoteRps...");

            final GovbrLotRpsDispatchConsultState buildGovbrDispatchConsultState = new GovbrLotRpsDispatchConsultState.Builder()
                    .withProtocol(protocol.get()).withServiceProviderIdentifier(getTestDomain().buildGovbrV100LotRpsDispatch().getLotRps()
                            .getRpsList().stream().findAny().get().getInfo().getServiceProviderIdentifier())
                    .build();

            final String requestXml = new FiscalDocumentSerializer<>(buildGovbrDispatchConsultState).serialize();

            System.out.println("Request XML ConsultarSituacaoLoteRps");
            System.out.println(requestXml);

            final TransmissionResult transmissionConsultStateResult = getTestDomain().geTransmissionChannel(NFSeTransmissor.GOVBR_V100)
                    .consultStateAuthorization(buildGovbrDispatchConsultState, "4118501", true);

            final GovbrLotRpsDispatchConsultStateResponse lotRpsDispatchConsultStateResponse = new FiscalDocumentDeserializer<>(
                    transmissionConsultStateResult.getResponseXml(), GovbrLotRpsDispatchConsultStateResponse.class).deserialize();

            System.out.println("Retorno ConsultarSituacaoLoteRps: " + lotRpsDispatchConsultStateResponse.getState().getDescription());

            final String returnConsultXml = new FiscalDocumentSerializer<>(lotRpsDispatchConsultStateResponse).serialize();

            System.out.println(returnConsultXml);

            System.out.println("ConsultarSituacaoLoteRps - teste concluído");
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

    // @Test
    public void transmitConsult() {

        try {
            System.out.println("Testando ConsultarLoteRpsEnvio...");

            final GovbrLotRpsDispatchConsult buildGovbrLotRpsDispatchConsult = new GovbrLotRpsDispatchConsult.Builder()
                    .withProtocol(protocol.get()).withServiceProviderIdentifier(getTestDomain().buildGovbrV100LotRpsDispatch().getLotRps()
                            .getRpsList().stream().findAny().get().getInfo().getServiceProviderIdentifier())
                    .build();

            final String requestXml = new FiscalDocumentSerializer<>(buildGovbrLotRpsDispatchConsult).serialize();

            System.out.println("Request XML ConsultarLoteRpsEnvio");
            System.out.println(requestXml);

            final TransmissionResult transmissionConsultResult = getTestDomain().geTransmissionChannel(NFSeTransmissor.GOVBR_V100)
                    .consultAuthorization(buildGovbrLotRpsDispatchConsult, "4118501", true);

            final GovbrLotRpsDispatchConsultResponse lotRpsDispatchConsultResponse = new FiscalDocumentDeserializer<>(
                    transmissionConsultResult.getResponseXml(), GovbrLotRpsDispatchConsultResponse.class).deserialize();

            System.out.println("Retorno ConsultarLoteRpsEnvio:");

            final String returnConsultXml = new FiscalDocumentSerializer<>(lotRpsDispatchConsultResponse).serialize();

            System.out.println(returnConsultXml);

            System.out.println("ConsultarLoteRpsEnvio - teste concluído");
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(GovbrLotRpsDispatchAsync.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return getTestDomain().buildGovbrV100LotRpsDispatch();
    }

}
