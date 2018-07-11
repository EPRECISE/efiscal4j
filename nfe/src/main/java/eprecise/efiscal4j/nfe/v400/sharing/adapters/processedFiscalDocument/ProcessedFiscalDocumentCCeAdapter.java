
package eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument;

import eprecise.efiscal4j.nfe.FiscalDocumentCCe;
import eprecise.efiscal4j.nfe.v400.sharing.EventProtocol;

public class ProcessedFiscalDocumentCCeAdapter {

	private final EventProtocol eventProtocol;

	public ProcessedFiscalDocumentCCeAdapter(final EventProtocol eventProtocol) {
		this.eventProtocol = eventProtocol;
	}

	public FiscalDocumentCCe.Processed buildProcessedFiscalDocumentCCe() {
		return null;
	}

}
