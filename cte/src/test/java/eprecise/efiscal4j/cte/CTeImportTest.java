
package eprecise.efiscal4j.cte;

import java.io.IOException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;


public class CTeImportTest {

    // TODO teste está falhando - revisar
    // @Test
    public void xmlImportTest() throws JAXBException, IOException {
        final CTe cte = new FiscalDocumentDeserializer<CTe>(this.getClass().getResource("/eprecise/efiscal4j/cte/in/test_cte.xml"), CTe.class).deserialize();
        Assert.assertNotNull(cte);
        Assert.assertEquals("00000000000100", cte.getInfo().getEmitter().getCnpj());
        try {
            ValidationBuilder.from(cte).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            final StringBuilder message = new StringBuilder("Erro de validação:");
            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }
            Assert.assertTrue(message.toString(), false);
        }
    }
}
