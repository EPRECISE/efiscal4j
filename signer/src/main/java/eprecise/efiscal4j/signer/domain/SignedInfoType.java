
package eprecise.efiscal4j.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * Classe Java de SignedInfoType complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SignedInfoType implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "CanonicalizationMethod", required = true) CanonicalizationMethod canonicalizationMethod = new CanonicalizationMethod();

    private @XmlElement(name = "SignatureMethod", required = true) SignatureMethod signatureMethod = new SignatureMethod();

    private @XmlElement(name = "Reference", required = true) ReferenceType reference;

    public SignedInfoType.CanonicalizationMethod getCanonicalizationMethod() {
        return this.canonicalizationMethod;
    }

    public void setCanonicalizationMethod(SignedInfoType.CanonicalizationMethod value) {
        this.canonicalizationMethod = value;
    }

    public SignedInfoType.SignatureMethod getSignatureMethod() {
        return this.signatureMethod;
    }

    public void setSignatureMethod(SignedInfoType.SignatureMethod value) {
        this.signatureMethod = value;
    }

    public ReferenceType getReference() {
        return this.reference;
    }

    public void setReference(ReferenceType value) {
        this.reference = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CanonicalizationMethod implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlAttribute(name = "Algorithm", required = true) String algorithm = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";

        public String getAlgorithm() {
            if (this.algorithm == null) {
                return "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
            } else {
                return this.algorithm;
            }
        }

        public void setAlgorithm(String value) {
            this.algorithm = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SignatureMethod implements Serializable {

        private static final long serialVersionUID = 1L;

        @XmlAttribute(name = "Algorithm", required = true) protected String algorithm = "http://www.w3.org/2000/09/xmldsig#rsa-sha1";

        public String getAlgorithm() {
            if (this.algorithm == null) {
                return "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
            } else {
                return this.algorithm;
            }
        }

        public void setAlgorithm(String value) {
            this.algorithm = value;
        }
    }
}
