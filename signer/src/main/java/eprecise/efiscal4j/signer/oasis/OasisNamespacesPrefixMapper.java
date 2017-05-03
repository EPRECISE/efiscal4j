
package eprecise.efiscal4j.signer.oasis;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;


public class OasisNamespacesPrefixMapper extends NamespacePrefixMapper {

    public static final String SECURITY_VALUE_TYPE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3";

    public static final String SECURITY_ENCODING_TYPE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary";

    public static final String SOAPENV_PREFIX = "SOAP-ENV";

    public static final String SOAPENV_URI = "http://schemas.xmlsoap.org/soap/envelope/";

    public static final String WSSE_PREFIX = "wsse";

    public static final String WSSE_URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

    public static final String WSU_PREFIX = "wsu";

    public static final String WSU_URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

    public static final String SIGNATURE_URI = "http://www.w3.org/2000/09/xmldsig#";

    public static final String SIGNATURE_PREFIX = "ds";

    public static final String EMPTY_PREFIX = "";

    @Override
    public String getPreferredPrefix(final String namespaceUri, final String suggestion, final boolean requirePrefix) {
        switch (namespaceUri) {
        case SOAPENV_URI: {
            return SOAPENV_PREFIX;
        }
        case WSSE_URI: {
            return WSSE_PREFIX;
        }
        case WSU_URI: {
            return WSU_PREFIX;
        }
        case SIGNATURE_URI: {
            return SIGNATURE_PREFIX;
        }
        default: {
            return suggestion;
        }
        }
    }

}
