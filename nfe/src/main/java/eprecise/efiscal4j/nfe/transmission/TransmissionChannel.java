
package eprecise.efiscal4j.nfe.transmission;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.sharing.EventDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.sharing.NFeDispatchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearch;
import eprecise.efiscal4j.nfe.sharing.ServiceStatusSearchResponseMethod;
import eprecise.efiscal4j.nfe.sharing.SynchronousProcessing;
import eprecise.efiscal4j.transmissor.Transmissor;


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

        responseXml = responseXml.substring(
                responseXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                responseXml.lastIndexOf("</env:Body"));

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

        responseXml = responseXml.substring(
                responseXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                responseXml.lastIndexOf("</env:Body"));

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

        responseXml = responseXml.substring(
                responseXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                responseXml.lastIndexOf("</env:Body"));

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

        responseXml = responseXml.substring(
                responseXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                responseXml.lastIndexOf("</env:Body"));

        return new TypedTransmissionResult<>(EventDispatch.class, EventDispatchResponseMethod.class, requestXml, responseXml);
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

    public static class TransmissionResult {

        private final String requestXml;

        private final String responseXml;

        public TransmissionResult(final String requestXml, final String responseXml) {
            this.requestXml = requestXml;
            this.responseXml = responseXml;
        }

        public String getRequestXml() {
            return this.requestXml;
        }

        public String getResponseXml() {
            return this.responseXml;
        }

        public <T> T getRequest(final Class<T> type) {
            return new FiscalDocumentDeserializer<>(this.requestXml, type).deserialize();
        }

        public <T> T getRespose(final Class<T> type) {
            return new FiscalDocumentDeserializer<>(this.responseXml, type).deserialize();
        }
    }

    public static class TypedTransmissionResult<RQ, RP> extends TransmissionResult {

        private final Class<RQ> requestType;

        private final Class<RP> responseType;

        private Optional<RQ> request = Optional.empty();

        private Optional<RP> response = Optional.empty();

        public TypedTransmissionResult(final Class<RQ> requestType, final Class<RP> responseType, final String requestXml, final String responseXml) {
            super(requestXml, responseXml);
            this.requestType = requestType;
            this.responseType = responseType;

        }

        public RQ getRequest() {
            return this.request.orElseGet(() -> {
                this.request = Optional.of(this.getRequest(this.requestType));
                return this.request.get();
            });
        }

        public RP getResponse() {
            return this.response.orElseGet(() -> {
                this.response = Optional.of(this.getRespose(this.responseType));
                return this.response.get();
            });
        }

    }
}
