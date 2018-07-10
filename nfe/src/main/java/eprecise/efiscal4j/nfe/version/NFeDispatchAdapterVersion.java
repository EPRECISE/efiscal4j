package eprecise.efiscal4j.nfe.version;

import eprecise.efiscal4j.nfe.transmission.request.NFeAuthorizationRequest;

public interface NFeDispatchAdapterVersion {
	
	NFeAuthorizationRequest buildNFeDispatch();

}
