
package eprecise.efiscal4j.nfse.danfe;

import java.util.HashMap;
import java.util.Map;

import eprecise.efiscal4j.nfse.tc.elotech.compNfse.ElotechCompNFSe;


public class DefaultJasperDanfeNFSeParamsSource implements JasperDanfeNFSeParamsSource {

    @Override
    public Map<String, Object> getParamsOf(final ElotechCompNFSe nfse) {
        final Map<String, Object> params = new HashMap<>();
        return params;
    }

}
