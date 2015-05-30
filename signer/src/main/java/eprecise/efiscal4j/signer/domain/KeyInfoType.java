
package eprecise.efiscal4j.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * Classe Java de KeyInfoType complex type.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class KeyInfoType implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "X509Data", required = true) X509DataType x509Data;

    public X509DataType getX509Data() {
        return this.x509Data;
    }

    public void setX509Data(X509DataType value) {
        this.x509Data = value;
    }
}
