
package eprecise.efiscal4j.nfse.transmission.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;


@XmlAccessorType(XmlAccessType.FIELD)
public class SoapHeaderBinarySecurityToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "EncodingType") String encodingType;

    private @XmlAttribute(name = "ValueType") String valueType;

    private @XmlAttribute(name = "wsu:Id") String id;

    private @XmlValue String value;

    public String getEncodingType() {
        return encodingType;
    }

    public String getValueType() {
        return valueType;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}
