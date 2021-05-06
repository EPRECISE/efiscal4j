
package eprecise.efiscal4j.nfse.elotech;

import org.junit.Test;

import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.domain.TestDomain;
import eprecise.efiscal4j.nfse.domain.Testable;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.ElotechSOAPBody;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.ElotechSOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.ElotechSOAPHeader;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.signer.oasis.OasisSigner;


public class ElotechSOAPEnvelopeTest implements Testable {

    private final TestDomain testDomain = new TestDomain();

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    // TODO revisar teste falhando
    // @Test
    public void domainTest() throws Exception {
        final ElotechSOAPEnvelope buildSOAPEnvelope = this.buildSOAPEnvelope();
        try {
            final String xml = new FiscalDocumentSerializer<>(buildSOAPEnvelope).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper()).serialize();
            System.out.println("xml final: " + xml + "\n");
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public TestDomain getTestDomain() {
        return this.testDomain;
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain();
    }

    public ElotechSOAPEnvelope buildSOAPEnvelope() throws Exception {
        return new ElotechSOAPEnvelope.Builder().withSoapHeader(new ElotechSOAPHeader.Builder().build()).withSoapBody(new ElotechSOAPBody.Builder().withTransmissibleBody(this.testDomain.buildElotechLotRpsDispatch()).build())
                .build(new OasisSigner(this.testDomain.getCertificate()));

    }

}
