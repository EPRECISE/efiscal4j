
package eprecise.efiscal4j.nfe.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.Charsets;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import com.google.common.io.Resources;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.v400.NFeTestParams;


public class NFeDomainImportTest {

    @Test
    public void xmlImportTest() throws JAXBException, IOException {
        Assume.assumeFalse(!NFeTestParams.getNFeImportXmlPath().isPresent());

        Files.newDirectoryStream(Paths.get(NFeTestParams.getNFeImportXmlPath().get()), path -> path.toString().endsWith(".xml")).forEach(path -> {
            try {
                System.out.println("teste de importação do xml: " + path);
                final FiscalDocument.Processed processedFiscalDocument = FiscalDocument.Processed.builder().buildFromXml(Resources.toString(path.toUri().toURL(), Charsets.UTF_8));
                Assert.assertNotNull(processedFiscalDocument);

                try {
                    ValidationBuilder.from(processedFiscalDocument).validate().throwIfViolate();
                } catch (final ConstraintViolationException e) {
                    final StringBuilder message = new StringBuilder("Erro de validação:");
                    for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                        message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
                    }
                    Assert.assertTrue(message.toString(), false);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

}
