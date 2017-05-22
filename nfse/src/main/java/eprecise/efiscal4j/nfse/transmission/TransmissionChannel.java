
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.nfse.tc.elotech.services.dispatch.LotRpsDispatchSync;


public interface TransmissionChannel<RQ, RP> {

    TypedTransmissionResult<RQ, RP> transmitAuthorization(final LotRpsDispatchSync lotRpsDispatch) throws Exception;

}
