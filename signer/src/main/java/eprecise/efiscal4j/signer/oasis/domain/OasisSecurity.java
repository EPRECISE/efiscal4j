
package eprecise.efiscal4j.signer.oasis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;


@XmlRootElement(name = "Security", namespace = OasisNamespacesPrefixMapper.WSSE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
public class OasisSecurity implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "BinarySecurityToken", namespace = OasisNamespacesPrefixMapper.WSSE_URI) OasisBinarySecurityToken binarySecurityToken;

    private @XmlElement(name = "Signature", namespace = OasisNamespacesPrefixMapper.SIGNATURE_URI) OasisSignature signature;

    private @XmlAttribute(name = "mustUnderstand", namespace = OasisNamespacesPrefixMapper.SOAPENV_URI) String mustUnderstand;

    public OasisBinarySecurityToken getBinarySecurityToken() {
        return binarySecurityToken;
    }

    public OasisSignature getSignature() {
        return signature;
    }

    public String getMustUnderstand() {
        return mustUnderstand;
    }

}
