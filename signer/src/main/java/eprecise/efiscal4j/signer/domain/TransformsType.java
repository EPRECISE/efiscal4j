
package eprecise.efiscal4j.signer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * Classe Java de TransformsType complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TransformsType implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Transform", required = true) List<TransformType> transform;

    public TransformsType() {
        this.transform = new ArrayList<TransformType>();
        this.transform.add(new TransformType("http://www.w3.org/2000/09/xmldsig#enveloped-signature"));
        this.transform.add(new TransformType("http://www.w3.org/TR/2001/REC-xml-c14n-20010315"));
    }

    public List<TransformType> getTransform() {
        if (this.transform == null) {
            this.transform = new ArrayList<TransformType>();
        }
        return this.transform;
    }

}
