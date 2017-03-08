
package eprecise.efiscal4j.nfse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * Nota Fiscal de Serviço Eletrônica
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = "NFSe")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "InfNfse", "signature" })
public class NFSe extends Assignable implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "InfNfse") @NotNull final NFSeInfo info;

    public static class Builder {

        private NFSeInfo info;

        /**
         * @see NFSeInfo
         *
         * @param info
         * @return
         */
        public Builder withNFeInfo(final NFSeInfo info) {
            this.info = info;
            return this;
        }

        public NFSe build(final Signer signer) throws Exception {
            NFSe entity = new NFSe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (NFSe) signer.sign(entity);
            return entity;
        }
    }

    public NFSe() {
        this.info = null;
    }

    public NFSe(final Builder builder) {
        this.info = builder.info;
    }

    public NFSeInfo getInfo() {
        return info;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<NFSe>(this).considering(NFSe.getValidationConsideringClasses()).serialize();
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
    public Assignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<NFSe>(xml, NFSe.class).considering(NFSe.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(SignatureType.class);
    }
}
