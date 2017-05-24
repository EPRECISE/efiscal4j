
package eprecise.efiscal4j.nfse.elotech;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.nfse.elotech.domain.TestDomain;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSync;


public class LotRpsDispatchTest implements Testable {

    @Test
    public void validateByBeanValidation() throws Exception {
        validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSD() throws Exception {
        validateByXSDDefault();
    }

    @Test
    public void xmlImportTestBatch() throws Exception {
        final String xmlPath = "/eprecise/efiscal4j/nfse/xml/elotech/request";

        final File folder = new File(this.getClass().getResource(xmlPath).toURI());
        final File[] fileList = folder.listFiles();

        if (fileList == null) {
            return;
        }

        for (final File file : fileList) {
            final URL xmlUrl = this.getClass().getResource(xmlPath + "/" + file.getName());
            System.out.println("Importando " + xmlUrl.toString() + "...");
            xmlImportTest(xmlUrl);
            System.out.println(xmlUrl.toString() + " - Importação finalizada\n");
        }
    }

    private void xmlImportTest(final URL xmlUrl) throws JAXBException, IOException {
        final ElotechLotRpsDispatchSync lotRpsDispatch = new FiscalDocumentDeserializer<>(xmlUrl, ElotechLotRpsDispatchSync.class).deserialize();
        Assert.assertNotNull(lotRpsDispatch);
        try {
            ValidationBuilder.from(lotRpsDispatch).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(ElotechLotRpsDispatchSync.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return getTestDomain().buildLotRpsDispatch();
    }

}