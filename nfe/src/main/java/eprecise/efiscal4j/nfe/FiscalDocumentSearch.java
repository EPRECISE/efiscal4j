
package eprecise.efiscal4j.nfe;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import eprecise.efiscal4j.commons.domain.transmission.TypedTransmissionResult;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.event.EventStatus;
import eprecise.efiscal4j.nfe.transmission.NFeTransmissionChannel;
import eprecise.efiscal4j.nfe.transmission.request.NFeBatchReceiptSearchRequest;
import eprecise.efiscal4j.nfe.transmission.response.NFeBatchReceiptSearchResponse;
import eprecise.efiscal4j.nfe.version.FiscalDocumentSupportedVersion;
import eprecise.efiscal4j.nfe.version.ProcessedNFeVersion;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class FiscalDocumentSearch {

    private final FiscalDocument.ReceiptedAsync receiptedAsync;

    /**
     * Transmite a consulta do documento fiscal
     *
     * @return resultado da transmissão
     */
    public FiscalDocumentSearch.TransmissionResult transmit(final Certificate certificate) {
        try {
            final FiscalDocumentSupportedVersion version = FiscalDocumentSupportedVersion.VERSION_4_00;
            final NFeBatchReceiptSearchRequest request = version.getBatchReceiptSearchClass().getConstructor(FiscalDocumentSearch.class, Certificate.class).newInstance(this, certificate)
                    .buildNFeBatchReceiptSearchRequest();
            final NFeTransmissionChannel transmissionChannel = version.getTransmissionChannelClass().getConstructor(Certificate.class).newInstance(certificate);
            final TypedTransmissionResult<? extends NFeBatchReceiptSearchRequest, ? extends NFeBatchReceiptSearchResponse> result = transmissionChannel.transmitBatchReceiptSearch(request,
                    this.receiptedAsync.getDocument().getEmitter().getAddress().getCity().getUf());
            return FiscalDocumentSearch.TransmissionResult.builder().version(version).document(this.receiptedAsync.getDocument()).certificate(certificate).result(result).build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Builder
    @Getter
    public static class TransmissionResult {

        private final FiscalDocumentSupportedVersion version;

        private final FiscalDocument document;

        private final Certificate certificate;

        private final TypedTransmissionResult<? extends NFeBatchReceiptSearchRequest, ? extends NFeBatchReceiptSearchResponse> result;

        public ProcessedNFeVersion getProcessedNFeVersion() {
            try {
                return this.version.getProcessedNFeClass().getConstructor(FiscalDocument.class, Certificate.class, this.result.getResponse().getClass()).newInstance(this.document, this.certificate,
                        this.result.getResponse());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }

        public FiscalDocument.Processed getProcessed() {
            return this.getProcessedNFeVersion().buildProcessedFiscalDocument();
        }

        public EventStatus getStatus() {
            return Optional.ofNullable(this.result).map(TypedTransmissionResult::getResponse).map(NFeBatchReceiptSearchResponse::getStatus).orElse(null);
        }
    }

}
