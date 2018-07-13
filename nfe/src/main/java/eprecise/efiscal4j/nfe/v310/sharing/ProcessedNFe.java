
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.FiscalDocument;
import eprecise.efiscal4j.nfe.v310.NFe;
import eprecise.efiscal4j.nfe.v310.sharing.adapters.processedFiscalDocument.ProcessedFiscalDocumentAdapter;
import eprecise.efiscal4j.nfe.version.ProcessedNFeVersion;


/**
 * Tipo da NF-e processada
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "nfeProc")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessedNFe implements Serializable, ProcessedNFeVersion {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v310/xsd/procNFe_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "NFe") @NotNull @Valid final NFe nfe;

    private @XmlElement(name = "protNFe") @NotNull @Valid final ProcessingStatusProtocol processingStatusProtocol;

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
    
    public ProcessedNFe(final NFeDispatch dispatchRequest, final NFeDispatchResponseMethod dispatchResponse) {
    	this.nfe = dispatchRequest.getnFes().stream().findFirst().orElse(null);
    	this.processingStatusProtocol = dispatchResponse.getnFeDispatchResponse().getProcessingStatusProtocol();
    }

    private ProcessedNFe(Builder builder) {
        this.nfe = builder.nfe;
        this.processingStatusProtocol = builder.processingStatusProtocol;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public NFe getNfe() {
        return this.nfe;
    }

    public ProcessingStatusProtocol getProcessingStatusProtocol() {
        return this.processingStatusProtocol;
    }

    @Override
    public FiscalDocument.Processed buildProcessedFiscalDocument() {
        return new ProcessedFiscalDocumentAdapter(this).buildProcessedFiscalDocument();
    }

	@Override
	public String getXsdPath() {
		return this.XSD;
	}

}
