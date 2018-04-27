
package eprecise.efiscal4j.nfe.v400.danfe;

import java.io.InputStream;

import eprecise.efiscal4j.nfe.v400.DANFEPrintFormat;


public interface JasperDanfeCatalog {

    InputStream get(DANFEPrintFormat printFormat);

}
