
package eprecise.efiscal4j.nfse.transmission.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPHeaderSecurity implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "wsse:BinarySecurityToken") SoapHeaderBinarySecurityToken binarySecurityToken;

    public SoapHeaderBinarySecurityToken getBinarySecurityToken() {
        return binarySecurityToken;
    }

}
