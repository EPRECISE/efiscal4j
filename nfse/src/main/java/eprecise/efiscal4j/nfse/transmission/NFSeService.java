
package eprecise.efiscal4j.nfse.transmission;

import java.util.Arrays;
import java.util.Optional;

import eprecise.efiscal4j.commons.properties.PropertiesLoader;
import eprecise.efiscal4j.commons.utils.Certificate;


public enum NFSeService {

                         ELOTECH(ElotechTransmissionChannel.class, "/eprecise/efiscal4j/nfse/transmission/elotechServices.properties");

    private final PropertiesLoader nfseServiceMap;

    private final Class<? extends TransmissionChannel> transmissionChannelClass;

    private NFSeService(final Class<? extends TransmissionChannel> transmissionChannelClass, final String serviceProperty) {
        nfseServiceMap = new PropertiesLoader.Builder().resourceLoader(NFSeService.class).from(serviceProperty).create();
        this.transmissionChannelClass = transmissionChannelClass;
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

    public TransmissionChannel geTransmissionChannel(final String cityCode, final Certificate certificate) {

    }

}
