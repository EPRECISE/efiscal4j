
package eprecise.efiscal4j.signer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * Classe Java de TransformType complex type.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TransformType implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "XPath") List<String> xPath;

    private @XmlAttribute(name = "Algorithm", required = true) String algorithm;

    public TransformType() {
    }

    public TransformType(String algorithm) {
        this.algorithm = algorithm;
    }

    public List<String> getXPath() {
        if (this.xPath == null) {
            this.xPath = new ArrayList<String>();
        }
        return this.xPath;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

}
