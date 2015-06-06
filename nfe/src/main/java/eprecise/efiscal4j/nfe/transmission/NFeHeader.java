
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "nfeCabecMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns;

    private @XmlElement(name = "cUF") @NotNull final UF uf;

    private @XmlElement(name = "versaoDados") @NotNull final String dataVersion = FiscalDocumentVersion.NFE_VERSION;

    public static class Builder {

        private String xmlns;

        private UF uf;

        /**
         * 
         * @param xmlns
         * @return
         */
        public Builder withXmlns(String xmlns) {
            this.xmlns = xmlns;
            return this;
        }

        /**
         * 
         * @param uf
         * @return
         */
        public Builder withUf(UF uf) {
            this.uf = uf;
            return this;
        }

        public NFeHeader build() {
            final NFeHeader entity = new NFeHeader(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeHeader() {
        this.xmlns = null;
        this.uf = null;
    }

    public NFeHeader(Builder builder) {
        this.xmlns = builder.xmlns;
        this.uf = builder.uf;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public UF getUf() {
        return this.uf;
    }

    public String getDataVersion() {
        return this.dataVersion;
    }
}
