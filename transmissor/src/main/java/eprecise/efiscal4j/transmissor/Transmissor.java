
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

    public Transmissor(Certificate keyCertificate, Certificate trustCertificate) {
        this.init(keyCertificate, trustCertificate);
    }

    public Transmissor(Certificate keyCertificate) {
        this(keyCertificate, new Certificate(() -> Transmissor.class.getResourceAsStream("/eprecise/efiscal4j/transmissor/NFeCacerts.jks"), "", "JKS"));
    }

    private void init(Certificate keyCertificate, Certificate trustCertificate) {
        this.initializeKeyStore(keyCertificate);
        this.initializeTrustStore(trustCertificate);
        this.initializeSSLContext();
    }

    private void initializeKeyStore(Certificate certificate) {
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

    private void initializeTrustStore(Certificate certificate) {
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

    private void initializeSSLContext() {
        try {
            this.sslContext = SSLContext.getInstance("SSL");
            this.sslContext.init(this.keyManagerFactory.getKeyManagers(), this.trustManagerFactory.getTrustManagers(), null);
        } catch (final Exception ex) {
            this.getLogger().error("Erro ao inicializar contexto SSL", ex);
            throw new RuntimeException(ex);
        }
    }

    public String transmit(String requestSoapEnvelope, String serviceUrl) {

        System.out.println("Service url:\n" + serviceUrl);

        System.out.println("Request SOAP Envelope:\n" + requestSoapEnvelope);

        try {
            final URL url = new URL(serviceUrl);
            HttpURLConnection httpConnection = null;

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
            httpConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConnection.connect();

            this.sendRequest(httpConnection, requestSoapEnvelope);

            final String responseXml = this.getResponse(httpConnection);

            System.out.println("Response xml:\n" + responseXml);

            return responseXml;
        } catch (final Exception ex) {
            this.logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    private void sendRequest(HttpURLConnection connection, String envelopeXML) throws IOException {
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

    private String getResponse(HttpURLConnection connection) throws IOException {
        final StringBuilder sb = new StringBuilder();
        InputStream in = null;
        BufferedReader br = null;
        try {
            in = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return sb.toString();
    }

    public Logger getLogger() {
        return this.logger;
    }
}
