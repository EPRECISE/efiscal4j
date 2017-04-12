
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityTokenReferenceValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "URI") String uri;

    private @XmlAttribute(name = "ValueType") String valueType;

    public String getUri() {
        return uri;
    }

    public String getValueType() {
        return valueType;
    }

}
