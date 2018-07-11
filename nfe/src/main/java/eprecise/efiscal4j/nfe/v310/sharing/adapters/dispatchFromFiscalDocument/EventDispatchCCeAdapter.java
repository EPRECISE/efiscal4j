package eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.nfe.FiscalDocumentCCe;
import eprecise.efiscal4j.nfe.v310.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.version.EventDispatchCCeVersion;

public class EventDispatchCCeAdapter implements EventDispatchCCeVersion {

	private final FiscalDocumentCCe fiscalDocumentCCe;

	public EventDispatchCCeAdapter(FiscalDocumentCCe fiscalDocumentCCe) {
		this.fiscalDocumentCCe = fiscalDocumentCCe;
	}

	public EventDispatch buildEventDispatchCCe() {
		return null;
	}

}
