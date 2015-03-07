
package eprecise.efiscal4j.nfe;

import java.io.IOException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;


public class NFeImportTest {

    @Test
    public void xmlImportTest() throws JAXBException, IOException {
        final NFe nfe = new FiscalDocumentDeserializer<NFe>(this.getClass().getResource("/eprecise/efiscal4j/nfe/in/test_nfe.xml"), NFe.class).considering(LegalEntityDocuments.class,
                NaturalPersonDocuments.class).deserialize();
        Assert.assertNotNull(nfe);
        Assert.assertEquals("11707347000195", nfe.getNFeInfo().getEmitter().getDocuments().getCnpjCpf());
        try {
            ValidationBuilder.from(nfe).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            final StringBuilder message = new StringBuilder("Erro de validação:");
            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }
            Assert.assertTrue(message.toString(), false);
        }
    }
}
