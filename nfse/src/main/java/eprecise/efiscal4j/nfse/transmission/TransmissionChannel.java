
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.nfse.sharing.LotRpsDispatch;


public interface TransmissionChannel<RQ, RP> {

    TypedTransmissionResult<RQ, RP> transmitAuthorization(final LotRpsDispatch lotRpsDispatch) throws Exception;

}
