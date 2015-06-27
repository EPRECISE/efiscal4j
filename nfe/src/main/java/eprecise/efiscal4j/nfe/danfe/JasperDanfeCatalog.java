
package eprecise.efiscal4j.nfe.danfe;

import java.io.InputStream;

import eprecise.efiscal4j.nfe.DANFEPrintFormat;


public interface JasperDanfeCatalog {

    InputStream get(DANFEPrintFormat printFormat);

}
