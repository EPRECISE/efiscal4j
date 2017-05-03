
package eprecise.efiscal4j.nfse;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class CompNFSe implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") String xmlns;

    private final @XmlElement(name = "Nfse") NFSe nfse;

    private final @XmlElement(name = "Xml") String xml;

    public static class Builder {

        private NFSe nfse;

        private String xml;

        /**
         *
         * @param xml
         * @return
         */
        public Builder withXml(final String xml) {
            this.xml = xml;
            return this;
        }

        /**
         *
         * @param nfse
         * @return
         */
        public Builder withNFSe(final NFSe nfse) {
            this.nfse = nfse;
            return this;
        }

        public CompNFSe build() {
            final CompNFSe entity = new CompNFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public CompNFSe() {
        nfse = null;
        xml = null;
    }

    public CompNFSe(final Builder builder) {
        nfse = builder.nfse;
        xml = builder.xml;
    }

    public NFSe getNfse() {
        return nfse;
    }

    public String getXml() {
        return xml;
    }

}
