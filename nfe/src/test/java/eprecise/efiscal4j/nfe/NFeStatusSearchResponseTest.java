
package eprecise.efiscal4j.nfe;

import org.junit.Test;

import eprecise.efiscal4j.nfe.domain.NFeDomain;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearchResponse;


public class NFeStatusSearchResponseTest implements Testable {

    private final NFeDomain nFeDomain = new NFeDomain(NFeStatusSearchResponse.XSD);

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSD() throws Exception {
        this.validateByXSDDefault();
    }

    @Override
    public NFeDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain().buildNFeStatusSearchResponse();
    }

}
