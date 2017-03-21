
package eprecise.efiscal4j.nfse;

import java.io.InputStreamReader;
import java.util.Scanner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.nfse.domain.TestDomain;


public interface Testable {

    TestDomain getTestDomain();

    Object getBuiltEntity() throws Exception;

    default void validateByBeanValidationDefault() throws Exception {
        try {
            ValidationBuilder.from(getBuiltEntity()).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            handleErrors(e);
        }
    }

    default void validateByXSDDefault() throws Exception {
        // final String xml = new FiscalDocumentSerializer<>(this.getBuiltEntity()).serialize();
        // System.out.println(xml + "\n");

        final String xml = new Scanner(new InputStreamReader(getClass().getResourceAsStream("/eprecise/efiscal4j/nfse/xsd/req7_test.xml"))).useDelimiter("\\Z").next();
        System.out.println(xml + "\n");

        final ValidationResult validate = getTestDomain().getValidator().validate(xml);
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
