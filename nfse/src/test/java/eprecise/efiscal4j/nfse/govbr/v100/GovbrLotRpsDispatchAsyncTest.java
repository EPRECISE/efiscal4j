
package eprecise.efiscal4j.nfse.govbr.v100;

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
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.GovbrLotRpsDispatchAsync;


public class GovbrLotRpsDispatchAsyncTest implements Testable {

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    // falha em teste - revistar
    // @Test
    public void validateByXSD() throws Exception {
        this.validateByXSDDefault();
    }

    // TODO erro ao executar teste - NPE
    // @Test
    public void xmlImportTestBatch() throws Exception {
        final String xmlPath = "/eprecise/efiscal4j/nfse/xml/govbr/request";

        final File folder = new File(this.getClass().getResource(xmlPath).toURI());
        final File[] fileList = folder.listFiles();

        if (fileList == null) {
            return;
        }

        for (final File file : fileList) {
            final URL xmlUrl = this.getClass().getResource(xmlPath + "/" + file.getName());
            System.out.println("Importando " + xmlUrl.toString() + "...");
            this.xmlImportTest(xmlUrl);
            System.out.println(xmlUrl.toString() + " - Importação finalizada\n");
        }
    }

    private void xmlImportTest(final URL xmlUrl) throws JAXBException, IOException {
        final GovbrLotRpsDispatchAsync lotRpsDispatch = new FiscalDocumentDeserializer<>(xmlUrl, GovbrLotRpsDispatchAsync.class)
                .deserialize();
        Assert.assertNotNull(lotRpsDispatch);
        try {
            ValidationBuilder.from(lotRpsDispatch).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(GovbrLotRpsDispatchAsync.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain().buildGovbrV203LotRpsDispatch();
    }

}
