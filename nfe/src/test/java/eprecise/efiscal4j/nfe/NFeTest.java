
package eprecise.efiscal4j.nfe;

import java.io.IOException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.nfe.domain.NFeDomain;


public class NFeTest implements Testable {

    private final NFeDomain nFeDomain = new NFeDomain(NFe.XSD);

    @Test
    public void validateByBeanValidation() throws Exception {
        try {
            ValidationBuilder.from(this.getTestDomain().buildNFe()).validate().throwIfViolate();
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
        final String xml = new FiscalDocumentSerializer<>(this.getTestDomain().buildNFe()).serialize();
        System.out.println(xml);
        final ValidationResult validate = this.getTestDomain().getValidator().validate(xml);
        Assert.assertTrue(validate.getError(), validate.isValid());
    }

    @Test
    public void xmlImportTest() throws JAXBException, IOException {
        //@formatter:off
        final NFe nfe = new FiscalDocumentDeserializer<NFe>(this.getClass().getResource("/eprecise/efiscal4j/nfe/in/xml/test_nfe.xml"), NFe.class)
                .considering(NFe.getValidationConsideringClasses()).deserialize();
        //@formatter:on

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

    @Override
    public NFeDomain getTestDomain() {
        return this.nFeDomain;
    }
}
