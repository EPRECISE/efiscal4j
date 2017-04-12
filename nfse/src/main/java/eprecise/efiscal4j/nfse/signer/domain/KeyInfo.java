
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class KeyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") String id;

    private @XmlElement(name = "SecurityTokenReference", namespace = NFSeNamespacesPrefixMapper.WSSE_URI) SecurityTokenReference securityTokenReference;

    public String getId() {
        return id;
    }

    public SecurityTokenReference getSecurityTokenReference() {
        return securityTokenReference;
    }

}
