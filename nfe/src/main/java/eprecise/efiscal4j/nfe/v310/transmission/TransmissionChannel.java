
package eprecise.efiscal4j.nfe.v310.transmission;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;

import eprecise.efiscal4j.nfe.transmission.NFeServiceDomain;
import org.xml.sax.SAXException;

import com.google.common.collect.ImmutableMap;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.transmission.NFeTransmissionChannel;
import eprecise.efiscal4j.nfe.transmission.request.NFeAuthorizationRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeBatchReceiptSearchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeDeliveryDFeDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeEventDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeNumberDisableDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeServiceStatusSearchRequest;
import eprecise.efiscal4j.nfe.transmission.request.NFeStatusSearchRequest;
import eprecise.efiscal4j.nfe.v310.NFe;
import eprecise.efiscal4j.nfe.v310.deliveryDFe.NFeDeliveryDFeRequest;
import eprecise.efiscal4j.nfe.v310.deliveryDFe.NFeDeliveryDFeResponse;
import eprecise.efiscal4j.nfe.v310.sharing.Event;
import eprecise.efiscal4j.nfe.v310.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.v310.sharing.EventDispatchResponseMethod;
import eprecise.efiscal4j.nfe.v310.sharing.EventType;
import eprecise.efiscal4j.nfe.v310.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.v310.sharing.NFeDispatchResponseMethod;
import eprecise.efiscal4j.nfe.v310.sharing.NFeNumberDisableDispatch;
import eprecise.efiscal4j.nfe.v310.sharing.NFeNumberDisableResponseMethod;
import eprecise.efiscal4j.nfe.v310.sharing.NFeStatusSearch;
import eprecise.efiscal4j.nfe.v310.sharing.NFeStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.v310.sharing.ServiceStatusSearch;
import eprecise.efiscal4j.nfe.v310.sharing.ServiceStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.v310.transmission.deliveryDfe.NFeDeliveryDFeBodyWrapper;
import eprecise.efiscal4j.nfe.v310.transmission.deliveryDfe.NFeDeliveryDFeSoapBody;
import eprecise.efiscal4j.nfe.v310.transmission.deliveryDfe.NFeDeliveryDFeSoapEnvelope;
import eprecise.efiscal4j.nfe.v400.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.v400.sharing.BatchReceiptSearchResponseMethod;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Felipe Bueno
 *
 */
public class TransmissionChannel implements NFeTransmissionChannel {

    private final Transmissor transmissor;

    public TransmissionChannel(final Certificate certificate) {
        this.transmissor = new Transmissor(certificate);
    }

    @Override
    public TypedTransmissionResult<NFeDispatch, NFeDispatchResponseMethod> transmitAuthorization(final NFeAuthorizationRequest nfeRequest)
            throws SAXException, IOException, ParserConfigurationException {
        final NFeDispatch nfeDispatch = (NFeDispatch) nfeRequest;
        final NFe nfe = nfeDispatch.getnFes().stream().findFirst().get();
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
//        //@formatter:off
//        final NFeDispatch nfeDispatch = new NFeDispatch.Builder()
//                                           .withBatchId(nfe.getNFeInfo().getnFeIdentification().getFiscalDocumentNumber())
//                                           .withSynchronousProcessing(SynchronousProcessing.SINCRONO)
//                                           .withNFes(Arrays.asList(nfe))
//                                           .build();
//        //@formatter:on

        final String xmlnsServiceName = NFeHeader.BASE_XMLNS + serviceUrl.replaceAll("^(.*[\\\\\\/])", "").replaceAll("\\.[^.]*$", "");

        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope(xmlnsServiceName, uf, nfeDispatch.getVersion(), nfeDispatch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(nfeDispatch).serialize();

        String responseXml = this.transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = this.postProcessResponseXML(responseXml);

        return new TypedTransmissionResult<>(NFeDispatch.class, NFeDispatchResponseMethod.class, requestXml, responseXml);
    }

    @Override
    public TypedTransmissionResult<ServiceStatusSearch, ServiceStatusSearchResponseMethod> transmitServiceStatusSearch(final NFeServiceStatusSearchRequest serviceStatusSearchRequest,
            final FiscalDocumentModel documentModel) {

        final ServiceStatusSearch serviceStatusSearch = (ServiceStatusSearch) serviceStatusSearchRequest;
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

    @Override
    public TypedTransmissionResult<EventDispatch, EventDispatchResponseMethod> transmitEventReceptionCancellation(final NFeEventDispatchRequest eventDispatchRequest,
            final FiscalDocumentModel documentModel) {

        final EventDispatch eventDispatch = (EventDispatch) eventDispatchRequest;

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

    @Override
    public TypedTransmissionResult<EventDispatch, EventDispatchResponseMethod> transmitEventReceptionCCe(final NFeEventDispatchRequest eventDispatchRequest, final FiscalDocumentModel documentModel) {

        final EventDispatch eventDispatch = (EventDispatch) eventDispatchRequest;

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

    @Override
    public TypedTransmissionResult<EventDispatch, EventDispatchResponseMethod> transmitRecipientManifestationEvent(final NFeEventDispatchRequest eventDispatchRequest) {

        final EventDispatch eventDispatch = (EventDispatch) eventDispatchRequest;

        String serviceUrl = null;

        final UF uf = UF.findByAcronym(eventDispatch.getEvents().get(0).getEventInfo().getIbgeOrgan().getAcronym());

        final Set<EventType> supportedEventTypes = Stream.of(EventType.CIENCIA_OPERACAO, EventType.CONFIRMACAO_OPERACAO, EventType.DESCONHECIMENTO_OPERACAO, EventType.OPERACAO_NAO_REALIZADA)
                .collect(Collectors.toSet());

        final Optional<Event> eventWithUnsupportedType = eventDispatch.getEvents().stream().filter(e -> !supportedEventTypes.contains(e.getEventInfo().getEventType())).findFirst();

        if (eventWithUnsupportedType.isPresent()) {
            throw new IllegalStateException(eventWithUnsupportedType.get().getEventInfo().getEventType().toString() + " not supported");
        }

        switch (eventDispatch.getEvents().get(0).getEventInfo().getTransmissionEnvironment()) {
        case HOMOLOGACAO:
            serviceUrl = NFeService.EVENT_RECEPTION.getHomologUrl(ServiceDomain.AN);
            break;
        case PRODUCAO:
            serviceUrl = NFeService.EVENT_RECEPTION.getProductionUrl(ServiceDomain.AN);
            break;
        }

        //@formatter:off
        final SOAPEnvelope soapEnvelope = this.buildSOAPEnvelope("http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento",
                UF.PR, eventDispatch.getVersion(), eventDispatch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).serialize()
                .replaceAll("xmlns:ns[0-9]{1}=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta3\"", "")
                .replaceAll("xmlns:ns[0-9]{1}=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3\"", "")
                .replaceAll("xmlns:ns[0-9]{1}=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao3\"", "")
                .replaceAll("xmlns:ns[0-9]{1}=\"http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento\" ", "");

        final String responseXml = this.transmissor.transmit(requestXml, serviceUrl, ImmutableMap.of("SOAPAction",
                "http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento/nfeRecepcaoEvento"));

        return new TypedTransmissionResult<>(EventDispatch.class, EventDispatchResponseMethod.class,
                new FiscalDocumentSerializer<>(eventDispatch).serialize(),this.postProcessReceiptManifestationResponseXML(responseXml));
        //@formatter:off
    }

    @Override
    public TypedTransmissionResult<NFeStatusSearch, NFeStatusSearchResponseMethod> transmitNFeStatusSearch(final NFeStatusSearchRequest nfeStatusSearchRequest, final FiscalDocumentModel documentModel,
            final UF uf) {

        final NFeStatusSearch nfeStatusSearch = (NFeStatusSearch) nfeStatusSearchRequest;

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

    @Override
    public TypedTransmissionResult<NFeNumberDisableDispatch, NFeNumberDisableResponseMethod> transmitNFeNumberDisable(
            final NFeServiceDomain domain,
            final NFeNumberDisableDispatchRequest nfeNumberDisableRequest
    ) {
        final NFeNumberDisableDispatch nfeNumberDisable = (NFeNumberDisableDispatch) nfeNumberDisableRequest;

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
    @Override
    public TypedTransmissionResult<NFeDeliveryDFeRequest, NFeDeliveryDFeResponse> transmitNFeDeliveryDFe(final NFeDeliveryDFeDispatchRequest deliveryDFeDispatchRequest) {

        final NFeDeliveryDFeRequest deliveryDFeRequest = (NFeDeliveryDFeRequest) deliveryDFeDispatchRequest;

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

        final NFeDeliveryDFeSoapEnvelope soapEnvelope = this.buildDeliveryDFeSOAPEnvelope(xmlnsServiceName, deliveryDFeRequest.getAuthorUf(), deliveryDFeRequest.getVersion(), deliveryDFeRequest);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(deliveryDFeRequest).serialize();

        final String serializedSoapEnvelope = new FiscalDocumentSerializer<>(soapEnvelope).serialize();

        String responseXml = this.transmissor.transmit(serializedSoapEnvelope, serviceUrl,
                ImmutableMap.of("SOAPAction", "http://www.portalfiscal.inf.br/nfe/wsdl/NFeDistribuicaoDFe/nfeDistDFeInteresse"));

        responseXml = this.postProcessNFeDeliveryDFeResponseXML(responseXml);

        return new TypedTransmissionResult<>(NFeDeliveryDFeRequest.class, NFeDeliveryDFeResponse.class, requestXml, responseXml);
    }

    private String postProcessReceiptManifestationResponseXML(final String responseXML) {
        final int startIndex = responseXML.indexOf("<soap:Body>")
                + "<soap:Body>".length();

        return responseXML.substring(startIndex, responseXML.lastIndexOf("</soap:Body>"));
    }

    private String postProcessNFeDeliveryDFeResponseXML(final String responseXml) {
        final int startIndex = responseXml.indexOf("<nfeDistDFeInteresseResult>") + "<nfeDistDFeInteresseResult>".length();

        return responseXml.substring(startIndex, responseXml.lastIndexOf("</nfeDistDFeInteresseResult"));
    }

    private String postProcessResponseXML(final String responseXml) {
        return responseXml.substring(responseXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                responseXml.lastIndexOf("</env:Body"));
    }

    private NFeDeliveryDFeSoapEnvelope buildDeliveryDFeSOAPEnvelope(final String xmlns, final UF uf, final FiscalDocumentVersion version, final TransmissibleBodyImpl transmissible) {
        //@formatter:off
        final NFeDeliveryDFeSoapBody body = new NFeDeliveryDFeSoapBody.Builder()
                .withNfeBodyWrapper(new NFeDeliveryDFeBodyWrapper.Builder()
                        .withNfeBody(new NFeBody.Builder().withXmlns(xmlns)
                                .withTransmissible(transmissible).build()).build()).build();


        return new NFeDeliveryDFeSoapEnvelope.Builder()
                  .withSoapHeader(new SOAPHeader.Builder()
                                     .withNfeHeader(new NFeHeader.Builder()
                                                       .withXmlns(xmlns)
                                                       .withUf(uf)
                                                       .withDataVersion(version)
                                                       .build())
                                     .build())
                  .withSoapBody(body)
                  .build();
        //@formatter:on
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

    @Override
    public TypedTransmissionResult<BatchReceiptSearch, BatchReceiptSearchResponseMethod> transmitBatchReceiptSearch(final NFeBatchReceiptSearchRequest request, final UF uf) {
        throw new UnsupportedOperationException();
    }

}
