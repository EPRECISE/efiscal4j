package eprecice.efiscal4j.cte.sharing;

import java.io.IOException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.cte.sharing.ProcessedCTe;

public class ProcessedCTeImportTest {
    
    @Test
    public void xmlImportTest() throws JAXBException, IOException {
	final ProcessedCTe processedCTe = new FiscalDocumentDeserializer(this.getClass().getResource(
		"/eprecise/efiscal4j/cte/in/CTE35150276302157001296570010002051661000280580.xml")).deserialize(ProcessedCTe.class);
	Assert.assertNotNull(processedCTe);
	Assert.assertEquals("76302157001296", processedCTe.getCte().getInfo().getEmitter().getCnpj());
	try {
	    ValidationBuilder.from(processedCTe).validate().throwIfViolate();
	} catch (final ConstraintViolationException e) {
	    final StringBuilder message = new StringBuilder("Erro de validação:");
	    for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
		message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
	    }
	    Assert.assertTrue(message.toString(), false);
	}
    }
}
