
package eprecise.efiscal4j.nfe.v310.sharing;

import java.io.Serializable;
import java.text.ParseException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringUtils;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v310.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v310.types.NFeCNPJ;
import eprecise.efiscal4j.nfe.v310.types.NFeFiscalDocumentNumber;
import eprecise.efiscal4j.nfe.v310.types.NFeFiscalDocumentSeries;
import eprecise.efiscal4j.nfe.v310.types.NFeString;
import eprecise.efiscal4j.nfe.v310.types.NFeYear;


/**
 *
 * Dados do Pedido de Inutilização de Numeração
 *
 * @author Fernando C Glizt
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeNumberDisableInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") @Pattern(regexp = "ID[0-9]{41}") final String id;

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "xServ") @NotNull @NFeString final String requestedService = "INUTILIZAR";

    private @XmlElement(name = "cUF") UF ufIbgeCode;

    private @XmlElement(name = "ano") @NFeYear @NotNull final String year;

    private @XmlElement(name = "CNPJ") @NFeCNPJ @NotNull final String cnpj;

    private @XmlElement(name = "mod") @NotNull final FiscalDocumentModel fiscalDocumentModel;

    private @XmlElement(name = "serie") @NotNull @NFeFiscalDocumentSeries final String fiscalDocumentSeries;

    private @XmlElement(name = "nNFIni") @NotNull @NFeFiscalDocumentNumber final String beginNumber;

    private @XmlElement(name = "nNFFin") @NotNull @NFeFiscalDocumentNumber final String endNumber;

    private @XmlElement(name = "xJust") @NotNull @Size(min = 15, max = 255) @NFeString final String justification;

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private UF ufIbgeCode;

        private String year;

        private String cnpj;

        private FiscalDocumentModel fiscalDocumentModel;

        private String fiscalDocumentSeries;

        private String beginNumber;

        private String endNumber;

        private String justification;

        /**
         * @see TransmissionEnvironment
         * @param transmissionEnvironment
         * @return
         */
        public Builder withTransmissionEnvironment(final TransmissionEnvironment transmissionEnvironment) {
            this.transmissionEnvironment = transmissionEnvironment;
            return this;
        }

        /**
         * @see UF
         * @param ufIbgeCode
         * @return
         */
        public Builder withUfIbgeCode(final UF ufIbgeCode) {
            this.ufIbgeCode = ufIbgeCode;
            return this;
        }

        /**
         * @see NFeYear
         * @param year
         * @return
         */
        public Builder withYear(final String year) {
            this.year = year;
            return this;
        }

        /**
         * @see NFeCNPJ
         * @param cnpj
         * @return
         */
        public Builder withCnpj(final String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Builder withFiscalDocumentModel(final FiscalDocumentModel fiscalDocumentModel) {
            this.fiscalDocumentModel = fiscalDocumentModel;
            return this;
        }

        /**
         * Serie Normal 0-889 Avulsa Fisco 890-899 SCAN 900-999
         *
         * @param fiscalDocumentSeries
         * @return
         */
        public Builder withFiscalDocumentSeries(final String fiscalDocumentSeries) {
            this.fiscalDocumentSeries = fiscalDocumentSeries;
            return this;
        }

        /**
         * @see NFeFiscalDocumentNumber
         * @param beginNumber
         * @return
         */
        public Builder withBeginNumber(final String beginNumber) {
            this.beginNumber = beginNumber;
            return this;
        }

        /**
         * @see NFeFiscalDocumentNumber
         * @param endNumber
         * @return
         */
        public Builder withEndNumber(final String endNumber) {
            this.endNumber = endNumber;
            return this;
        }

        /**
         * @param justification
         * @return
         */
        public Builder withJustification(final String justification) {
            this.justification = justification;
            return this;
        }

        public NFeNumberDisableInfo build() throws Exception {
            final NFeNumberDisableInfo entity = new NFeNumberDisableInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }

    }

    public NFeNumberDisableInfo() {
        this.id = null;
        this.transmissionEnvironment = null;
        this.ufIbgeCode = null;
        this.year = null;
        this.cnpj = null;
        this.fiscalDocumentModel = null;
        this.fiscalDocumentSeries = null;
        this.beginNumber = null;
        this.endNumber = null;
        this.justification = null;
    }

    public NFeNumberDisableInfo(final Builder builder) throws ParseException {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.ufIbgeCode = builder.ufIbgeCode;
        this.year = builder.year;
        this.cnpj = builder.cnpj;
        this.fiscalDocumentModel = builder.fiscalDocumentModel;
        this.fiscalDocumentSeries = builder.fiscalDocumentSeries;
        this.beginNumber = builder.beginNumber;
        this.endNumber = builder.endNumber;
        this.justification = builder.justification;
        this.id = this.generateId();
    }

    /**
     * Gera o ID da inutilização da NF-e, que é composto por:
     *
     * <ul>
     * <li>ID - texto contedo ID</li>
     * <li>UF - Código da UF do emitente do Documento Fiscal</li>
     * <li>AA - Ano de emissão da NF-e</li>
     * <li>CNPJ - CNPJ do emitente</li>
     * <li>mod - Modelo do Documento Fiscal</li>
     * <li>serie - Série do Documento Fiscal</li>
     * <li>nNFIni - Número inicial a ser inutilizado</li>
     * <li>nNFFin - Número final a ser inutilizado</li>
     * </ul>
     *
     * @param builder
     * @throws ParseException
     */
    private String generateId() throws ParseException {
        final StringBuilder inutId = new StringBuilder();

        inutId.append(this.ufIbgeCode.getIbgeCode());
        inutId.append(this.year);
        inutId.append(this.cnpj);
        inutId.append(this.fiscalDocumentModel.getValue());
        inutId.append(StringUtils.leftPad(this.fiscalDocumentSeries, 3, "0"));
        inutId.append(StringUtils.leftPad(this.beginNumber, 9, "0"));
        inutId.append(StringUtils.leftPad(this.endNumber, 9, "0"));

        inutId.insert(0, "ID");

        return inutId.toString();
    }

    public UF getUfIbgeCode() {
        return this.ufIbgeCode;
    }

    public void setUfIbgeCode(final UF ufIbgeCode) {
        this.ufIbgeCode = ufIbgeCode;
    }

    public String getId() {
        return this.id;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getRequestedService() {
        return this.requestedService;
    }

    public String getYear() {
        return this.year;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public FiscalDocumentModel getFiscalDocumentModel() {
        return this.fiscalDocumentModel;
    }

    public String getFiscalDocumentSeries() {
        return this.fiscalDocumentSeries;
    }

    public String getBeginNumber() {
        return this.beginNumber;
    }

    public String getEndNumber() {
        return this.endNumber;
    }

    public String getJustification() {
        return this.justification;
    }

}
