
package eprecise.efiscal4j.nfse.govbr.v203;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.domain.transmission.TransmissionResult;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSyncResponse;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel.GovbrNFSeDispatchCancel;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel.GovbrNFSeDispatchCancelResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;


public class GovbrTest implements Testable {

    private final String cityCode = "4118501";

    private final TransmissionChannel transmissionChannel = this.getTestDomain().geTransmissionChannel(NFSeTransmissor.GOVBR_V203);

//    @Test
    public void serializeTest() throws Exception {
        System.out.println("Teste de Serialização");
        final String serializedEntity = new FiscalDocumentSerializer<>(this.getBuiltEntity()).serialize();
        assertNotNull(serializedEntity);
        System.out.println(String.format("Teste de Serialização finalizado! Entidade Serializada -> %s", serializedEntity));
    }

//    @Test
    public void deserializeBatchTest() throws Exception, IOException {
        System.out.println("Teste de Deserialização em lote");

        final String xmlPath = "/eprecise/efiscal4j/nfse/xml/govbr/v203/request";
        final File folder = new File(this.getClass().getResource(xmlPath).toURI());
        final File[] fileList = folder.listFiles();
        if (fileList == null) {
            return;
        }

        for (final File file : fileList) {
            final URL xmlUrl = this.getClass().getResource(xmlPath + "/" + file.getName());
            System.out.println("Importando " + xmlUrl.toString() + "...");
            this.xmlDeserializer(xmlUrl);
            System.out.println(xmlUrl.toString() + " - Importação finalizada\n");
        }
        System.out.println("Teste de Deserialização concluido!");
    }

    private void xmlDeserializer(final URL xmlUrl) throws JAXBException, IOException {
        final GovbrLotRpsDispatchSync lotRpsDispatch = new FiscalDocumentDeserializer<>(xmlUrl, GovbrLotRpsDispatchSync.class)
                .deserialize();
        Assert.assertNotNull(lotRpsDispatch);
        try {
            ValidationBuilder.from(lotRpsDispatch).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

//    @Test
    public void transmitLotRpsSyncDispatch() throws Exception {
        try {
            System.out.println("Testando EnviarLoteRpsSincrono");

            final GovbrLotRpsDispatchSync dispatchEntity = this.getBuiltEntity();

            final String requestXml = new FiscalDocumentSerializer<>(dispatchEntity).serialize();

            System.out.println("Request XML EnviarLoteRpsSincrono");
            System.out.println(requestXml);

            final TransmissionResult transmissionResult = this.transmissionChannel.transmitAuthorization(dispatchEntity, cityCode, true);

            final GovbrLotRpsDispatchSyncResponse dispatchResponse = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(),
                    GovbrLotRpsDispatchSyncResponse.class).deserialize();

            System.out.println("Retorno EnviarLoteRpsSincrono:");

            final String returnXml = new FiscalDocumentSerializer<>(dispatchResponse).serialize();

            System.out.println(returnXml);

            System.out.println("RecepcionarLoteRps - teste concluído");
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }

    }

    @Test
    public void transmitNFSeDispatchCancel() throws Exception {
        try {
            System.out.println("Testando CancelarNfseEnvio");
            final GovbrNFSeDispatchCancel dispatchCancelEntity = this.getBuiltCancelEntity();

            final String requestXml = new FiscalDocumentSerializer<>(dispatchCancelEntity).serialize();

            System.out.println("Request XML CancelarNfseEnvio");
            System.out.println(requestXml);

            final TransmissionResult transmissionResult = this.transmissionChannel.transmitCancellation(dispatchCancelEntity, this.cityCode,
                    true);

            final GovbrNFSeDispatchCancelResponse dispatchCancelResponse = new FiscalDocumentDeserializer<>(
                    transmissionResult.getResponseXml(), GovbrNFSeDispatchCancelResponse.class).deserialize();

            System.out.println("Retorno CancelarNfseEnvio:");

            final String returnXml = new FiscalDocumentSerializer<>(dispatchCancelResponse).serialize();

            System.out.println(returnXml);
            System.out.println("CancelarNfseEnvio - teste concluído");
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }

    }
    
//    @Test
    public void validateByXsd() {
        try {
            validateByXSDDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(GovbrLotRpsDispatchSync.XSD);
    }

    public GovbrNFSeDispatchCancel getBuiltCancelEntity() throws Exception {
        return this.getTestDomain().buildGovbrV203CancelDispatch();
    }

    @Override
    public GovbrLotRpsDispatchSync getBuiltEntity() throws Exception {
        return this.getTestDomain().buildGovbrV203LotRpsDispatch();
    }

}
