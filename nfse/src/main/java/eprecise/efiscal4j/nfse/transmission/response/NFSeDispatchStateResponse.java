
package eprecise.efiscal4j.nfse.transmission.response;

import eprecise.efiscal4j.nfse.transmission.response.state.NFSeConsultState;


public interface NFSeDispatchStateResponse extends NFSeResponse {

    NFSeConsultState getState();

}
