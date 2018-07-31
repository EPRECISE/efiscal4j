
package eprecise.efiscal4j.nfe.version;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.nfe.FiscalDocument;


public interface ProcessedNFeVersion {

    FiscalDocument.Processed buildProcessedFiscalDocument();

    String getXsdPath();

    DanfePrintFormatVersion getDanfePrintFormat();

    FiscalDocumentVersion getVersion();

}
