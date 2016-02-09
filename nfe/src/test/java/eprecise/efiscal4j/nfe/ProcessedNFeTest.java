
package eprecise.efiscal4j.nfe;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.nfe.domain.NFeDomain;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;


public class ProcessedNFeTest implements Testable {

    private final NFeDomain nFeDomain = new NFeDomain(ProcessedNFe.XSD);

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
        final DecimalFormat formatter = new DecimalFormat("000");
        for (int fileCount = 1; fileCount <= 999; fileCount++) {
            final String xmlPath = "/eprecise/efiscal4j/nfe/in/xml/nfeProc/" + (formatter.format(fileCount)) + ".xml";
            final URL xmlUrl = this.getClass().getResource(xmlPath);
            if (xmlUrl == null) {
                System.out.println("Arquivo " + xmlPath + " não encontrado. Finalizando teste.");
                return;
            }
            System.out.println("Importando " + xmlPath + "...");
            this.xmlImportTest(xmlUrl);
            System.out.println(xmlPath + " - Importação finalizada\n");
        }
    }

    private void xmlImportTest(URL xmlUrl) throws JAXBException, IOException {
        final ProcessedNFe processedNFe = new FiscalDocumentDeserializer<ProcessedNFe>(xmlUrl, ProcessedNFe.class).deserialize();
        Assert.assertNotNull(processedNFe);
        try {
            ValidationBuilder.from(processedNFe).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    @Override
    public NFeDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain().buildProcessedNFe();
    }

}
