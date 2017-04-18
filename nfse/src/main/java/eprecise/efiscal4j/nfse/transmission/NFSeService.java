
package eprecise.efiscal4j.nfse.transmission;

import java.util.Arrays;
import java.util.Optional;

import eprecise.efiscal4j.commons.properties.PropertiesLoader;


public enum NFSeService {

                         ELOTECH("/eprecise/efiscal4j/nfse/transmission/elotechServices.properties");

    private final PropertiesLoader nfseServiceMap;

    private NFSeService(final String serviceProperty) {
        nfseServiceMap = new PropertiesLoader.Builder().resourceLoader(NFSeService.class).from(serviceProperty).create();
    }

    public String getServiceUrl(final String cityCode) {
        return nfseServiceMap.valueFrom(cityCode);
    }

    public static String getUrl(final String cityCode) {
        for (final NFSeService service : Arrays.asList(NFSeService.values())) {
            final Optional<String> url = Optional.ofNullable(service.getServiceUrl(cityCode));
            if (url.isPresent()) {
                return url.get();
            }
        }
        return null;
    }

}
