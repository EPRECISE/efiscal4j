
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
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
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

import org.xml.sax.SAXException;

import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;


/**
 *
 * @author Fernando C Glizt
 *
 */
public class NFSeSigner implements Signer {

    private final eprecise.efiscal4j.commons.utils.Certificate keyCertificate;

    private XMLSignatureFactory signatureFactory;

    private ArrayList<Transform> transformList;

    private PrivateKey privateKey;

    private KeyInfo keyInfo;

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
        transformList.add(signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));

        loadCertificates();
    }

    private void loadCertificates() throws Exception {
        final KeyStore ks = KeyStore.getInstance("pkcs12");
        try {
            ks.load(getKeyCertificate().getCertificate(), getKeyCertificate().getPassphrase().toCharArray());
        } catch (final IOException e) {
            throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");
        }

        Certificate cert = null;
        final Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            final String alias = aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                cert = ks.getCertificate(alias);
                privateKey = (PrivateKey) ks.getKey(alias, getKeyCertificate().getPassphrase().toCharArray());
                break;
            }
        }

        final KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
        final List<Certificate> x509Content = new ArrayList<>();
        x509Content.add(cert);

        final X509Data x509Data = keyInfoFactory.newX509Data(x509Content);

        keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
    }

    /**
     * Assina o documento assinável, retornando a mesma entidade com as tags de Signature preenchidas
     *
     * @param assignableParam
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws MarshalException
     * @throws XMLSignatureException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws SOAPException
     * @throws DatatypeConfigurationException
     */
    @Override
    public Assignable sign(final Assignable assignable) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, SAXException, IOException,
            ParserConfigurationException, TransformerException, SOAPException, DatatypeConfigurationException {

        // final Document request = documentFactory(assignable.getAsXml());
        //
        // request.normalizeDocument();
        // // request.getDocumentElement().getAttributeNS("urn:oasis:names:tc:SAML:2.0:protocol", "ID");
        // final XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        // final Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null), Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
        // null,
        // null);
        // final SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1,
        // null),
        // Collections.singletonList(ref));
        // final DOMSignContext dsc = new DOMSignContext(privateKey, request.getDocumentElement());
        // // dsc.setIdAttributeNS(request.getDocumentElement(), "urn:oasis:names:tc:SAML:2.0:protocol", "Id");
        // final XMLSignature signature = fac.newXMLSignature(si, keyInfo);
        // signature.sign(dsc);
        //
        // return assignable.getAsEntity(outputXML(request));

        final String NAMESPACEURI_WSSECURITY_WSU = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3";

        final String NAMESPACEURI_WSSECURITY_WSSE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

        final String ATTRIBUTENAME_X509TOKEN = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3";

        final String xml = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"><Header></Header><Body Id=\"body\"></Body></Envelope>";
        // final String xml = assignable.getAsXml();
        final InputStream is = new ByteArrayInputStream(xml.getBytes());
        final SOAPMessage msg = MessageFactory.newInstance().createMessage(null, is);

        // Prepare envelope
        final SOAPPart soapPart = msg.getSOAPPart();
        final SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("u", NAMESPACEURI_WSSECURITY_WSU);

        // Prepare header
        SOAPHeader header = envelope.getHeader();
        if (header == null) {
            header = envelope.addHeader();
        }
        final SOAPHeaderElement securityElement = header.addHeaderElement(new QName(NAMESPACEURI_WSSECURITY_WSSE, "Security", "wsse"));
        securityElement.setMustUnderstand(true);

        // Prepare body
        final SOAPBody body = envelope.getBody();
        body.addAttribute(new QName(NAMESPACEURI_WSSECURITY_WSU, "Id", "u"), "body");

        // // Generate timestamps
        // final GregorianCalendar calendar = new GregorianCalendar();
        // DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        // calendar.add(Calendar.MINUTE, 5);
        // DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        // // Prepare timestamp element
        // final SOAPElement timeStampElement = securityElement.addChildElement("Timestamp", "u");
        // timeStampElement.addAttribute(new QName(NAMESPACEURI_WSSECURITY_WSU, "Id", "u"), "timestamp");
        // final SOAPElement createdElement = timeStampElement.addChildElement("Created", "u");
        // createdElement.addTextNode(xmlNow.toString());
        // final SOAPElement expiresElement = timeStampElement.addChildElement("Expires", "u");
        // expiresElement.addTextNode(xmlLater.toString());

        // Prepare security token element
        final SOAPElement binarySecurityTokenElement = securityElement.addChildElement("BinarySecurityToken", "wsse");
        binarySecurityTokenElement.addAttribute(new QName(NAMESPACEURI_WSSECURITY_WSU, "Id", "u"), "cert");
        binarySecurityTokenElement.addAttribute(new QName("ValueType"), ATTRIBUTENAME_X509TOKEN);
        binarySecurityTokenElement.addTextNode(new String(Base64.getEncoder().encode(privateKey.getEncoded())));

        // Signature generation
        final XMLSignatureFactory signFactory = XMLSignatureFactory.getInstance("DOM", new org.jcp.xml.dsig.internal.dom.XMLDSigRI());
        final C14NMethodParameterSpec spec1 = null;
        final CanonicalizationMethod c14nMethod = signFactory.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, spec1);
        final DigestMethod digestMethod = signFactory.newDigestMethod(DigestMethod.SHA1, null);
        final SignatureMethod signMethod = signFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
        final TransformParameterSpec spec2 = null;
        final Transform transform = signFactory.newTransform(CanonicalizationMethod.EXCLUSIVE, spec2);
        final List<Transform> transformList = Collections.singletonList(transform);
        final List<Reference> referenceList = new ArrayList<>();
        final Reference reference1 = signFactory.newReference("#body", digestMethod, transformList, null, null);
        referenceList.add(reference1);
        // final Reference reference2 = signFactory.newReference("#timestamp", digestMethod, transformList, null, null);
        // referenceList.add(reference2);
        final SignedInfo signInfo = signFactory.newSignedInfo(c14nMethod, signMethod, referenceList);
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
