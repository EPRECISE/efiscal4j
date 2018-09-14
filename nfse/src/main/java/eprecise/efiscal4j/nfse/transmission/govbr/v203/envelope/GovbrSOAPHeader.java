
package eprecise.efiscal4j.nfse.transmission.govbr.v203.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.signer.oasis.domain.OasisSecurity;
import lombok.Builder;


@XmlAccessorType(XmlAccessType.FIELD)
@Builder
public class GovbrSOAPHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Security", namespace = OasisNamespacesPrefixMapper.WSSE_URI) OasisSecurity security;

}
