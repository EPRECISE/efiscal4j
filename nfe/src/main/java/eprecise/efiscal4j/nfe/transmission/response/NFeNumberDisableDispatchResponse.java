
package eprecise.efiscal4j.nfe.transmission.response;

import eprecise.efiscal4j.nfe.event.EventStatus;


public interface NFeNumberDisableDispatchResponse extends NFeResponse {

    EventStatus getStatus();

}
