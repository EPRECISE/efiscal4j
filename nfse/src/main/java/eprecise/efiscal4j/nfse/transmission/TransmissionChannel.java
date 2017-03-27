
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.transmissor.Transmissor;


/**
 * 
 * @author Fernando C Glizt
 *
 */
public class TransmissionChannel {

    private final Transmissor transmissor;

    public TransmissionChannel(final Certificate certificate) {
        transmissor = new Transmissor(certificate);
    }

}
