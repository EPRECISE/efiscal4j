
package eprecise.efiscal4j.commons.utils;

import java.io.InputStream;


public class Certificate {

    private final InputStream certificate;

    private final String passphrase;

    private final String certificateStoreImpl;

    public Certificate(InputStream certificate, String passphrase) {
        this(certificate, passphrase, "PKCS12");
    }

    public Certificate(InputStream certificate, String passphrase, String keyStoreImpl) {
        this.certificate = certificate;
        this.passphrase = passphrase;
        this.certificateStoreImpl = keyStoreImpl;
    }

    public InputStream getCertificate() {
        return this.certificate;
    }

    public String getPassphrase() {
        return this.passphrase;
    }

    public String getCertificateStoreImpl() {
        return this.certificateStoreImpl;
    }
}
