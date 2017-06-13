
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

    public Transmissor() {
    }

    public Transmissor(final Certificate keyCertificate, final Certificate trustCertificate) {
        init(keyCertificate, trustCertificate);
    }

    public Transmissor(final Certificate keyCertificate) {
        this(keyCertificate, new Certificate(() -> Transmissor.class.getResourceAsStream("/eprecise/efiscal4j/transmissor/NFeCacerts.jks"), "", "JKS"));
    }

    private void init(final Certificate keyCertificate, final Certificate trustCertificate) {
        initializeKeyStore(keyCertificate);
        initializeTrustStore(trustCertificate);
        initializeSSLContext();
    }

    private void initializeKeyStore(final Certificate certificate) {
        try {
            keyStore = KeyStore.getInstance(certificate.getCertificateStoreImpl());
            keyStore.load(certificate.getCertificate(), certificate.getPassphrase().toCharArray());
            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, certificate.getPassphrase().toCharArray());
        } catch (final Exception ex) {
            getLogger().error("Erro ao inicializar certificado PKCS", ex);
            throw new RuntimeException(ex);
        }
    }

    private void initializeTrustStore(final Certificate certificate) {
        try {
            trustStore = KeyStore.getInstance(certificate.getCertificateStoreImpl());
            trustStore.load(certificate.getCertificate(), null);
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);
        } catch (final Exception ex) {
            getLogger().error("Erro ao inicializar certificado JKS", ex);
            throw new RuntimeException(ex);
        }
    }

    private void initializeSSLContext() {
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        } catch (final Exception ex) {
            getLogger().error("Erro ao inicializar contexto SSL", ex);
            throw new RuntimeException(ex);
        }
    }

    public String transmit(final String requestSoapEnvelope, final String serviceUrl) {

        System.out.println("Service url:\n" + serviceUrl);

        System.out.println("Request SOAP Envelope:\n" + requestSoapEnvelope);

        try {
            final URL url = new URL(serviceUrl);
            HttpURLConnection httpConnection = null;

            if (url.getProtocol().equalsIgnoreCase("HTTPS")) {
                httpConnection = (HttpsURLConnection) url.openConnection();
                ((HttpsURLConnection) httpConnection).setSSLSocketFactory(sslContext.getSocketFactory());
            } else {
                httpConnection = (HttpURLConnection) url.openConnection();
            }

            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);
            httpConnection.setUseCaches(false);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("SOAPAction", "http://tempuri.org/INFSEGeracao/RecepcionarLoteRps");
            httpConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConnection.connect();

            sendRequest(httpConnection, requestSoapEnvelope);

            final String responseXml = getResponse(httpConnection);

            System.out.println("Response xml:\n" + responseXml);

            return responseXml;
        } catch (final Exception ex) {
            logger.error(ex.getMessage(), ex);
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
        return logger;
    }
}
