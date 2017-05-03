
package eprecise.efiscal4j.signer.oasis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class OasisSecurityTokenReference implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns:" + OasisNamespacesPrefixMapper.WSU_PREFIX) final String xmlns = OasisNamespacesPrefixMapper.WSU_URI;

    private @XmlAttribute(name = "Id", namespace = OasisNamespacesPrefixMapper.WSU_URI) String id;

    private @XmlElement(name = "Reference", namespace = OasisNamespacesPrefixMapper.WSSE_URI) OasisSecurityTokenReferenceValue reference;

    public OasisSecurityTokenReferenceValue getReference() {
        return reference;
    }

}
