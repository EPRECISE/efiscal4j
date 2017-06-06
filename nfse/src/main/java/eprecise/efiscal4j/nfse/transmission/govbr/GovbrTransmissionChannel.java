
package eprecise.efiscal4j.nfse.transmission.govbr;

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
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.oasis.OasisSigner;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Fernando C Glizt
 *
 */
public class GovbrTransmissionChannel implements TransmissionChannel {

    private final Transmissor transmissor;

    private final Signer signer;

    public GovbrTransmissionChannel() {
        transmissor = null;
        signer = null;
    }

    public GovbrTransmissionChannel(final Certificate certificate) {
        transmissor = new Transmissor(certificate);
        try {
            signer = new OasisSigner(certificate);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TypedTransmissionResult<GovbrLotRpsDispatchAsync, GovbrLotRpsDispatchAsyncResponse> transmitAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode,
            final boolean homologation) throws Exception {

        final GovbrLotRpsDispatchAsync lotRpsDispatch = (GovbrLotRpsDispatchAsync) transmissible;

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();

        final String responseXml = transmissor.transmit(requestXml, NFSeTransmissor.getUrl(cityCode, homologation));

        return new TypedTransmissionResult<>(GovbrLotRpsDispatchAsync.class, GovbrLotRpsDispatchAsyncResponse.class, requestXml, responseXml);

    }

    @Override
    public TypedTransmissionResult<GovbrLotRpsDispatchConsult, GovbrLotRpsDispatchConsultResponse> consultAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode,
            final boolean homologation) throws Exception {
        final GovbrLotRpsDispatchConsult lotRpsDispatch = (GovbrLotRpsDispatchConsult) transmissible;

        final String requestXml = new FiscalDocumentSerializer<>(lotRpsDispatch).serialize();

        final String responseXml = transmissor.transmit(requestXml, NFSeTransmissor.getUrl(cityCode, homologation));

        return new TypedTransmissionResult<>(GovbrLotRpsDispatchConsult.class, GovbrLotRpsDispatchConsultResponse.class, requestXml, responseXml);
    }

}
