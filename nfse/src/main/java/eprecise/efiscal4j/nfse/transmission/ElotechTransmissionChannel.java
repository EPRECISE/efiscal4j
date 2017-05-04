
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatchResponse;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPBody;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPHeader;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.signer.oasis.OasisSigner;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Fernando C Glizt
 *
 */
public class ElotechTransmissionChannel implements TransmissionChannel {

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

    public TypedTransmissionResult<SOAPEnvelope, LotRpsDispatchResponse> transmitAuthorization(final LotRpsDispatch lotRpsDispatch) throws Exception {

        final String cityCode = lotRpsDispatch.getLotRps().getStatementProvisionServices().stream().findAny().orElseThrow(IllegalStateException::new).getInfo().getServiceProvider().getAddress()
                .getCityCode();

        lotRpsDispatch.setXmlns(NFSeService.getXmlns(cityCode));

        final SOAPEnvelope soapEnvelope = new SOAPEnvelope.Builder().withSoapHeader(new SOAPHeader.Builder().build()).withSoapBody(new SOAPBody.Builder().withTransmissibleBody(lotRpsDispatch).build())
                .build(signer);

        ((LotRpsDispatch) soapEnvelope.getSoapBody().getTransmissibleBody()).setXmlns(NFSeService.getXmlns(cityCode));

        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).withNamespacePrefixMapper(new OasisNamespacesPrefixMapper()).serialize();

        String responseXml = transmissor.transmit(requestXml, NFSeService.getUrl(cityCode));

        responseXml = responseXml.substring(responseXml.indexOf("<EnviarLoteRpsSincronoResposta"), responseXml.lastIndexOf("</SOAP-ENV:Body>"));

        return new TypedTransmissionResult<>(SOAPEnvelope.class, LotRpsDispatchResponse.class, requestXml, responseXml);

    }

}
