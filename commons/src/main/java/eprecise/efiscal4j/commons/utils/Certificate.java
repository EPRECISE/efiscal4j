
package eprecise.efiscal4j.commons.utils;

import java.io.IOException;
import java.io.InputStream;


public class Certificate {

    private final InputStreamSource certificate;

    private final String passphrase;

    private final String certificateStoreImpl;

    public Certificate(InputStreamSource certificate, String passphrase) {
        this(certificate, passphrase, "PKCS12");
    }

    public Certificate(InputStreamSource certificate, String passphrase, String keyStoreImpl) {
        this.certificate = certificate;
        this.passphrase = passphrase;
        this.certificateStoreImpl = keyStoreImpl;
    }

    public InputStream getCertificate() {
        try {
            return this.certificate.get();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPassphrase() {
        return this.passphrase;
    }

    public String getCertificateStoreImpl() {
        return this.certificateStoreImpl;
    }

    public interface InputStreamSource {

        public InputStream get() throws IOException;

    }
}
