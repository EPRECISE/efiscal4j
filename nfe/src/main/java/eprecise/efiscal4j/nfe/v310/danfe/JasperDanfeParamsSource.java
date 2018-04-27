
package eprecise.efiscal4j.nfe.v310.danfe;

import java.util.Map;

import eprecise.efiscal4j.nfe.v310.sharing.ProcessedNFe;


public interface JasperDanfeParamsSource {

    Map<String, Object> getParamsOf(ProcessedNFe nfe);

}
