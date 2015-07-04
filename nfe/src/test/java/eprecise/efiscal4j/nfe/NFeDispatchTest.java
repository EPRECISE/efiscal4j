
package eprecise.efiscal4j.nfe;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.nfe.domain.NFeDomain;
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;


public class NFeDispatchTest implements Testable {

    private final NFeDomain nFeDomain = new NFeDomain(NFeDispatch.XSD);

    @Test
    public void validateByBeanValidation() throws Exception {
        try {
            final NFeDispatch nFeDispatch = this.getTestDomain().buildNFeDispatch();

            ValidationBuilder.from(nFeDispatch).validate().throwIfViolate();

        } catch (final ConstraintViolationException e) {
            final StringBuilder message = new StringBuilder("Erro de validação:");

            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }

            Assert.assertTrue(message.toString(), false);
        }
    }

    @Test
    public void validateByXSD() throws Exception {
        final NFeDispatch nFeDispatch = this.getTestDomain().buildNFeDispatch();

        final String xml = new FiscalDocumentSerializer<>(nFeDispatch).serialize();

        System.out.println(xml);

        final ValidationResult validate = this.getTestDomain().getValidator().validate(xml);

        Assert.assertTrue(validate.getError(), validate.isValid());
    }

    @Override
    public NFeDomain getTestDomain() {
        return this.nFeDomain;
    }

}
