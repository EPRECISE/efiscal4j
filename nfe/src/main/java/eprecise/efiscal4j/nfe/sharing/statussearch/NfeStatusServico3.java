
package eprecise.efiscal4j.nfe.sharing.statussearch;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


@WebServiceClient(
        name = "NfeStatusServico3",
        wsdlLocation = "file:/home/felipe/Desenvolvimento/e-Precise/OpenSource/e-Fiscal4J/e-fiscal4j/transmissor/src/main/resources/eprecise/efiscal4j/transmissor/nfe/HOMOLOG_NFeStatusServico3.wsdl",
        targetNamespace = NfeStatusServico3.DEFAULT_NAMESPACE)
public class NfeStatusServico3 extends Service {

    public final static String DEFAULT_NAMESPACE = "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico3";

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName(NfeStatusServico3.DEFAULT_NAMESPACE, "NfeStatusServico3");

    public final static QName NfeStatusServicoServicePort = new QName(NfeStatusServico3.DEFAULT_NAMESPACE, "NfeStatusServicoServicePort");
    static {
        URL url = null;
        try {
            url = new URL(
                    "file:/home/felipe/Desenvolvimento/e-Precise/OpenSource/e-Fiscal4J/e-fiscal4j/transmissor/src/main/resources/eprecise/efiscal4j/transmissor/nfe/HOMOLOG_NFeStatusServico3.wsdl");
        } catch (final MalformedURLException e) {
            java.util.logging.Logger.getLogger(NfeStatusServico3.class.getName()).log(java.util.logging.Level.INFO, "Can not initialize the default wsdl from {0}",
                    "file:/home/felipe/Desenvolvimento/e-Precise/OpenSource/e-Fiscal4J/e-fiscal4j/transmissor/src/main/resources/eprecise/efiscal4j/transmissor/nfe/HOMOLOG_NFeStatusServico3.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public NfeStatusServico3(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public NfeStatusServico3(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NfeStatusServico3() {
        super(WSDL_LOCATION, SERVICE);
    }

    public NfeStatusServico3(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public NfeStatusServico3(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public NfeStatusServico3(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "NfeStatusServicoServicePort")
    public NfeStatusServicoSoap12 getNfeStatusServicoServicePort() {
        return super.getPort(NfeStatusServicoServicePort, NfeStatusServicoSoap12.class);
    }

    @WebEndpoint(name = "NfeStatusServicoServicePort")
    public NfeStatusServicoSoap12 getNfeStatusServicoServicePort(WebServiceFeature... features) {
        return super.getPort(NfeStatusServicoServicePort, NfeStatusServicoSoap12.class, features);
    }

}
