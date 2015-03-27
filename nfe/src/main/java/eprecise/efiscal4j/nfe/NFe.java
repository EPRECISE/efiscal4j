
package eprecise.efiscal4j.nfe;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.signer.Assignable;


/**
 * Tipo Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "NFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFe implements Serializable, Assignable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "infNFe") @NotNull final NFeInfo nFeInfo;

    private @XmlTransient String signedXml;

    public static class Builder {

        private NFeInfo nFeInfo;

        /**
         * @see NFeInfo
         * 
         * @param nFeInfo
         * @return
         */
        public Builder withNFeInfo(NFeInfo nFeInfo) {
            this.nFeInfo = nFeInfo;
            return this;
        }

        public NFe build() {
            final NFe entity = new NFe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFe() {
        this.nFeInfo = null;
    }

    public NFe(Builder builder) {
        this.nFeInfo = builder.nFeInfo;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public NFeInfo getNFeInfo() {
        return this.nFeInfo;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).considering(LegalEntityDocuments.class, NaturalPersonDocuments.class).serialize();
    }

    @Override
    public String getSignedXml() {
        return this.signedXml;
    }

    @Override
    public void setSignedXml(String signedXml) {
        this.signedXml = signedXml;
    }

    @Override
    public String getRootTagName() {
        return "NFe";
    }

    @Override
    public String getAssignableTagName() {
        return "infNFe";
    }

    @Override
    public String getIdAttributeTagName() {
        return "Id";
    }

}
