
package eprecise.efiscal4j.signer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
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
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 *
 * @author Felipe Bueno
 *
 */
public class Signer {

    private final eprecise.efiscal4j.commons.utils.Certificate keyCertificate;

    private XMLSignatureFactory signatureFactory;

    private ArrayList<Transform> transformList;

    private PrivateKey privateKey;

    private KeyInfo keyInfo;

    public Signer(final eprecise.efiscal4j.commons.utils.Certificate keyCertificate) throws Exception {
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
        this.transformList.add(this.signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
        this.transformList.add(this.signatureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (TransformParameterSpec) null));

        this.loadCertificates();
    }

    private void loadCertificates() throws Exception {
        final KeyStore ks = KeyStore.getInstance("pkcs12");
        try {
            ks.load(this.getKeyCertificate().getCertificate(), this.getKeyCertificate().getPassphrase().toCharArray());
        } catch (final IOException e) {
            throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");
        }

        Certificate cert = null;
        final Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            final String alias = aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                cert = ks.getCertificate(alias);
                this.privateKey = (PrivateKey) ks.getKey(alias, this.getKeyCertificate().getPassphrase().toCharArray());
                break;
            }
        }

        final KeyInfoFactory keyInfoFactory = this.signatureFactory.getKeyInfoFactory();
        final List<Certificate> x509Content = new ArrayList<Certificate>();
        x509Content.add(cert);

        final X509Data x509Data = keyInfoFactory.newX509Data(x509Content);

        this.keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
    }

    /**
     * Assina o documento assinável, retornando a mesma entidade com as tags de Signature preenchidas
     *
     * @param assignable
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws MarshalException
     * @throws XMLSignatureException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public Assignable sign(final Assignable assignable) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, SAXException, IOException,
            ParserConfigurationException, TransformerException {
        // Instantiate the document to be signed.
        final Document document = this.documentFactory(assignable.getAsXml());

        // Loop through all root tags of assignable elements in document (may be a batch of assignable elements)
        for (int i = 0; i < document.getElementsByTagName(assignable.getRootTagName()).getLength(); i++) {
            // Retrieve the assignable element
            final Element element = (Element) document.getElementsByTagName(assignable.getAssignableTagName()).item(i);
            // Retrieve the assignable element´s id
            final String idAttribute = element.getAttribute(assignable.getIdAttributeTagName());

            element.setIdAttribute(assignable.getIdAttributeTagName(), true);

            // Create a Reference to the enveloped document, and also specify the SHA1 digest algorithm and the ENVELOPED and C14N Transform.
            //@formatter:off
            final Reference reference = this.signatureFactory.newReference("#" + idAttribute,
                                                                           this.signatureFactory.newDigestMethod(DigestMethod.SHA1, null),
                                                                           this.transformList,
                                                                           null,
                                                                           null);
            //Create the signedInfo element
            final SignedInfo signedInfo = this.signatureFactory.newSignedInfo(
                    this.signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                                                                   (C14NMethodParameterSpec) null),
                    this.signatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1,
                                                             null),
                    Collections.singletonList(reference));

            // Create a DOMSignContext and specify the RSA PrivateKey and
            // location of the resulting XMLSignature's parent element.
            final DOMSignContext signContext = new DOMSignContext(this.privateKey,
                                                                  document.getElementsByTagName(assignable.getRootTagName()).item(i));

            //@formatter:on
            // Create the XMLSignature, but don't sign it yet.
            final XMLSignature xmlSignature = this.signatureFactory.newXMLSignature(signedInfo, this.keyInfo);

            // Marshal, generate, and sign the enveloped signature.
            xmlSignature.sign(signContext);
        }

        return assignable.getAsEntity(this.outputXML(document));
    }

    private Document documentFactory(final String xml) throws SAXException, IOException, ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.replaceAll("\\>[\\n\\t ]+\\<", "><").replaceAll(" standalone=\"no\"", "").getBytes()));
    }

    private String outputXML(final Document document) throws TransformerException {
        final OutputStream outputStream = new ByteArrayOutputStream();
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        transformer.transform(new DOMSource(document), new StreamResult(outputStream));
        return outputStream.toString();
    }

    public eprecise.efiscal4j.commons.utils.Certificate getKeyCertificate() {
        return this.keyCertificate;
    }
}
