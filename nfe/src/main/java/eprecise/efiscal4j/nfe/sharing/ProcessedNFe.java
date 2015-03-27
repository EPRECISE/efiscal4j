
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.commons.xml.FiscalDocumentSerializer;
import eprecise.efiscal4j.nfe.LegalEntityDocuments;
import eprecise.efiscal4j.nfe.NFe;
import eprecise.efiscal4j.nfe.NaturalPersonDocuments;
import eprecise.efiscal4j.nfe.types.NFeVersion;
import eprecise.efiscal4j.signer.Assignable;


/**
 * Tipo da NF-e processada
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "nfeProc")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessedNFe implements Serializable, Assignable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final String version = NFeVersion.NFE_VERSION;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "NFe") @NotNull @Valid final NFe nfe;

    private @XmlElement(name = "protNFe") @NotNull @Valid final ProcessingStatusProtocol processingStatusProtocol;

    private @XmlTransient String signedXml;

    public static class Builder {

        private NFe nfe;

        private ProcessingStatusProtocol processingStatusProtocol;

        /**
         * 
         * @param nfe
         * @return
         */
        public Builder withNfe(NFe nfe) {
            this.nfe = nfe;
            return this;
        }

        /**
         * @see ProcessingStatusProtocol
         * @param processingStatusProtocol
         * @return
         */
        public Builder withProcessingStatusProtocol(ProcessingStatusProtocol processingStatusProtocol) {
            this.processingStatusProtocol = processingStatusProtocol;
            return this;
        }

        public ProcessedNFe build() {
            final ProcessedNFe entity = new ProcessedNFe(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ProcessedNFe() {
        this.nfe = null;
        this.processingStatusProtocol = null;
    }

    public ProcessedNFe(Builder builder) {
        this.nfe = builder.nfe;
        this.processingStatusProtocol = builder.processingStatusProtocol;
    }

    public NFe getNfe() {
        return this.nfe;
    }

    public ProcessingStatusProtocol getProcessingStatusProtocol() {
        return this.processingStatusProtocol;
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
