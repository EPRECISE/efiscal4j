package eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.nfe.FiscalDocumentNumberDisable;
import eprecise.efiscal4j.nfe.v310.sharing.NFeNumberDisableDispatch;
import eprecise.efiscal4j.nfe.version.EventDispatchNumberDisableVersion;

public class EventDispatchNumberDisableAdapter implements EventDispatchNumberDisableVersion {

	private final FiscalDocumentNumberDisable fiscalDocumentNumberDisable;

	public EventDispatchNumberDisableAdapter(FiscalDocumentNumberDisable fiscalDocumentNumberDisable) {
		this.fiscalDocumentNumberDisable = fiscalDocumentNumberDisable;
	}

	public NFeNumberDisableDispatch buildEventDispatchNumberDisable() {
		return null;
	}

}
