package eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.nfe.event.cancel.FiscalDocumentCancel;
import eprecise.efiscal4j.nfe.v310.sharing.EventDispatch;
import eprecise.efiscal4j.nfe.version.EventDispatchCancelVersion;

public class EventDispatchCancelAdapter implements EventDispatchCancelVersion{

	private final FiscalDocumentCancel fiscalDocumentCancel;

	public EventDispatchCancelAdapter(FiscalDocumentCancel fiscalDocumentCancel) {
		this.fiscalDocumentCancel = fiscalDocumentCancel;
	}

	public EventDispatch buildEventDispatchCancel() {
		return null;
	}

}
