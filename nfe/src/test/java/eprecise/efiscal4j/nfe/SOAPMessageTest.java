
package eprecise.efiscal4j.nfe;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.domain.TestDomain;
import eprecise.efiscal4j.nfe.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.sharing.EventDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.transmission.SOAPBody;
import eprecise.efiscal4j.nfe.transmission.SOAPEnvelope;
import eprecise.efiscal4j.nfe.transmission.SOAPHeader;
import eprecise.efiscal4j.nfe.transmission.TransmissionChannel.TransmissionResult;


public class SOAPMessageTest implements Testable {

    private final TestDomain nFeDomain = new TestDomain();

    /**
     * Teste do serviço de NFeStatusServico
     * 
     * @throws Exception
     */
    // @Test
    public void validateServiceStatusSearch() throws Exception {
        try {
            System.out.println("Testando NFeStatusServico...");

            final TransmissionResult transmissionResult = this.getTestDomain().getTransmissionChannel().transmitServiceStatusSearch(this.getTestDomain().buildServiceStatusSearch(),
                    FiscalDocumentModel.NFE);

            final ServiceStatusSearchResponseMethod serviceStatusSearchResponseMethod = new FiscalDocumentDeserializer<ServiceStatusSearchResponseMethod>(transmissionResult.getResponseXml(),
                    ServiceStatusSearchResponseMethod.class).deserialize();

            System.out.println("Retorno convertido:");

            final String returnXml = new FiscalDocumentSerializer<ServiceStatusSearchResponseMethod>(serviceStatusSearchResponseMethod).serialize();

            System.out.println(returnXml);

            System.out.println("NFeStatusServico - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    /**
     * Teste do serviço de NFeConsultaProtocolo
     * 
     * @throws Exception
     */
    @Test
    public void validateNFeStatusSearch() throws Exception {
        try {
            System.out.println("Testando NFeConsultaProtocolo...");

            final TransmissionResult transmissionResult = this.getTestDomain().getTransmissionChannel().transmitNFeStatusSearch(this.getTestDomain().buildNFeStatusSearch(), FiscalDocumentModel.NFE,
                    UF.PR);

            final NFeStatusSearchResponseMethod nfeStatusSearchResponseMethod = new FiscalDocumentDeserializer<NFeStatusSearchResponseMethod>(transmissionResult.getResponseXml(),
                    NFeStatusSearchResponseMethod.class).deserialize();

            System.out.println("Retorno convertido:");

            final String returnXml = new FiscalDocumentSerializer<NFeStatusSearchResponseMethod>(nfeStatusSearchResponseMethod).serialize();

            System.out.println(returnXml);

            System.out.println("NFeConsultaProtocolo - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    /**
     * Teste do serviço de NFeAutorizacao
     * 
     * @throws Exception
     */
    // @Test
    public void validateNfeAuthorization() throws Exception {
        try {
            System.out.println("Testando NFeAutorizacao...");

            final TransmissionResult transmissionResult = this.getTestDomain().getTransmissionChannel().transmitAuthorization(this.getTestDomain().buildNFe());

            final NFeDispatchResponseMethod returnMethod = new FiscalDocumentDeserializer<NFeDispatchResponseMethod>(transmissionResult.getResponseXml(), NFeDispatchResponseMethod.class)
                    .deserialize();

            System.out.println("Retorno convertido:");

            final String returnXml = new FiscalDocumentSerializer<NFeDispatchResponseMethod>(returnMethod).serialize();

            System.out.println(returnXml);

            System.out.println("NFeAutorizacao - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    /**
     * Teste do serviço de NFeRetAutorizacao
     * 
     * @throws Exception
     */
    // @Test
    public void validateNfeAuthorizationResult() throws Exception {
        try {
            System.out.println("Testando NFeRetAutorizacao");

            final SOAPHeader soapHeader = this.getTestDomain().buildSoapHeader(this.getTestDomain().buildNFeHeader("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetAutorizacao3", UF.PR));

            final BatchReceiptSearch batchReceiptSearch = this.getTestDomain().buildBatchReceiptSearch();

            final SOAPBody soapBody = this.getTestDomain().buildSoapBody(this.getTestDomain().buildNFeBody("http://www.portalfiscal.inf.br/nfe/wsdl/NfeRetAutorizacao3", batchReceiptSearch));

            final SOAPEnvelope soapEnvelope = this.getTestDomain().buildSoapEnvelope(soapHeader, soapBody);

            ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

            final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).serialize();

            // final String returnXml = getTestDomain().getTransmissionChannel()..transmit(requestXml, NFeService.AUTHORIZATION_RESULT.getHomologUrl(UF.PR));

            // final SOAPEnvelopeResponse soapEnvelopeResponse = new FiscalDocumentDeserializer<SOAPEnvelopeResponse>(returnXml, SOAPEnvelopeResponse.class).deserialize();
            //
            // System.out.println("retorno convertido:");
            //
            // returnXml = new FiscalDocumentSerializer<SOAPEnvelopeResponse>(soapEnvelopeResponse).serialize();
            //
            // System.out.println(returnXml);

            System.out.println("NFeRetAutorizacao - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    /**
     * Teste do serviço de RecepcaoEvento - Cancelamento
     * 
     * @throws Exception
     */
    // @Test
    public void validateEventDispatchCancellation() throws Exception {
        try {
            System.out.println("Testando RecepcaoEvento - Cancelamento...");

            final EventDispatch eventDispatch = this.getTestDomain().buildEventDispatchCancellation();

            final TransmissionResult transmissionResult = this.getTestDomain().getTransmissionChannel().transmitEventReceptionCancellation(eventDispatch, FiscalDocumentModel.NFE);

            final EventDispatchResponseMethod eventDispatchResponseMethod = new FiscalDocumentDeserializer<EventDispatchResponseMethod>(transmissionResult.getResponseXml(),
                    EventDispatchResponseMethod.class).deserialize();

            System.out.println("Retorno convertido:");

            final String returnXml = new FiscalDocumentSerializer<EventDispatchResponseMethod>(eventDispatchResponseMethod).serialize();

            System.out.println(returnXml);
            System.out.println("RecepcaoEvento - Cancelamento - teste concluído\n");

        } catch (final ConstraintViolationException e) {
            this.handleErrors(e);
        }
    }

    /**
     * Teste do serviço de RecepcaoEvento - Carta de Correção
     * 
     * @throws Exception
     */
    // @Test
    public void validateEventDispatchCCe() throws Exception {
        try {
            System.out.println("Testando RecepcaoEvento - Carta de Correção...");

            final EventDispatch eventDispatch = this.getTestDomain().buildEventDispatchCCe();

            final TransmissionResult transmissionResult = this.getTestDomain().getTransmissionChannel().transmitEventReceptionCCe(eventDispatch, FiscalDocumentModel.NFE);

            final EventDispatchResponseMethod eventDispatchResponseMethod = new FiscalDocumentDeserializer<EventDispatchResponseMethod>(transmissionResult.getResponseXml(),
                    EventDispatchResponseMethod.class).deserialize();

            System.out.println("Retorno convertido:");

            final String returnXml = new FiscalDocumentSerializer<EventDispatchResponseMethod>(eventDispatchResponseMethod).serialize();

            System.out.println(returnXml);
            System.out.println("RecepcaoEvento - Carta de Correção - teste concluído");
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
    public Object getBuiltEntity() throws Exception {
        return null;
    }

}
