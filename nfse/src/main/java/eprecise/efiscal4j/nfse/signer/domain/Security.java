
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlRootElement(name = "Security", namespace = NFSeNamespacesPrefixMapper.WSSE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
public class Security implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns:" + NFSeNamespacesPrefixMapper.WSSE_PREFIX) final String xmlns = NFSeNamespacesPrefixMapper.WSSE_URI;

    private @XmlElement(name = "BinarySecurityToken", namespace = NFSeNamespacesPrefixMapper.WSSE_URI) BinarySecurityToken binarySecurityToken;

    private @XmlElement(name = "Signature", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) Signature signature;

    private @XmlAttribute(name = "mustUnderstand", namespace = NFSeNamespacesPrefixMapper.SOAPENV_URI) String mustUnderstand;

    public BinarySecurityToken getBinarySecurityToken() {
        return binarySecurityToken;
    }

    public Signature getSignature() {
        return signature;
    }

    public String getMustUnderstand() {
        return mustUnderstand;
    }

}
