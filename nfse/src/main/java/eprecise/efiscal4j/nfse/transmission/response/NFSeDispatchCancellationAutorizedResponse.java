
package eprecise.efiscal4j.nfse.transmission.response;

import eprecise.efiscal4j.nfse.tc.cancel.NFSeCancellationCode;


public interface NFSeDispatchCancellationAutorizedResponse extends NFSeResponse {

    NFSeCancellationCode getCancellationCode();

}
