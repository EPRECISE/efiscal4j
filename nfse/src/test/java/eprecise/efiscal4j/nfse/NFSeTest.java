
package eprecise.efiscal4j.nfse;

import org.junit.Test;

import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;


public class NFSeTest implements Testable {

    @Test
    public void validateByBeanValidation() throws Exception {
        validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSD() throws Exception {
        validateByXSDDefault();
    }

    @Override
    public TestDomain getTestDomain() {
        return new TestDomain(LotRpsDispatch.XSD);
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return getTestDomain().buildLotRpsDispatch();
    }

}
