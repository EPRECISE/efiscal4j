
package eprecise.efiscal4j.nfe;

import org.junit.Test;

import eprecise.efiscal4j.nfe.domain.TestDomain;
import eprecise.efiscal4j.nfe.sharing.NFeNumberDisable;


public class NFeNumberDisableTest implements Testable {

    private final TestDomain nFeDomain = new TestDomain(NFeNumberDisable.XSD);

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
    public NFeNumberDisable getBuiltEntity() throws Exception {
        return this.getTestDomain().buildNFeNumberDisable();
    }

}
