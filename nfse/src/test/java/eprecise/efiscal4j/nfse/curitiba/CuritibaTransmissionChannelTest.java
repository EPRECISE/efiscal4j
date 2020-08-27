
package eprecise.efiscal4j.nfse.curitiba;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.tc.curitiba.CuritibaNFSeIdentifier;
import eprecise.efiscal4j.nfse.tc.curitiba.cancel.CuritibaCancellationCode;
import eprecise.efiscal4j.nfse.tc.curitiba.cancel.CuritibaNfseCancelRequest;
import eprecise.efiscal4j.nfse.tc.curitiba.cancel.CuritibaNfseCancelRequest.CuritibaNfseCancelRequestInfo;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.CuritibaLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.cancel.CuritibaNfseDispatchCancel;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.cancel.CuritibaNfseDispatchCancelResponse;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.CuritibaLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.CuritibaLotRpsDispatchConsultResponse;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.state.CuritibaLotRpsDispatchConsultState;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.state.CuritibaLotRpsDispatchConsultStateResponse;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.GovbrLotRpsDispatchAsyncResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class CuritibaTransmissionChannelTest implements Testable {
    
    private static Optional<String> protocol = Optional.of("637341365233221595");
    
    private static Optional<String> nfseCancelNumber = Optional.of("1337");

    /**
     *
     * @throws Exception
     */
    //@Test
    public void transmitAuthorization() throws Exception {
        try {
            System.out.println("Testando RecepcionarLoteRps...");

            final CuritibaLotRpsDispatchAsync buildCuritibaLotRpsDispatch = getTestDomain().buildCuritibaLotRpsDispatch();

            final String requestXml = new FiscalDocumentSerializer<>(buildCuritibaLotRpsDispatch).serialize();

            System.out.println("Request XML RecepcionarLoteRps");
            System.out.println(requestXml);

            final TransmissionResult transmissionResult = getTestDomain().geTransmissionChannel(NFSeTransmissor.CURITIBA)
                    .transmitAuthorization(buildCuritibaLotRpsDispatch, "4106902", true);

            final GovbrLotRpsDispatchAsyncResponse lotRpsDispatchResponse = new FiscalDocumentDeserializer<>(
                    transmissionResult.getResponseXml(), GovbrLotRpsDispatchAsyncResponse.class).deserialize();

            System.out.println("Retorno RecepcionarLoteRps:");
            
            protocol = Optional.ofNullable(lotRpsDispatchResponse.getProtocol());

            final String returnXml = new FiscalDocumentSerializer<>(lotRpsDispatchResponse).serialize();

            System.out.println(returnXml);

            System.out.println("RecepcionarLoteRps - teste concluído");

        } catch (final ConstraintViolationException e) {
            e.printStackTrace();
            handleErrors(e);
        }
    }
    
    //@Test
    public void transmitConsultState() {

        try {
            System.out.println("Testando ConsultarSituacaoLoteRps...");

            final CuritibaLotRpsDispatchConsultState buildDispatchConsultState = new CuritibaLotRpsDispatchConsultState.Builder()
                    .withProtocol(protocol.get()).withServiceProviderIdentifier(getTestDomain().buildCuritibaLotRpsDispatch().getLotRps()
                            .getRpsList().stream().findAny().get().getInfo().getServiceProviderIdentifier())
                    .build();

            final String requestXml = new FiscalDocumentSerializer<>(buildDispatchConsultState).serialize();

            System.out.println("Request XML ConsultarSituacaoLoteRps");
            System.out.println(requestXml);

            final TransmissionResult transmissionConsultStateResult = getTestDomain().geTransmissionChannel(NFSeTransmissor.CURITIBA)
                    .consultStateAuthorization(buildDispatchConsultState, "4106902", true);

            final CuritibaLotRpsDispatchConsultStateResponse lotRpsDispatchConsultStateResponse = new FiscalDocumentDeserializer<>(
                    transmissionConsultStateResult.getResponseXml(), CuritibaLotRpsDispatchConsultStateResponse.class).deserialize();

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
    
    //@Test
    public void transmitConsult() {

        try {
            System.out.println("Testando ConsultarLoteRpsEnvio...");

            final CuritibaLotRpsDispatchConsult buildLotRpsDispatchConsult = new CuritibaLotRpsDispatchConsult.Builder()
                    .withProtocol(protocol.get()).withServiceProviderIdentifier(getTestDomain().buildCuritibaLotRpsDispatch().getLotRps()
                            .getRpsList().stream().findAny().get().getInfo().getServiceProviderIdentifier())
                    .build();

            final String requestXml = new FiscalDocumentSerializer<>(buildLotRpsDispatchConsult).serialize();

            System.out.println("Request XML ConsultarLoteRpsEnvio");
            System.out.println(requestXml);

            final TransmissionResult transmissionConsultResult = getTestDomain().geTransmissionChannel(NFSeTransmissor.CURITIBA)
                    .consultAuthorization(buildLotRpsDispatchConsult, "4106902", true);

            final CuritibaLotRpsDispatchConsultResponse lotRpsDispatchConsultResponse = new FiscalDocumentDeserializer<>(
                    transmissionConsultResult.getResponseXml(), CuritibaLotRpsDispatchConsultResponse.class).deserialize();
            
            lotRpsDispatchConsultResponse.getCompNFSe().ifPresent(nfse ->{
                this.nfseCancelNumber = Optional.of(nfse.getProcessedNfse().getNumber());
            });

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
    
    @Test
    public void transmitCancel() {

        try {
            System.out.println("Testando CancelarNfse...");

            final CuritibaNfseDispatchCancel buildNfseDispatchCancel = new CuritibaNfseDispatchCancel.Builder()
                    .withCancelRequest(new CuritibaNfseCancelRequest.Builder()
                            .withInfo(CuritibaNfseCancelRequestInfo.builder()
                                    .identifier(CuritibaNFSeIdentifier.builder()
                                            .number(nfseCancelNumber.get())
                                            .cnpj(getTestDomain().buildCuritibaLotRpsDispatch().getLotRps().getCnpj())
                                            .municipalRegistration(getTestDomain().buildCuritibaLotRpsDispatch().getLotRps().getMunicipalRegistration())
                                            .cityCode("4106902")
                                            .build())
                                    .cancellationCode(CuritibaCancellationCode.CODE3)
                                    .build())
                            .build(new DefaultSigner(getTestDomain().getCertificate())))
                    .build();

            final String requestXml = new FiscalDocumentSerializer<>(buildNfseDispatchCancel).serialize();

            System.out.println("Request XML CancelarNfse");
            System.out.println(requestXml);

            final TransmissionResult transmissionCanceltResult = getTestDomain().geTransmissionChannel(NFSeTransmissor.CURITIBA)
                    .transmitCancellation(buildNfseDispatchCancel, "4106902", true);

            final CuritibaNfseDispatchCancelResponse nfseCancelResponse = new FiscalDocumentDeserializer<>(
                    transmissionCanceltResult.getResponseXml(), CuritibaNfseDispatchCancelResponse.class).deserialize();

            System.out.println("Retorno CancelarNfse:");

            final String returnCanceltXml = new FiscalDocumentSerializer<>(nfseCancelResponse).serialize();

            System.out.println(returnCanceltXml);

            System.out.println("CancelarNfse - teste concluído");
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(CuritibaLotRpsDispatchAsync.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return getTestDomain().buildCuritibaLotRpsDispatch();
    }

}
