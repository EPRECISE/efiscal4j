
package eprecise.efiscal4j.nfe.v400;

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

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentDeserializer;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.v400.danfe.JasperDanfeNfceUrlPath;
import eprecise.efiscal4j.nfe.v400.nfce.CSC;
import eprecise.efiscal4j.nfe.v400.person.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.v400.person.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.v400.qrCode.NFCeQRCodeBuilder;
import eprecise.efiscal4j.signer.Signer;
import eprecise.efiscal4j.signer.defaults.DefaultAssignable;
import eprecise.efiscal4j.signer.domain.SignatureType;


/**
 * Tipo Nota Fiscal Eletrônica
 *
 * @author Felipe Bueno
 *
 */
@XmlRootElement(name = "NFe")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "xmlns", "nFeInfo", "nFeSuplementaryInfo", "signature" })
public class NFe extends DefaultAssignable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v400/xsd/nfe_v4.00.xsd";

    private @XmlAttribute(name = "xmlns") @NotNull final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "infNFe") @NotNull final NFeInfo nFeInfo;

    private @XmlElement(name = "infNFeSupl") NFeSuplementaryInfo nFeSuplementaryInfo;

    public static class Builder {

        private NFeInfo nFeInfo;

        private CSC csc;

        private NFeSuplementaryInfo nFeSuplementaryInfo;

        /**
         * @see NFeInfo
         *
         * @param nFeInfo
         * @return
         */
        public Builder withNFeInfo(final NFeInfo nFeInfo) {
            this.nFeInfo = nFeInfo;
            return this;
        }

        /**
         * @see NFeSuplementaryInfo
         *
         * @param nFeSuplementaryInfo
         * @return
         */
        public Builder withNFeSuplementaryInfo(final NFeSuplementaryInfo nFeSuplementaryInfo) {
            this.nFeSuplementaryInfo = nFeSuplementaryInfo;
            return this;
        }

        /**
         * Código de Segurança do Contribuinte
         *
         * @param csc
         * @return
         */
        public Builder withCSC(final CSC csc) {
            this.csc = csc;
            return this;
        }

        public NFe build(final Signer signer) throws Exception {
            NFe entity = new NFe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            entity = (NFe) signer.sign(entity);
            if (entity.getNFeInfo().getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFCE)) {
                if (this.csc == null) {
                    throw new IllegalStateException("CSC não informado para NFCE");
                }
                entity.setnFeSuplementaryInfo(new NFeSuplementaryInfo.Builder().withQrCode(new NFCeQRCodeBuilder(entity, this.csc).build()).withUrlQueryByKey(
                        JasperDanfeNfceUrlPath.QUERY.getUrl(entity.getNFeInfo().getEmitter().getAdress().getCity().getUf(), entity.getNFeInfo().getnFeIdentification().getTransmissionEnvironment()))
                        .build());
            }
            return entity;
        }
    }

    public NFe() {
        this.nFeInfo = null;
        this.nFeSuplementaryInfo = null;
    }

    public NFe(final Builder builder) {
        this.nFeInfo = builder.nFeInfo;
        this.nFeSuplementaryInfo = builder.nFeSuplementaryInfo;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public NFeInfo getNFeInfo() {
        return this.nFeInfo;
    }

    public NFeSuplementaryInfo getnFeSuplementaryInfo() {
        return this.nFeSuplementaryInfo;
    }

    private void setnFeSuplementaryInfo(final NFeSuplementaryInfo nFeSuplementaryInfo) {
        this.nFeSuplementaryInfo = nFeSuplementaryInfo;
    }

    @Override
    public String getAsXml() {
        return new FiscalDocumentSerializer<>(this).considering(NFe.getValidationConsideringClasses()).serialize();
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
    public DefaultAssignable getAsEntity(final String xml) {
        return new FiscalDocumentDeserializer<>(xml, NFe.class).considering(NFe.getValidationConsideringClasses()).deserialize();
    }

    public static List<Class<?>> getValidationConsideringClasses() {
        return Arrays.asList(LegalEntityDocuments.class, NaturalPersonDocuments.class, SignatureType.class);
    }
}
