
package eprecise.efiscal4j.nfse.domain.adapters;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfse.domain.NFSe;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;
import eprecise.efiscal4j.nfse.transmission.request.NFSeRequest;


public interface NFSeDomainAdapter {

    public static enum NFSeAdapter {
                                    ELOTECH(ElotechNFSeDomainAdapter.class, NFSeTransmissor.ELOTECH.getSupportedCityCodes()),
                                    GOVBR(GovbrNFSeDomainAdapter.class, NFSeTransmissor.GOVBR.getSupportedCityCodes());

        private final Collection<String> supportedCityCodes;

        private final Class<? extends NFSeDomainAdapter> nfseAdapterClass;

        private NFSeAdapter(final Class<? extends NFSeDomainAdapter> nfseAdapterClass, final Collection<String> supportedCityCodes) {
            this.nfseAdapterClass = nfseAdapterClass;
            this.supportedCityCodes = supportedCityCodes;
        }

        public Collection<String> getSupportedCityCodes() {
            return supportedCityCodes;
        }

        public Class<? extends NFSeDomainAdapter> getNfseAdapterClass() {
            return nfseAdapterClass;
        }

        public static Class<? extends NFSeDomainAdapter> findNFSeDomainAdapterBy(final String cityCode) {
            for (final NFSeAdapter adapter : NFSeAdapter.values()) {
                if (adapter.supportedCityCodes.contains(cityCode)) {
                    return adapter.getNfseAdapterClass();
                }
            }
            return null;
        }

        public static NFSeAdapter findAdapterBy(final String cityCode) {
            for (final NFSeAdapter adapter : NFSeAdapter.values()) {
                if (adapter.supportedCityCodes.contains(cityCode)) {
                    return adapter;
                }
            }
            return null;
        }
    }

    public static class Builder {

        private NFSe nfse;

        private Certificate certificate;

        public Builder withNFSe(final NFSe nfse) {
            this.nfse = nfse;
            return this;
        }

        public Builder withCertificate(final Certificate certificate) {
            this.certificate = certificate;
            return this;
        }

        public NFSeDomainAdapter build() {
            final String cityCode = nfse.getService().getCityService().getIbgeCode();
            try {
                return NFSeAdapter.findNFSeDomainAdapterBy(cityCode).getConstructor(Builder.class).newInstance(this);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }

        public NFSe getNfse() {
            return nfse;
        }

        public Certificate getCertificate() {
            return certificate;
        }

    }

    NFSeRequest toDispatch();

    NFSeRequest toDispatchConsult(final String protocol);

    NFSeRequest toDispatchConsultState(final String protocol);

}
