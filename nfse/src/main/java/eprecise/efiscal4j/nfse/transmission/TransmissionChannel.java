
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.transmission.response.NFSeResponse;


public interface TransmissionChannel {

    TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> transmitAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode, final boolean homologation)
            throws Exception;

    default TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> transmitAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode) throws Exception {
        return transmitAuthorization(transmissible, cityCode, false);
    }

    TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> consultAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode, final boolean homologation)
            throws Exception;

}
