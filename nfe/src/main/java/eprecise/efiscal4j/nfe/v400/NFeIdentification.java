
package eprecise.efiscal4j.nfe.v400;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.v400.refdocuments.ReferencedDocuments;
import eprecise.efiscal4j.nfe.v400.types.NFeCityIBGECode;
import eprecise.efiscal4j.nfe.v400.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.v400.types.NFeFiscalDocumentNumber;
import eprecise.efiscal4j.nfe.v400.types.NFeFiscalDocumentSeries;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


@XmlAccessorType(XmlAccessType.FIELD)
public class NFeIdentification implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "cUF") UF ufIbgeCode;

    private @XmlElement(name = "cNF") @Pattern(regexp = "[0-9]{8}") String nFeCode;

    private @XmlElement(name = "natOp") @NotNull @Size(min = 1, max = 60) @NFeString final String operationType;

    private @XmlElement(name = "mod") @NotNull final FiscalDocumentModel fiscalDocumentModel;

    private @XmlElement(name = "serie") @NotNull @NFeFiscalDocumentSeries final String fiscalDocumentSeries;

    private @XmlElement(name = "nNF") @NotNull @NFeFiscalDocumentNumber final String fiscalDocumentNumber;

    private @XmlElement(name = "dhEmi") @NotNull @NFeDateTimeUTC final String emissionDateTime;

    private @XmlElement(name = "dhSaiEnt") @NFeDateTimeUTC final String entranceOrExitDateTime;

    private @XmlElement(name = "tpNF") @NotNull final FiscalDocumentType fiscalDocumentType;

    private @XmlElement(name = "idDest") @NotNull final DestinationOperationIdentifier destinationOperationIdentifier;

    private @XmlElement(name = "cMunFG") @NotNull @NFeCityIBGECode final String taxableEventCityIbgeCode;

    private @XmlElement(name = "tpImp") @NotNull final DANFEPrintFormat danfePrintFormat;

    private @XmlElement(name = "tpEmis") @NotNull final NFeTransmissionMethod nFeTransmissionMethod;

    private @XmlElement(name = "cDV") @NotNull final @Pattern(regexp = "[0-9]{1}") String checksum;

    private @XmlElement(name = "tpAmb") @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "finNFe") @NotNull final NFeFinality nFeFinality;

    private @XmlElement(name = "indFinal") @NotNull final FinalCustomerOperation finalCustomerOperation;

    private @XmlElement(name = "indPres") @NotNull final PurchaserPresenceIndicator purchaserPresenceIndicator;

    private @XmlElement(name = "procEmi") @NotNull final NFeTransmissionProcess nFeTransmissionProcess;

    private @XmlElement(name = "verProc") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "NFref") @Size(max = 500) @Valid final List<ReferencedDocuments> referencedDocuments;

    public static class Builder {

        private UF ufIbgeCode;

        private String nFeCode;

        private String operationType;

        private FiscalDocumentModel fiscalDocumentModel;

        private String fiscalDocumentSeries;

        private String fiscalDocumentNumber;

        private String emissionDateTime;

        private String entranceOrExitDateTime;

        private FiscalDocumentType fiscalDocumentType;

        private DestinationOperationIdentifier destinationOperationIdentifier;

        private String taxableEventCityIbgeCode;

        private DANFEPrintFormat danfePrintFormat;

        private NFeTransmissionMethod nFeTransmissionMethod;
        
        private String checksum;

        private TransmissionEnvironment transmissionEnvironment;

        private NFeFinality nFeFinality;

        private FinalCustomerOperation finalCustomerOperation;

        private PurchaserPresenceIndicator purchaserPresenceIndicator;

        private NFeTransmissionProcess nFeTransmissionProcess;

        private String applicationVersion;

        private List<ReferencedDocuments> referencedDocuments;

        public Builder withUFIbgeCode(UF ufIbgeCode) {
            this.ufIbgeCode = ufIbgeCode;
            return this;
        }

        public Builder withNFeCode(String nFeCode) {
            this.nFeCode = nFeCode;
            return this;
        }

        public Builder withOperationType(String operationType) {
            this.operationType = operationType;
            return this;
        }

        public Builder withFiscalDocumentModel(FiscalDocumentModel fiscalDocumentModel) {
            this.fiscalDocumentModel = fiscalDocumentModel;
            return this;
        }

        /**
         * Serie Normal 0-889 Avulsa Fisco 890-899 SCAN 900-999
         * 
         * @param fiscalDocumentSeries
         * @return
         */
        public Builder withFiscalDocumentSeries(String fiscalDocumentSeries) {
            this.fiscalDocumentSeries = fiscalDocumentSeries;
            return this;
        }

        public Builder withFiscalDocumentNumber(String fiscalDocumentNumber) {
            this.fiscalDocumentNumber = fiscalDocumentNumber;
            return this;
        }

        public Builder withEmissionDateTime(String emissionDateTime) {
            this.emissionDateTime = emissionDateTime;
            return this;
        }

        public Builder withEntranceOrExitDateTime(String entranceOrExitDateTime) {
            this.entranceOrExitDateTime = entranceOrExitDateTime;
            return this;
        }

        public Builder withFiscalDocumentType(FiscalDocumentType fiscalDocumentType) {
            this.fiscalDocumentType = fiscalDocumentType;
            return this;
        }

        public Builder withDestinationOperationIdentifier(DestinationOperationIdentifier destinationOperationIdentifier) {
            this.destinationOperationIdentifier = destinationOperationIdentifier;
            return this;
        }

        public Builder withTaxableEventCityIbgeCode(String taxableEventCityIbgeCode) {
            this.taxableEventCityIbgeCode = taxableEventCityIbgeCode;
            return this;
        }

        public Builder withDanfePrintFormat(DANFEPrintFormat danfePrintFormat) {
            this.danfePrintFormat = danfePrintFormat;
            return this;
        }

        public Builder withNFeTransmissionMethod(NFeTransmissionMethod nFeTransmissionMethod) {
            this.nFeTransmissionMethod = nFeTransmissionMethod;
            return this;
        }
        
        public Builder withChecksum(String checksum) {
            this.checksum = checksum;
            return this;
        }

        public Builder withTransmissionEnvironment(TransmissionEnvironment transmissionEnvironment) {
            this.transmissionEnvironment = transmissionEnvironment;
            return this;
        }

        public Builder withNFeFinality(NFeFinality nFeFinality) {
            this.nFeFinality = nFeFinality;
            return this;
        }

        public Builder withFinalCustomerOperation(FinalCustomerOperation finalCustomerOperation) {
            this.finalCustomerOperation = finalCustomerOperation;
            return this;
        }

        public Builder withPurchaserPresenceIndicator(PurchaserPresenceIndicator purchaserPresenceIndicator) {
            this.purchaserPresenceIndicator = purchaserPresenceIndicator;
            return this;
        }

        public Builder withNFeTransmissionProcess(NFeTransmissionProcess nFeTransmissionProcess) {
            this.nFeTransmissionProcess = nFeTransmissionProcess;
            return this;
        }

        public Builder withApplicationVersion(String applicationVersion) {
            this.applicationVersion = applicationVersion;
            return this;
        }

        /**
         * @see ReferencedDocuments
         * @param referencedDocuments
         * @return
         */
        public Builder withReferencedDocuments(List<ReferencedDocuments> referencedDocuments) {
            this.referencedDocuments = referencedDocuments;
            return this;
        }

        public NFeIdentification build() {
            return new NFeIdentification(this);
        }
    }

    public NFeIdentification() {
        this.operationType = null;
        this.fiscalDocumentModel = null;
        this.fiscalDocumentSeries = null;
        this.fiscalDocumentNumber = null;
        this.emissionDateTime = null;
        this.entranceOrExitDateTime = null;
        this.fiscalDocumentType = null;
        this.destinationOperationIdentifier = null;
        this.taxableEventCityIbgeCode = null;
        this.danfePrintFormat = null;
        this.nFeTransmissionMethod = null;
        this.checksum = null;
        this.transmissionEnvironment = null;
        this.nFeFinality = null;
        this.finalCustomerOperation = null;
        this.purchaserPresenceIndicator = null;
        this.nFeTransmissionProcess = null;
        this.applicationVersion = null;
        this.referencedDocuments = null;
    }

    public NFeIdentification(Builder builder) {
        this.ufIbgeCode = builder.ufIbgeCode;
        this.nFeCode = builder.nFeCode;
        this.operationType = builder.operationType;
        this.fiscalDocumentModel = builder.fiscalDocumentModel;
        this.fiscalDocumentSeries = builder.fiscalDocumentSeries;
        this.fiscalDocumentNumber = builder.fiscalDocumentNumber;
        this.emissionDateTime = builder.emissionDateTime;
        this.entranceOrExitDateTime = builder.entranceOrExitDateTime;
        this.fiscalDocumentType = builder.fiscalDocumentType;
        this.destinationOperationIdentifier = builder.destinationOperationIdentifier;
        this.taxableEventCityIbgeCode = builder.taxableEventCityIbgeCode;
        this.danfePrintFormat = builder.danfePrintFormat;
        this.nFeTransmissionMethod = builder.nFeTransmissionMethod;
        this.checksum = builder.checksum;
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.nFeFinality = builder.nFeFinality;
        this.finalCustomerOperation = builder.finalCustomerOperation;
        this.purchaserPresenceIndicator = builder.purchaserPresenceIndicator;
        this.nFeTransmissionProcess = builder.nFeTransmissionProcess;
        this.applicationVersion = builder.applicationVersion;
        this.referencedDocuments = builder.referencedDocuments;
    }

    public UF getUfIbgeCode() {
        return this.ufIbgeCode;
    }

    public String getnFeCode() {
        return this.nFeCode;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public FiscalDocumentModel getFiscalDocumentModel() {
        return this.fiscalDocumentModel;
    }

    public String getFiscalDocumentSeries() {
        return this.fiscalDocumentSeries;
    }

    public String getFiscalDocumentNumber() {
        return this.fiscalDocumentNumber;
    }

    public String getEmissionDateTime() {
        return this.emissionDateTime;
    }

    public FiscalDocumentType getFiscalDocumentType() {
        return this.fiscalDocumentType;
    }

    public DestinationOperationIdentifier getDestinationOperationIdentifier() {
        return this.destinationOperationIdentifier;
    }

    public String getTaxableEventCityIbgeCode() {
        return this.taxableEventCityIbgeCode;
    }

    public DANFEPrintFormat getDanfePrintFormat() {
        return this.danfePrintFormat;
    }

    public NFeTransmissionMethod getnFeTransmissionMethod() {
        return this.nFeTransmissionMethod;
    }

    public String getChecksum() {
        return this.checksum;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public NFeFinality getnFeFinality() {
        return this.nFeFinality;
    }

    public FinalCustomerOperation getFinalCustomerOperation() {
        return this.finalCustomerOperation;
    }

    public PurchaserPresenceIndicator getPurchaserPresenceIndicator() {
        return this.purchaserPresenceIndicator;
    }

    public NFeTransmissionProcess getnFeTransmissionProcess() {
        return this.nFeTransmissionProcess;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public List<ReferencedDocuments> getReferencedDocuments() {
        return this.referencedDocuments;
    }

    public String getEntranceOrExitDateTime() {
        return entranceOrExitDateTime;
    }

}
