
package eprecise.efiscal4j.transmissor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.utils.Certificate;


/**
 *
 * @author Felipe Bueno
 *
 */
public class Transmissor {

    private final Logger logger = LoggerFactory.getLogger(Transmissor.class);

    private KeyStore keyStore;

    private KeyManagerFactory keyManagerFactory;

    private KeyStore trustStore;

    private TrustManagerFactory trustManagerFactory;

    private SSLContext sslContext;

    private String protocol = "SSL";

    public Transmissor() {
    }

    public Transmissor(final Certificate keyCertificate, final Certificate trustCertificate) {
        this.init(keyCertificate, trustCertificate);
    }

    public Transmissor(final Certificate keyCertificate) {
        this(keyCertificate, "SSL");
    }

    public Transmissor(final Certificate keyCertificate, final String protocol) {
        this.protocol = protocol;
        this.init(keyCertificate, new Certificate(() -> Transmissor.class.getResourceAsStream("/eprecise/efiscal4j/transmissor/NFeCacerts.jks"), "", "JKS"));
    }

    private void init(final Certificate keyCertificate, final Certificate trustCertificate) {
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        this.initializeKeyStore(keyCertificate);
        this.initializeTrustStore(trustCertificate);
        this.initializeSSLContext(this.protocol);
    }

    private void initializeKeyStore(final Certificate certificate) {
        try {
            this.keyStore = KeyStore.getInstance(certificate.getCertificateStoreImpl());
            this.keyStore.load(certificate.getCertificate(), certificate.getPassphrase().toCharArray());
            this.keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            this.keyManagerFactory.init(this.keyStore, certificate.getPassphrase().toCharArray());
        } catch (final Exception ex) {
            this.getLogger().error("Erro ao inicializar certificado PKCS", ex);
            throw new RuntimeException(ex);
        }
    }

    private void initializeTrustStore(final Certificate certificate) {
        try {
            this.trustStore = KeyStore.getInstance(certificate.getCertificateStoreImpl());
            this.trustStore.load(certificate.getCertificate(), null);
            this.trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            this.trustManagerFactory.init(this.trustStore);
        } catch (final Exception ex) {
            this.getLogger().error("Erro ao inicializar certificado JKS", ex);
            throw new RuntimeException(ex);
        }
    }

    private void initializeSSLContext(final String protocol) {
        try {
            this.sslContext = SSLContext.getInstance(protocol);
            this.sslContext.init(this.keyManagerFactory.getKeyManagers(), this.trustManagerFactory.getTrustManagers(), null);
        } catch (final Exception ex) {
            this.getLogger().error("Erro ao inicializar contexto SSL", ex);
            throw new RuntimeException(ex);
        }
    }

    public String transmit(final String requestSoapEnvelope, final String serviceUrl) {
        return this.transmit(requestSoapEnvelope, serviceUrl, new HashMap<>());
    }

    public String transmit(final String requestSoapEnvelope, final String serviceUrl, final String action) {
        return this.transmit(requestSoapEnvelope, serviceUrl, action, new HashMap<>());
    }

    public String transmit(final String requestSoapEnvelope, final String serviceUrl, final Map<String, String> requestProperties) {
        return this.transmit(requestSoapEnvelope, serviceUrl, null, requestProperties);
    }

    public String transmit(final String requestSoapEnvelope, final String serviceUrl, final String action, final Map<String, String> requestProperties) {

        try {
            final URL url = new URL(serviceUrl);
            final HttpURLConnection httpConnection;

            if (url.getProtocol().equalsIgnoreCase("HTTPS")) {
                httpConnection = (HttpsURLConnection) url.openConnection();
                ((HttpsURLConnection) httpConnection).setSSLSocketFactory(this.sslContext.getSocketFactory());
            } else {
                httpConnection = (HttpURLConnection) url.openConnection();
            }

            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);
            httpConnection.setUseCaches(false);
            httpConnection.setRequestMethod("POST");

            if (action != null) {
                httpConnection.setRequestProperty("SOAPAction", action);
            }

            requestProperties.forEach((key, value) -> {
                httpConnection.setRequestProperty(key, value);
            });

            this.logger.info("Protocol: " + this.protocol);
            this.logger.info("Action: " + action);

            final String actionProperty = Optional.ofNullable(action).map(it -> new StringBuilder("action=\"").append(it).append("\"")).map(StringBuilder::toString).orElse("");
            if (this.protocol.equals("SSL")) {
                httpConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8; ".concat(actionProperty));
            } else {
                httpConnection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8; ".concat(actionProperty));
            }
            httpConnection.connect();

            this.logger.info("Request: " + requestSoapEnvelope);

            this.sendRequest(httpConnection, requestSoapEnvelope);

            final String responseXml = this.getResponse(httpConnection);

            this.logger.info("Response: " + responseXml);

            return responseXml;
        } catch (final Exception ex) {
            this.logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }

    }

    private void sendRequest(final HttpURLConnection connection, final String envelopeXML) throws IOException {
        OutputStream out = null;
        Writer wout = null;
        try {
            out = connection.getOutputStream();
            wout = new OutputStreamWriter(out);
            wout.write(envelopeXML);
        } finally {
            if (wout != null) {
                wout.flush();
                wout.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    private String getResponse(final HttpURLConnection connection) throws IOException {
        final StringBuilder sb = new StringBuilder();
        InputStream in = null;
        BufferedReader br = null;
        BufferedReader bre = null;
        try {
            in = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (final IOException e) {
            if (connection.getErrorStream() != null) {
                bre = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String ln;
                System.out.println("\nERRO: ");
                while ((ln = bre.readLine()) != null) {
                    System.out.println(ln);
                }
            }
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
            if (in != null) {
                in.close();
            }
            if (bre != null) {
                bre.close();
            }
        }
        return sb.toString();
    }

    public Logger getLogger() {
        return this.logger;
    }
}
