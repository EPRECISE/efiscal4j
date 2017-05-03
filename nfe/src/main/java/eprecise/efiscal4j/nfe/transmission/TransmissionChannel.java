
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
import eprecise.efiscal4j.nfe.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.sharing.NFeDispatchResponseMethod;
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
        transmissor = new Transmissor(certificate);
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

        final SOAPEnvelope soapEnvelope = buildSOAPEnvelope(xmlnsServiceName, uf, nfeDispatch.getVersion(), nfeDispatch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(nfeDispatch).serialize();

        String responseXml = transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

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

        final SOAPEnvelope soapEnvelope = buildSOAPEnvelope(xmlnsServiceName, uf, serviceStatusSearch.getVersion(), serviceStatusSearch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(serviceStatusSearch).serialize();

        String responseXml = transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

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

        final SOAPEnvelope soapEnvelope = buildSOAPEnvelope("http://www.portalfiscal.inf.br/nfe", uf, eventDispatch.getVersion(), eventDispatch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(eventDispatch).serialize();

        String responseXml = transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

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

        final SOAPEnvelope soapEnvelope = buildSOAPEnvelope("http://www.portalfiscal.inf.br/nfe", uf, eventDispatch.getVersion(), eventDispatch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(eventDispatch).serialize();

        String responseXml = transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = responseXml.substring(
                responseXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                responseXml.lastIndexOf("</env:Body"));

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

        final SOAPEnvelope soapEnvelope = buildSOAPEnvelope(xmlnsServiceName, uf, nfeStatusSearch.getVersion(), nfeStatusSearch);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(nfeStatusSearch).serialize();

        String responseXml = transmissor.transmit(new FiscalDocumentSerializer<>(soapEnvelope).serialize(), serviceUrl);

        responseXml = responseXml.substring(
                responseXml.indexOf("env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>") + "env:Body xmlns:env='http://www.w3.org/2003/05/soap-envelope'>".length(),
                responseXml.lastIndexOf("</env:Body"));

        return new TypedTransmissionResult<>(NFeStatusSearch.class, NFeStatusSearchResponseMethod.class, requestXml, responseXml);
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
