
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class Signature implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns:" + NFSeNamespacesPrefixMapper.SIGNATURE_PREFIX) final String xmlns = NFSeNamespacesPrefixMapper.SIGNATURE_URI;

    private @XmlAttribute(name = "Id") String id;

    private @XmlElement(name = "SignedInfo", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) SignedInfo signedInfo;

    private @XmlElement(name = "SignatureValue", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) String signatureValue;

    private @XmlElement(name = "KeyInfo", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) KeyInfo keyInfo;

    public SignedInfo getSignedInfo() {
        return signedInfo;
    }

    public String getSignatureValue() {
        return signatureValue;
    }

    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

}
