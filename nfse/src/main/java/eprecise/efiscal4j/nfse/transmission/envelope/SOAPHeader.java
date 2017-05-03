
package eprecise.efiscal4j.nfse.transmission.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.signer.oasis.OasisNamespacesPrefixMapper;
import eprecise.efiscal4j.signer.oasis.domain.OasisSecurity;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Security", namespace = OasisNamespacesPrefixMapper.WSSE_URI) OasisSecurity security;

    public static class Builder {

        public SOAPHeader build() {
            final SOAPHeader entity = new SOAPHeader(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public SOAPHeader() {

    }

    public SOAPHeader(final Builder builder) {
    }

}
