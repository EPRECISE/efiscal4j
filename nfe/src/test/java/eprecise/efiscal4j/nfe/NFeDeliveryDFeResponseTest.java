
package eprecise.efiscal4j.nfe;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeResponse;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDfeDocument;
import eprecise.efiscal4j.nfe.domain.NFeDeliveryDFeDomain;
import eprecise.efiscal4j.nfe.domain.TestDomain;


public class NFeDeliveryDFeResponseTest implements Testable<NFeDeliveryDFeResponse> {

    private final TestDomain nFeDomain = new TestDomain(NFeDeliveryDFeResponse.XSD);

    private final NFeDeliveryDFeDomain domain = new NFeDeliveryDFeDomain();

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSDAsQueryByAccessKey() throws Exception {
        this.validateByXSD(this.domain.buildQueryAccesKeyResponse());
    }

    @Test
    public void validateByXSDAsQueryByNSU() throws Exception {
        this.validateByXSD(this.domain.buildQueryByNsuResponse());
    }

    @Test
    public void validateByXSDAsDeliveryNSU() throws Exception {
        this.validateByXSD(this.domain.buildDeliveryNsuResponse());
    }

    @Test
    public void validateBase64DocumentContetRegex() throws Exception {
        NFeDeliveryDfeDocument entity = null;

        try {
            entity = this.domain.buildDfeDocumentWithInvalidBase64();
        } catch (final ConstraintViolationException e) {
            entity = null;
        }

        Assert.assertNull(entity);
    }

    @Override
    public TestDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public NFeDeliveryDFeResponse getBuiltEntity() throws Exception {
        return new NFeDeliveryDFeDomain().buildQueryAccesKeyResponse();
    }

}
