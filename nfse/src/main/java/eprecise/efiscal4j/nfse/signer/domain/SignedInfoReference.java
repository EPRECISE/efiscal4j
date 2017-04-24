
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class SignedInfoReference implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "URI") String uri;

    private @XmlElementWrapper(name = "Transforms", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) @XmlElement(
            name = "Transform", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) Collection<Transform> transforms;

    private @XmlElement(name = "DigestMethod", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) DigestMethod digestMethod;

    private @XmlElement(name = "DigestValue", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) String digestValue;

    public String getUri() {
        return uri;
    }

    public Collection<Transform> getTransforms() {
        return transforms;
    }

    public DigestMethod getDigestMethod() {
        return digestMethod;
    }

    public String getDigestValue() {
        return digestValue;
    }

}