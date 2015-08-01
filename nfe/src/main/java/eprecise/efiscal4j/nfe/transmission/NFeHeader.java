
package eprecise.efiscal4j.nfe.transmission;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;


/**
 * 
 * @author Felipe Bueno
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    public @XmlTransient static final String BASE_XMLNS = "http://www.portalfiscal.inf.br/nfe/wsdl/";

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns;

    private @XmlElement(name = "cUF") @NotNull final UF uf;

    private @XmlElement(name = "versaoDados") @NotNull FiscalDocumentVersion dataVersion = FiscalDocumentVersion.VERSION_3_10;

    public static class Builder {

        private String xmlns;

        private UF uf;

        private FiscalDocumentVersion dataVersion;

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

        /**
         * 
         * @param dataVersion
         * @return
         */
        public Builder withDataVersion(FiscalDocumentVersion dataVersion) {
            this.dataVersion = dataVersion;
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
        if (builder.dataVersion != null) {
            this.dataVersion = builder.dataVersion;
        }
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public UF getUf() {
        return this.uf;
    }

    public FiscalDocumentVersion getDataVersion() {
        return this.dataVersion;
    }
}
