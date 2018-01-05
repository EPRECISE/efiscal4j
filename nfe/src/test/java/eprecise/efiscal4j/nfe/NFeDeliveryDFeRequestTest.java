
package eprecise.efiscal4j.nfe;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDFeResponse;
import eprecise.efiscal4j.nfe.deliveryDFe.NFeDeliveryDfeDocument;
import eprecise.efiscal4j.nfe.domain.NFeDeliveryDFeDomain;
import eprecise.efiscal4j.nfe.domain.TestDomain;


public class NFeDeliveryDFeRequestTest implements Testable<NFeDeliveryDFeRequest> {

    private final TestDomain nFeDomain = new TestDomain(NFeDeliveryDFeRequest.XSD);

    private final NFeDeliveryDFeDomain domain = new NFeDeliveryDFeDomain();

    @Test
    public void transmit() throws Exception {
        try {
            final TypedTransmissionResult<NFeDeliveryDFeRequest, NFeDeliveryDFeResponse> result = this.nFeDomain.getTransmissionChannel().transmitNFeDeliveryDFe(this.domain.buildQueryByNsuRequest());

            result.getResponse();

            System.out.println(result.getResponseXml());

            int i = 0;
            for (final NFeDeliveryDfeDocument doc : result.getResponse().getDocumentLots().getLot()) {
                final Object o = doc.getContent();

                System.out.println(String.format("-- Doc %d - %s - %s", ++i, doc.getSchema(), ToStringBuilder.reflectionToString(o)));
                System.out.println(String.format("-- XML %d - %s - %s", ++i, doc.getSchema(), new FiscalDocumentSerializer<>(o).serialize()));
            }

        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSDAsQueryByAccessKey() throws Exception {
        this.validateByXSD(this.domain.buildQueryAccesKeyRequest());
    }

    @Test
    public void validateByXSDAsQueryByNSU() throws Exception {
        this.validateByXSD(this.domain.buildQueryByNsuRequest());
    }

    @Test
    public void validateByXSDAsDeliveryNSU() throws Exception {
        this.validateByXSD(this.domain.buildDeliveryNsuRequest());
    }

    @Override
    public TestDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public NFeDeliveryDFeRequest getBuiltEntity() throws Exception {
        return new NFeDeliveryDFeDomain().buildDeliveryNsuRequest();
    }

}
