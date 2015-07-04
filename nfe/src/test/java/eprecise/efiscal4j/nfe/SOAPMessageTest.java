
package eprecise.efiscal4j.nfe;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.domain.NFeDomain;
import eprecise.efiscal4j.nfe.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.sharing.NFeDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearch;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearch;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.transmission.ClientSample;
import eprecise.efiscal4j.nfe.transmission.NFeService;
import eprecise.efiscal4j.nfe.transmission.SOAPBody;
import eprecise.efiscal4j.nfe.transmission.SOAPEnvelope;
import eprecise.efiscal4j.nfe.transmission.SOAPHeader;


public class SOAPMessageTest implements Testable {

    private final NFeDomain nFeDomain = new NFeDomain();

    /**
     * Teste do serviço de NFeStatusServico
     * 
     * @throws Exception
     */
    @Test
    public void validateServiceStatusSearch() throws Exception {
        try {
            System.out.println("testando NFeStatusServico...");

            final SOAPHeader soapHeader = this.getTestDomain().buildSoapHeader(this.getTestDomain().buildNFeHeader("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3", UF.PR));

            final ServiceStatusSearch serviceStatusSearch = this.getTestDomain().buildServiceStatusSearch();

            final SOAPBody soapBody = this.getTestDomain().buildSoapBody(this.getTestDomain().buildNFeBody("http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3", serviceStatusSearch));

            final SOAPEnvelope soapEnvelope = this.getTestDomain().buildSoapEnvelope(soapHeader, soapBody);

            ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

            final ClientSample clientSample = new ClientSample();

            String returnXml = clientSample.testHttpsConnection(soapEnvelope, NFeService.SERVICE_STATUS.getHomologUrl(UF.PR));

            System.out.println(returnXml);

            returnXml = returnXml.substring(
                    returnXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                    returnXml.lastIndexOf("</env:Body"));

            final ServiceStatusSearchResponseMethod serviceStatusSearchResponseMethod = new FiscalDocumentDeserializer<ServiceStatusSearchResponseMethod>(returnXml,
                    ServiceStatusSearchResponseMethod.class).deserialize();

            System.out.println("retorno convertido:");

            returnXml = new FiscalDocumentSerializer<ServiceStatusSearchResponseMethod>(serviceStatusSearchResponseMethod).serialize();

            System.out.println(returnXml);

            System.out.println("NFeStatusServico - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            final StringBuilder message = new StringBuilder("Erro de validação:");
            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }
            Assert.assertTrue(message.toString(), false);
        }
    }

    /**
     * Teste do serviço de NFeAutorizacao
     * 
     * @throws Exception
     */
    // @Test
    public void validateNfeDispatch() throws Exception {
        try {
            System.out.println("Testando NFeAutorizacao...");
            final SOAPHeader soapHeader = this.getTestDomain().buildSoapHeader(this.getTestDomain().buildNFeHeader("http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao3", UF.PR));

            final NFeDispatch nFeDispatch = this.getTestDomain().buildNFeDispatch();

            final SOAPBody soapBody = this.getTestDomain().buildSoapBody(this.getTestDomain().buildNFeBody("http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao3", nFeDispatch));

            final SOAPEnvelope soapEnvelope = this.getTestDomain().buildSoapEnvelope(soapHeader, soapBody);

            ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

            final ClientSample clientSample = new ClientSample();

            String returnXml = clientSample.testHttpsConnection(soapEnvelope, NFeService.AUTHORIZATION.getHomologUrl(UF.PR));

            returnXml = returnXml.substring(
                    returnXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                    returnXml.lastIndexOf("</env:Body"));

            final NFeDispatchResponseMethod returnMethod = new FiscalDocumentDeserializer<NFeDispatchResponseMethod>(returnXml, NFeDispatchResponseMethod.class).deserialize();

            System.out.println("retorno convertido:");

            returnXml = new FiscalDocumentSerializer<NFeDispatchResponseMethod>(returnMethod).serialize();

            System.out.println(returnXml);

            System.out.println("NFeAutorizacao - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            final StringBuilder message = new StringBuilder("Erro de validação:");
            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }
            Assert.assertTrue(message.toString(), false);
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

            final ClientSample clientSample = new ClientSample();

            final String returnXml = clientSample.testHttpsConnection(soapEnvelope, NFeService.AUTHORIZATION_RESULT.getHomologUrl(UF.PR));

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
            final StringBuilder message = new StringBuilder("Erro de validação:");
            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }
            Assert.assertTrue(message.toString(), false);
        }
    }

    /**
     * Teste do serviço de NFeConsultaProtocolo
     * 
     * @throws Exception
     */
    // @Test
    public void validateNfeProtocolSearch() throws Exception {
        try {
            System.out.println("Testando NFeConsultaProtocolo...");
            final SOAPHeader soapHeader = this.getTestDomain().buildSoapHeader(this.getTestDomain().buildNFeHeader("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta3", UF.PR));

            final NFeStatusSearch nFeStatusSearch = this.getTestDomain().buildNFeStatusSearch();

            final SOAPBody soapBody = this.getTestDomain().buildSoapBody(this.getTestDomain().buildNFeBody("http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta3", nFeStatusSearch));

            final SOAPEnvelope soapEnvelope = this.getTestDomain().buildSoapEnvelope(soapHeader, soapBody);

            ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

            final ClientSample clientSample = new ClientSample();

            final String returnXml = clientSample.testHttpsConnection(soapEnvelope, NFeService.PROTOCOL_SEARCH.getHomologUrl(UF.PR));

            // final SOAPEnvelopeResponse soapEnvelopeResponse = new FiscalDocumentDeserializer<SOAPEnvelopeResponse>(returnXml, SOAPEnvelopeResponse.class).deserialize();
            //
            // System.out.println("retorno convertido:");
            //
            // returnXml = new FiscalDocumentSerializer<SOAPEnvelopeResponse>(soapEnvelopeResponse).serialize();
            //
            // System.out.println(returnXml);

            System.out.println("NFeConsultaProtocolo - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            final StringBuilder message = new StringBuilder("Erro de validação:");
            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }
            Assert.assertTrue(message.toString(), false);
        }
    }

    /**
     * Teste do serviço de RecepcaoEvento
     * 
     * @throws Exception
     */
    // @Test
    public void validateEventDispatch() throws Exception {
        try {
            System.out.println("Testando RecepcaoEvento...");
            final SOAPHeader soapHeader = this.getTestDomain().buildSoapHeader(
                    this.getTestDomain().buildNFeHeader("http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento", UF.PR, FiscalDocumentVersion.VERSION_1_00));

            final EventDispatch eventDispatch = this.getTestDomain().buildEventDispatch();

            final SOAPBody soapBody = this.getTestDomain().buildSoapBody(this.getTestDomain().buildNFeBody("http://www.portalfiscal.inf.br/nfe", eventDispatch));

            final SOAPEnvelope soapEnvelope = this.getTestDomain().buildSoapEnvelope(soapHeader, soapBody);

            ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

            final ClientSample clientSample = new ClientSample();

            final String returnXml = clientSample.testHttpsConnection(soapEnvelope, NFeService.EVENT_RECEPTION.getHomologUrl(UF.PR));

            // final SOAPEnvelopeResponse soapEnvelopeResponse = new FiscalDocumentDeserializer<SOAPEnvelopeResponse>(returnXml, SOAPEnvelopeResponse.class).deserialize();
            //
            // System.out.println("retorno convertido:");
            //
            // returnXml = new FiscalDocumentSerializer<SOAPEnvelopeResponse>(soapEnvelopeResponse).serialize();
            //
            // System.out.println(returnXml);
            System.out.println("RecepcaoEvento - teste concluído");
            System.out.println("");

        } catch (final ConstraintViolationException e) {
            final StringBuilder message = new StringBuilder("Erro de validação:");
            for (final ConstraintViolation<?> v : e.getConstraintViolations()) {
                message.append("\n").append(v.getLeafBean()).append(" ").append(v.getPropertyPath()).append(" ").append(v.getMessage());
            }
            Assert.assertTrue(message.toString(), false);
        }
    }

    @Override
    public NFeDomain getTestDomain() {
        return this.nFeDomain;
    }

}
