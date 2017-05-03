
package eprecise.efiscal4j.signer.oasis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class OasisKeyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") String id;

    private @XmlElement(name = "X509Data", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI, required = true) OasisX509Data x509Data;

    private @XmlElement(name = "SecurityTokenReference", namespace = OasisNamespacesPrefixMapper.WSSE_URI) OasisSecurityTokenReference securityTokenReference;

    public String getId() {
        return id;
    }

    public OasisX509Data getX509Data() {
        return x509Data;
    }

    public void setX509Data(final OasisX509Data x509Data) {
        this.x509Data = x509Data;
    }

    public OasisSecurityTokenReference getSecurityTokenReference() {
        return securityTokenReference;
    }

}
