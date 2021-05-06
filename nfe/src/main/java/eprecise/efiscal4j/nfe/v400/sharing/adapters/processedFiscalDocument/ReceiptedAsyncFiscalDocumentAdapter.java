
package eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument;

import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.v400.sharing.BatchReceipt;


public class ReceiptedAsyncFiscalDocumentAdapter {

    private final BatchReceipt batchReceipt;

    private final FiscalDocument document;

    public ReceiptedAsyncFiscalDocumentAdapter(final BatchReceipt batchReceipt, final FiscalDocument document) {
        this.batchReceipt = batchReceipt;
        this.document = document;
    }

    public FiscalDocument.ReceiptedAsync buildReceiptedAsyncNFe() {
     // @formatter:off
        try {
            return FiscalDocument.ReceiptedAsync.builder()
                    .receiptNumber(this.batchReceipt.getReceiptNumber())
                    .averageTime(this.batchReceipt.getAverageTime())
                    .document(this.document)
                    .build();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
     // @formatter:on
    }

}
