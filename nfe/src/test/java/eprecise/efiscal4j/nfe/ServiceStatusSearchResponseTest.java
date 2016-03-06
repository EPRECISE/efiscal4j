
package eprecise.efiscal4j.nfe;

import org.junit.Test;

import eprecise.efiscal4j.nfe.domain.TestDomain;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponse;


public class ServiceStatusSearchResponseTest implements Testable {

    private final TestDomain nFeDomain = new TestDomain(ServiceStatusSearchResponse.XSD);

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSD() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Override
    public TestDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain().buildServiceStatusSearchResponse();
    }
}
