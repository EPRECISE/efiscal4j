
package eprecise.efiscal4j.nfe;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentModel;
import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.additionalinfo.AdditionalInfo;
import eprecise.efiscal4j.nfe.charging.NFeCharging;
import eprecise.efiscal4j.nfe.payment.NFePayment;
import eprecise.efiscal4j.nfe.person.Emitter;
import eprecise.efiscal4j.nfe.person.Receiver;
import eprecise.efiscal4j.nfe.total.NFeTotal;
import eprecise.efiscal4j.nfe.transport.NFeTransport;
import eprecise.efiscal4j.nfe.validation.NFePaymentValidation;


/**
 * Informações da Nota Fiscal eletrônica
 *
 * @author Felipe Bueno
 *
 */
@NFePaymentValidation
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private @XmlAttribute(name = "Id") @Pattern(regexp = "NFe[0-9]{44}") final String id;

    private @XmlAttribute(name = "versao") final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlElement(name = "ide") @NotNull @Valid final NFeIdentification nFeIdentification;

    private @XmlElement(name = "emit") @NotNull final Emitter emitter;

    private @XmlElement(name = "dest") final Receiver receiver;

    private @XmlElement(name = "det") @Size(max = 990) @NotNull @Valid final List<NFeDetail> nFeDetails;

    private @XmlElement(name = "total") @NotNull @Valid final NFeTotal nFeTotal;

    private @XmlElement(name = "transp") @NotNull final NFeTransport nFeTransport;

    private @XmlElement(name = "cobr") final NFeCharging nFeCharging;

    private @XmlElement(name = "pag") @Size(min = 0, max = 100) final List<NFePayment> nFePayments;

    private @XmlElement(name = "infAdic") final AdditionalInfo additionalInfo;

    public static class Builder {

        private NFeIdentification nFeIdentification;

        private Emitter emitter;

        private Receiver receiver;

        private List<NFeDetail> nFeDetails;

        private NFeTotal nFeTotal;

        private NFeTransport nFeTransport;

        private NFeCharging nFeCharging;

        private List<NFePayment> nFePayments;

        private AdditionalInfo additionalInfo;

        /**
         * @see NFeIdentification
         * @param nFeIdentification
         * @return
         */
        public Builder withNFeIdentification(final NFeIdentification nFeIdentification) {
            this.nFeIdentification = nFeIdentification;
            return this;
        }

        /**
         * @see Emitter
         * @param emitter
         * @return
         */
        public Builder withEmitter(final Emitter emitter) {
            this.emitter = emitter;
            return this;
        }

        /**
         * @see Receiver
         * @param receiver
         * @return
         */
        public Builder withReceiver(final Receiver receiver) {
            this.receiver = receiver;
            return this;
        }

        /**
         * List of NFeDetail
         *
         * @see NFeDetail
         * @param nFeDetails
         * @return
         */
        public Builder withNFeDetail(final List<NFeDetail> nFeDetails) {
            this.nFeDetails = nFeDetails;
            return this;
        }

        /**
         * @see NFeTotal
         * @param nFeTotal
         * @return
         */
        public Builder withNFeTotal(final NFeTotal nFeTotal) {
            this.nFeTotal = nFeTotal;
            return this;
        }

        /**
         * @see NFeTransport
         * @param nFeTransport
         * @return
         */
        public Builder withNFeTransport(final NFeTransport nFeTransport) {
            this.nFeTransport = nFeTransport;
            return this;
        }

        /**
         * @see NFeCharging
         * @param nFeCharging
         * @return
         */
        public Builder withNFeCharging(final NFeCharging nFeCharging) {
            this.nFeCharging = nFeCharging;
            return this;
        }

        /**
         * List of NFePayment
         *
         * @see NFePayment
         * @param nFePayments
         * @return
         */
        public Builder withNFePayments(final List<NFePayment> nFePayments) {
            this.nFePayments = nFePayments;
            return this;
        }

        /**
         * @see AdditionalInfo
         * @param additionalInfo
         * @return
         */
        public Builder withAdditionalInfo(final AdditionalInfo additionalInfo) {
            this.additionalInfo = additionalInfo;
            return this;
        }

        public NFeInfo build() throws ParseException {
            final NFeInfo entity = new NFeInfo(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    protected NFeInfo() {
        this.nFeIdentification = null;
        this.emitter = null;
        this.receiver = null;
        this.nFeDetails = null;
        this.nFeTotal = null;
        this.nFeTransport = null;
        this.nFeCharging = null;
        this.nFePayments = null;
        this.additionalInfo = null;
        this.id = null;
    }

    protected NFeInfo(final Builder builder) throws ParseException {
        this.nFeIdentification = builder.nFeIdentification;
        this.emitter = builder.emitter;
        this.receiver = builder.receiver;
        this.nFeDetails = builder.nFeDetails;
        this.nFeTotal = builder.nFeTotal;
        this.nFeTransport = builder.nFeTransport;
        this.nFeCharging = builder.nFeCharging;
        this.nFePayments = builder.nFePayments;
        this.additionalInfo = builder.additionalInfo;
        this.id = this.generateNfeId();
        this.fillCalculableFields();
    }

    private void fillCalculableFields() {
        if (this.getReceiver() != null && this.getnFeIdentification().getTransmissionEnvironment() == TransmissionEnvironment.HOMOLOGACAO) {
            this.getReceiver().getDocuments().setAbstractName("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        }

        if (this.getnFeIdentification().getFiscalDocumentModel().equals(FiscalDocumentModel.NFCE) && this.getnFeIdentification().getTransmissionEnvironment() == TransmissionEnvironment.HOMOLOGACAO) {
            this.nFeDetails.stream().map(NFeDetail::getnFeItem).findFirst().ifPresent(nfeItem -> nfeItem.setItemDescription("NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL"));
        }

        final DecimalFormatSymbols separatorSymbols = new DecimalFormatSymbols();
        separatorSymbols.setDecimalSeparator('.');
        final DecimalFormat decimalFormat = new DecimalFormat("##0.00", separatorSymbols);
        decimalFormat.setGroupingUsed(false);

        if (this.getnFeTotal().getIcmsTotal() != null) {
            Double pisTotal = 0.0;
            Double cofinsTotal = 0.0;
            Double ipiTotal = 0.0;
            Double iiTotal = 0.0;
            for (final NFeDetail nFeDetail : this.getnFeDetails()) {
                pisTotal += (nFeDetail.getTax().getPis() == null ? 0.0 : Double.valueOf(nFeDetail.getTax().getPis().getPisValue()));
                cofinsTotal += (nFeDetail.getTax().getCofins() == null ? 0.0 : Double.valueOf(nFeDetail.getTax().getCofins().getCofinsValue()));
                ipiTotal += (nFeDetail.getTax().getIpi() == null ? 0.0 : Double.valueOf(nFeDetail.getTax().getIpi().getIpiValue()));
                iiTotal += (nFeDetail.getTax().getIi() == null ? 0.0 : Double.valueOf(nFeDetail.getTax().getIi().getIiValue()));
            }

            this.getnFeTotal().getIcmsTotal().setPisTotalValue(decimalFormat.format(pisTotal));
            this.getnFeTotal().getIcmsTotal().setCofinsTotalValue(decimalFormat.format(cofinsTotal));
            this.getnFeTotal().getIcmsTotal().setIpiTotalValue(decimalFormat.format(ipiTotal));
            this.getnFeTotal().getIcmsTotal().setIiTotalValue(decimalFormat.format(iiTotal));
        }
    }

    /**
     * Gera o ID da NF-e, que é composto por:
     *
     * <ul>
     * <li>NFe - texto contendo NFe</li>
     * <li>UF - Código da UF do emitente do Documento Fiscal</li>
     * <li>AAMM - Ano e Mês de emissão da NF-e</li>
     * <li>CNPJ - CNPJ do emitente</li>
     * <li>mod - Modelo do Documento Fiscal</li>
     * <li>serie - Série do Documento Fiscal</li>
     * <li>nNF - Número do Documento Fiscal</li>
     * <li>tpEmis - forma de emissão da NF-e</li>
     * <li>cNF - Código Numérico que compõe a Chave de Acesso</li>
     * <li>cDV - Dígito Verificador da Chave de Acesso</li>
     * </ul>
     *
     * @param builder
     * @throws ParseException
     */
    private String generateNfeId() throws ParseException {
        final StringBuilder nfeId = new StringBuilder();
        final Date emissionDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(this.getnFeIdentification().getEmissionDateTime());

        nfeId.append(this.getnFeIdentification().getUfIbgeCode().getIbgeCode());
        nfeId.append(new SimpleDateFormat("yy").format(emissionDate));
        nfeId.append(new SimpleDateFormat("MM").format(emissionDate));
        nfeId.append(this.getEmitter().getDocuments().getCnpjCpf());
        nfeId.append(this.getnFeIdentification().getFiscalDocumentModel().getValue());
        nfeId.append(new DecimalFormat("000").format(Integer.valueOf(this.getnFeIdentification().getFiscalDocumentSeries())));
        nfeId.append(new DecimalFormat("000000000").format(Integer.valueOf(this.getnFeIdentification().getFiscalDocumentNumber())));
        nfeId.append(this.getnFeIdentification().getnFeTransmissionMethod().getValue());
        nfeId.append(this.getnFeIdentification().getnFeCode());

        this.getnFeIdentification().setChecksum(this.calcModule11(nfeId.toString()));

        nfeId.append(this.getnFeIdentification().getChecksum());

        nfeId.insert(0, "NFe");

        return nfeId.toString();
    }

    private int calcModule11(final String key) {
        int total = 0;
        int weight = 2;

        for (int i = 0; i < key.length(); i++) {
            total += (key.charAt((key.length() - 1) - i) - '0') * weight;
            weight++;
            if (weight == 10) {
                weight = 2;
            }
        }

        final int remainder = total % 11;
        return (remainder == 0 || remainder == 1) ? 0 : (11 - remainder);
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getId() {
        return this.id;
    }

    public NFeIdentification getnFeIdentification() {
        return this.nFeIdentification;
    }

    public Emitter getEmitter() {
        return this.emitter;
    }

    public Receiver getReceiver() {
        return this.receiver;
    }

    public List<NFeDetail> getnFeDetails() {
        return this.nFeDetails;
    }

    public NFeTotal getnFeTotal() {
        return this.nFeTotal;
    }

    public NFeTransport getnFeTransport() {
        return this.nFeTransport;
    }

    public NFeCharging getnFeCharging() {
        return this.nFeCharging;
    }

    public List<NFePayment> getnFePayments() {
        return this.nFePayments;
    }

    public AdditionalInfo getAdditionalInfo() {
        return this.additionalInfo;
    }

}
