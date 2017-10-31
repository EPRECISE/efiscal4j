
package eprecise.efiscal4j.nfe.sharing;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.transmission.TransmissibleBodyImpl;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
import eprecise.efiscal4j.signer.domain.SignatureType;


/**
 * Tipo Pedido de Inutilização de Numeração da Nota Fiscal Eletrônica<
 *
 * @author Fernando C Glizt
 *
 */
@XmlRootElement(name = ObjectFactory.INUT_NFE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "xmlns", "nfeNumberDisableInfo", "signature", "version" })
public class NFeNumberDisable extends DefaultAssignable implements TransmissibleBodyImpl {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/xsd/inutNFe_v3.10.xsd";

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "infInut") @NotNull @Valid final NFeNumberDisableInfo nfeNumberDisableInfo;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlTransient QName qName = new QName(ObjectFactory.INUT_NFE);

    public static class Builder {

        private NFeNumberDisableInfo nfeNumberDisableInfo;

        public Builder withNFeNumberDisableInfo(final NFeNumberDisableInfo nfeNumberDisableInfo) {
            this.nfeNumberDisableInfo = nfeNumberDisableInfo;
            return this;
        }

        public NFeNumberDisable build(final Signer signer) throws Exception {
            NFeNumberDisable entity = new NFeNumberDisable(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (NFeNumberDisable) signer.sign(entity);
            return entity;
        }
    }

    public NFeNumberDisable() {
        this.nfeNumberDisableInfo = null;
    }

    public NFeNumberDisable(final Builder builder) {
        this.nfeNumberDisableInfo = builder.nfeNumberDisableInfo;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public NFeNumberDisableInfo getNfeNumberDisableInfo() {
        return this.nfeNumberDisableInfo;
    }

    @Override
    public void setQName(final QName qName) {
        this.qName = qName;
    }

    @Override
    public QName getQName() {
        return this.qName;
    }

    @Override
    public String getRootTagName() {
        return "inutNFe";
    }

    @Override
    public String getAssignableTagName() {
        return "infInut";
    }

    @Override
    public String getIdAttributeTagName() {
        return "Id";
    }

    @Override
    public DefaultAssignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, NFeNumberDisable.class).considering(NFeNumberDisable.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(SignatureType.class);
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).considering(NFeNumberDisable.getValidationConsideringClasses()).serialize();
    }
}
