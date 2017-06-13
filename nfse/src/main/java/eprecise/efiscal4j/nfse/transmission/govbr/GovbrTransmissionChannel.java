
package eprecise.efiscal4j.nfse.transmission.govbr;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.GovbrLotRpsDispatchAsync;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.GovbrLotRpsDispatchAsyncResponse;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.consult.GovbrLotRpsDispatchConsult;
import eprecise.efiscal4j.nfse.tc.govbr.services.dispatch.consult.GovbrLotRpsDispatchConsultResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.govbr.envelope.GovbrConsultLotRps;
import eprecise.efiscal4j.nfse.transmission.govbr.envelope.GovbrReceiptLotRps;
import eprecise.efiscal4j.nfse.transmission.govbr.envelope.GovbrXmlRequest;
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
    public TypedTransmissionResult<GovbrLotRpsDispatchAsync, GovbrLotRpsDispatchAsyncResponse> transmitAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode,
            final boolean homologation) throws Exception {

        final GovbrLotRpsDispatchAsync lotRpsDispatch = (GovbrLotRpsDispatchAsync) transmissible;

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(GovbrReceiptLotRps.class).createMarshaller();
        marshaller.marshal(new GovbrReceiptLotRps.Builder().withXmlRequest(new GovbrXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();

        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<EnviarLoteRpsEnvio[^>]*>.*?</EnviarLoteRpsEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation)))).map(str-> str.substring(str.indexOf("<EnviarLoteRpsResposta"), str.lastIndexOf("</RecepcionarLoteRpsResult>"))).get();
        //@formatter:on

        return new TypedTransmissionResult<>(GovbrLotRpsDispatchAsync.class, GovbrLotRpsDispatchAsyncResponse.class, requestXml, responseXml);

    }

    @Override
    public TypedTransmissionResult<GovbrLotRpsDispatchConsult, GovbrLotRpsDispatchConsultResponse> consultAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode,
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

        //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<ConsultarLoteRpsEnvio[^>]*>.*?</ConsultarLoteRpsEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation)))).map(str-> str.substring(str.indexOf("<ConsultarLoteRpsResposta"), str.lastIndexOf("</ConsultarLoteRpsResponse>"))).get();
        //@formatter:on

        return new TypedTransmissionResult<>(GovbrLotRpsDispatchConsult.class, GovbrLotRpsDispatchConsultResponse.class, requestXml, responseXml);
    }

}
