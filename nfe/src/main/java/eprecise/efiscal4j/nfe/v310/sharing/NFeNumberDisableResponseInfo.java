
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.v310.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.v310.types.NFeFiscalDocumentNumber;
import eprecise.efiscal4j.nfe.v310.types.NFeFiscalDocumentSeries;
import eprecise.efiscal4j.nfe.v310.types.NFeYear;


/**
 *
 * Dados do Pedido de Inutilização de Numeração
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeNumberDisableResponseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") String id;

    private @XmlElement(name = "tpAmb") @Valid @NotNull TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull String appVersion;

    private @XmlElement(name = "cStat") @NotNull String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull String reason;

    private @XmlElement(name = "cUF") UF ufIbgeCode;

    private @XmlElement(name = "ano") @NFeYear String year;

    private @XmlElement(name = "CNPJ") @NFeCNPJ String cnpj;

    private @XmlElement(name = "mod") FiscalDocumentModel fiscalDocumentModel;

    private @XmlElement(name = "serie") @NFeFiscalDocumentSeries String fiscalDocumentSeries;

    private @XmlElement(name = "nNFIni") @NFeFiscalDocumentNumber String beginNumber;

    private @XmlElement(name = "nNFFin") @NFeFiscalDocumentNumber String endNumber;

    private @XmlElement(name = "dhRecbto") @NFeDateTimeUTC String receptionDateTime;

    private @XmlElement(name = "nProt") @Size(max = 15) @Pattern(regexp = "[0-9]{15}") String protocolNumber;

    public NFeNumberDisableResponseInfo() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public void setTransmissionEnvironment(final TransmissionEnvironment transmissionEnvironment) {
        this.transmissionEnvironment = transmissionEnvironment;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public UF getUfIbgeCode() {
        return this.ufIbgeCode;
    }

    public void setUfIbgeCode(final UF ufIbgeCode) {
        this.ufIbgeCode = ufIbgeCode;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(final String year) {
        this.year = year;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    public FiscalDocumentModel getFiscalDocumentModel() {
        return this.fiscalDocumentModel;
    }

    public void setFiscalDocumentModel(final FiscalDocumentModel fiscalDocumentModel) {
        this.fiscalDocumentModel = fiscalDocumentModel;
    }

    public String getFiscalDocumentSeries() {
        return this.fiscalDocumentSeries;
    }

    public void setFiscalDocumentSeries(final String fiscalDocumentSeries) {
        this.fiscalDocumentSeries = fiscalDocumentSeries;
    }

    public String getBeginNumber() {
        return this.beginNumber;
    }

    public void setBeginNumber(final String beginNumber) {
        this.beginNumber = beginNumber;
    }

    public String getEndNumber() {
        return this.endNumber;
    }

    public void setEndNumber(final String endNumber) {
        this.endNumber = endNumber;
    }

    public String getReceptionDateTime() {
        return this.receptionDateTime;
    }

    public void setReceptionDateTime(final String receptionDateTime) {
        this.receptionDateTime = receptionDateTime;
    }

    public String getProtocolNumber() {
        return this.protocolNumber;
    }

    public void setProtocolNumber(final String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

}
