
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocumentSearch;
import eprecise.efiscal4j.nfe.transmission.request.NFeBatchReceiptSearchRequest;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.version.NFeDispatchSearchAdapterVersion;


public class BatchReceiptSearchAdapter implements NFeDispatchSearchAdapterVersion {

    private final FiscalDocumentSearch fiscalDocumentSearch;

    public BatchReceiptSearchAdapter(final FiscalDocumentSearch fiscalDocumentSearch, final Certificate certificate) {
        this.fiscalDocumentSearch = fiscalDocumentSearch;
    }

    @Override
    public NFeBatchReceiptSearchRequest buildNFeBatchReceiptSearchRequest() {
     // @formatter:off
        try {
            return new BatchReceiptSearch.Builder()
                    .withReceiptNumber(this.fiscalDocumentSearch.getReceiptedAsync().getReceiptNumber())
                    .withTransmissionEnvironment(TransmissionEnvironment.findBy(this.fiscalDocumentSearch.getReceiptedAsync().getDocument().getSerie().getEnvironment().getValue()).orElseThrow(IllegalStateException::new))
                    .build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
     // @formatter:on
    }

}
