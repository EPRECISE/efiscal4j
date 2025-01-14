
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


/**
 *
 * @author JavaC Community
 *
 */
public class NFeBuildAllCacerts {

    private static final int TIMEOUT_WS = 30;

    private final String jsseCacerts;

    private final char[] passphrase = "changeit".toCharArray();

    public NFeBuildAllCacerts(final String jsseCacerts) {
        this.jsseCacerts = jsseCacerts;
    }

    public static void main(final String[] args) {
        try {
			if (args[0] == null) {
				System.out.println("Não foi definido o caminho e nome do certificado cacerts. Abortando operação.");
				return;
			}

            final NFeBuildAllCacerts buildCacerts = new NFeBuildAllCacerts(args[0]);
            buildCacerts.load();
            buildCacerts.info("Certificate sucessfully generated in " + buildCacerts.getJsseCacerts());

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void load() throws Exception {

        File file = new File(this.getJsseCacerts());
        if (file.isFile() == false) {
            final char SEP = File.separatorChar;
            final File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
            file = new File(dir, this.getJsseCacerts());
            if (file.isFile() == false) {
                file = new File(dir, "cacerts");
            }
        }

        this.info("| Loading KeyStore " + file + "...");
        final InputStream in = new FileInputStream(file);
        final KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(in, this.passphrase);
        in.close();

		//NFE HOMOLOGACAO
        this.get("homnfe.sefaz.am.gov.br", 443, ks);
        this.get("hnfe.sefaz.ba.gov.br", 443, ks);
        this.get("nfeh.sefaz.ce.gov.br", 443, ks);
        this.get("app.sefaz.es.gov.br", 443, ks);
        this.get("homolog.sefaz.go.gov.br", 443, ks);
        this.get("sistemas.sefaz.ma.gov.br", 443, ks);
        this.get("hnfe.fazenda.mg.gov.br", 443, ks);
        this.get("hom.nfe.sefaz.ms.gov.br", 443, ks);
        this.get("homologacao.sefaz.mt.gov.br", 443, ks);
        this.get("nfehomolog.sefaz.pe.gov.br", 443, ks);
        this.get("homologacao.nfe.sefa.pr.gov.br", 443, ks);
        this.get("nfe-homologacao.sefazrs.rs.gov.br", 443, ks);
        this.get("cad.sefazrs.rs.gov.br", 443, ks);
        this.get("homologacao.nfe.fazenda.sp.gov.br", 443, ks);
        this.get("hom.sefazvirtual.fazenda.gov.br", 443, ks);
        this.get("nfe-homologacao.svrs.rs.gov.br", 443, ks);
        this.get("cad.svrs.rs.gov.br", 443, ks);
        this.get("hom.svc.fazenda.gov.br", 443, ks);
        this.get("hom.nfe.fazenda.gov.br", 443, ks);

        // NFE PRODUCAO
        this.get("nfe.sefaz.am.gov.br", 443, ks);
        this.get("nfe.sefaz.ba.gov.br", 443, ks);
        this.get("nfe.sefaz.ce.gov.br", 443, ks);
        this.get("nfe.sefaz.go.gov.br", 443, ks);
        this.get("nfe.fazenda.mg.gov.br", 443, ks);
        this.get("nfe.sefaz.ms.gov.br", 443, ks);
        this.get("nfe.sefaz.mt.gov.br", 443, ks);
        this.get("nfe.sefaz.pe.gov.br", 443, ks);
        this.get("nfe.sefa.pr.gov.br", 443, ks);
        this.get("nfe.sefazrs.rs.gov.br", 443, ks);
        this.get("nfe.fazenda.sp.gov.br", 443, ks);
        this.get("www.sefazvirtual.fazenda.gov.br", 443, ks);
        this.get("nfe.svrs.rs.gov.br", 443, ks);
        this.get("www.svc.fazenda.gov.br", 443, ks);
        this.get("www.nfe.fazenda.gov.br", 443, ks);
        this.get("www1.nfe.fazenda.gov.br", 443, ks);

        // NFCE HOMOLOGACAO
        this.get("homnfce.sefaz.am.gov.br", 443, ks);
        this.get("nfceh.sefaz.ce.gov.br", 443, ks);
        this.get("hom.nfce.sefaz.ms.gov.br", 443, ks);
        this.get("hnfce.fazenda.mg.gov.br", 443, ks);
        this.get("nfcehomolog.sefaz.pe.gov.br", 443, ks);
        this.get("homologacao.nfce.sefa.pr.gov.br", 443, ks);
        this.get("nfce-homologacao.sefazrs.rs.gov.br", 443, ks);
        this.get("homologacao.nfce.fazenda.sp.gov.br", 443, ks);
        this.get("nfce-homologacao.svrs.rs.gov.br", 443, ks);

        // NFCE PRODUCAO
        this.get("nfce.sefaz.am.gov.br", 443, ks);
        this.get("nfce.sefaz.ms.gov.br", 443, ks);
        this.get("nfce.fazenda.mg.gov.br", 443, ks);
        this.get("nfce.sefaz.mt.gov.br", 443, ks);
        this.get("nfce.sefaz.pe.gov.br", 443, ks);
        this.get("nfce.sefa.pr.gov.br", 443, ks);
        this.get("nfce.sefazrs.rs.gov.br", 443, ks);
        this.get("nfce.fazenda.sp.gov.br", 443, ks);
        this.get("nfce.svrs.rs.gov.br", 443, ks);

        // CTE HOMOLOGACAO
        this.get("hcte.fazenda.mg.gov.br", 443, ks);
        this.get("homologacao.cte.ms.gov.br", 443, ks);
        this.get("homologacao.cte.fazenda.pr.gov.br", 443, ks);
        this.get("cte-homologacao.svrs.rs.gov.br", 443, ks);
        this.get("hom1.cte.fazenda.gov.br", 443, ks);

        // CTE PRODUCAO
        this.get("cte.fazenda.mg.gov.br", 443, ks);
        this.get("producao.cte.ms.gov.br", 443, ks);
        this.get("cte.sefaz.mt.gov.br", 443, ks);
        this.get("cte.fazenda.pr.gov.br", 443, ks);
        this.get("cte.svrs.rs.gov.br", 443, ks);
        this.get("www1.cte.fazenda.gov.br", 443, ks);

        //MDFE HOMOLOGACAO
        this.get("mdfe-homologacao.svrs.rs.gov.br", 443, ks);

        //MDFE PRODUCAO
        this.get("mdfe.svrs.rs.gov.br", 443, ks);

        //eSOCIAL Homologação
        this.get("webservices.producaorestrita.esocial.gov.br", 443, ks);

        //eSOCIAL Produção
        this.get("webservices.download.esocial.gov.br", 443, ks);
        this.get("webservices.consulta.esocial.gov.br", 443, ks);
        this.get("webservices.envio.esocial.gov.br", 443, ks);

        //EFD-REINF Homologação
        this.get("preprodefdreinf.receita.fazenda.gov.br", 443, ks);

        //EFD-REINF Produção
        this.get("reinf.receita.fazenda.gov.br", 443, ks);

        final File cafile = new File(this.getJsseCacerts());
        final OutputStream out = new FileOutputStream(cafile);
        ks.store(out, this.passphrase);
        out.close();
    }

    private void get(final String host, final int port, final KeyStore ks) throws Exception {
        final SSLContext context = SSLContext.getInstance("TLS");
        final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(ks);
        final X509TrustManager defaultTrustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
        final SavingTrustManager savingTrustManager = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[] { savingTrustManager }, null);
        final SSLSocketFactory factory = context.getSocketFactory();

        this.info("| Opening connection to " + host + ":" + port + "...");
        final SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        socket.setSoTimeout(NFeBuildAllCacerts.TIMEOUT_WS * 1000);
        try {
            this.info("| Starting SSL handshake...");
            socket.startHandshake();
            socket.close();
            this.info("| No errors, certificate is already trusted");
        } catch (final SSLHandshakeException e) {
            //@formatter:off
            /**
             * PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
             * unable to find valid certification path to requested target
             * Não tratado, pois sempre ocorre essa exceção quando o cacerts nao esta gerado.
             */
            //@formatter:on
        } catch (final SSLException e) {
            this.error("| " + e.toString());
        }

        final X509Certificate[] chain = savingTrustManager.chain;
        if (chain == null) {
            this.info("| Could not obtain server certificate chain");
        } else {
	        this.info("| Server sent " + chain.length + " certificate(s):");
	        final MessageDigest sha1 = MessageDigest.getInstance("SHA1");
	        final MessageDigest md5 = MessageDigest.getInstance("MD5");
	        for (int i = 0; i < chain.length; i++) {
	            final X509Certificate cert = chain[i];
	            sha1.update(cert.getEncoded());
	            md5.update(cert.getEncoded());
	
	            final String alias = host + "-" + (i);
	            ks.setCertificateEntry(alias, cert);
	            this.info("| Added certificate to keystore '" + this.getJsseCacerts() + "' using alias '" + alias + "'");
	        }
        }
    }

    private class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;

        private X509Certificate[] chain;

        SavingTrustManager(final X509TrustManager tm) {
            this.tm = tm;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
            throw new UnsupportedOperationException();
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
            this.chain = chain;
            this.tm.checkServerTrusted(chain, authType);
        }
    }

    private void info(final String log) {
        System.out.println("INFO: " + log);
    }

    private void error(final String log) {
        System.out.println("ERROR: " + log);
    }

    public String getJsseCacerts() {
        return this.jsseCacerts;
    }

    public char[] getPassphrase() {
        return this.passphrase;
    }
}
