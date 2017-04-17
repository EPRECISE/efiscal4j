
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;
import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;
import eprecise.efiscal4j.nfse.signer.NFSeSigner;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPBody;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPEnvelope;
import eprecise.efiscal4j.nfse.transmission.envelope.SOAPHeader;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Fernando C Glizt
 *
 */
public class TransmissionChannel {

    private final Transmissor transmissor;

    private Signer signer;

    public TransmissionChannel() {
        transmissor = null;
    }

    public TransmissionChannel(final NFSeSigner signer) {
        transmissor = new Transmissor(signer.getKeyCertificate());
        this.signer = signer;
    }

    public void transmitAuthorization(final LotRpsDispatch lotRpsDispatch) throws Exception {

        final SOAPEnvelope soapEnvelope = new SOAPEnvelope.Builder().withSoapHeader(new SOAPHeader.Builder().build()).withSoapBody(new SOAPBody.Builder().withTransmissibleBody(lotRpsDispatch).build())
                .build(signer);
        ValidationBuilder.from(soapEnvelope).validate().throwIfViolate();

        final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).withNamespacePrefixMapper(new NFSeNamespacesPrefixMapper()).serialize();

        final String responseXml = transmissor.transmit(requestXml, "http://200.195.154.239/WebEloWSIss/nfseService");

        System.out.println(responseXml);

    }

}
