
package eprecise.efiscal4j.nfe.danfe;

import java.util.Map;

import eprecise.efiscal4j.nfe.version.ProcessedNFeVersion;


public interface JasperDanfeParamsSource {

    Map<String, Object> getParamsOf(ProcessedNFeVersion nfe);

}
