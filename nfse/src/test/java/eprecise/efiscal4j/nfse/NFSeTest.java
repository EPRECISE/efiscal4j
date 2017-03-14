
package eprecise.efiscal4j.nfse;

import java.util.Arrays;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;


public class NFSeTest {

    @Test
    public void validateByBeanValidation() throws Exception {
        validateByBeanValidationDefault();
    }

    @Test
    public void testXml() {
        final String xml = new FiscalDocumentSerializer<>(getBuiltEntity()).serialize();
        System.out.println(xml + "\n");
    }

    public void validateByBeanValidationDefault() throws Exception {
        try {
            ValidationBuilder.from(getBuiltEntity()).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

    public void handleErrors(final ConstraintViolationException e) {
        final StringBuilder message = new StringBuilder("Erro de validação:");
        for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
            message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
        }
        Assert.assertTrue(message.toString(), false);
    }

    public LotRps getBuiltEntity() {
        try {
            LotRps lotRps;
            lotRps = new LotRps.Builder().withLotNumber("1000").withRpsQuantity(1).withStatementProvisionService(Arrays.asList(new StatementProvisionService())).build();
            return lotRps;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}
