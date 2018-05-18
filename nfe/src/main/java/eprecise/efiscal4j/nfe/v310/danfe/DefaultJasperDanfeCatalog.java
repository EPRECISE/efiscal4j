
package eprecise.efiscal4j.nfe.v310.danfe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import eprecise.efiscal4j.nfe.v310.DANFEPrintFormat;


public class DefaultJasperDanfeCatalog implements JasperDanfeCatalog {

    private final Map<DANFEPrintFormat, String> danfeMap = new HashMap<DANFEPrintFormat, String>() {

        private static final long serialVersionUID = 1L;
        {
            this.put(DANFEPrintFormat.SEM_DANFE, "eprecise/efiscal4j/nfe/v310/danfe/retrato/danfe_retrato.jasper");
            this.put(DANFEPrintFormat.DANFE_SIMPLIFICADO, "eprecise/efiscal4j/nfe/v310/danfe/paisagem/danfe_paisagem.jasper");
            this.put(DANFEPrintFormat.DANFE_RETRATO, "eprecise/efiscal4j/nfe/v310/danfe/retrato/danfe_retrato.jasper");
            this.put(DANFEPrintFormat.DANFE_PAISAGEM, "eprecise/efiscal4j/nfe/v310/danfe/paisagem/danfe_paisagem.jasper");
            this.put(DANFEPrintFormat.DANFE_NFCE, "eprecise/efiscal4j/nfe/v310/nfce/danfe/danfe_nfce.jasper");
            this.put(DANFEPrintFormat.DANFE_NFCE_MENSAGEM_ELETRONICA, "eprecise/efiscal4j/nfe/v310/nfce/danfe/danfe_nfce.jasper");
        }
    };

    @Override
    public InputStream get(final DANFEPrintFormat printFormat) {
        if (!this.danfeMap.containsKey(printFormat)) {
            throw new IllegalArgumentException("Não existe formato padrão para " + printFormat);
        }
        try {
            final String file = this.danfeMap.get(printFormat);
            final URL resource = this.getClass().getClassLoader().getResource(file);
            if (resource != null) {
                return resource.openStream();
            } else {
                throw new FileNotFoundException(String.format("Arquivo %s para o formato de DANFE %s não foi encontrado", file, printFormat));
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
