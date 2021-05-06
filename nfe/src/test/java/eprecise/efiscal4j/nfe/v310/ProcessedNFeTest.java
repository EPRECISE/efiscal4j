
package eprecise.efiscal4j.nfe.v310;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.nfe.v310.domain.TestDomain;
import eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFe;


public class ProcessedNFeTest implements Testable {

    private final TestDomain testDomain = new TestDomain(ProcessedNFe.XSD);

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSD() throws Exception {
        this.validateByXSDDefault();
    }

    @Test
    public void xmlImportTestBatch() throws Exception {
        final String xmlPath = "/eprecise/efiscal4j/nfe/v310/in/xml/nfeProc";

        final File[] fileList = Optional.ofNullable(this.getClass().getResource(xmlPath)).map(it -> {
            try {
                return it.toURI();
            } catch (final URISyntaxException e) {
                return null;
            }
        }).map(File::new).map(File::listFiles).orElse(null);

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
        final ProcessedNFe processedNFe = new FiscalDocumentDeserializer<ProcessedNFe>(xmlUrl, ProcessedNFe.class).deserialize();
        Assert.assertNotNull(processedNFe);
        try {
            ValidationBuilder.from(processedNFe).validate().throwIfViolate();

            final String xml = new FiscalDocumentSerializer<>(processedNFe).serialize();

            System.out.println(xml + "\n");

            final ValidationResult validate = this.getTestDomain().getValidator().validate(xml);
            Assert.assertTrue(validate.getError(), validate.isValid());
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return this.testDomain;
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain().buildProcessedNFe();
    }

}
