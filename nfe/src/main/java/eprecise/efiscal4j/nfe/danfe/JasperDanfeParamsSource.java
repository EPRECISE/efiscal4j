
package eprecise.efiscal4j.nfe.danfe;

import java.util.Map;
import java.util.Optional;

import eprecise.efiscal4j.nfe.danfe.nfce.CSC;
import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;


public interface JasperDanfeParamsSource {

    Map<String, Object> getParamsOf(ProcessedNFe nfe, Optional<CSC> csc);

}
