
package eprecise.efiscal4j.nfse.transmission.goiania;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.goiania.services.dispatch.GoianiaLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.goiania.services.dispatch.GoianiaLotRpsDispatchSyncResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAutorizedResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchStateResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeResponse;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
import eprecise.efiscal4j.transmissor.Transmissor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.util.Optional;


@NoArgsConstructor(force = true)
public class GoianiaTransmissionChannel implements TransmissionChannel {

    private final Transmissor transmissor;

    public GoianiaTransmissionChannel(final Certificate certificate) {
        if (certificate == null) {
            this.transmissor = new Transmissor();
        } else {
            this.transmissor = new Transmissor(certificate);
        }
    }

    @Override
    public TypedTransmissionResult<GoianiaLotRpsDispatchSync, GoianiaLotRpsDispatchSyncResponse> transmitAuthorization(final NFSeRequest nfseRequest,
            final String cityCode, final boolean homologation) throws Exception {

        final GoianiaLotRpsDispatchSync lotRpsDispatch = (GoianiaLotRpsDispatchSync) nfseRequest;

        this.clearnAssignableXmlnsFrom(lotRpsDispatch);

        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        final Document document = documentBuilderFactory.newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(GoianiaReceiptSyncLotRps.class).createMarshaller();
        marshaller.marshal(new GoianiaReceiptSyncLotRps.Builder().withXmlRequest(new GoianiaXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();

        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);
        
        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();

      //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<GerarNfseEnvio[^>]*>.*?GerarNfseEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), "http://nfse.goiania.go.gov.br/ws/GerarNfse"))).map(str-> str.substring(str.indexOf("<EnviarLoteRpsSincronoResposta"), str.lastIndexOf("</EnviarLoteRpsSincronoResult>"))).get();
        //@formatter:on
        
        return new TypedTransmissionResult<>(GoianiaLotRpsDispatchSync.class, GoianiaLotRpsDispatchSyncResponse.class, requestXml, responseXml);
    }

    @Override
	public TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> transmitCancellation(
			NFSeRequest nfseRequest, String cityCode, boolean homologation) throws Exception {
		throw new UnsupportedOperationException();
	}

    @Override
    public TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeDispatchAutorizedResponse> consultAuthorization(
            NFSeRequest nfseRequest, String cityCode, boolean homologation) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeDispatchStateResponse> consultStateAuthorization(
            NFSeRequest nfseRequest, String cityCode, boolean homologation) throws Exception {
        throw new UnsupportedOperationException();
    }

    private void clearnAssignableXmlnsFrom(DefaultAssignable assignable) {
        if(assignable != null && assignable.signature != null) {
            assignable.signature.setXmlns(null);
        }
    }

}
