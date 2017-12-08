
package eprecise.efiscal4j.nfe;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.nfe.domain.TestDomain;


public interface Testable<T> {

    TestDomain getTestDomain();

    T getBuiltEntity() throws Exception;

    default void validateByBeanValidationDefault() throws Exception {
        this.validateByBeanValidation(getBuiltEntity());
    }

    default void validateByBeanValidation(T entity) throws Exception {
        try {
            ValidationBuilder.from(this.getBuiltEntity()).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    default void validateByXSDDefault() throws Exception {
        this.validateByXSD(getBuiltEntity());
    }

    default void validateByXSD(T entity) throws Exception {
        final String xml = new FiscalDocumentSerializer<>(entity).serialize();
        System.out.println(xml + "\n");
        final ValidationResult validate = this.getTestDomain().getValidator().validate(xml);
        Assert.assertTrue(validate.getError(), validate.isValid());
    }

    default void handleErrors(final ConstraintViolationException e) {
        final StringBuilder message = new StringBuilder("Erro de validação:");
        for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
            message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
        }
        Assert.assertTrue(message.toString(), false);
    }
}
