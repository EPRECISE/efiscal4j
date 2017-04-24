
package eprecise.efiscal4j.nfse.signer.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class X509Data implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "X509Certificate", namespace = NFSeNamespacesPrefixMapper.SIGNATURE_URI) byte[] x509Certificate;

    public byte[] getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(final byte[] x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

}
