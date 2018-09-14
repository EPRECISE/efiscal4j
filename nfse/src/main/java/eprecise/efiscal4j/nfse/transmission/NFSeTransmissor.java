
package eprecise.efiscal4j.nfse.transmission;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import eprecise.efiscal4j.commons.properties.PropertiesLoader;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfse.transmission.elotech.ElotechTransmissionChannel;
import eprecise.efiscal4j.nfse.transmission.govbr.v100.GovbrTransmissionChannel;


public enum NFSeTransmissor {

                             ELOTECH(ElotechTransmissionChannel.class,
                                     "/eprecise/efiscal4j/nfse/transmission/production/elotechTransmissionProdUrl.properties",
                                     "/eprecise/efiscal4j/nfse/transmission/homologation/elotechTransmissionHomologUrl.properties",
                                     "4119905"),
                             GOVBR_V100(GovbrTransmissionChannel.class,
                                     "/eprecise/efiscal4j/nfse/transmission/production/govbrTransmissionProdUrl.properties",
                                     "/eprecise/efiscal4j/nfse/transmission/homologation/govbrTransmissionHomologUrl.properties", "123"),
                             GOVBR_V203(eprecise.efiscal4j.nfse.transmission.govbr.v203.GovbrTransmissionChannel.class,
                                     "/eprecise/efiscal4j/nfse/transmission/production/govbrTransmissionProdUrl.properties",
                                     "/eprecise/efiscal4j/nfse/transmission/homologation/govbrTransmissionHomologUrl.properties",
                                     "4118501");

    private final Class<? extends TransmissionChannel> transmissionChannelClass;

    private final Collection<String> supportedCityCodes;

    private final PropertiesLoader nfseTransmissionProdMap;

    private final PropertiesLoader nfseTransmissionHomologMap;

    private NFSeTransmissor(final Class<? extends TransmissionChannel> transmissionChannelClass, final String nfseTransmissionProdProperty,
            final String nfseTransmissionHomologProperty, final String... supportedCityCodes) {
        this.transmissionChannelClass = transmissionChannelClass;
        this.supportedCityCodes = Arrays.asList(supportedCityCodes);
        nfseTransmissionProdMap = new PropertiesLoader.Builder().resourceLoader(NFSeTransmissor.class).from(nfseTransmissionProdProperty)
                .create();
        nfseTransmissionHomologMap = new PropertiesLoader.Builder().resourceLoader(NFSeTransmissor.class)
                .from(nfseTransmissionHomologProperty).create();
    }

    public String getTransmissionUrl(final String cityCode, final boolean homolog) {
        if (homolog) {
            return nfseTransmissionHomologMap.valueFrom(cityCode);
        } else {
            return nfseTransmissionProdMap.valueFrom(cityCode);
        }
    }

    public static String getUrl(final String cityCode, final boolean homolog) {
        for (final NFSeTransmissor service : Arrays.asList(NFSeTransmissor.values())) {
            final Optional<String> url = Optional.ofNullable(service.getTransmissionUrl(cityCode, homolog));
            if (url.isPresent()) {
                return url.get();
            }
        }
        return null;
    }

    public TransmissionChannel getTransmissionChannel(final Certificate certificate) {
        try {
            return transmissionChannelClass.getConstructor(Certificate.class).newInstance(certificate);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static TransmissionChannel getTransmissionChannel(final String cityCode, final Certificate certificate) {
        for (final NFSeTransmissor transmissor : Arrays.asList(NFSeTransmissor.values())) {
            if (transmissor.supportedCityCodes.contains(cityCode)) {
                return transmissor.getTransmissionChannel(certificate);
            }
        }
        return null;
    }

    public Collection<String> getSupportedCityCodes() {
        return supportedCityCodes;
    }

}
