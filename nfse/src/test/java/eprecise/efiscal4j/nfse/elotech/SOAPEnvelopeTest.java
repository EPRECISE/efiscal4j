
package eprecise.efiscal4j.nfse.elotech;

import org.junit.Test;

import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPBody;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPHeader;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.signer.oasis.OasisSigner;


public class SOAPEnvelopeTest implements Testable {

    private final TestDomain testDomain = new TestDomain();

    @Test
    public void validateByBeanValidation() throws Exception {
        validateByBeanValidationDefault();
    }

    @Test
    public void domainTest() throws Exception {
        final SOAPEnvelope buildSOAPEnvelope = buildSOAPEnvelope();
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
        return getTestDomain();
    }

    public SOAPEnvelope buildSOAPEnvelope() throws Exception {
        return new SOAPEnvelope.Builder().withSoapHeader(new SOAPHeader.Builder().build()).withSoapBody(new SOAPBody.Builder().withTransmissibleBody(testDomain.buildElotechLotRpsDispatch()).build())
                .build(new OasisSigner(testDomain.getCertificate()));

    }

}
