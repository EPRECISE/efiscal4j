
package eprecise.efiscal4j.nfe.item.di;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.types.NFeDate;
import eprecise.efiscal4j.nfe.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Declaração de Importação (NT 2011/004)
 *
 * @author Felipe Bueno
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportDeclaration implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlElement(name = "nDI") @NotNull @Size(min = 1, max = 12) @NFeString final String number;

    private @XmlElement(name = "dDI") @NotNull @NFeDate final String date;

    private @XmlElement(name = "xLocDesemb") @NotNull @Size(min = 1, max = 60) @NFeString final String clearanceSpot;

    private @XmlElement(name = "UFDesemb") @NotNull final String clearanceUf;

    private @XmlElement(name = "dDesemb") @NotNull @NFeDate final String clearanceDate;

    private @XmlElement(name = "tpViaTransp") @NotNull final InternationalTransportPathway internationalTransportPathway;

    private @XmlElement(name = "vAFRMM") @NFeDecimal1302 final String additValShipMerchMarineRenovation;

    private @XmlElement(name = "tpIntermedio") @NotNull final IntermediaryImportType intermediaryImportType;

    private @XmlElement(name = "CNPJ") @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ final String acquirerOrOrderingPartyCnpj;

    private @XmlElement(name = "UFTerceiro") final UF acquirerOrOrderingPartyUf;

    private @XmlElement(name = "cExportador") @NotNull @Size(min = 1, max = 60) @NFeString final String exporterCode;

    private @XmlElement(name = "adi") @Size(max = 100) @Valid final List<Addition> additions;

    public static class Builder {

        private String number;

        private String date;

        private String clearanceSpot;

        private String clearanceUf;

        private String clearanceDate;

        private InternationalTransportPathway internationalTransportPathway;

        private String additValShipMerchMarineRenovation;

        private IntermediaryImportType intermediaryImportType;

        private String acquirerOrOrderingPartyCnpj;

        private UF acquirerOrOrderingPartyUf;

        private String exporterCode;

        private List<Addition> additions;

        /**
         * Numero do Documento de Importação DI/DSI/DA/DRI-E (DI/DSI/DA/DRI-E) (NT2011/004)
         * 
         * @param number
         * @return
         */
        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        /**
         * Data de registro da DI/DSI/DA (AAAA-MM-DD)
         * 
         * @param date
         * @return
         */
        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        /**
         * Local do desembaraço aduaneiro
         * 
         * @param clearanceSpot
         * @return
         */
        public Builder withClearanceSpot(String clearanceSpot) {
            this.clearanceSpot = clearanceSpot;
            return this;
        }

        /**
         * UF onde ocorreu o desembaraço aduaneiro
         * 
         * @param clearanceUf
         * @return
         */
        public Builder withClearanceUf(UF clearanceUf) {
            this.clearanceUf = clearanceUf.getAcronym();
            return this;
        }

        /**
         * Data do desembaraço aduaneiro (AAAA-MM-DD)
         * 
         * @param clearanceDate
         * @return
         */
        public Builder withClearanceDate(String clearanceDate) {
            this.clearanceDate = clearanceDate;
            return this;
        }

        /**
         * @see InternationalTransportPathway
         * @param internationalTransportPathway
         * @return
         */
        public Builder withInternationalTransportPathway(InternationalTransportPathway internationalTransportPathway) {
            this.internationalTransportPathway = internationalTransportPathway;
            return this;
        }

        /**
         * Valor Adicional ao frete para renovação de marinha mercante
         * 
         * @param additValShipMerchMarineRenovation
         * @return
         */
        public Builder withAdditValShipMerchMarineRenovation(String additValShipMerchMarineRenovation) {
            this.additValShipMerchMarineRenovation = additValShipMerchMarineRenovation;
            return this;
        }

        /**
         * @see IntermediaryImportType
         * @param intermediaryImportType
         * @return
         */
        public Builder withIntermediaryImportType(IntermediaryImportType intermediaryImportType) {
            this.intermediaryImportType = intermediaryImportType;
            return this;
        }

        /**
         * CNPJ do adquirente ou do encomendante
         * 
         * @param acquirerOrOrderingPartyCnpj
         * @return
         */
        public Builder withAcquirerOrOrderingPartyCnpj(String acquirerOrOrderingPartyCnpj) {
            this.acquirerOrOrderingPartyCnpj = acquirerOrOrderingPartyCnpj;
            return this;
        }

        /**
         * Sigla da UF do adquirente ou do encomendante
         * 
         * @param acquirerOrOrderingPartyUf
         * @return
         */
        public Builder withAcquirerOrOrderingPartyUf(UF acquirerOrOrderingPartyUf) {
            this.acquirerOrOrderingPartyUf = acquirerOrOrderingPartyUf;
            return this;
        }

        /**
         * Código do exportador (usado nos sistemas internos de informação do emitente da NF-e)
         * 
         * @param exporterCode
         * @return
         */
        public Builder withExporterCode(String exporterCode) {
            this.exporterCode = exporterCode;
            return this;
        }

        /**
         * 
         * @see Addition
         * @param additions
         * @return
         */
        public Builder withAdditions(List<Addition> additions) {
            this.additions = additions;
            return this;
        }

        public ImportDeclaration build() {
            final ImportDeclaration entity = new ImportDeclaration(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public ImportDeclaration() {
        this.number = null;
        this.date = null;
        this.clearanceSpot = null;
        this.clearanceUf = null;
        this.clearanceDate = null;
        this.internationalTransportPathway = null;
        this.additValShipMerchMarineRenovation = null;
        this.intermediaryImportType = null;
        this.acquirerOrOrderingPartyCnpj = null;
        this.acquirerOrOrderingPartyUf = null;
        this.exporterCode = null;
        this.additions = null;
    }

    public ImportDeclaration(Builder builder) {
        this.number = builder.number;
        this.date = builder.date;
        this.clearanceSpot = builder.clearanceSpot;
        this.clearanceUf = builder.clearanceUf;
        this.clearanceDate = builder.clearanceDate;
        this.internationalTransportPathway = builder.internationalTransportPathway;
        this.additValShipMerchMarineRenovation = builder.additValShipMerchMarineRenovation;
        this.intermediaryImportType = builder.intermediaryImportType;
        this.acquirerOrOrderingPartyCnpj = builder.acquirerOrOrderingPartyCnpj;
        this.acquirerOrOrderingPartyUf = builder.acquirerOrOrderingPartyUf;
        this.exporterCode = builder.exporterCode;
        this.additions = builder.additions;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDate() {
        return this.date;
    }

    public String getClearanceSpot() {
        return this.clearanceSpot;
    }

    public UF getClearanceUf() {
        return UF.findByAcronym(this.clearanceUf);
    }

    public String getClearanceDate() {
        return this.clearanceDate;
    }

    public InternationalTransportPathway getInternationalTransportPathway() {
        return this.internationalTransportPathway;
    }

    public String getAdditValShipMerchMarineRenovation() {
        return this.additValShipMerchMarineRenovation;
    }

    public IntermediaryImportType getIntermediaryImportType() {
        return this.intermediaryImportType;
    }

    public String getAcquirerOrOrderingPartyCnpj() {
        return this.acquirerOrOrderingPartyCnpj;
    }

    public UF getAcquirerOrOrderingPartyUf() {
        return this.acquirerOrOrderingPartyUf;
    }

    public String getExporterCode() {
        return this.exporterCode;
    }

    public List<Addition> getAdditions() {
        return this.additions;
    }

}
