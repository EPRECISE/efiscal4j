
package eprecise.efiscal4j.nfe.v400.imports;

import java.io.IOException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.nfe.v400.NFe;
import eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFe;


public class NFeSimpleImportTest {

    @Test
    public void xmlImportTest() throws JAXBException, IOException {
        final ProcessedNFe processedNFe = new FiscalDocumentDeserializer<>(this.getClass().getResource("/eprecise/efiscal4j/nfe/v400/in/xml/nfe/001.xml"), ProcessedNFe.class).considering(NFe.getValidationConsideringClasses()).deserialize();
        Assert.assertNotNull(processedNFe);
        Assert.assertEquals("07243119000134", processedNFe.getNfe().getNFeInfo().getEmitter().getDocuments().getCnpjCpf());
        try {
            ValidationBuilder.from(processedNFe).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            final StringBuilder message = new StringBuilder("Erro de validação:");
            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }
            Assert.assertTrue(message.toString(), false);
        }
    }
}
