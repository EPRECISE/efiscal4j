
package eprecise.efiscal4j.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * Classe Java de X509DataType complex type.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class X509DataType implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "X509Certificate", required = true) byte[] x509Certificate;

    public byte[] getX509Certificate() {
        return this.x509Certificate;
    }

    public void setX509Certificate(byte[] value) {
        this.x509Certificate = value;
    }

}
