
package eprecise.efiscal4j.nfe.danfe;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import eprecise.efiscal4j.nfe.version.DanfePrintFormatVersion;


public interface JasperDanfeCatalog {

    InputStream get(DanfePrintFormatVersion printFormat);

    default Map<String, InputStream> getDetails(final DanfePrintFormatVersion printFormat) {
        return new HashMap<>();
    }

}
