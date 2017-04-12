
package eprecise.efiscal4j.nfse.transmission.envelope;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.signer.NFSeNamespacesPrefixMapper;
import eprecise.efiscal4j.nfse.signer.domain.Security;


@XmlAccessorType(XmlAccessType.FIELD)
public class SOAPHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "Security", namespace = NFSeNamespacesPrefixMapper.WSSE_URI) Security security;

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
