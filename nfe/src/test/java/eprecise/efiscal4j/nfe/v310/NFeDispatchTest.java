
package eprecise.efiscal4j.nfe.v310;

import org.junit.Test;

import eprecise.efiscal4j.nfe.v310.domain.TestDomain;
import eprecise.efiscal4j.nfe.v310.sharing.NFeDispatch;


public class NFeDispatchTest implements Testable {

    private final TestDomain nFeDomain = new TestDomain(NFeDispatch.XSD);

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSD() throws Exception {
        this.validateByXSDDefault();
    }

    @Override
    public TestDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain().buildNFeDispatch();
    }

}
