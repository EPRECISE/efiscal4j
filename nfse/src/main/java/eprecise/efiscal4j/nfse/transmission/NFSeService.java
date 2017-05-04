
package eprecise.efiscal4j.nfse.transmission;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import eprecise.efiscal4j.commons.properties.PropertiesLoader;
import eprecise.efiscal4j.commons.utils.Certificate;


@SuppressWarnings("rawtypes")
public enum NFSeService {

                         ELOTECH(ElotechTransmissionChannel.class, "/eprecise/efiscal4j/nfse/transmission/serviceUrl/elotechServiceUrl.properties",
                                 "/eprecise/efiscal4j/nfse/transmission/serviceXmlns/elotechXmlns.properties", "4119905");

    private final Class<? extends TransmissionChannel> transmissionChannelClass;

    private final Collection<String> supportedCityCodes;

    private final PropertiesLoader nfseServiceUrlMap;

    private final PropertiesLoader nfseServiceXmlnsMap;

    private NFSeService(final Class<? extends TransmissionChannel> transmissionChannelClass, final String serviceUrlProperty, final String serviceXmlnsProperty, final String... supportedCityCodes) {
        this.transmissionChannelClass = transmissionChannelClass;
        this.supportedCityCodes = Arrays.asList(supportedCityCodes);
        nfseServiceUrlMap = new PropertiesLoader.Builder().resourceLoader(NFSeService.class).from(serviceUrlProperty).create();
        nfseServiceXmlnsMap = new PropertiesLoader.Builder().resourceLoader(NFSeService.class).from(serviceXmlnsProperty).create();
    }

    public String getServiceUrl(final String cityCode) {
        return nfseServiceUrlMap.valueFrom(cityCode);
    }

    public String getServiceXmlns(final String cityCode) {
        return nfseServiceXmlnsMap.valueFrom(cityCode);
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

    public static String getXmlns(final String cityCode) {
        for (final NFSeService service : Arrays.asList(NFSeService.values())) {
            final Optional<String> xmlns = Optional.ofNullable(service.getServiceXmlns(cityCode));
            if (xmlns.isPresent()) {
                return xmlns.get();
            }
        }
        return null;
    }

    public static TransmissionChannel getTransmissionChannel(final String cityCode, final Certificate certificate) {
        for (final NFSeService service : Arrays.asList(NFSeService.values())) {
            if (service.supportedCityCodes.contains(cityCode)) {
                try {
                    return service.transmissionChannelClass.getConstructor(Certificate.class).newInstance(certificate);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public Collection<String> getSupportedCityCodes() {
        return supportedCityCodes;
    }

}
