
package eprecise.efiscal4j.nfse;

import org.junit.Test;

import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPEnvelope;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;


public class SOAPEnvelopeTest implements Testable {

    private final TestDomain testDomain = new TestDomain();

    @Test
    public void validateByBeanValidation() throws Exception {
        validateByBeanValidationDefault();
    }

    @Test
    public void domainTest() throws Exception {
        final SOAPEnvelope buildSOAPEnvelope = getTestDomain().buildSOAPEnvelope();
        try {
            final String xml = new FiscalDocumentSerializer<>(buildSOAPEnvelope).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper()).serialize();
            System.out.println("xml final: " + xml + "\n");
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public TestDomain getTestDomain() {
        return testDomain;
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return getTestDomain().buildSOAPEnvelope();
    }

}
