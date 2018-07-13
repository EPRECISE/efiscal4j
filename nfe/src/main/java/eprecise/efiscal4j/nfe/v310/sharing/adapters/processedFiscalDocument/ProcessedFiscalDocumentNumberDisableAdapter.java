
package eprecise.efiscal4j.nfe.v310.sharing.adapters.processedFiscalDocument;

import eprecise.efiscal4j.nfe.FiscalDocumentNumberDisable;
import eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFeNumberDisable;

public class ProcessedFiscalDocumentNumberDisableAdapter {

	private final ProcessedNFeNumberDisable processedNFeNumberDisable;

	public ProcessedFiscalDocumentNumberDisableAdapter(final ProcessedNFeNumberDisable processedNFeNumberDisable) {
		this.processedNFeNumberDisable = processedNFeNumberDisable;
	}

	public FiscalDocumentNumberDisable.Processed buildProcessedFiscalDocumentNumberDisable() {
		return null;
	}

}
