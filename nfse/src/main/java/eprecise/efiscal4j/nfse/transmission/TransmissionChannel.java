
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 *
 * @author Fernando C Glizt
 *
 */
public class TransmissionChannel {

    private final Transmissor transmissor;

    public TransmissionChannel() {
        transmissor = null;
    }

    public TransmissionChannel(final Certificate certificate) {
        transmissor = new Transmissor(certificate);
    }

    public void transmitAuthorization(final LotRpsDispatch lotRpsDispatch) {

        final SOAPEnvelope soapEnvelope = new SOAPEnvelope.Builder().withSoapHeader(new SOAPHeader.Builder().build()).withSoapBody(new SOAPBody.Builder().withTransmissibleBody(lotRpsDispatch).build())
                .build();

    }

}
