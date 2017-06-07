
package eprecise.efiscal4j.nfse.danfe;

import java.util.Map;

import eprecise.efiscal4j.nfse.domain.comp.CompNFSe;


public interface JasperDanfeNFSeParamsSource {

    Map<String, Object> getParamsOf(CompNFSe nfse);

}
