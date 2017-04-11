
package eprecise.efiscal4j.nfse.signer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.TransformerException;

import org.jcp.xml.dsig.internal.dom.XMLDSigRI;
import org.xml.sax.SAXException;

import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;


/**
 *
 * @author Fernando C Glizt
 *
 */
@SuppressWarnings("restriction")
public class NFSeSigner implements Signer {

    private static final String NAMESPACEURI_WSSECURITY_WSU = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3";

    private static final String NAMESPACEURI_WSSECURITY_WSSE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

    private static final String ATTRIBUTENAME_X509TOKEN = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3";

    private final eprecise.efiscal4j.commons.utils.Certificate keyCertificate;

    private XMLSignatureFactory signatureFactory;

    private ArrayList<Transform> transformList;

    private PrivateKey privateKey;

    public NFSeSigner(final eprecise.efiscal4j.commons.utils.Certificate keyCertificate) throws Exception {
        this.keyCertificate = keyCertificate;
        init();
    }

    /**
     *
     * Create a DOM XMLSignatureFactory that will be used to generate the enveloped signature.<br>
     * Create a list of Transforms, including ENVELOPED and C14N Transforms<br>
     * Load the KeyStore and get the signing key and certificate<br>
     *
     * @throws Exception
     */
    private void init() throws Exception {
        signatureFactory = XMLSignatureFactory.getInstance("DOM");

        transformList = new ArrayList<>();
        final Transform transform = signatureFactory.newTransform(CanonicalizationMethod.EXCLUSIVE, (TransformParameterSpec) null);
        transformList.add(transform);

        loadCertificates();
    }

    private void loadCertificates() throws Exception {
        final KeyStore ks = KeyStore.getInstance("pkcs12");
        try {
            ks.load(getKeyCertificate().getCertificate(), getKeyCertificate().getPassphrase().toCharArray());
        } catch (final IOException e) {
            throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");
        }

        final Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            final String alias = aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                privateKey = (PrivateKey) ks.getKey(alias, getKeyCertificate().getPassphrase().toCharArray());
                break;
            }
        }
    }

    /**
     * Assina o documento assinável, retornando a mesma entidade com as tags de Signature preenchidas
     *
     */
    @Override
    public Assignable sign(final Assignable assignable) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, SAXException, IOException,
            ParserConfigurationException, TransformerException, SOAPException, DatatypeConfigurationException {
        final String xml = assignable.getAsXml();
        final InputStream is = new ByteArrayInputStream(xml.getBytes());
        final SOAPMessage msg = MessageFactory.newInstance().createMessage(null, is);

        // Prepare envelope
        final SOAPPart soapPart = msg.getSOAPPart();

        final SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("wsu", NAMESPACEURI_WSSECURITY_WSU);

        // Prepare header
        SOAPHeader header = envelope.getHeader();
        if (header == null) {
            header = envelope.addHeader();
        }
        final SOAPHeaderElement securityElement = header.addHeaderElement(new QName(NAMESPACEURI_WSSECURITY_WSSE, "Security", "wsse"));
        securityElement.setMustUnderstand(true);

        // Prepare body
        final SOAPBody body = envelope.getBody();
        body.addAttribute(new QName(NAMESPACEURI_WSSECURITY_WSU, "Id", "wsu"), "body");

        // Prepare security token element
        final SOAPElement binarySecurityTokenElement = securityElement.addChildElement("BinarySecurityToken", "wsse");
        binarySecurityTokenElement.addAttribute(new QName(NAMESPACEURI_WSSECURITY_WSU, "Id", "wsu"), "cert");
        binarySecurityTokenElement.addAttribute(new QName("ValueType"), ATTRIBUTENAME_X509TOKEN);
        binarySecurityTokenElement.addTextNode(new String(Base64.getEncoder().encode(privateKey.getEncoded())));

        // Signature generation
        final XMLSignatureFactory signFactory = XMLSignatureFactory.getInstance("DOM", new XMLDSigRI());
        final C14NMethodParameterSpec spec1 = null;
        final CanonicalizationMethod c14nMethod = signFactory.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, spec1);
        final DigestMethod digestMethod = signFactory.newDigestMethod(DigestMethod.SHA1, null);
        final SignatureMethod signMethod = signFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
        final SignedInfo signInfo = signFactory.newSignedInfo(c14nMethod, signMethod, Arrays.asList(signFactory.newReference("#body", digestMethod, transformList, null, null)));
        final DOMSignContext dsc = new DOMSignContext(privateKey, securityElement);
        final XMLSignature signature = signFactory.newXMLSignature(signInfo, null);
        signature.sign(dsc);

        // Prepare key info element
        final SOAPElement signatureElement = (SOAPElement) securityElement.getLastChild();
        final SOAPElement keyInfoElement = signatureElement.addChildElement("KeyInfo");
        final SOAPElement securityTokenReferenceElement = keyInfoElement.addChildElement("SecurityTokenReference", "wsse");
        final SOAPElement referenceElement = securityTokenReferenceElement.addChildElement("Reference", "wsse");
        referenceElement.setAttribute("URI", "#cert");
        referenceElement.setAttribute("ValueType", NAMESPACEURI_WSSECURITY_WSU);

        return assignable.getAsEntity(outputXML(msg));
    }

    public eprecise.efiscal4j.commons.utils.Certificate getKeyCertificate() {
        return keyCertificate;
    }

    private String outputXML(final SOAPMessage message) throws TransformerException, SOAPException, IOException {
        final OutputStream outputStream = new ByteArrayOutputStream();

        message.writeTo(outputStream);

        final String outputXML = outputStream.toString();
        System.out.println("Assinatura: " + outputXML);
        return outputXML;
    }

}
