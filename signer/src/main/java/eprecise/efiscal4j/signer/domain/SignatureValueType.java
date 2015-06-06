
package eprecise.efiscal4j.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 * Classe Java de SignatureValueType complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SignatureValueType implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlValue protected byte[] value;

    public byte[] getValue() {
        return this.value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

}
