
package eprecise.efiscal4j.nfe.version;

import eprecise.efiscal4j.nfe.FiscalDocument;

public interface ReceiptedAsyncNFeVersion {

    FiscalDocument.ReceiptedAsync buildReceiptedAsyncNFe(final FiscalDocument fiscalDocument);

    String getReceiptNumber();

}
