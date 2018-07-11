
package eprecise.efiscal4j.nfe.version;

import eprecise.efiscal4j.nfe.FiscalDocumentCCe;
import eprecise.efiscal4j.nfe.FiscalDocumentCancel;

public interface ProcessedEventVersion {

    FiscalDocumentCancel.Processed buildProcessedFiscalDocumentCancel();
    
    FiscalDocumentCCe.Processed buildProcessedFiscalDocumentCCe();

}
