
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityTokenReference implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Reference", namespace = NFSeNamespacesPrefixMapper.WSSE_URI) SecurityTokenReferenceValue reference;

    public SecurityTokenReferenceValue getReference() {
        return reference;
    }

}
