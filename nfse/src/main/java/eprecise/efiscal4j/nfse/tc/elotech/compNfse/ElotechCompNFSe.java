
package eprecise.efiscal4j.nfse.tc.elotech.compNfse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfse.domain.comp.CompNFSeWithXml;


@XmlRootElement(name = "CompNfse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElotechCompNFSe implements CompNFSeWithXml {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") String xmlns;

    private final @XmlElement(name = "Nfse") ElotechNFSe nfse;

    private final @XmlElement(name = "Xml") String xml;

    public static class Builder {

        private ElotechNFSe nfse;

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
        public Builder withNFSe(final ElotechNFSe nfse) {
            this.nfse = nfse;
            return this;
        }

        public ElotechCompNFSe build() {
            final ElotechCompNFSe entity = new ElotechCompNFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ElotechCompNFSe() {
        xml = null;
        nfse = null;
    }

    public ElotechCompNFSe(final Builder builder) {
        nfse = builder.nfse;
        xml = builder.xml;
    }

    @Override
    public ElotechNFSe getProcessedNfse() {
        return nfse;
    }

    @Override
    public String getXml() {
        return xml;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(final String xmlns) {
        this.xmlns = xmlns;
    }

}
