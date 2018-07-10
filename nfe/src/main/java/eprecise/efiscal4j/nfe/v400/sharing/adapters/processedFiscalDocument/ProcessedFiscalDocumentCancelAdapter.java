
package eprecise.efiscal4j.nfe.v400.sharing.adapters.processedFiscalDocument;

import eprecise.efiscal4j.nfe.event.cancel.FiscalDocumentCancel;
import eprecise.efiscal4j.nfe.v400.sharing.EventProtocol;

public class ProcessedFiscalDocumentCancelAdapter {

	private final EventProtocol eventProtocol;

	public ProcessedFiscalDocumentCancelAdapter(final EventProtocol eventProtocol) {
		this.eventProtocol = eventProtocol;
	}

	public FiscalDocumentCancel.Processed buildProcessedFiscalDocumentCancel() {
		return null;
	}

}
