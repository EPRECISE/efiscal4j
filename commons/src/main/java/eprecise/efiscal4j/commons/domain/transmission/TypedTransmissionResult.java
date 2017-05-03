
package eprecise.efiscal4j.commons.domain.transmission;

import java.util.Optional;


/**
 *
 * @author Clécius J. Martinkoski
 *
 * @param <RQ>
 * @param <RP>
 */
public class TypedTransmissionResult<RQ, RP> extends TransmissionResult {

    private final Class<RQ> requestType;

    private final Class<RP> responseType;

    private Optional<RQ> request = Optional.empty();

    private Optional<RP> response = Optional.empty();

    public TypedTransmissionResult(final Class<RQ> requestType, final Class<RP> responseType, final String requestXml, final String responseXml) {
        super(requestXml, responseXml);
        this.requestType = requestType;
        this.responseType = responseType;

    }

    public RQ getRequest() {
        return this.request.orElseGet(() -> {
            this.request = Optional.of(this.getRequest(this.requestType));
            return this.request.get();
        });
    }

    public RP getResponse() {
        return this.response.orElseGet(() -> {
            this.response = Optional.of(this.getRespose(this.responseType));
            return this.response.get();
        });
    }

}
