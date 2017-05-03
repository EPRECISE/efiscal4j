
package eprecise.efiscal4j.signer.oasis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class OasisSignedInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "CanonicalizationMethod", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) OasisCanonicalizationMethod canonicalizationMethod;

    private @XmlElement(name = "SignatureMethod", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) OasisSignatureMethod signatureMethod;

    private @XmlElement(name = "Reference", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) OasisSignedInfoReference reference;

    public OasisCanonicalizationMethod getCanonicalizationMethod() {
        return canonicalizationMethod;
    }

    public OasisSignedInfoReference getReference() {
        return reference;
    }

}
