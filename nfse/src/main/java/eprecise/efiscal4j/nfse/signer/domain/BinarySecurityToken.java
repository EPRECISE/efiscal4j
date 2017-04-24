
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class BinarySecurityToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns:" + NFSeNamespacesPrefixMapper.WSU_PREFIX) final String xmlns = NFSeNamespacesPrefixMapper.WSU_URI;

    private @XmlAttribute(name = "EncodingType") String encodingType;

    private @XmlAttribute(name = "ValueType") String valueType;

    private @XmlAttribute(name = "Id", namespace = NFSeNamespacesPrefixMapper.WSU_URI) String id;

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
