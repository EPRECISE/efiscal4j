
package eprecise.efiscal4j.nfse.transmission.elotech;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSync;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.ElotechLotRpsDispatchSyncResponse;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.TransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPBody;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.elotech.envelope.SOAPHeader;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.signer.oasis.OasisSigner;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Fernando C Glizt
 *
 */
public class ElotechTransmissionChannel implements TransmissionChannel<SOAPEnvelope, ElotechLotRpsDispatchSyncResponse> {

    private final Transmissor transmissor;

    private final Signer signer;

    public ElotechTransmissionChannel() {
        transmissor = null;
        signer = null;
    }

    public ElotechTransmissionChannel(final Certificate certificate) {
        transmissor = new Transmissor(certificate);
        try {
            signer = new OasisSigner(certificate);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TypedTransmissionResult<SOAPEnvelope, ElotechLotRpsDispatchSyncResponse> transmitAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode, final boolean homologation)
            throws Exception {

        final ElotechLotRpsDispatchSync lotRpsDispatch = (ElotechLotRpsDispatchSync) transmissible;

        final SOAPEnvelope soapEnvelope = new SOAPEnvelope.Builder().withSoapHeader(new SOAPHeader.Builder().build()).withSoapBody(new SOAPBody.Builder().withTransmissibleBody(lotRpsDispatch).build())
                .build(signer);

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper()).serialize();

        String responseXml = transmissor.transmit(requestXml, NFSeTransmissor.getUrl(cityCode, lotRpsDispatch.getApplicant().isHomologation()));

        responseXml = responseXml.substring(responseXml.indexOf("<EnviarLoteRpsSincronoResposta"), responseXml.lastIndexOf("</SOAP-ENV:Body>"));

        return new TypedTransmissionResult<>(SOAPEnvelope.class, ElotechLotRpsDispatchSyncResponse.class, requestXml, responseXml);

    }

}
