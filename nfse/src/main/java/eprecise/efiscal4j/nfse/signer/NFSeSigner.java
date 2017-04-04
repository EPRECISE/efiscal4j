
package eprecise.efiscal4j.nfse.signer;

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
        transformList.add(signatureFactory.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", (TransformParameterSpec) null));

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
     */
    @Override
    public Assignable sign(final Assignable assignable) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, SAXException, IOException,
            ParserConfigurationException, TransformerException {

        // Instantiate the document to be signed.
        final Document document = documentFactory(assignable.getAsXml());

        // Loop through all root tags of assignable elements in document (may be a batch of assignable elements)
        for (int i = 0; i < document.getElementsByTagName("Header").getLength(); i++) {
            // Retrieve the assignable element
            final Element element = (Element) document.getElementsByTagName("Header").item(i);
            // Retrieve the assignable element´s id
            final String idAttribute = element.getAttribute("Id");

            element.setIdAttribute("Id", true);

            // Create a Reference to the enveloped document, and also specify the SHA1 digest algorithm and the ENVELOPED and C14N Transform.
            //@formatter:off
            final Reference reference = signatureFactory.newReference("#" + idAttribute,
                                                                           signatureFactory.newDigestMethod(DigestMethod.SHA1, null),
                                                                           transformList,
                                                                           null,
                                                                           null);
            //Create the signedInfo element
            final SignedInfo signedInfo = signatureFactory.newSignedInfo(
                    signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                                                                   (C14NMethodParameterSpec) null),
                    signatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1,
                                                             null),
                    Collections.singletonList(reference));

            // Create a DOMSignContext and specify the RSA PrivateKey and
            // location of the resulting XMLSignature's parent element.
            final DOMSignContext signContext = new DOMSignContext(privateKey,
                                                                  document.getElementsByTagName("Header").item(i));

            //@formatter:on
            // Create the XMLSignature, but don't sign it yet.
            final XMLSignature xmlSignature = signatureFactory.newXMLSignature(signedInfo, keyInfo);

            // Marshal, generate, and sign the enveloped signature.
            xmlSignature.sign(signContext);
        }

        return assignable.getAsEntity(outputXML(document));
    }

    private Document documentFactory(final String xml) throws SAXException, IOException, ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.replaceAll("\\>[\\n\\t ]+\\<", "><").replaceAll(" standalone=\"no\"", "").replaceAll("SOAP-ENV:", "").getBytes()));
    }

    private String outputXML(final Document document) throws TransformerException {
        final OutputStream outputStream = new ByteArrayOutputStream();
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        transformer.transform(new DOMSource(document), new StreamResult(outputStream));
        final String outputXML = outputStream.toString().replaceAll("Header", "SOAP-ENV:Header");
        System.out.println("Assinatura: " + outputXML);
        return outputXML;
    }

    public eprecise.efiscal4j.commons.utils.Certificate getKeyCertificate() {
        return keyCertificate;
    }

}
