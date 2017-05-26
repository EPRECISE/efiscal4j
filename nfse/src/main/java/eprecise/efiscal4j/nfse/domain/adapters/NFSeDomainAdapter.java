
package eprecise.efiscal4j.nfse.domain.adapters;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.nfse.domain.NFSe;
import eprecise.efiscal4j.nfse.transmission.NFSeTransmissor;


public interface NFSeDomainAdapter {

    public static enum NFSeAdapter {
                                    ELOTECH(ElotechNFSeDomainAdapter.class, NFSeTransmissor.ELOTECH.getSupportedCityCodes());

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

        public static Class<? extends NFSeDomainAdapter> findAdapterBy(final String cityCode) {
            for (final NFSeAdapter adapter : NFSeAdapter.values()) {
                if (adapter.supportedCityCodes.contains(cityCode)) {
                    return adapter.getNfseAdapterClass();
                }
            }
            return null;
        }
    }

    public static class Builder {

        private NFSe nfse;

        private Certificate certificate;

        private String number;

        private String lotNumber;

        public Builder withNFSe(final NFSe nfse) {
            this.nfse = nfse;
            return this;
        }

        public Builder withCertificate(final Certificate certificate) {
            this.certificate = certificate;
            return this;
        }

        public Builder withNumber(final String number) {
            this.number = number;
            return this;
        }

        public Builder withLotNumber(final String lotNumber) {
            this.lotNumber = lotNumber;
            return this;
        }

        public NFSeDomainAdapter build() {
            final String cityCode = nfse.getService().getCityService().getIbgeCode();
            try {
                return NFSeAdapter.findAdapterBy(cityCode).getConstructor(Builder.class).newInstance(this);
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

        public String getNumber() {
            return number;
        }

        public String getLotNumber() {
            return lotNumber;
        }

    }

    TransmissibleBodyImpl toTransmissible();

}
