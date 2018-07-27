
package eprecise.efiscal4j.nfe.version;

import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.FiscalDocumentCCe;
import eprecise.efiscal4j.nfe.FiscalDocumentCancel;


public interface ProcessedEventVersion {

    FiscalDocumentCancel.Processed buildProcessedFiscalDocumentCancel(final FiscalDocument.Processed processedFiscalDocument);

    FiscalDocumentCCe.Processed buildProcessedFiscalDocumentCCe(final FiscalDocument.Processed processedFiscalDocument);

}
