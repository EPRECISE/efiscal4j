
package eprecise.efiscal4j.nfe.danfe;

import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.properties.PropertiesLoader;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.transmission.NFeService;


public enum NfceUrlPath {

    QUERY("/eprecise/efiscal4j/nfe/danfe/nfceUrlQueryHomolog.properties", "/eprecise/efiscal4j/nfe/danfe/nfceUrlQueryProduction.properties"),
    SERVICE("/eprecise/efiscal4j/nfe/danfe/nfceUrlServiceHomolog.properties", "/eprecise/efiscal4j/nfe/danfe/nfceUrlServiceProduction.properties");

    private final PropertiesLoader nfceUrlHomologMap;

    private final PropertiesLoader nfceUrlProductionMap;

    private NfceUrlPath(String nfceUrlHomologPath, String nfceUrlProductionPath) {
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
