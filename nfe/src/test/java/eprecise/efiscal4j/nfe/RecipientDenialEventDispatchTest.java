
package eprecise.efiscal4j.nfe;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.nfe.domain.TestDomain;
import eprecise.efiscal4j.nfe.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.sharing.EventDispatchResponseMethod;


public class RecipientDenialEventDispatchTest implements Testable<EventDispatch> {

    private final TestDomain nFeDomain = new TestDomain(EventDispatch.XSD_RECIP_MANIF);

    @Test
    public void validateByBeanValidation() throws Exception {
        this.validateByBeanValidationDefault();
    }

    @Test
    public void validateByXSD() throws Exception {
        this.validateByXSDDefault();
    }

    @Test
    public void validateRecipientDenialEventDispatch() throws Exception {
        try {
            System.out.println("Testando Evento de Manifestação do Destinatário - Operação não Realizada");

            final TypedTransmissionResult<EventDispatch, EventDispatchResponseMethod> transmissionResult = this.getTestDomain().getTransmissionChannel()
                    .transmitRecipientManifestationEvent(this.getBuiltEntity(), FiscalDocumentModel.NFE);

            System.out.println("Retorno convertido:");
            System.out.println(transmissionResult.getResponseXml());
            System.out.println("Manifestação do Destinatário - Operação não Realizada - Teste Concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    @Override
    public TestDomain getTestDomain() {
        return this.nFeDomain;
    }

    @Override
    public EventDispatch getBuiltEntity() throws Exception {
        return this.getTestDomain().buildRecipientDenialEventDispatch();
    }

}
