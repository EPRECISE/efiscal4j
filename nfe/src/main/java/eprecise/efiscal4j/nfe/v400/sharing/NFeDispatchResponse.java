
package eprecise.efiscal4j.nfe.v400.sharing;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.v400.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.v400.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.v400.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.v400.types.NFeString;


/**
 * Retorno do Pedido de Concessão de Autorização da Nota Fiscal Eletrônica
 *
 * @author Felipe Bueno
 *
 */
@XmlRootElement(name = ObjectFactory.RET_ENVI_NFE, namespace = "http://www.portalfiscal.inf.br/nfe")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeDispatchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String XSD = "/eprecise/efiscal4j/nfe/v400/xsd/retEnviNFe_v4.00.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_4_00;

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "cStat") @NotNull @Size(max = 3) @Pattern(regexp = "[0-9]{3}") final String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull @Size(min = 1, max = 255) @NFeString final String statusDescription;

    private @XmlElement(name = "cUF") @NotNull final UF serviceUf;

    private @XmlElement(name = "dhRecbto") @NotNull @NFeDateTimeUTC final String receptionDateTime;

    private @XmlElement(name = "cMsg") final String msgCode;

    private @XmlElement(name = "xMsg") final String msgDescription;

    private @XmlElement(name = "infRec") @Valid final BatchReceipt batchReceipt;

    private @XmlElement(name = "protNFe") @Valid final ProcessingStatusProtocol processingStatusProtocol;

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private String applicationVersion;

        private String statusCode;

        private String statusDescription;

        private UF serviceUf;

        private String receptionDateTime;

        private String msgCode;

        private String msgDescription;

        private BatchReceipt batchReceipt;

        private ProcessingStatusProtocol processingStatusProtocol;

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
         * Versão do Aplicativo que recebeu o Lote
         *
         * @param applicationVersion
         * @return
         */
        public Builder withApplicationVersion(final String applicationVersion) {
            this.applicationVersion = applicationVersion;
            return this;
        }

        /**
         * Código do status da mensagem enviada.
         *
         * @param statusCode
         * @return
         */
        public Builder withStatusCode(final String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        /**
         * Descrição literal do status do serviço solicitado
         *
         * @param statusDescription
         * @return
         */
        public Builder withStatusDescription(final String statusDescription) {
            this.statusDescription = statusDescription;
            return this;
        }

        /**
         * Código da UF de atendimento
         *
         * @param serviceUf
         * @return
         */
        public Builder withServiceUf(final UF serviceUf) {
            this.serviceUf = serviceUf;
            return this;
        }

        /**
         * Data e hora do recebimento
         *
         * @param receptionDateTime
         * @return
         */
        public Builder withReceptionDateTime(final String receptionDateTime) {
            this.receptionDateTime = receptionDateTime;
            return this;
        }

        /**
         * Código da Mensagem
         *
         * @param msgCode
         * @return
         */
        public Builder withMsgCode(final String msgCode) {
            this.msgCode = msgCode;
            return this;
        }

        /**
         * Mensagem da SEFAZ para o emissor
         *
         * @param msgDescription
         * @return
         */
        public Builder withMsgDescription(final String msgDescription) {
            this.msgDescription = msgDescription;
            return this;
        }

        /**
         * @see BatchReceipt
         * @param batchReceipt
         * @return
         */
        public Builder withBatchReceipt(final BatchReceipt batchReceipt) {
            this.batchReceipt = batchReceipt;
            return this;
        }

        /**
         * @see ProcessingStatusProtocol
         * @param processingStatusProtocol
         * @return
         */
        public Builder withProcessingStatusProtocol(final ProcessingStatusProtocol processingStatusProtocol) {
            this.processingStatusProtocol = processingStatusProtocol;
            return this;
        }

        public NFeDispatchResponse build() {
            final NFeDispatchResponse entity = new NFeDispatchResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeDispatchResponse() {
        this.transmissionEnvironment = null;
        this.applicationVersion = null;
        this.statusCode = null;
        this.statusDescription = null;
        this.serviceUf = null;
        this.receptionDateTime = null;
        this.msgCode = null;
        this.msgDescription = null;
        this.batchReceipt = null;
        this.processingStatusProtocol = null;
    }

    public NFeDispatchResponse(final Builder builder) {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.statusCode = builder.statusCode;
        this.statusDescription = builder.statusDescription;
        this.serviceUf = builder.serviceUf;
        this.receptionDateTime = builder.receptionDateTime;
        this.msgCode = builder.msgCode;
        this.msgDescription = builder.msgDescription;
        this.batchReceipt = builder.batchReceipt;
        this.processingStatusProtocol = builder.processingStatusProtocol;
    }

    public FiscalDocumentVersion getVersion() {
        return this.version;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public TransmissionEnvironment getTransmissionEnvironment() {
        return this.transmissionEnvironment;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public UF getServiceUf() {
        return this.serviceUf;
    }

    public String getReceptionDateTime() {
        return this.receptionDateTime;
    }

    public String getMsgCode() {
        return this.msgCode;
    }

    public String getMsgDescription() {
        return this.msgDescription;
    }

    public BatchReceipt getBatchReceipt() {
        return this.batchReceipt;
    }

    public ProcessingStatusProtocol getProcessingStatusProtocol() {
        return this.processingStatusProtocol;
    }

}
