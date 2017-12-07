
package eprecise.efiscal4j.nfe.transmission;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.sharing.EventDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.sharing.NFeDeliveryDFeResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.sharing.NFeDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeNumberDisableDispatch;
import eprecise.efiscal4j.nfe.sharing.NFeNumberDisableResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearch;
import eprecise.efiscal4j.nfe.sharing.NFeStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearch;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.SynchronousProcessing;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Felipe Bueno
 *
 */
public class TransmissionChannel {

    private final Transmissor transmissor;

    public TransmissionChannel(final Certificate certificate) {
        this.transmissor = new Transmissor(certificate);
    }

    public TypedTransmissionResult<NFe, NFeDispatchResponseMethod> transmitAuthorization(final NFe nfe) throws SAXException, IOException, ParserConfigurationException {
        String serviceUrl = null;
        final UF uf = nfe.getNFeInfo().getEmitter().getAdress().getCity().getUf();

        switch (nfe.getNFeInfo().getnFeIdentification().getTransmissionEnvironment()) {
        case HOMOLOGACAO:
            if (nfe.getNFeInfo().getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.AUTHORIZATION.getHomologUrl(uf);
            } else if (nfe.getNFeInfo().getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.AUTHORIZATION.getHomologUrl(uf);
            }
            break;
        case PRODUCAO:
            if (nfe.getNFeInfo().getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.AUTHORIZATION.getProductionUrl(uf);
            } else if (nfe.getNFeInfo().getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.AUTHORIZATION.getProductionUrl(uf);
            }
            break;
        }
        //@formatter:off
        final NFeDispatch nfeDispatch = new NFeDispatch.Builder()
                                           .withBatchId(nfe.getNFeInfo().getnFeIdentification().getFiscalDocumentNumber())
                                           .withSynchronousProcessing(SynchronousProcessing.SINCRONO)
                                           .withNFes(Arrays.asList(nfe))
                                           .build();
        //@formatter:on

        final String xmlnsServiceName = NFeHeader.BASE_XMLNS + serviceUrl.replaceAll("^(.*[\\\\\\/])", "").replaceAll("\\.[^.]*$", "");

        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope(xmlnsServiceName, uf, nfeDispatch.getVersion(), nfeDispatch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(nfeDispatch).serialize();

        String responseXml = this.transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = this.postProcessResponseXML(responseXml);

        return new TypedTransmissionResult<>(NFe.class, NFeDispatchResponseMethod.class, requestXml, responseXml);
    }

    public TypedTransmissionResult<ServiceStatusSearch, ServiceStatusSearchResponseMethod> transmitServiceStatusSearch(final ServiceStatusSearch serviceStatusSearch,
            final FiscalDocumentModel documentModel) {
        String serviceUrl = null;

        final UF uf = serviceStatusSearch.getServiceUf();

        switch (serviceStatusSearch.getTransmissionEnvironment()) {
        case HOMOLOGACAO:
            if (documentModel.equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.SERVICE_STATUS.getHomologUrl(uf);
            } else if (documentModel.equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.SERVICE_STATUS.getHomologUrl(uf);
            } else {
                throw new IllegalStateException(documentModel.toString() + " not supported");
            }
            break;
        case PRODUCAO:
            if (documentModel.equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.SERVICE_STATUS.getProductionUrl(uf);
            } else if (documentModel.equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.SERVICE_STATUS.getProductionUrl(uf);
            } else {
                throw new IllegalStateException(documentModel.toString() + " not supported");
            }
            break;
        }

        final String xmlnsServiceName = NFeHeader.BASE_XMLNS + serviceUrl.replaceAll("^(.*[\\\\\\/])", "").replaceAll("\\.[^.]*$", "");

        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope(xmlnsServiceName, uf, serviceStatusSearch.getVersion(), serviceStatusSearch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(serviceStatusSearch).serialize();

        String responseXml = this.transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = this.postProcessResponseXML(responseXml);

        return new TypedTransmissionResult<>(ServiceStatusSearch.class, ServiceStatusSearchResponseMethod.class, requestXml, responseXml);
    }

    public TypedTransmissionResult<EventDispatch, EventDispatchResponseMethod> transmitEventReceptionCancellation(final EventDispatch eventDispatch, final FiscalDocumentModel documentModel) {
        String serviceUrl = null;

        final UF uf = UF.findByAcronym(eventDispatch.getEvents().get(0).getEventInfo().getIbgeOrgan().getAcronym());

        switch (eventDispatch.getEvents().get(0).getEventInfo().getTransmissionEnvironment()) {
        case HOMOLOGACAO:
            if (documentModel.equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.EVENT_RECEPTION.getHomologUrl(uf);
            } else if (documentModel.equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.EVENT_RECEPTION.getHomologUrl(uf);
            } else {
                throw new IllegalStateException(documentModel.toString() + " not supported");
            }
            break;
        case PRODUCAO:
            if (documentModel.equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.EVENT_RECEPTION.getProductionUrl(uf);
            } else if (documentModel.equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.EVENT_RECEPTION.getProductionUrl(uf);
            } else {
                throw new IllegalStateException(documentModel.toString() + " not supported");
            }
            break;
        }

        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope("http://www.portalfiscal.inf.br/nfe", uf, eventDispatch.getVersion(), eventDispatch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(eventDispatch).serialize();

        String responseXml = this.transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = this.postProcessResponseXML(responseXml);

        return new TypedTransmissionResult<>(EventDispatch.class, EventDispatchResponseMethod.class, requestXml, responseXml);
    }

    public TypedTransmissionResult<EventDispatch, EventDispatchResponseMethod> transmitEventReceptionCCe(final EventDispatch eventDispatch, final FiscalDocumentModel documentModel) {
        String serviceUrl = null;

        final UF uf = UF.findByAcronym(eventDispatch.getEvents().get(0).getEventInfo().getIbgeOrgan().getAcronym());

        if (!documentModel.equals(FiscalDocumentModel.NFE)) {
            throw new IllegalStateException(documentModel.toString() + " not supported");
        }

        switch (eventDispatch.getEvents().get(0).getEventInfo().getTransmissionEnvironment()) {
        case HOMOLOGACAO:
            serviceUrl = NFeService.EVENT_RECEPTION.getHomologUrl(uf);
            break;
        case PRODUCAO:
            serviceUrl = NFeService.EVENT_RECEPTION.getProductionUrl(uf);
            break;
        }

        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope("http://www.portalfiscal.inf.br/nfe", uf, eventDispatch.getVersion(), eventDispatch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(eventDispatch).serialize();

        String responseXml = this.transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = this.postProcessResponseXML(responseXml);

        return new TypedTransmissionResult<>(EventDispatch.class, EventDispatchResponseMethod.class, requestXml, responseXml);
    }

    public TypedTransmissionResult<NFeStatusSearch, NFeStatusSearchResponseMethod> transmitNFeStatusSearch(final NFeStatusSearch nfeStatusSearch, final FiscalDocumentModel documentModel,
            final UF uf) {
        String serviceUrl = null;

        switch (nfeStatusSearch.getTransmissionEnvironment()) {
        case HOMOLOGACAO:
            if (documentModel.equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.PROTOCOL_SEARCH.getHomologUrl(uf);
            } else if (documentModel.equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.PROTOCOL_SEARCH.getHomologUrl(uf);
            } else {
                throw new IllegalStateException(documentModel.toString() + " not supported");
            }
            break;
        case PRODUCAO:
            if (documentModel.equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.PROTOCOL_SEARCH.getProductionUrl(uf);
            } else if (documentModel.equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.PROTOCOL_SEARCH.getProductionUrl(uf);
            } else {
                throw new IllegalStateException(documentModel.toString() + " not supported");
            }
            break;
        }

        final String xmlnsServiceName = NFeHeader.BASE_XMLNS + serviceUrl.replaceAll("^(.*[\\\\\\/])", "").replaceAll("\\.[^.]*$", "");

        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope(xmlnsServiceName, uf, nfeStatusSearch.getVersion(), nfeStatusSearch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(nfeStatusSearch).serialize();

        String responseXml = this.transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = this.postProcessResponseXML(responseXml);

        return new TypedTransmissionResult<>(NFeStatusSearch.class, NFeStatusSearchResponseMethod.class, requestXml, responseXml);
    }

    public TypedTransmissionResult<NFeNumberDisableDispatch, NFeNumberDisableResponseMethod> transmitNFeNumberDisable(final NFeNumberDisableDispatch nfeNumberDisable) {

        final UF uf = nfeNumberDisable.getInfo().getUfIbgeCode();

        final FiscalDocumentModel documentModel = nfeNumberDisable.getInfo().getFiscalDocumentModel();
        String serviceUrl = null;

        switch (nfeNumberDisable.getInfo().getTransmissionEnvironment()) {
        case HOMOLOGACAO:
            if (documentModel.equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.DISABILITY.getHomologUrl(uf);
            } else if (documentModel.equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.DISABILITY.getHomologUrl(uf);
            } else {
                throw new IllegalStateException(documentModel.toString() + " not supported");
            }
            break;
        case PRODUCAO:
            if (documentModel.equals(FiscalDocumentModel.NFE)) {
                serviceUrl = NFeService.DISABILITY.getProductionUrl(uf);
            } else if (documentModel.equals(FiscalDocumentModel.NFCE)) {
                serviceUrl = NFCeService.DISABILITY.getProductionUrl(uf);
            } else {
                throw new IllegalStateException(documentModel.toString() + " not supported");
            }
            break;
        }

        final String xmlnsServiceName = NFeHeader.BASE_XMLNS + serviceUrl.replaceAll("^(.*[\\\\\\/])", "").replaceAll("\\.[^.]*$", "");

        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope(xmlnsServiceName, uf, nfeNumberDisable.getVersion(), nfeNumberDisable);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(nfeNumberDisable).serialize();

        String responseXml = this.transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = this.postProcessResponseXML(responseXml);

        return new TypedTransmissionResult<>(NFeNumberDisableDispatch.class, NFeNumberDisableResponseMethod.class, requestXml, responseXml);
    }

    /**
     * Web Service - NFeDistribuicaoDFe
     * 
     * Distribui documentos e informações de interesse do ator da NF-e
     * 
     * Função: Serviço destinado à distribuição de informações resumidas e documentos fiscais eletrônicos de interesse de um ator, seja este uma pessoa física ou jurídica. Processo: síncrono Método:
     * nfeDistDFeInteresse Este serviço permite que um ator da NF-e tenha acesso aos documentos fiscais eletrônicos (DF-e) e informações resumidas que não tenham sido gerados por ele e que sejam de
     * seu interesse. Pode ser consumido por qualquer ator de NF-e, Pessoa Jurídica ou Pessoa Física, que possua um certificado digital de PJ ou PF. No caso de Pessoa Jurídica, a empresa será
     * autenticada pelo CNPJ base e poderá realizar a consulta com qualquer CNPJ da empresa desde que o CNPJ base consultado seja o mesmo do certificado digital.
     * 
     * NT 2014/002
     * 
     * @param deliveryDFeRequest
     *            - Requisição de informção de DF-e
     * @return Resultado da requisição, com valor dependendo da informação solicitada
     */
    public TypedTransmissionResult<NFeDeliveryDFeRequest, NFeDeliveryDFeResponseMethod> transmitNFeDeliveryDFe(final NFeDeliveryDFeRequest deliveryDFeRequest) {

        String serviceUrl = null;

        switch (deliveryDFeRequest.getTransmissionEnvironment()) {
        case HOMOLOGACAO:
            serviceUrl = NFeService.DELIVERY_DFE.getHomologUrl(ServiceDomain.AN);
            break;
        case PRODUCAO:
            serviceUrl = NFeService.DELIVERY_DFE.getProductionUrl(ServiceDomain.AN);
            break;
        }

        final String xmlnsServiceName = NFeHeader.BASE_XMLNS + serviceUrl.replaceAll("^(.*[\\\\\\/])", "").replaceAll("\\.[^.]*$", "");

        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope(xmlnsServiceName, uf, deliveryDFeRequest.getVersion(), deliveryDFeRequest);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(deliveryDFeRequest).serialize();

        String responseXml = this.transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = this.postProcessResponseXML(responseXml);

        return new TypedTransmissionResult<>(NFeDeliveryDFeRequest.class, NFeDeliveryDFeResponseMethod.class, requestXml, responseXml);
    }

    private String postProcessResponseXML(String responseXml) {
        return responseXml.substring(responseXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                responseXml.lastIndexOf("</env:Body"));
    }

    private SOAPEnvelope buildSOAPEnvelope(final String xmlns, final UF uf, final FiscalDocumentVersion version, final TransmissibleBodyImpl transmissible) {
        //@formatter:off
        return new SOAPEnvelope.Builder()
                  .withSoapHeader(new SOAPHeader.Builder()
                                     .withNfeHeader(new NFeHeader.Builder()
                                                       .withXmlns(xmlns)
                                                       .withUf(uf)
                                                       .withDataVersion(version)
                                                       .build())
                                     .build())
                  .withSoapBody(new SOAPBody.Builder()
                                   .withNfeBody(new NFeBody.Builder()
                                                   .withXmlns(xmlns)
                                                   .withTransmissible(transmissible)
                                                   .build())
                                   .build())
                  .build();
        //@formatter:on
    }

}
