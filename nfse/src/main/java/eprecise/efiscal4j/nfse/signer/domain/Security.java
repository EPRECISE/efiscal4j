
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class Security implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "BinarySecurityToken", namespace = NFSeNamespacesPrefixMapper.WSSE_URI) BinarySecurityToken binarySecurityToken;

    private @XmlElement(name = "Signature") Signature signature;

    public BinarySecurityToken getBinarySecurityToken() {
        return binarySecurityToken;
    }

    public Signature getSignature() {
        return signature;
    }

}
