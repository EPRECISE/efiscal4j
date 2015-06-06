
package eprecise.efiscal4j.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;


/**
 * 
 * Classe Java de ReferenceType complex type.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferenceType implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Transforms", required = true) TransformsType transforms = new TransformsType();

    private @XmlElement(name = "DigestMethod", required = true) DigestMethod digestMethod = new DigestMethod();

    private @XmlElement(name = "DigestValue", required = true) byte[] digestValue;

    private @XmlAttribute(name = "URI", required = true) String uri;

    public TransformsType getTransforms() {
        return this.transforms;
    }

    public void setTransforms(TransformsType value) {
        this.transforms = value;
    }

    public ReferenceType.DigestMethod getDigestMethod() {
        return this.digestMethod;
    }

    public void setDigestMethod(ReferenceType.DigestMethod value) {
        this.digestMethod = value;
    }

    public byte[] getDigestValue() {
        return this.digestValue;
    }

    public void setDigestValue(byte[] value) {
        this.digestValue = value;
    }

    public String getURI() {
        return this.uri;
    }

    public void setURI(String value) {
        this.uri = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DigestMethod implements Serializable {

        private static final long serialVersionUID = 1L;

        private @XmlAttribute(name = "Algorithm", required = true) @XmlSchemaType(name = "anyURI") String algorithm = "http://www.w3.org/2000/09/xmldsig#sha1";

        public String getAlgorithm() {
            if (this.algorithm == null) {
                return "http://www.w3.org/2000/09/xmldsig#sha1";
            } else {
                return this.algorithm;
            }
        }

        public void setAlgorithm(String value) {
            this.algorithm = value;
        }

    }

}
