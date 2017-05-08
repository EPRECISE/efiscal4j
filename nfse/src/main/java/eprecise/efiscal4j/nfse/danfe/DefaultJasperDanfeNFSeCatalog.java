
package eprecise.efiscal4j.nfse.danfe;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class DefaultJasperDanfeNFSeCatalog implements JasperDanfeNFSeCatalog {

    private static final String DANFE_PATH = "eprecise/efiscal4j/nfse/danfe/retrato/danfe_retrato.jasper";

    @Override
    public InputStream get() {
        try {
            final URL resource = this.getClass().getClassLoader().getResource(DANFE_PATH);
            return resource.openStream();

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
