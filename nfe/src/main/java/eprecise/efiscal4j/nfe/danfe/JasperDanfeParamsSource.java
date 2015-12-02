
package eprecise.efiscal4j.nfe.danfe;

import java.util.Map;

import eprecise.efiscal4j.nfe.sharing.ProcessedNFe;


public interface JasperDanfeParamsSource {

    Map<String, Object> getParamsOf(ProcessedNFe nfe);

}
