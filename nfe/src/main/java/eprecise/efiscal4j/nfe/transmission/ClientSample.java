
package eprecise.efiscal4j.nfe.transmission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.sharing.statussearch.NfeStatusServico3;
import eprecise.efiscal4j.nfe.sharing.statussearch.NfeStatusServicoSoap12;
import eprecise.efiscal4j.transmissor.utils.SOAPLoggingHandler;
import eprecise.efiscal4j.transmissor.utils.SOAPNamespaceHandler;


public class ClientSample {

    public void testJaxWs(NFeHeader fiscalDocumentHeader, NFeBody fiscalDocumentBody) {
        // Autenticação no servidor
        System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", "/home/felipe/Documentos/Desenvolvimento/e-Fiscal4j/Fonebras/FONEBRAS 0989Lu.pfx");
        System.setProperty("javax.net.ssl.keyStorePassword", "0989Lu");

        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore", "/home/felipe/Documentos/Desenvolvimento/e-Fiscal4j/e-Precise/NFeCacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        System.out.println("***********************");
        System.out.println("Create Web Service Client...");
        final NfeStatusServico3 service1 = new NfeStatusServico3();

        service1.setHandlerResolver(new HandlerResolver() {

            @Override
            public List<Handler> getHandlerChain(PortInfo portInfo) {
                final List<Handler> handlerChain = new ArrayList<>();

                final String bindingID = portInfo.getBindingID();
                handlerChain.add(new SOAPNamespaceHandler());
                handlerChain.add(new SOAPLoggingHandler());

                return handlerChain;
            }
        });
        System.out.println("Create Web Service...");
        final NfeStatusServicoSoap12 port1 = service1.getNfeStatusServicoServicePort();

        System.out.println("Call Web Service Operation...");
        System.out.println("Server said: ");

        final List<Object> returnContent = port1.nfeStatusServicoNF(fiscalDocumentHeader, fiscalDocumentBody).getContent();

        System.out.println("***********************");
        System.out.println("Call Over!");
    }

    public String testHttpsConnection(SOAPEnvelope soapEnvelope, String serviceUrl) {
        System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        // Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", "/home/felipe/Documentos/Desenvolvimento/e-Fiscal4j/Fonebras/FONEBRAS 0989Lu.pfx");
        System.setProperty("javax.net.ssl.keyStorePassword", "0989Lu");

        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStore", "/home/felipe/Documentos/Desenvolvimento/e-Fiscal4j/e-Precise/NFeCacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        final String xmlEnviado = new FiscalDocumentSerializer<>(soapEnvelope).considering(SOAPEnvelope.class).serialize();

        System.out.println(xmlEnviado);

        try {
            final URL url = new URL(null, serviceUrl, new sun.net.www.protocol.https.Handler());
            final HttpsURLConnection httpsConnection = (HttpsURLConnection) url.openConnection();

            httpsConnection.setDoInput(true);
            httpsConnection.setDoOutput(true);
            httpsConnection.setRequestMethod("POST");
            httpsConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpsConnection.connect();

            this.enviarEnvelopeXML(httpsConnection, xmlEnviado);

            final String xmlRetornado = this.getResposta(httpsConnection);

            System.out.println("retorno:");

            System.out.println(xmlRetornado);

            return xmlRetornado;
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private void enviarEnvelopeXML(HttpsURLConnection connection, String envelopeXML) throws IOException {
        final OutputStream out = connection.getOutputStream();
        final Writer wout = new OutputStreamWriter(out);
        wout.write(envelopeXML);
        wout.flush();
        wout.close();
    }

    private String getResposta(HttpsURLConnection connection) throws ParserConfigurationException, IOException, SAXException {
        final Document doc = null;
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        final DocumentBuilder db = dbf.newDocumentBuilder();
        final InputStream in = connection.getInputStream();

        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        final StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();
        in.close();
        return sb.toString();
    }
}
