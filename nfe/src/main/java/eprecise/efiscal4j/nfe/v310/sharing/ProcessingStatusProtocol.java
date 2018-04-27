
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;


/**
 * 
 * Tipo Protocolo de status resultado do processamento da NF-e
 * 
 * @author Felipe Bueno
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessingStatusProtocol implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlElement(name = "infProt") @NotNull @Valid final ProcessingStatusProtocolInfo processingStatusProtocolInfo;

    public static class Builder {

        private ProcessingStatusProtocolInfo processingStatusProtocolInfo;

        public Builder withProcessingStatusProtocolInfo(ProcessingStatusProtocolInfo processingStatusProtocolInfo) {
            this.processingStatusProtocolInfo = processingStatusProtocolInfo;
            return this;
        }

        public ProcessingStatusProtocol build() {
            return new ProcessingStatusProtocol(this);
        }
    }

    public ProcessingStatusProtocol() {
        this.processingStatusProtocolInfo = null;
    }

    public ProcessingStatusProtocol(Builder builder) {
        this.processingStatusProtocolInfo = builder.processingStatusProtocolInfo;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public ProcessingStatusProtocolInfo getProcessingStatusProtocolInfo() {
        return this.processingStatusProtocolInfo;
    }

}
