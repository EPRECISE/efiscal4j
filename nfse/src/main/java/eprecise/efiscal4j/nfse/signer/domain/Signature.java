
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class Signature implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "SignedInfo") SignedInfo signedInfo;

    private @XmlElement(name = "SignatureValue") String signatureValue;

    private @XmlElement(name = "KeyInfo") KeyInfo keyInfo;

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
