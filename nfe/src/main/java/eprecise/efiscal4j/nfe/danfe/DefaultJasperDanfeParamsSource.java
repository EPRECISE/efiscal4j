
package eprecise.efiscal4j.nfe.danfe;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eprecise.efiscal4j.nfe.danfe.nfce.CSC;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;


public class DefaultJasperDanfeParamsSource implements JasperDanfeParamsSource {

    @Override
    public Map<String, Object> getParamsOf(ProcessedNFe nfe, Optional<CSC> csc) {
        final Map<String, Object> params = new HashMap<>();
        switch (nfe.getNfe().getNFeInfo().getnFeIdentification().getFiscalDocumentModel()) {
        case NFCE:
            params.put(
                    "url_consulta_nfce",
                    JasperDanfeNfceUrlPath.QUERY.getUrl(nfe.getNfe().getNFeInfo().getEmitter().getAdress().getCity().getUf(), nfe.getProcessingStatusProtocol().getProcessingStatusProtocolInfo()
                            .getTransmissionEnvironment()));

            csc.ifPresent(c -> params.put("hash_qr_code", new JasperDanfeNfceQRCodeBuilder(nfe, c).build()));

            break;
        default:
            break;
        }
        return params;
    }

}
