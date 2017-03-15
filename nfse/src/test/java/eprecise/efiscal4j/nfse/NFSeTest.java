
package eprecise.efiscal4j.nfse;

import org.junit.Test;

import eprecise.efiscal4j.nfse.domain.TestDomain;


public class NFSeTest implements Testable {

    @Test
    public void validateByBeanValidation() throws Exception {
        validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSD() throws Exception {
        this.validateByXSDDefault();
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(LotRps.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain().buildLotRps();
    }

}
