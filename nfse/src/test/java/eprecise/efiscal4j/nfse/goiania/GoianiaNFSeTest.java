
package eprecise.efiscal4j.nfse.goiania;

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
import eprecise.efiscal4j.nfse.tc.goiania.services.dispatch.GoianiaLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.goiania.services.dispatch.GoianiaLotRpsDispatchSyncResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;


public class GoianiaNFSeTest implements Testable {

    private final String cityCode = "5208707";

    private final TransmissionChannel transmissionChannel = this.getTestDomain().geTransmissionChannel(NFSeTransmissor.GOIANIA);

    //@Test
    public void serializeTest() throws Exception {
        System.out.println("Teste de Serialização");
        final String serializedEntity = new FiscalDocumentSerializer<>(this.getBuiltEntity()).serialize();
        assertNotNull(serializedEntity);
        System.out.println(String.format("Teste de Serialização finalizado! Entidade Serializada -> %s", serializedEntity));
    }

//    @Test
    public void deserializeBatchTest() throws Exception, IOException {
        System.out.println("Teste de Deserialização em lote");

        final String xmlPath = "/eprecise/efiscal4j/nfse/xml/goiania/v203/request";
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
        final GoianiaLotRpsDispatchSync lotRpsDispatch = new FiscalDocumentDeserializer<>(xmlUrl, GoianiaLotRpsDispatchSync.class)
                .deserialize();
        Assert.assertNotNull(lotRpsDispatch);
        try {
            ValidationBuilder.from(lotRpsDispatch).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

    @Test
    public void transmitLotRpsSyncDispatch() throws Exception {
        try {
            System.out.println("Testando EnviarLoteRpsSincrono");

            final GoianiaLotRpsDispatchSync dispatchEntity = this.getBuiltEntity();

            final String requestXml = new FiscalDocumentSerializer<>(dispatchEntity).serialize();

            System.out.println("Request XML EnviarLoteRpsSincrono");
            System.out.println(requestXml);

            final TransmissionResult transmissionResult = this.transmissionChannel.transmitAuthorization(dispatchEntity, cityCode, true);

            final GoianiaLotRpsDispatchSyncResponse dispatchResponse = new FiscalDocumentDeserializer<>(transmissionResult.getResponseXml(),
                    GoianiaLotRpsDispatchSyncResponse.class).deserialize();

            System.out.println("Retorno EnviarLoteRpsSincrono:");

            final String returnXml = new FiscalDocumentSerializer<>(dispatchResponse).serialize();

            System.out.println(returnXml);

            System.out.println("RecepcionarLoteRps - teste concluído");
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
        return new TestDomain(GoianiaLotRpsDispatchSync.XSD);
    }

    @Override
    public GoianiaLotRpsDispatchSync getBuiltEntity() throws Exception {
        return this.getTestDomain().buildGoianiaLotRpsDispatch();
    }

}
