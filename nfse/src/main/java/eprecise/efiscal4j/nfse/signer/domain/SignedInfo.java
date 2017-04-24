
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class SignedInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "CanonicalizationMethod", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) CanonicalizationMethod canonicalizationMethod;

    private @XmlElement(name = "SignatureMethod", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) SignatureMethod signatureMethod;

    private @XmlElement(name = "Reference", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) SignedInfoReference reference;

    public CanonicalizationMethod getCanonicalizationMethod() {
        return canonicalizationMethod;
    }

    public SignedInfoReference getReference() {
        return reference;
    }

}
