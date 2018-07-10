
package eprecise.efiscal4j.nfe.version;

import eprecise.efiscal4j.nfe.FiscalDocument;

public interface ProcessedNFeVersion {

    FiscalDocument.Processed buildProcessedFiscalDocument();
    
    String getXsdPath();

}
