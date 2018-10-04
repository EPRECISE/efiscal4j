
package eprecise.efiscal4j.nfse.transmission.govbr.v203;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.lang3.StringEscapeUtils;
import org.w3c.dom.Document;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.GovbrLotRpsDispatchSyncResponse;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel.GovbrNFSeDispatchCancel;
import eprecise.efiscal4j.nfse.tc.govbr.v203.services.dispatch.cancel.GovbrNFSeDispatchCancelResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.UnavailableServiceException;
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrNfseCancel;
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrReceiptSyncLotRps;
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrSOAPBody;
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrSOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrSOAPHeader;
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrXmlRequest;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAutorizedResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchStateResponse;
import eprecise.efiscal4j.nfse.ts.govbr.types.GovbrVersion;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.signer.oasis.OasisSigner;
import eprecise.efiscal4j.transmissor.Transmissor;
import lombok.NoArgsConstructor;


@NoArgsConstructor(force = true)
public class GovbrTransmissionChannel implements TransmissionChannel {

    private final Transmissor transmissor;

    private final Signer signer;

    public GovbrTransmissionChannel(final Certificate certificate) {
        if (certificate == null) {
            this.transmissor = new Transmissor();
            this.signer = null;
        } else {
            this.transmissor = new Transmissor(certificate);
            try {
                this.signer = new OasisSigner(certificate);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public TypedTransmissionResult<GovbrLotRpsDispatchSync, GovbrLotRpsDispatchSyncResponse> transmitAuthorization(final NFSeRequest nfseRequest,
            final String cityCode, final boolean homologation) throws Exception {

        final GovbrLotRpsDispatchSync lotRpsDispatch = (GovbrLotRpsDispatchSync) nfseRequest;

        
        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(GovbrReceiptSyncLotRps.class).createMarshaller();
        marshaller.marshal(new GovbrReceiptSyncLotRps.Builder().withXmlRequest(new GovbrXmlRequest.Builder().withNfseRequest(lotRpsDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        
        final SOAPElement header = soapMessage.getSOAPHeader().addChildElement("cabecalho", "n0", "http://tempuri.org/");
        header.addAttribute(header.createQName("versao","n0"), GovbrVersion.VERSION_2_03.getVersion());
        SOAPElement dataVersion = header.addChildElement(header.createQName("versaoDados", "n0"));
        dataVersion.setValue(GovbrVersion.VERSION_2_03.getVersion());
        
        soapMessage.getSOAPBody().addDocument(document);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapMessage.writeTo(outputStream);
        
        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();

        final Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("SOAPAction", "http://tempuri.org/INFSEGeracao/EnviarLoteRpsSincrono");

        
      //@formatter:off
        final String responseXml = Optional.ofNullable(StringEscapeUtils.unescapeXml(transmissor.transmit(
                new String(outputStream.toByteArray()).replaceFirst("(?s)<EnviarLoteRpsSincronoEnvio[^>]*>.*?</EnviarLoteRpsSincronoEnvio>", StringEscapeUtils.escapeXml(requestXml)),
                NFSeTransmissor.getUrl(cityCode, homologation), requestProperty))).map(str-> str.substring(str.indexOf("<EnviarLoteRpsSincronoResposta"), str.lastIndexOf("</EnviarLoteRpsSincronoResult>"))).get();
        //@formatter:on
        
        return new TypedTransmissionResult<>(GovbrLotRpsDispatchSync.class, GovbrLotRpsDispatchSyncResponse.class, requestXml, responseXml);
    }

    @Override
    public TypedTransmissionResult<GovbrNFSeDispatchCancel, GovbrNFSeDispatchCancelResponse> transmitCancellation(final NFSeRequest nfseRequest,
            final String cityCode, final boolean homologation) throws Exception {
        
        final GovbrNFSeDispatchCancel cancellationDispatch = (GovbrNFSeDispatchCancel) nfseRequest;

        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        final Marshaller marshaller = JAXBContext.newInstance(GovbrNfseCancel.class).createMarshaller();
        marshaller.marshal(new GovbrNfseCancel.Builder().withXmlRequest(new GovbrXmlRequest.Builder().withNfseRequest(cancellationDispatch).build()).build(), document);
        final SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        
        final SOAPElement header = soapMessage.getSOAPHeader().addChildElement("cabecalho", "n0", "http://tempuri.org/");
        header.addAttribute(header.createQName("versao","n0"), GovbrVersion.VERSION_2_03.getVersion());
        SOAPElement dataVersion = header.addChildElement(header.createQName("versaoDados", "n0"));
        dataVersion.setValue(GovbrVersion.VERSION_2_03.getVersion());
        
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
            return new TypedTransmissionResult<>(GovbrNFSeDispatchCancel.class, GovbrNFSeDispatchCancelResponse.class, requestXml, responseXml);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new UnavailableServiceException();
        }
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

}
