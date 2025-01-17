
package eprecise.efiscal4j.nfe.v310.sharing;

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
import eprecise.efiscal4j.nfe.transmission.request.NFeNumberDisableDispatchRequest;
import eprecise.efiscal4j.nfe.v310.transmission.ObjectFactory;
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
@XmlType(propOrder = { "xmlns", "info", "signature", "version" })
public class NFeNumberDisableDispatch extends DefaultAssignable implements TransmissibleBodyImpl, NFeNumberDisableDispatchRequest {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v310/xsd/inutNFe_v3.10.xsd";

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "infInut") @NotNull @Valid final NFeNumberDisableInfo info;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlTransient QName qName = new QName(ObjectFactory.INUT_NFE);

    public static class Builder {

        private NFeNumberDisableInfo info;

        public Builder withInfo(final NFeNumberDisableInfo info) {
            this.info = info;
            return this;
        }

        public NFeNumberDisableDispatch build(final Signer signer) throws Exception {
            NFeNumberDisableDispatch entity = new NFeNumberDisableDispatch(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (NFeNumberDisableDispatch) signer.sign(entity);
            return entity;
        }
    }

    public NFeNumberDisableDispatch() {
        this.info = null;
    }

    public NFeNumberDisableDispatch(final Builder builder) {
        this.info = builder.info;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public NFeNumberDisableInfo getInfo() {
        return this.info;
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
        return new FiscalDocumentDeserializer<>(xml, NFeNumberDisableDispatch.class).considering(NFeNumberDisableDispatch.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(SignatureType.class);
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).considering(NFeNumberDisableDispatch.getValidationConsideringClasses()).serialize();
    }
}
