
package eprecise.efiscal4j.nfe;

import org.junit.Test;

import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.domain.NFeDeliveryDFeDomain;
import eprecise.efiscal4j.nfe.domain.TestDomain;


public class NFeDeliveryDFeRequestTest implements Testable<NFeDeliveryDFeRequest> {

    private final TestDomain nFeDomain = new TestDomain(NFeDeliveryDFeRequest.XSD);

    private final NFeDeliveryDFeDomain domain = new NFeDeliveryDFeDomain();;

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSDAsQueryByAccessKey() throws Exception {
        this.validateByXSD(this.domain.buildQueryAccesKeyRequest());
    }

    @Test
    public void validateByXSDAsQueryByNSU() throws Exception {
        this.validateByXSD(this.domain.buildQueryByNsuRequest());
    }

    @Test
    public void validateByXSDAsDeliveryNSU() throws Exception {
        this.validateByXSD(this.domain.buildDeliveryNsuRequest());
    }

    @Override
    public TestDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public NFeDeliveryDFeRequest getBuiltEntity() throws Exception {
        return new NFeDeliveryDFeDomain().buildQueryAccesKeyRequest();
    }

}
