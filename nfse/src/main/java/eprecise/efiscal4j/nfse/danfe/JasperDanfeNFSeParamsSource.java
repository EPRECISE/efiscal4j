
package eprecise.efiscal4j.nfse.danfe;

import java.util.Map;

import eprecise.efiscal4j.nfse.tc.elotech.compNfse.ElotechCompNFSe;


public interface JasperDanfeNFSeParamsSource {

    Map<String, Object> getParamsOf(ElotechCompNFSe nfse);

}
