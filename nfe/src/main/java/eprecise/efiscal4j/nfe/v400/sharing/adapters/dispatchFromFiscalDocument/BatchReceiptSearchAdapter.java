
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocumentSearchByReceipt;
import eprecise.efiscal4j.nfe.transmission.request.NFeBatchReceiptSearchRequest;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.sharing.BatchReceiptSearch;
import eprecise.efiscal4j.nfe.version.NFeBatchReceiptSearchAdapterVersion;


public class BatchReceiptSearchAdapter implements NFeBatchReceiptSearchAdapterVersion {

    private final FiscalDocumentSearchByReceipt fiscalDocumentSearch;

    public BatchReceiptSearchAdapter(final FiscalDocumentSearchByReceipt fiscalDocumentSearch, final Certificate certificate) {
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
