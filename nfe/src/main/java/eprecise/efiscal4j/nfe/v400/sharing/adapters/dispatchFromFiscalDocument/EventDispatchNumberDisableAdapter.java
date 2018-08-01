
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocumentNumberDisable;
import eprecise.efiscal4j.nfe.v400.sharing.NFeNumberDisableDispatch;
import eprecise.efiscal4j.nfe.version.EventDispatchNumberDisableVersion;


public class EventDispatchNumberDisableAdapter implements EventDispatchNumberDisableVersion {

    private final FiscalDocumentNumberDisable fiscalDocumentNumberDisable;

    private final Certificate certificate;

    public EventDispatchNumberDisableAdapter(final FiscalDocumentNumberDisable fiscalDocumentNumberDisable, final Certificate certificate) {
        this.fiscalDocumentNumberDisable = fiscalDocumentNumberDisable;
        this.certificate = certificate;
    }

    @Override
    public NFeNumberDisableDispatch buildEventDispatchNumberDisable() {
        return null;
    }

}
