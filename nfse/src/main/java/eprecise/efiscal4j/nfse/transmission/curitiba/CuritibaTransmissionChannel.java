
package eprecise.efiscal4j.nfse.transmission.curitiba;

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
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.CuritibaLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.CuritibaLotRpsDispatchAsyncResponse;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.cancel.CuritibaNfseDispatchCancel;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.cancel.CuritibaNfseDispatchCancelResponse;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.CuritibaLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.CuritibaLotRpsDispatchConsultResponse;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.state.CuritibaLotRpsDispatchConsultState;
import eprecise.efiscal4j.nfse.tc.curitiba.services.dispatch.consult.state.CuritibaLotRpsDispatchConsultStateResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.UnavailableServiceException;
import eprecise.efiscal4j.nfse.transmission.curitiba.envelope.CuritibaReceiptXml;
import eprecise.efiscal4j.nfse.transmission.curitiba.envelope.CuritibaXmlRequest;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAutorizedResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchStateResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeResponse;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
import eprecise.efiscal4j.transmissor.Transmissor;


public class CuritibaTransmissionChannel implements TransmissionChannel {

    private final Transmissor transmissor;

    public CuritibaTransmissionChannel() {
        transmissor = null;
    }

    public CuritibaTransmissionChannel(final Certificate certificate) {
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
    public TypedTransmissionResult<CuritibaLotRpsDispatchAsync, CuritibaLotRpsDispatchAsyncResponse> transmitAuthorization(final NFSeRequest transmissible, final String cityCode, final boolean homologation)
            throws Exception {

        final CuritibaLotRpsDispatchAsync lotRpsDispatch = (CuritibaLotRpsDispatchAsync) transmissible;
        this.clearnAssignableXmlnsFrom(lotRpsDispatch);

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(CuritibaReceiptXml.class).createMarshaller();
        marshaller.marshal(new CuritibaReceiptXml.Builder().withMethod("RecepcionarLoteRps").withXmlRequest(new CuritibaXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();
        
        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://www.e-governeapps2.com.br/RecepcionarXml");

        try {
        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<EnviarLoteRpsEnvio[^>]*>.*?</EnviarLoteRpsEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<EnviarLoteRpsResposta"), str.lastIndexOf("</RecepcionarXmlResult>"))).get();
        //@formatter:on
            return new TypedTransmissionResult<>(CuritibaLotRpsDispatchAsync.class, CuritibaLotRpsDispatchAsyncResponse.class, requestXml, responseXml);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new UnavailableServiceException();
        }

    }

    @Override
    public TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeDispatchAutorizedResponse> consultAuthorization(NFSeRequest transmissible, String cityCode, boolean homologation)
            throws Exception {
        final CuritibaLotRpsDispatchConsult lotRpsDispatch = (CuritibaLotRpsDispatchConsult) transmissible;

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(CuritibaReceiptXml.class).createMarshaller();
        marshaller.marshal(new CuritibaReceiptXml.Builder().withMethod("ConsultarLoteRps").withXmlRequest(new CuritibaXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();
        
        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://www.e-governeapps2.com.br/RecepcionarXml");

        try {
        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<ConsultarLoteRpsEnvio[^>]*>.*?</ConsultarLoteRpsEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<ConsultarLoteRpsResposta"), str.lastIndexOf("</RecepcionarXmlResult>"))).get();
        //@formatter:on
            return new TypedTransmissionResult<>(CuritibaLotRpsDispatchConsult.class, CuritibaLotRpsDispatchConsultResponse.class, requestXml, responseXml);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new UnavailableServiceException();
        }
    }

    @Override
    public TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeDispatchStateResponse> consultStateAuthorization(NFSeRequest transmissible, String cityCode, boolean homologation)
            throws Exception {
        final CuritibaLotRpsDispatchConsultState lotRpsDispatch = (CuritibaLotRpsDispatchConsultState) transmissible;

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(CuritibaReceiptXml.class).createMarshaller();
        marshaller.marshal(new CuritibaReceiptXml.Builder().withMethod("ConsultarSituacaoLoteRps").withXmlRequest(new CuritibaXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();
        
        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://www.e-governeapps2.com.br/RecepcionarXml");

        try {
        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<ConsultarSituacaoLoteRpsEnvio[^>]*>.*?</ConsultarSituacaoLoteRpsEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<ConsultarSituacaoLoteRpsResposta"), str.lastIndexOf("</RecepcionarXmlResult>"))).get();
        //@formatter:on
            return new TypedTransmissionResult<>(CuritibaLotRpsDispatchConsultState.class, CuritibaLotRpsDispatchConsultStateResponse.class, requestXml, responseXml);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new UnavailableServiceException();
        }
    }

    @Override
    public TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> transmitCancellation(NFSeRequest transmissible, String cityCode, boolean homologation) throws Exception {
        
        final CuritibaNfseDispatchCancel lotRpsDispatch = (CuritibaNfseDispatchCancel) transmissible;
        this.clearnAssignableXmlnsFrom(lotRpsDispatch.getCancelRequest());

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(CuritibaReceiptXml.class).createMarshaller();
        marshaller.marshal(new CuritibaReceiptXml.Builder().withMethod("CancelarNfse").withXmlRequest(new CuritibaXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();
        
        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://www.e-governeapps2.com.br/RecepcionarXml");

        try {
        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<CancelarNfseEnvio[^>]*>.*?</CancelarNfseEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<CancelarNfseResposta"), str.lastIndexOf("</RecepcionarXmlResult>"))).get();
        //@formatter:on
            return new TypedTransmissionResult<>(CuritibaNfseDispatchCancel.class, CuritibaNfseDispatchCancelResponse.class, requestXml, responseXml);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new UnavailableServiceException();
        }
    }
    
    private void clearnAssignableXmlnsFrom(DefaultAssignable assignable) {
        if(assignable != null && assignable.signature != null) {
            assignable.signature.setXmlns(null);
        }
    }

}
