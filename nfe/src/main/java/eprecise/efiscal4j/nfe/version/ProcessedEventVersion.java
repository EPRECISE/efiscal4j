
package eprecise.efiscal4j.nfe.version;

import eprecise.efiscal4j.nfe.event.cancel.FiscalDocumentCancel;

public interface ProcessedEventVersion {

    FiscalDocumentCancel.Processed buildProcessedFiscalDocumentCancel();

}
