
package eprecise.efiscal4j.nfe.v310;

import java.io.IOException;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.v310.domain.TestDomain;
import eprecise.efiscal4j.nfe.v310.person.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.v310.person.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.v310.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.v310.transmission.NFeBody;
import eprecise.efiscal4j.nfe.v310.transmission.NFeService;


public class NFeBodyTest implements Testable {

    private final TestDomain nFeDomain = new TestDomain();

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    // @Test
    // TODO revisar deserialização de soapEnvelope, NFeBody e NFeHeader
    public void xmlImportTest() throws JAXBException, IOException {
        final NFeBody nFeBody = new FiscalDocumentDeserializer<NFeBody>(this.getClass().getResource("/eprecise/efiscal4j/nfe/in/xml/nfeBody/001.xml"), NFeBody.class)
                .considering(LegalEntityDocuments.class, NaturalPersonDocuments.class, NFeDispatch.class).deserialize();

        Assert.assertNotNull(nFeBody);

        Assert.assertEquals("14241297000191", ((NFeDispatch) nFeBody.getTransmissible()).getnFes().get(0).getNFeInfo().getEmitter().getDocuments().getCnpjCpf());

        final String xml = new FiscalDocumentSerializer<>(nFeBody).considering(NFeDispatch.class).serialize();

        System.out.println(xml);

        try {
            ValidationBuilder.from(nFeBody).validate().throwIfViolate();
        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public Object getBuiltEntity() throws Exception {
        return this.getTestDomain().buildNFeBody(this.getAuthorizationXmlns(), this.getTestDomain().buildNFeDispatch());
    }

    private String getAuthorizationXmlns() {
        final String serviceUrl = NFeService.AUTHORIZATION.getHomologUrl(UF.PR);
        return NFeBody.BASE_XMLNS + serviceUrl.replaceAll("^(.*[\\\\\\/])", "").replaceAll("\\.[^.]*$", "");
    }

}
