
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;


public interface TransmissionChannel<RQ, RP> {

    TypedTransmissionResult<RQ, RP> transmitAuthorization(final TransmissibleBodyImpl transmissible) throws Exception;

}
