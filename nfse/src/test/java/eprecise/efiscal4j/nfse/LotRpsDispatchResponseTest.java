
package eprecise.efiscal4j.nfse;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatchResponse;


public class LotRpsDispatchResponseTest implements Testable {

    // @Test
    // public void validateByBeanValidation() throws Exception {
    // validateByBeanValidationDefault();
    // }
    //
    // @Test
    // public void validateByXSD() throws Exception {
    // validateByXSDDefault();
    // }

    @Test
    public void xmlImportTestBatch() throws Exception {
        final String xmlPath = "/eprecise/efiscal4j/nfse/xml/response";

        final File folder = new File(this.getClass().getResource(xmlPath).toURI());
        final File[] fileList = folder.listFiles();

        if (fileList == null) {
            return;
        }

        for (final File file : fileList) {
            final URL xmlUrl = this.getClass().getResource(xmlPath + "/" + file.getName());
            String responseXml = xmlUrl.toString();
            responseXml = responseXml.substring(responseXml.indexOf("<SOAP-ENV:Body"), responseXml.lastIndexOf("</SOAP-ENV:Body>"));
            System.out.println("Importando " + responseXml + "...");
            xmlImportTest(responseXml);
            System.out.println(xmlUrl.toString() + " - Importação finalizada\n");
        }
    }

    private void xmlImportTest(final String xmlUrl) throws JAXBException, IOException {
        final LotRpsDispatchResponse lotRpsDispatchResponse = new FiscalDocumentDeserializer<>(xmlUrl, LotRpsDispatchResponse.class).deserialize();
        Assert.assertNotNull(lotRpsDispatchResponse);
        try {
            ValidationBuilder.from(lotRpsDispatchResponse).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(LotRpsDispatchResponse.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return null;
    }

}
