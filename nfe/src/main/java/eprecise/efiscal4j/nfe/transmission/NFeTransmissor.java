
package eprecise.efiscal4j.nfe.transmission;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import eprecise.efiscal4j.commons.utils.Certificate;


public enum NFeTransmissor {

                             V310(eprecise.efiscal4j.nfe.v310.transmission.TransmissionChannel.class, eprecise.efiscal4j.nfe.v310.transmission.ServiceDomain.values()),
                             V400(eprecise.efiscal4j.nfe.v400.transmission.TransmissionChannel.class, eprecise.efiscal4j.nfe.v400.transmission.ServiceDomain.values());

    private final Class<? extends NFeTransmissionChannel> transmissionChannelClass;

    private final Collection<NFeServiceDomain> supportedServiceDomains;

    private NFeTransmissor(final Class<? extends NFeTransmissionChannel> transmissionChannelClass, 
            final NFeServiceDomain... supportedServiceDomains) {
        this.transmissionChannelClass = transmissionChannelClass;
        this.supportedServiceDomains = Arrays.asList(supportedServiceDomains);
    }

    public NFeTransmissionChannel getTransmissionChannel(final Certificate certificate) {
        try {
            return transmissionChannelClass.getConstructor(Certificate.class).newInstance(certificate);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }


}
