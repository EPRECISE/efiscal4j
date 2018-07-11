
package eprecise.efiscal4j.nfe.v310.sharing.adapters.processedFiscalDocument;

import eprecise.efiscal4j.nfe.FiscalDocumentCancel;
import eprecise.efiscal4j.nfe.v310.sharing.EventProtocol;

public class ProcessedFiscalDocumentCancelAdapter {

	private final EventProtocol eventProtocol;

	public ProcessedFiscalDocumentCancelAdapter(final EventProtocol eventProtocol) {
		this.eventProtocol = eventProtocol;
	}

	public FiscalDocumentCancel.Processed buildProcessedFiscalDocumentCancel() {
		return null;
	}

}
