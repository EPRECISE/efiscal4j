
package eprecise.efiscal4j.nfe;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.signer.Assignable;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.domain.SignatureType;


/**
 * Tipo Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "NFe")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "xmlns", "nFeInfo", "signature" })
public class NFe extends Assignable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static String XSD = "/eprecise/efiscal4j/nfe/nfe_v3.10.xsd";

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "infNFe") @NotNull final NFeInfo nFeInfo;

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

        public NFe build(Signer signer) throws Exception {
            NFe entity = new NFe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (NFe) signer.sign(entity);
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
        return new FiscalDocumentSerializer<NFe>(this).considering(NFe.getValidationConsideringClasses()).serialize();
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

    @Override
    public Assignable getAsEntity(String xml) {
        return new FiscalDocumentDeserializer<NFe>(xml, NFe.class).considering(NFe.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(LegalEntityDocuments.class, NaturalPersonDocuments.class, SignatureType.class);
    }
}
