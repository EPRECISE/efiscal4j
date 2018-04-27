
package eprecise.efiscal4j.nfe.v310.danfe;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.properties.PropertiesLoader;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.transmission.NFeService;


public enum JasperDanfeNfceUrlPath {

    QUERY("/eprecise/efiscal4j/nfe/danfe/nfceUrlQueryHomolog.properties", "/eprecise/efiscal4j/nfe/danfe/nfceUrlQueryProduction.properties"),
    SERVICE("/eprecise/efiscal4j/nfe/danfe/nfceUrlServiceHomolog.properties", "/eprecise/efiscal4j/nfe/danfe/nfceUrlServiceProduction.properties");

    private final PropertiesLoader nfceUrlHomologMap;

    private final PropertiesLoader nfceUrlProductionMap;

    private JasperDanfeNfceUrlPath(String nfceUrlHomologPath, String nfceUrlProductionPath) {
        this.nfceUrlHomologMap = new PropertiesLoader.Builder().resourceLoader(NFeService.class).from(nfceUrlHomologPath).create();
        this.nfceUrlProductionMap = new PropertiesLoader.Builder().resourceLoader(NFeService.class).from(nfceUrlProductionPath).create();
    }

    public String getUrl(UF uf, TransmissionEnvironment environment) {
        switch (environment) {
        case HOMOLOGACAO:
            return this.nfceUrlHomologMap.valueFrom(uf.getAcronym(), "");
        case PRODUCAO:
            return this.nfceUrlProductionMap.valueFrom(uf.getAcronym(), "");
        }
        throw new UnsupportedOperationException(uf.getAcronym());
    }

}
