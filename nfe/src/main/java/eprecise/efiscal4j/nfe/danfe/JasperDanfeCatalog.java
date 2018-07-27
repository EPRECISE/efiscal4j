
package eprecise.efiscal4j.nfe.danfe;

import java.io.InputStream;

import eprecise.efiscal4j.nfe.version.DanfePrintFormatVersion;


public interface JasperDanfeCatalog {

    InputStream get(DanfePrintFormatVersion printFormat);

}
