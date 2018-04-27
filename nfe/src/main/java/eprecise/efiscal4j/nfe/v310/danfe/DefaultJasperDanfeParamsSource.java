
package eprecise.efiscal4j.nfe.v310.danfe;

import java.util.HashMap;
import java.util.Map;

import eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFe;


public class DefaultJasperDanfeParamsSource implements JasperDanfeParamsSource {

    @Override
    public Map<String, Object> getParamsOf(final ProcessedNFe nfe) {
        final Map<String, Object> params = new HashMap<>();
        switch (nfe.getNfe().getNFeInfo().getnFeIdentification().getFiscalDocumentModel()) {
        case NFCE:
            params.put("url_consulta_nfce", JasperDanfeNfceUrlPath.QUERY.getUrl(nfe.getNfe().getNFeInfo().getEmitter().getAdress().getCity().getUf(),
                    nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo().getTransmissionEnvironment()));

            break;
        default:
            break;
        }
        return params;
    }

}
