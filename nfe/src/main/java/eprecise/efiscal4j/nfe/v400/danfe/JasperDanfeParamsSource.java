
package eprecise.efiscal4j.nfe.v400.danfe;

import java.util.Map;

import eprecise.efiscal4j.nfe.v400.sharing.ProcessedNFe;


public interface JasperDanfeParamsSource {

    Map<String, Object> getParamsOf(ProcessedNFe nfe);

}
