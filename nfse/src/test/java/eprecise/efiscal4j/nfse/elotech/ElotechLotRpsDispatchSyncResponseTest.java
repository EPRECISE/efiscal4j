
package eprecise.efiscal4j.nfse.elotech;

import java.io.File;
import java.io.IOException;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSyncResponse;


public class ElotechLotRpsDispatchSyncResponseTest implements Testable {

    // TODO teste com falha - revisar
    // @Test
    public void xmlImportTestBatch() throws Exception {
        final String xmlPath = "/eprecise/efiscal4j/nfse/xml/elotech/response";

        final File folder = new File(this.getClass().getResource(xmlPath).toURI());
        final File[] fileList = folder.listFiles();

        if (fileList == null) {
            return;
        }

        for (final File file : fileList) {

            String responseXml = Files.toString(file, Charsets.UTF_8);

            responseXml = responseXml.substring(responseXml.indexOf("<EnviarLoteRpsSincronoResposta"), responseXml.lastIndexOf("</SOAP-ENV:Body>"));

            System.out.println("Importando " + file.getName() + "...");
            this.xmlImportTest(responseXml);
            System.out.println(file.getName() + " - Importação finalizada\n");
        }
    }

    private void xmlImportTest(final String xmlUrl) throws JAXBException, IOException {
        final ElotechLotRpsDispatchSyncResponse lotRpsDispatchResponse = new FiscalDocumentDeserializer<>(xmlUrl, ElotechLotRpsDispatchSyncResponse.class).deserialize();
        Assert.assertNotNull(lotRpsDispatchResponse);
        try {
            ValidationBuilder.from(lotRpsDispatchResponse).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(ElotechLotRpsDispatchSyncResponse.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return null;
    }

}
