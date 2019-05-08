
package eprecise.efiscal4j.nfe.v400.sharing.adapters.dispatchFromFiscalDocument;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfe.FiscalDocumentNumberDisable;
import eprecise.efiscal4j.nfe.v400.sharing.NFeNumberDisableDispatch;
import eprecise.efiscal4j.nfe.v400.sharing.NFeNumberDisableInfo;
import eprecise.efiscal4j.nfe.version.EventDispatchNumberDisableVersion;
import eprecise.efiscal4j.signer.defaults.DefaultSigner;


public class EventDispatchNumberDisableAdapter implements EventDispatchNumberDisableVersion {

    private final FiscalDocumentNumberDisable fiscalDocumentNumberDisable;

    private final Certificate certificate;

    public EventDispatchNumberDisableAdapter(final FiscalDocumentNumberDisable fiscalDocumentNumberDisable, final Certificate certificate) {
        this.fiscalDocumentNumberDisable = fiscalDocumentNumberDisable;
        this.certificate = certificate;
    }

    @Override
    public NFeNumberDisableDispatch buildEventDispatchNumberDisable() {
        try {
            return new NFeNumberDisableDispatch.Builder()
                    .withInfo(buildNFeInfo())
                    .build(new DefaultSigner(this.certificate));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private NFeNumberDisableInfo buildNFeInfo() {
        // TODO Auto-generated method stub
        return null;
    }

}
