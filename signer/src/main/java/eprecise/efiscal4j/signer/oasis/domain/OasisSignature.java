
package eprecise.efiscal4j.signer.oasis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class OasisSignature implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") String id;

    private @XmlElement(name = "SignedInfo", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) OasisSignedInfo signedInfo;

    private @XmlElement(name = "SignatureValue", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) String signatureValue;

    private @XmlElement(name = "KeyInfo", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) OasisKeyInfo keyInfo;

    public OasisSignedInfo getSignedInfo() {
        return signedInfo;
    }

    public String getSignatureValue() {
        return signatureValue;
    }

    public OasisKeyInfo getKeyInfo() {
        return keyInfo;
    }

}
