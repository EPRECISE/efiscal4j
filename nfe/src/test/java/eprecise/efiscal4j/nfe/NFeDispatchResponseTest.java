
package eprecise.efiscal4j.nfe;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator;
import eprecise.efiscal4j.commons.xml.FiscalDocumentValidator.ValidationResult;
import eprecise.efiscal4j.nfe.sharing.BatchReceipt;
import eprecise.efiscal4j.nfe.sharing.NFeDispatchResponse;


public class NFeDispatchResponseTest {

    @Test
    public void validateByBeanValidation() throws Exception {
        try {
            final NFeDispatchResponse nFeDispatchResponse = this.buildNFeDispatchResponse();
            ValidationBuilder.from(nFeDispatchResponse).validate().throwIfViolate();
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
        final FiscalDocumentValidator validator = new FiscalDocumentValidator(this.getClass().getResource("/eprecise/efiscal4j/nfe/retEnviNFe_v3.10.xsd"));
        final NFeDispatchResponse nFeDispatchResponse = this.buildNFeDispatchResponse();
        final String xml = new FiscalDocumentSerializer<>(nFeDispatchResponse).serialize();
        System.out.println(xml);
        final ValidationResult validate = validator.validate(xml);
        Assert.assertTrue(validate.getError(), validate.isValid());
    }

    private NFeDispatchResponse buildNFeDispatchResponse() throws Exception {
        //@formatter:off
        return new NFeDispatchResponse.Builder()
                     .withTransmissionEnvironment(TransmissionEnvironment.HOMOLOGACAO)
                     .withApplicationVersion("PR-v3_3_2")
                     .withStatusCode("103")
                     .withStatusDescription("Lote recebido com sucesso")
                     .withServiceUf(UF.PR)
                     .withReceptionDateTime("2013-02-06T14:51:09-02:00")
                     .withBatchReceipt(new BatchReceipt.Builder()
                                             .withReceiptNumber("431000015906453")
                                             .withAverageTime("1")
                                             .build())                                                                  
                    .build();
        //@formatter:on
    }
}
