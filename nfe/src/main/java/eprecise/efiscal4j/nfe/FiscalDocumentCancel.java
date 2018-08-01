
package eprecise.efiscal4j.nfe;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Optional;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.transmission.NFeTransmissionChannel;
import eprecise.efiscal4j.nfe.transmission.request.NFeEventDispatchRequest;
import eprecise.efiscal4j.nfe.transmission.response.NFeEventDispatchResponse;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;
import eprecise.efiscal4j.nfe.version.ProcessedEventVersion;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class FiscalDocumentCancel {

    private final String justification;

    private final FiscalDocument.Processed processedFiscalDocument;

    /**
     * Transmite o cancelamento do documento fiscal
     * 
     * @return resultado da transmissão
     */
    public FiscalDocumentCancel.TransmissionResult transmit(final Certificate certificate) {
        try {
            final FiscalDocumentSupportedVersion version = this.processedFiscalDocument.getVersion();
            final NFeEventDispatchRequest request = version.getEventDispatchCancelClass().getConstructor(FiscalDocumentCancel.class, Certificate.class).newInstance(this, certificate)
                    .buildEventDispatchCancel();
            final NFeTransmissionChannel transmissionChannel = version.getTransmissionChannelClass().getConstructor(Certificate.class).newInstance(certificate);
            final TypedTransmissionResult<? extends NFeEventDispatchRequest, ? extends NFeEventDispatchResponse> result = transmissionChannel.transmitEventReceptionCancellation(request,
                    this.processedFiscalDocument.getDocument().getModel());
            return FiscalDocumentCancel.TransmissionResult.builder().processedFiscalDocument(this.processedFiscalDocument).version(version).result(result).build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Builder
    @Getter
    public static class Processed {

        private final String id;

        private final Date date;

        private final String protocolNumber;

        private final String details;

        private final EventStatus status;

        private final FiscalDocumentCancel document;

        private final ProcessedEventVersion processedEventVersion;

    }

    @Builder
    @Getter
    public static class TransmissionResult {

        private final FiscalDocument.Processed processedFiscalDocument;

        private final FiscalDocumentSupportedVersion version;

        private final TypedTransmissionResult<? extends NFeEventDispatchRequest, ? extends NFeEventDispatchResponse> result;

        public ProcessedEventVersion getProcessedEventVersion() {
            try {
                return this.version.getProcessedEventClass().getConstructor(NFeEventDispatchRequest.class, NFeEventDispatchResponse.class).newInstance(this.result.getRequest(),
                        this.result.getResponse());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }

        public FiscalDocumentCancel.Processed getProcessed() {
            return this.getProcessedEventVersion().buildProcessedFiscalDocumentCancel(this.processedFiscalDocument);
        }

        public EventStatus getStatus() {
            return Optional.ofNullable(this.result).map(r -> r.getResponse()).map(rp -> rp.getStatus()).orElse(null);
        }
    }

}
