
package eprecise.efiscal4j.nfe.v310.danfe;

import java.io.InputStream;

import eprecise.efiscal4j.nfe.v310.DANFEPrintFormat;


public interface JasperDanfeCatalog {

    InputStream get(DANFEPrintFormat printFormat);

}
