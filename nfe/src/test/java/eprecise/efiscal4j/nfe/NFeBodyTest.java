
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
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.transmission.NFeBody;


public class NFeBodyTest implements Testable {

    private final NFeDomain nFeDomain = new NFeDomain(NFeDispatch.XSD);

    @Test
    public void validateByBeanValidation() throws Exception {
        try {
            final NFeBody nFeBody = this.getTestDomain().buildNFeBody("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3", this.getTestDomain().buildNFeDispatch());

            ValidationBuilder.from(nFeBody).validate().throwIfViolate();

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

        //@formatter:off
        final NFeBody nFeBody = this.getTestDomain().buildNFeBody("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3", this.getTestDomain().buildNFeDispatch());
        //@formatter:on

        final String xml = new FiscalDocumentSerializer<>(nFeBody).considering(NFeDispatch.class).serialize();

        System.out.println(xml);

        final ValidationResult validate = this.getTestDomain().getValidator().validate(xml);

        Assert.assertTrue(validate.getError(), validate.isValid());
    }

    @Test
    public void xmlImportTest() throws JAXBException, IOException {
        final NFeBody nFeBody = new FiscalDocumentDeserializer<NFeBody>(this.getClass().getResource("/eprecise/efiscal4j/nfe/in/xml/test_nfe2.xml"), NFeBody.class).considering(
                LegalEntityDocuments.class, NaturalPersonDocuments.class, NFeDispatch.class).deserialize();

        Assert.assertNotNull(nFeBody);

        Assert.assertEquals("14241297000191", ((NFeDispatch) nFeBody.getTransmissible()).getnFes().get(0).getNFeInfo().getEmitter().getDocuments().getCnpjCpf());

        final String xml = new FiscalDocumentSerializer<>(nFeBody).considering(NFeDispatch.class).serialize();

        System.out.println(xml);

        try {
            ValidationBuilder.from(nFeBody).validate().throwIfViolate();
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
