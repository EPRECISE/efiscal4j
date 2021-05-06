
package eprecise.efiscal4j.nfe.transmission.response;

import eprecise.efiscal4j.nfe.event.EventStatus;


public interface NFeAuthorizationResponse extends NFeResponse {

    EventStatus getStatus();

    String getProtocol();

}
