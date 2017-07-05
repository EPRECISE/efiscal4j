
package eprecise.efiscal4j.nfse.tc.cancel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


public class NFSeCancellationRequestData implements Serializable {

    private static final long serialVersionUID = 1L;

    private final @NotNull(message = "Número da NFS-e: o valor é necessário") String nfseNumber;

    private final NFSeCancellationCode cancellationCode;

    private final String accessKey;

    public static class Builder {

        private String nfseNumber;

        private NFSeCancellationCode cancellationCode;

        private String accessKey;

        public Builder withNfseNumber(final String nfseNumber) {
            this.nfseNumber = nfseNumber;
            return this;
        }

        public Builder withCancellationCode(final NFSeCancellationCode cancellationCode) {
            this.cancellationCode = cancellationCode;
            return this;
        }

        public Builder withAccessKey(final String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        public NFSeCancellationRequestData build() {
            final NFSeCancellationRequestData entity = new NFSeCancellationRequestData(this);
            return entity;
        }
    }

    public NFSeCancellationRequestData() {
        nfseNumber = null;
        cancellationCode = null;
        accessKey = null;
    }

    public NFSeCancellationRequestData(final Builder builder) {
        nfseNumber = builder.nfseNumber;
        cancellationCode = builder.cancellationCode;
        accessKey = builder.accessKey;
    }

    public String getNfseNumber() {
        return nfseNumber;
    }

    public NFSeCancellationCode getCancellationCode() {
        return cancellationCode;
    }

    public String getAccessKey() {
        return accessKey;
    }

}
