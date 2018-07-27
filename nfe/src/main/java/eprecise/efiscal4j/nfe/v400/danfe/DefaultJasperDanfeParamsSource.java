
package eprecise.efiscal4j.nfe.v400.danfe;

import java.util.HashMap;
import java.util.Map;

import eprecise.efiscal4j.nfe.danfe.JasperDanfeParamsSource;
import eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFe;
import eprecise.efiscal4j.nfe.version.ProcessedNFeVersion;


public class DefaultJasperDanfeParamsSource implements JasperDanfeParamsSource {

    @Override
    public Map<String, Object> getParamsOf(final ProcessedNFeVersion nfeVersion) {
        final ProcessedNFe nfe = (ProcessedNFe) nfeVersion;
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
