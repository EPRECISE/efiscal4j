
package eprecise.efiscal4j.nfse.transmission;

import eprecise.efiscal4j.commons.domain.transmission.Receivable;
import eprecise.efiscal4j.commons.domain.transmission.Transmissible;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;


public interface TransmissionChannel {

    TypedTransmissionResult<? extends Transmissible, ? extends Receivable> transmitAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode, final boolean homologation)
            throws Exception;

    default TypedTransmissionResult<? extends Transmissible, ? extends Receivable> transmitAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode) throws Exception {
        return transmitAuthorization(transmissible, cityCode, false);
    }

    TypedTransmissionResult<? extends Transmissible, ? extends Receivable> consultAuthorization(final TransmissibleBodyImpl transmissible, final String cityCode, final boolean homologation)
            throws Exception;

}
