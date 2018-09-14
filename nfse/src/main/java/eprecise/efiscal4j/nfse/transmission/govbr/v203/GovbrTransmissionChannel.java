
package eprecise.efiscal4j.nfse.transmission.govbr.v203;

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
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrSOAPBody;
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrSOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope.GovbrSOAPHeader;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchAutorizedResponse;
import eprecise.efiscal4j.nfse.transmission.response.NFSeDispatchStateResponse;
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
            try {
                this.signer = new OasisSigner(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
    public TypedTransmissionResult<GovbrSOAPEnvelope, GovbrLotRpsDispatchSyncResponse> transmitAuthorization(final NFSeRequest nfseRequest,
            final String cityCode, final boolean homologation) throws Exception {

        final GovbrLotRpsDispatchSync lotRpsDispatch = (GovbrLotRpsDispatchSync) nfseRequest;

        final GovbrSOAPEnvelope soapEnvelope = GovbrSOAPEnvelope.builder().soapHeader(GovbrSOAPHeader.builder().build())
                .soapBody(GovbrSOAPBody.builder().transmissibleBody(lotRpsDispatch).build()).build().sign(signer);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper())
                .serialize();

        String responseXml = transmissor.transmit(requestXml, NFSeTransmissor.getUrl(cityCode, homologation));

        if (responseXml == null || responseXml != null && responseXml.isEmpty()) {
            throw new UnavailableServiceException();
        }

        responseXml = responseXml.substring(responseXml.indexOf("<EnviarLoteRpsSincronoResposta"),
                responseXml.lastIndexOf("</SOAP-ENV:Body>"));

        return new TypedTransmissionResult<>(GovbrSOAPEnvelope.class, GovbrLotRpsDispatchSyncResponse.class, requestXml, responseXml);
    }

    @Override
    public TypedTransmissionResult<GovbrSOAPEnvelope, GovbrNFSeDispatchCancelResponse> transmitCancellation(final NFSeRequest nfseRequest,
            final String cityCode, final boolean homologation) throws Exception {

        final GovbrNFSeDispatchCancel cancelDispatch = (GovbrNFSeDispatchCancel) nfseRequest;

        final GovbrSOAPEnvelope soapEnvelope = GovbrSOAPEnvelope.builder().soapHeader(GovbrSOAPHeader.builder().build())
                .soapBody(GovbrSOAPBody.builder().transmissibleBody(cancelDispatch).build()).build().sign(signer);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper())
                .serialize();

        String responseXml = transmissor.transmit(requestXml, NFSeTransmissor.getUrl(cityCode, homologation));

        if (responseXml == null || responseXml != null && responseXml.isEmpty()) {
            throw new UnavailableServiceException();
        }

        responseXml = responseXml.substring(responseXml.indexOf("<CancelarNfseResposta"), responseXml.lastIndexOf("</SOAP-ENV:Body>"));
        return new TypedTransmissionResult<>(GovbrSOAPEnvelope.class, GovbrNFSeDispatchCancelResponse.class, requestXml, responseXml);
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
