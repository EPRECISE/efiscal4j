
package eprecise.efiscal4j.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * Classe Java de SignatureType complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SignatureType implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.w3.org/2000/09/xmldsig#";

    private @XmlElement(name = "SignedInfo", required = true) SignedInfoType signedInfo;

    private @XmlElement(name = "SignatureValue", required = true) SignatureValueType signatureValue;

    private @XmlElement(name = "KeyInfo", required = true) KeyInfoType keyInfo;

    public SignedInfoType getSignedInfo() {
        return this.signedInfo;
    }

    public void setSignedInfo(SignedInfoType value) {
        this.signedInfo = value;
    }

    public SignatureValueType getSignatureValue() {
        return this.signatureValue;
    }

    public void setSignatureValue(SignatureValueType value) {
        this.signatureValue = value;
    }

    public KeyInfoType getKeyInfo() {
        return this.keyInfo;
    }

    public void setKeyInfo(KeyInfoType value) {
        this.keyInfo = value;
    }

}
