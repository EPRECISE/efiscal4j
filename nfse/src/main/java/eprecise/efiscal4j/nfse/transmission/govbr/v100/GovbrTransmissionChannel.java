
package eprecise.efiscal4j.nfse.transmission.govbr.v100;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.GovbrLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.GovbrLotRpsDispatchAsyncResponse;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.cancel.GovbrNfseDispatchCancel;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.cancel.GovbrNfseDispatchCancelResponse;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.GovbrLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.GovbrLotRpsDispatchConsultResponse;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.state.GovbrLotRpsDispatchConsultState;
import eprecise.efiscal4j.nfse.tc.govbr.v100.services.dispatch.consult.state.GovbrLotRpsDispatchConsultStateResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.UnavailableServiceException;
import eprecise.efiscal4j.nfse.transmission.govbr.v100.envelope.GovbrConsultLotRps;
import eprecise.efiscal4j.nfse.transmission.govbr.v100.envelope.GovbrConsultStateLotRps;
import eprecise.efiscal4j.nfse.transmission.govbr.v100.envelope.GovbrNfseCancel;
import eprecise.efiscal4j.nfse.transmission.govbr.v100.envelope.GovbrReceiptLotRps;
import eprecise.efiscal4j.nfse.transmission.govbr.v100.envelope.GovbrXmlRequest;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.transmission.response.NFSeResponse;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Fernando C Glizt
 *
 */
public class GovbrTransmissionChannel implements TransmissionChannel {

    private final Transmissor transmissor;

    public GovbrTransmissionChannel() {
        transmissor = null;
    }

    public GovbrTransmissionChannel(final Certificate certificate) {
        if (certificate == null) {
            transmissor = new Transmissor();
        } else {
            transmissor = new Transmissor(certificate);
        }
        try {
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TypedTransmissionResult<GovbrLotRpsDispatchAsync, GovbrLotRpsDispatchAsyncResponse> transmitAuthorization(final NFSeRequest transmissible, final String cityCode, final boolean homologation)
            throws Exception {

        final GovbrLotRpsDispatchAsync lotRpsDispatch = (GovbrLotRpsDispatchAsync) transmissible;

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(GovbrReceiptLotRps.class).createMarshaller();
        marshaller.marshal(new GovbrReceiptLotRps.Builder().withXmlRequest(new GovbrXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();

        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://tempuri.org/INFSEGeracao/RecepcionarLoteRps");

        try {

        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<EnviarLoteRpsEnvio[^>]*>.*?</EnviarLoteRpsEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<EnviarLoteRpsResposta"), str.lastIndexOf("</RecepcionarLoteRpsResult>"))).get();
        //@formatter:on
            return new TypedTransmissionResult<>(GovbrLotRpsDispatchAsync.class, GovbrLotRpsDispatchAsyncResponse.class, requestXml, responseXml);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new UnavailableServiceException();
        }

    }

    @Override
    public TypedTransmissionResult<GovbrLotRpsDispatchConsult, GovbrLotRpsDispatchConsultResponse> consultAuthorization(final NFSeRequest transmissible, final String cityCode,
            final boolean homologation) throws Exception {
        final GovbrLotRpsDispatchConsult lotRpsDispatch = (GovbrLotRpsDispatchConsult) transmissible;

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(GovbrConsultLotRps.class).createMarshaller();
        marshaller.marshal(new GovbrConsultLotRps.Builder().withXmlRequest(new GovbrXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();

        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://tempuri.org/INFSEConsultas/ConsultarLoteRps");

        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<ConsultarLoteRpsEnvio[^>]*>.*?</ConsultarLoteRpsEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<ConsultarLoteRpsResposta"), str.lastIndexOf("</ConsultarLoteRpsResult>"))).get();
        //@formatter:on

        System.out.println("Request: " + requestXml);
        System.out.println("Response: " + responseXml);

        return new TypedTransmissionResult<>(GovbrLotRpsDispatchConsult.class, GovbrLotRpsDispatchConsultResponse.class, requestXml, responseXml);
    }

    @Override
    public TypedTransmissionResult<GovbrLotRpsDispatchConsultState, GovbrLotRpsDispatchConsultStateResponse> consultStateAuthorization(final NFSeRequest transmissible, final String cityCode,
            final boolean homologation) throws Exception {
        final GovbrLotRpsDispatchConsultState lotRpsDispatch = (GovbrLotRpsDispatchConsultState) transmissible;

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(GovbrConsultStateLotRps.class).createMarshaller();
        marshaller.marshal(new GovbrConsultStateLotRps.Builder().withXmlRequest(new GovbrXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();

        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://tempuri.org/INFSEConsultas/ConsultarSituacaoLoteRps");

        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<ConsultarSituacaoLoteRpsEnvio[^>]*>.*?</ConsultarSituacaoLoteRpsEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<ConsultarSituacaoLoteRpsResposta"), str.lastIndexOf("</ConsultarSituacaoLoteRpsResult>"))).get();
        //@formatter:on

        System.out.println("Request: " + requestXml);
        System.out.println("Response: " + responseXml);

        return new TypedTransmissionResult<>(GovbrLotRpsDispatchConsultState.class, GovbrLotRpsDispatchConsultStateResponse.class, requestXml, responseXml);
    }

    @Override
    public TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> transmitCancellation(final NFSeRequest nfseRequest, final String cityCode, final boolean homologation)
            throws Exception {
        final GovbrNfseDispatchCancel cancellationDispatch = (GovbrNfseDispatchCancel) nfseRequest;

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(GovbrNfseCancel.class).createMarshaller();
        marshaller.marshal(new GovbrNfseCancel.Builder().withXmlRequest(new GovbrXmlRequest.Builder().withNfseRequest(cancellationDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(cancellationDispatch).serialize();

        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://tempuri.org/INFSEGeracao/CancelarNfse");

        try {

        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<CancelarNfseEnvio[^>]*>.*?</CancelarNfseEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<CancelarNfseResposta"), str.lastIndexOf("</CancelarNfseResult>"))).get();
        //@formatter:on
            return new TypedTransmissionResult<>(GovbrNfseDispatchCancel.class, GovbrNfseDispatchCancelResponse.class, requestXml, responseXml);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new UnavailableServiceException();
        }
    }

}
