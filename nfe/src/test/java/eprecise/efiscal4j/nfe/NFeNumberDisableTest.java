
package eprecise.efiscal4j.nfe;

import org.junit.Test;

import eprecise.efiscal4j.nfe.domain.TestDomain;
import eprecise.efiscal4j.nfe.sharing.NFeNumberDisableDispatch;


public class NFeNumberDisableTest implements Testable {

    private final TestDomain nFeDomain = new TestDomain(NFeNumberDisableDispatch.XSD);

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
    public NFeNumberDisableDispatch getBuiltEntity() throws Exception {
        return this.getTestDomain().buildNFeNumberDisable();
    }

}