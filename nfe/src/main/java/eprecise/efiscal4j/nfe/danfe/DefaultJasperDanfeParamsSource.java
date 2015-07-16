
package eprecise.efiscal4j.nfe.danfe;

import java.util.HashMap;
import java.util.Map;

import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;


public class DefaultJasperDanfeParamsSource implements JasperDanfeParamsSource {

    @Override
    public Map<String, Object> getParamsOf(ProcessedNFe nfe) {
        final Map<String, Object> params = new HashMap<>();
        switch (nfe.getNfe().getNFeInfo().getnFeIdentification().getFiscalDocumentModel()) {
        case NFCE:
            params.put(
                    "url_consulta_nfce",
                    NfceUrlPath.QUERY.getUrl(nfe.getNfe().getNFeInfo().getEmitter().getAdress().getCity().getUf(), nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo()
                            .getTransmissionEnvironment()));
            params.put("hash_qr_code", nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo().getAcessKey());
            break;
        default:
            break;
        }
        return params;
    }

}
