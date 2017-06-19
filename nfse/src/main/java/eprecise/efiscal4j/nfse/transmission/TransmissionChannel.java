
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;
import eprecise.efiscal4j.nfse.transmission.response.NFSeResponse;


public interface TransmissionChannel {

    TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> transmitAuthorization(final NFSeRequest nfseRequest, final String cityCode, final boolean homologation) throws Exception;

    TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> consultAuthorization(final NFSeRequest nfseRequest, final String cityCode, final boolean homologation) throws Exception;

    TypedTransmissionResult<? extends NFSeRequest, ? extends NFSeResponse> consultStateAuthorization(final NFSeRequest nfseRequest, final String cityCode, final boolean homologation) throws Exception;

}
