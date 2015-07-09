
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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eprecise.efiscal4j.commons.utils.Certificate;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;


public class Transmissor {

    private final Logger log = LoggerFactory.getLogger(Transmissor.class);

    private KeyStore keyStore;

    private KeyManagerFactory keyManagerFactory;

    private KeyStore trustStore;

    private TrustManagerFactory trustManagerFactory;

    private SSLContext sslContext;

    public Transmissor(Certificate keyCertificate, Certificate trustCertificate) {
        this.initCert();
        this.initializeKeyStore(keyCertificate);
        this.initializeTrustStore(trustCertificate);
    }

    public Transmissor(Certificate keyCertificate) {
        this(keyCertificate, new Certificate(Transmissor.class.getResourceAsStream("/eprecise/efiscal4j/transmissor/NFeCacerts.jks"), "", "JKS"));
    }

    private void initCert() {
        final HostnameVerifier hv = new HostnameVerifier() {

            @Override
            public boolean verify(String string, SSLSession ssls) {
                return true;
            }
        };
        // System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        // HttpsURLConnection.setDefaultHostnameVerifier(hv);

    }

    public void initializeKeyStore(Certificate certificate) {
        try {
            this.getLogger().info("Inicializando certificado PKCS");
            this.keyStore = KeyStore.getInstance(certificate.getCertificateStoreImpl());
            this.keyStore.load(certificate.getCertificate(), certificate.getPassphrase().toCharArray());
            this.keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            this.keyManagerFactory.init(this.keyStore, certificate.getPassphrase().toCharArray());
        } catch (final Exception ex) {
            this.getLogger().error("Erro ao inicializar certificado PKCS.", ex);
        }
    }

    public void initializeTrustStore(Certificate certificate) {
        try {
            this.getLogger().info("Inicializando certificado JKS");
            this.trustStore = KeyStore.getInstance(certificate.getCertificateStoreImpl());
            this.trustStore.load(certificate.getCertificate(), null);
            this.trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            this.trustManagerFactory.init(this.trustStore);
        } catch (final Exception ex) {
            this.getLogger().error("Erro ao inicializar certificado JKS.", ex);
        }
    }

    public void initializeSSLContext() {
        if (this.trustManagerFactory != null && this.keyManagerFactory != null) {
            try {
                this.sslContext = SSLContext.getInstance("SSL");
                this.sslContext.init(this.keyManagerFactory.getKeyManagers(), this.trustManagerFactory.getTrustManagers(), null);
            } catch (final Exception ex) {
                this.getLogger().error("Erro ao inicializar contexto SSL.", ex);
            }
        }
    }

    public String transmit(TransmissibleEnvelope soapEnvelope, String serviceUrl) {

        // System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        // // Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        //
        // System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        // System.setProperty("javax.net.ssl.keyStore", "/home/felipe/Documentos/Desenvolvimento/e-Fiscal4j/Fonebras/FONEBRAS 0989Lu.pfx");
        // System.setProperty("javax.net.ssl.keyStorePassword", "0989Lu");
        //
        // System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        // System.setProperty("javax.net.ssl.trustStore", "/home/felipe/Documentos/Desenvolvimento/e-Fiscal4j/e-Precise/NFeCacerts");
        // System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        final String requestXml = new FiscalDocumentSerializer<>(soapEnvelope).serialize();

        this.log.info("Service url:\n" + serviceUrl);

        this.log.info("Request xml:\n" + requestXml);

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
            // postUrlConnection.setRequestProperty("Content-Length", xmlContent.length() + "");
            // postUrlConnection.setRequestProperty("SOAPAction", soapAction);
            httpConnection.connect();

            this.sendRequest(httpConnection, requestXml);

            final String responseXml = this.getResponse(httpConnection);

            this.log.info("Response xml:\n" + responseXml);

            return responseXml;
        } catch (final Exception ex) {
            this.log.error(ex.getMessage(), ex);
        }
        return "";
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
        return this.log;
    }
}
