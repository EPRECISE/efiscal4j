
package eprecise.efiscal4j.nfe.danfe;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import eprecise.efiscal4j.nfe.DANFEPrintFormat;


public class DefaultJasperDanfeCatalog implements JasperDanfeCatalog {

    private final Map<DANFEPrintFormat, String> danfeMap = new HashMap<DANFEPrintFormat, String>() {

        private static final long serialVersionUID = 1L;
        {
            this.put(DANFEPrintFormat.DANFE_SIMPLIFICADO, "eprecise/efiscal4j/nfe/danfe/simplified/simplified.jasper");
        }
    };

    @Override
    public InputStream get(DANFEPrintFormat printFormat) {
        if (!this.danfeMap.containsKey(printFormat)) {
            throw new IllegalArgumentException("Não existe formato padrão para " + printFormat);
        }
        try {
            return this.getClass().getClassLoader().getResource(this.danfeMap.get(printFormat)).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
