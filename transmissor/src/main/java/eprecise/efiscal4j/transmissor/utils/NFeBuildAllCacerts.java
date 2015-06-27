
package eprecise.efiscal4j.transmissor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


public class NFeBuildAllCacerts {

    private static final String JSSECACERTS = "/home/felipe/Documentos/Desenvolvimento/e-Fiscal4j/e-Precise/NFeCacerts";

    private static final int TIMEOUT_WS = 30;

    public static void main(String[] args) {
        try {
            final char[] passphrase = "changeit".toCharArray();

            File file = new File(JSSECACERTS);
            if (file.isFile() == false) {
                final char SEP = File.separatorChar;
                final File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
                file = new File(dir, JSSECACERTS);
                if (file.isFile() == false) {
                    file = new File(dir, "cacerts");
                }
            }

            info("| Loading KeyStore " + file + "...");
            final InputStream in = new FileInputStream(file);
            final KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(in, passphrase);
            in.close();

            /**
             * AM - 3.10: homnfe.sefaz.am.gov.br<br>
             * BA - 3.10: hnfe.sefaz.ba.gov.br<br>
             * CE - 3.10: nfeh.sefaz.ce.gov.br<br>
             * GO - 3.10: homolog.sefaz.go.gov.br<br>
             * MG - 3.10: hnfe.fazenda.mg.gov.br<br>
             * MA - 2.00: sistemas.sefaz.ma.gov.br<br>
             * MS - 3.10: homologacao.nfe.ms.gov.br<br>
             * MT - 3.10: homologacao.sefaz.mt.gov.br<br>
             * PE - 3.10: nfehomolog.sefaz.pe.gov.br<br>
             * PR - 3.10: homologacao.nfe.fazenda.pr.gov.br<br>
             * RS - 3.10: nfe-homologacao.sefazrs.rs.gov.br<br>
             * SP - 3.10: homologacao.nfe.fazenda.sp.gov.br<br>
             * SVAN - 3.10: hom.sefazvirtual.fazenda.gov.br<br>
             * SVRS - 3.10: nfe-homologacao.svrs.rs.gov.br<br>
             * SVC-AN - 3.10: hom.svc.fazenda.gov.br<br>
             * SVC-RS - 3.10: nfe-homologacao.svrs.rs.gov.br<br>
             * AN - 3.10: hom.nfe.fazenda.gov.br<br>
             */
            get("homnfe.sefaz.am.gov.br", 443, ks);
            get("hnfe.sefaz.ba.gov.br", 443, ks);
            get("nfeh.sefaz.ce.gov.br", 443, ks);
            get("homolog.sefaz.go.gov.br", 443, ks);
            get("hnfe.fazenda.mg.gov.br", 443, ks);
            get("sistemas.sefaz.ma.gov.br", 443, ks);
            get("homologacao.nfe.ms.gov.br", 443, ks);
            get("homologacao.sefaz.mt.gov.br", 443, ks);
            get("nfehomolog.sefaz.pe.gov.br", 443, ks);
            get("homologacao.nfe.fazenda.pr.gov.br", 443, ks);
            get("nfe-homologacao.sefazrs.rs.gov.br", 443, ks);
            get("homologacao.nfe.fazenda.sp.gov.br", 443, ks);
            get("hom.sefazvirtual.fazenda.gov.br", 443, ks);
            get("nfe-homologacao.svrs.rs.gov.br", 443, ks);
            get("hom.svc.fazenda.gov.br", 443, ks);
            get("nfe-homologacao.svrs.rs.gov.br", 443, ks);
            get("hom.nfe.fazenda.gov.br", 443, ks);

            final File cafile = new File(JSSECACERTS);
            final OutputStream out = new FileOutputStream(cafile);
            ks.store(out, passphrase);
            out.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void get(String host, int port, KeyStore ks) throws Exception {
        final SSLContext context = SSLContext.getInstance("TLS");
        final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        final X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        final SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[] { tm }, null);
        final SSLSocketFactory factory = context.getSocketFactory();

        info("| Opening connection to " + host + ":" + port + "...");
        final SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        socket.setSoTimeout(TIMEOUT_WS * 1000);
        try {
            info("| Starting SSL handshake...");
            socket.startHandshake();
            socket.close();
            info("| No errors, certificate is already trusted");
        } catch (final SSLHandshakeException e) {
            /**
             * PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target Não tratado, pois sempre ocorre essa
             * exceção quando o cacerts nao esta gerado.
             */
        } catch (final SSLException e) {
            error("| " + e.toString());
        }

        final X509Certificate[] chain = tm.chain;
        if (chain == null) {
            info("| Could not obtain server certificate chain");
        }

        info("| Server sent " + chain.length + " certificate(s):");
        final MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        final MessageDigest md5 = MessageDigest.getInstance("MD5");
        for (int i = 0; i < chain.length; i++) {
            final X509Certificate cert = chain[i];
            sha1.update(cert.getEncoded());
            md5.update(cert.getEncoded());

            final String alias = host + "-" + (i);
            ks.setCertificateEntry(alias, cert);
            info("| Added certificate to keystore '" + JSSECACERTS + "' using alias '" + alias + "'");
        }
    }

    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;

        private X509Certificate[] chain;

        SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            this.chain = chain;
            this.tm.checkServerTrusted(chain, authType);
        }
    }

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    private static void error(String log) {
        System.out.println("ERROR: " + log);
    }

}
