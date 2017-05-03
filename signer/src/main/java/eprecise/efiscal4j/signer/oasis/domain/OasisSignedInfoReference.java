
package eprecise.efiscal4j.signer.oasis.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class OasisSignedInfoReference implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "URI") String uri;

    private @XmlElementWrapper(name = "Transforms", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) @XmlElement(
            name = "Transform", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) Collection<OasisTransform> transforms;

    private @XmlElement(name = "DigestMethod", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) OasisDigestMethod digestMethod;

    private @XmlElement(name = "DigestValue", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) String digestValue;

    public String getUri() {
        return uri;
    }

    public Collection<OasisTransform> getTransforms() {
        return transforms;
    }

    public OasisDigestMethod getDigestMethod() {
        return digestMethod;
    }

    public String getDigestValue() {
        return digestValue;
    }

}
