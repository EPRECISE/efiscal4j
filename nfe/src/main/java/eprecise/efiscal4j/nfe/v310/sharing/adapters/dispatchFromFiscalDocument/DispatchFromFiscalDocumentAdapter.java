
package eprecise.efiscal4j.nfe.v310.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.v310.sharing.NFeDispatch;
import eprecise.efiscal4j.nfe.version.NFeDispatchAdapterVersion;


public class DispatchFromFiscalDocumentAdapter implements NFeDispatchAdapterVersion{

    private final FiscalDocument fiscalDocument;

    public DispatchFromFiscalDocumentAdapter(FiscalDocument fiscalDocument) {
        this.fiscalDocument = fiscalDocument;
    }

    public NFeDispatch buildNFeDispatch() {

        return null;
    }

}
