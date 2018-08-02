
package eprecise.efiscal4j.nfe.v400.summaries;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.caelum.stella.bean.validation.CNPJ;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.FiscalDocumentType;
import eprecise.efiscal4j.nfe.v400.types.Base64;
import eprecise.efiscal4j.nfe.v400.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.v400.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.v400.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.v400.types.NFeDecimal1302;
import eprecise.efiscal4j.nfe.v400.types.NFeDeliveryDFeEventProtocolNumber;
import eprecise.efiscal4j.nfe.v400.types.NFeDeliveryDFeStateRegistration;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


@XmlRootElement(name = "resNFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessedNFeSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v400/xsd/deliveryDFe/resNFe_v1.01.xsd";

    private @XmlTransient final NFeDateTimeUTC.Converter dateTimeConverter = new NFeDateTimeUTC.Converter();

    private @XmlTransient final NFeDeliveryDFeEventProtocolNumber.Converter protocolNumberConverter = new NFeDeliveryDFeEventProtocolNumber.Converter();

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_1_01;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "chNFe") @NotNull @NFeAccessKey final String accessKey;

    private @XmlElement(name = "CNPJ") @NotNull @CNPJ(formatted = false) @Size(max = 14) @NFeCNPJ final String cnpj; // Campo é uma xs:choice com CPF, não mapeado

    private @XmlElement(name = "xNome") @NotNull @Size(min = 2, max = 60) @NFeString final String name;

    private @XmlElement(name = "IE") @NotNull @NFeDeliveryDFeStateRegistration final String stateRegistration;

    private @XmlElement(name = "dhEmi") @NotNull @NFeDateTimeUTC final String emissionDateTime;

    private @XmlElement(name = "tpNF") @NotNull final FiscalDocumentType fiscalDocumentType;

    private @XmlElement(name = "vNF") @NotNull @NFeDecimal1302 final String nfeTotalValue;

    private @XmlElement(name = "digVal") @Base64 final String digestValue;

    private @XmlElement(name = "dhRecbto") @NotNull @NFeDateTimeUTC final String authorizationDateTime;

    private @XmlElement(name = "nProt") @NotNull @NFeDeliveryDFeEventProtocolNumber final String eventProtocolNumber;

    private @XmlElement(name = "cSitNFe") @NotNull final ProcessedNFeStatus nfeStatus;

    public static class Builder {

        private String accessKey;

        private String cnpj;

        private String name;

        private String stateRegistration;

        private ZonedDateTime emissionDateTime;

        private FiscalDocumentType fiscalDocumentType;

        private String nfeTotalValue;

        private String digestValue;

        private ZonedDateTime authorizationDateTime;

        private long eventProtocolNumber;

        private ProcessedNFeStatus nfeStatus;

        /**
         * Chave de acesso da NF-e
         */
        public Builder withAccessKey(String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        /**
         * CNPJ do Emitente
         */
        public Builder withCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        /**
         * Razão Social ou Nome do emitente
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Inscrição Estadual do Emitente
         */
        public Builder withStateRegistration(String stateRegistration) {
            this.stateRegistration = stateRegistration;
            return this;
        }

        /**
         * Data e Hora de emissão do Documento Fiscal (AAAA-MM-DDThh:mm:ssTZD) ex.: 2012-09-01T13:00:00-03:00
         */
        public Builder withEmissionDateTime(ZonedDateTime emissionDateTime) {
            this.emissionDateTime = emissionDateTime;
            return this;
        }

        /**
         * Tipo do Documento Fiscal (0 - entrada; 1 - saída)
         */
        public Builder withFiscalDocumentType(FiscalDocumentType fiscalDocumentType) {
            this.fiscalDocumentType = fiscalDocumentType;
            return this;
        }

        /**
         * Valor Total da NF-e
         */
        public Builder withNfeTotalValue(String nfeTotalValue) {
            this.nfeTotalValue = nfeTotalValue;
            return this;
        }

        /**
         * Digest Value da NF-e processada. Utilizado para conferir a integridade da NF-e original
         */
        public Builder withDigestValue(String digestValue) {
            this.digestValue = digestValue;
            return this;
        }

        /**
         * Data e hora de autorização da NF-e, no formato AAAA-MM-DDTHH:MM:SSTZD
         */
        public Builder withAuthorizationDateTime(ZonedDateTime authorizationDateTime) {
            this.authorizationDateTime = authorizationDateTime;
            return this;
        }

        /**
         * >Número do Protocolo de Status da NF-e. 1 posição (1 – Secretaria de Fazenda Estadual 2 – Receita Federal); 2 - códiga da UF - 2 posições ano; 10 seqüencial no ano
         */
        public Builder withEventProtocolNumber(long eventProtocolNumber) {
            this.eventProtocolNumber = eventProtocolNumber;
            return this;
        }

        /**
         * Situação da NF-e: 1=Uso autorizado; 2=Uso denegado; 3=NF-e Cancelada;
         */
        public Builder withNfeStatus(ProcessedNFeStatus nfeStatus) {
            this.nfeStatus = nfeStatus;
            return this;
        }

        public ProcessedNFeSummary build() {
            final ProcessedNFeSummary entity = new ProcessedNFeSummary(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public ProcessedNFeSummary() {
        this.accessKey = null;
        this.cnpj = null;
        this.name = null;
        this.stateRegistration = null;
        this.emissionDateTime = null;
        this.fiscalDocumentType = null;
        this.nfeTotalValue = null;
        this.digestValue = null;
        this.authorizationDateTime = null;
        this.eventProtocolNumber = null;
        this.nfeStatus = null;
    }

    private ProcessedNFeSummary(Builder builder) {
        this.accessKey = builder.accessKey;
        this.cnpj = builder.cnpj;
        this.name = builder.name;
        this.stateRegistration = builder.stateRegistration;
        this.emissionDateTime = this.dateTimeConverter.serialize(builder.emissionDateTime);
        this.fiscalDocumentType = builder.fiscalDocumentType;
        this.nfeTotalValue = builder.nfeTotalValue;
        this.digestValue = builder.digestValue;
        this.authorizationDateTime = this.dateTimeConverter.serialize(builder.authorizationDateTime);
        this.eventProtocolNumber = this.protocolNumberConverter.serialize(builder.eventProtocolNumber);
        this.nfeStatus = builder.nfeStatus;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getName() {
        return this.name;
    }

    public String getStateRegistration() {
        return this.stateRegistration;
    }

    public ZonedDateTime getEmissionDateTime() {
        return this.emissionDateTime == null ? null : this.dateTimeConverter.parse(this.emissionDateTime);
    }

    public FiscalDocumentType getFiscalDocumentType() {
        return this.fiscalDocumentType;
    }

    public String getNfeTotalValue() {
        return this.nfeTotalValue;
    }

    public String getDigestValue() {
        return this.digestValue;
    }

    public ZonedDateTime getAuthorizationDateTime() {
        return this.authorizationDateTime == null ? null : this.dateTimeConverter.parse(this.authorizationDateTime);
    }

    public long getEventProtocolNumber() {
        return this.eventProtocolNumber == null ? 0L : this.protocolNumberConverter.parse(this.eventProtocolNumber);
    }

    public ProcessedNFeStatus getNfeStatus() {
        return this.nfeStatus;
    }

}
