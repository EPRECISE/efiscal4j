
package eprecise.efiscal4j.signer.oasis;

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
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

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
public class OasisSigner implements Signer {

    private final eprecise.efiscal4j.commons.utils.Certificate keyCertificate;

    private XMLSignatureFactory signatureFactory;

    private ArrayList<Transform> transformList;

    private PrivateKey privateKey;

    private KeyInfo keyInfo;

    private Certificate cert;

    public OasisSigner(final eprecise.efiscal4j.commons.utils.Certificate keyCertificate) throws Exception {
        this.keyCertificate = keyCertificate;
        this.init();
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
        this.signatureFactory = XMLSignatureFactory.getInstance("DOM");

        this.transformList = new ArrayList<>();
        final Transform transform = this.signatureFactory.newTransform(CanonicalizationMethod.EXCLUSIVE, (TransformParameterSpec) null);
        this.transformList.add(transform);

        this.loadCertificates();
    }

    private void loadCertificates() throws Exception {
        final KeyStore ks = KeyStore.getInstance("pkcs12");
        try {
            ks.load(this.getKeyCertificate().getCertificate(), this.getKeyCertificate().getPassphrase().toCharArray());
        } catch (final IOException e) {
            throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");
        }

        this.cert = null;
        final Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            final String alias = aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                this.cert = ks.getCertificate(alias);
                this.privateKey = (PrivateKey) ks.getKey(alias, this.getKeyCertificate().getPassphrase().toCharArray());
                break;
            }
        }

        final KeyInfoFactory keyInfoFactory = this.signatureFactory.getKeyInfoFactory();
        final List<Certificate> x509Content = new ArrayList<>();
        x509Content.add(this.cert);

        final X509Data x509Data = keyInfoFactory.newX509Data(x509Content);

        this.keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
    }

    /**
     * Assina o documento assinável, retornando a mesma entidade com as tags de Signature preenchidas
     *
     * @throws CertificateEncodingException
     *
     */
    @Override
    public Assignable sign(final Assignable assignable) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, SAXException, IOException,
            ParserConfigurationException, TransformerException, SOAPException, DatatypeConfigurationException, CertificateEncodingException {
        final String xml = assignable.getAsXml();
        final InputStream is = new ByteArrayInputStream(xml.getBytes());
        final SOAPMessage msg = MessageFactory.newInstance().createMessage(null, is);
        final String certId = new StringBuilder("CertId-").append(UUID.randomUUID().toString().replaceAll("-", "")).toString();
        final String bodyId = new StringBuilder("BodyId-").append(UUID.randomUUID().toString().replaceAll("-", "")).toString();
        final String keyId = new StringBuilder("KeyId-").append(UUID.randomUUID().toString().replaceAll("-", "")).toString();

        // Prepare envelope
        final SOAPPart soapPart = msg.getSOAPPart();

        final SOAPEnvelope envelope = soapPart.getEnvelope();

        // Prepare header
        SOAPHeader header = envelope.getHeader();
        if (header == null) {
            header = envelope.addHeader();
        }
        final SOAPHeaderElement securityElement = header.addHeaderElement(new QName(OasisNamespacesPrefixMapper.WSSE_URI, "Security", OasisNamespacesPrefixMapper.WSSE_PREFIX));
        securityElement.setMustUnderstand(true);

        // Prepare body
        final SOAPBody body = envelope.getBody();
        body.addAttribute(new QName(OasisNamespacesPrefixMapper.WSU_URI, "Id", OasisNamespacesPrefixMapper.WSU_PREFIX), bodyId);

        // Prepare security token element
        final SOAPElement binarySecurityTokenElement = securityElement.addChildElement("BinarySecurityToken", "wsse");
        binarySecurityTokenElement.addNamespaceDeclaration(OasisNamespacesPrefixMapper.WSU_PREFIX, OasisNamespacesPrefixMapper.WSU_URI);
        binarySecurityTokenElement.addAttribute(new QName(OasisNamespacesPrefixMapper.WSU_URI, "Id", OasisNamespacesPrefixMapper.WSU_PREFIX), certId);
        binarySecurityTokenElement.addAttribute(new QName("ValueType"), OasisNamespacesPrefixMapper.SECURITY_VALUE_TYPE);
        binarySecurityTokenElement.addAttribute(new QName("EncodingType"), OasisNamespacesPrefixMapper.SECURITY_ENCODING_TYPE);
        binarySecurityTokenElement.addTextNode(new String(Base64.getEncoder().encode(this.cert.getEncoded())));

        // Signature generation
        final XMLSignatureFactory signFactory = XMLSignatureFactory.getInstance("DOM");
        final C14NMethodParameterSpec spec1 = null;
        final CanonicalizationMethod c14nMethod = signFactory.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, spec1);
        final DigestMethod digestMethod = signFactory.newDigestMethod(DigestMethod.SHA1, null);
        final SignatureMethod signMethod = signFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
        final SignedInfo signInfo = signFactory.newSignedInfo(c14nMethod, signMethod, Arrays.asList(signFactory.newReference("#" + bodyId, digestMethod, this.transformList, null, null)));
        final DOMSignContext dsc = new DOMSignContext(this.privateKey, securityElement);
        dsc.setDefaultNamespacePrefix(OasisNamespacesPrefixMapper.SIGNATURE_PREFIX);
        final XMLSignature signature = signFactory.newXMLSignature(signInfo, null);
        signature.sign(dsc);

        // Prepare key info element
        final SOAPElement signatureElement = (SOAPElement) securityElement.getLastChild();
        signatureElement.setAttribute("Id", "Signature-" + UUID.randomUUID().toString().replaceAll("-", ""));
        signatureElement.addNamespaceDeclaration(OasisNamespacesPrefixMapper.SIGNATURE_PREFIX, OasisNamespacesPrefixMapper.SIGNATURE_URI);
        final SOAPElement keyInfoElement = signatureElement.addChildElement("KeyInfo", OasisNamespacesPrefixMapper.SIGNATURE_PREFIX, OasisNamespacesPrefixMapper.SIGNATURE_URI);
        keyInfoElement.addAttribute(new QName("Id"), keyId);
        final SOAPElement securityTokenReferenceElement = keyInfoElement.addChildElement("SecurityTokenReference", "wsse");
        final SOAPElement referenceElement = securityTokenReferenceElement.addChildElement("Reference", "wsse");
        referenceElement.setAttribute("URI", "#" + certId);
        referenceElement.setAttribute("ValueType", OasisNamespacesPrefixMapper.SECURITY_VALUE_TYPE);

        return assignable.getAsEntity(this.outputXML(msg));
    }

    public KeyInfo getKeyInfo() {
        return this.keyInfo;
    }

    @Override
    public eprecise.efiscal4j.commons.utils.Certificate getKeyCertificate() {
        return this.keyCertificate;
    }

    private String outputXML(final SOAPMessage message) throws TransformerException, SOAPException, IOException {
        final OutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);
        final String outputXML = outputStream.toString();
        return outputXML;
    }

}
