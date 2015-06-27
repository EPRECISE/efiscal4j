
package eprecise.efiscal4j.nfe.sharing;

import java.io.Serializable;
import java.util.ArrayList;

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
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.transmission.ObjectFactory;
import eprecise.efiscal4j.nfe.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Tipo Retorno do Pedido de Consulta do Recido do Lote de Notas Fiscais Eletrônicas
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = ObjectFactory.RET_CONS_RECI_NFE)
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchReceiptSearchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public static String XSD = "/eprecise/efiscal4j/nfe/retConsReciNFe_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final FiscalDocumentVersion version = FiscalDocumentVersion.VERSION_3_10;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "nRec") @NotNull @Size(max = 15) @Pattern(regexp = "[0-9]{15}") final String receiptNumber;

    private @XmlElement(name = "cStat") @NotNull @Size(max = 3) @Pattern(regexp = "[0-9]{3}") final String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull @Size(min = 1, max = 255) @NFeString final String statusDescription;

    private @XmlElement(name = "cUF") @NotNull final UF serviceUf;

    private @XmlElement(name = "dhRecbto") @NotNull @NFeDateTimeUTC final String receptionDateTime;

    private @XmlElement(name = "cMsg") @Pattern(regexp = "[0-9]{1,4}") final String messageCode;

    private @XmlElement(name = "xMsg") @Size(min = 1, max = 200) @NFeString final String messageDescription;

    private @XmlElement(name = "protNFe") @Size(min = 0, max = 50) final ArrayList<ProcessingStatusProtocol> processingStatusProtocols;

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private String applicationVersion;

        private String receiptNumber;

        private String statusCode;

        private String statusDescription;

        private UF serviceUf;

        private String receptionDateTime;

        private String messageCode;

        private String messageDescription;

        private ArrayList<ProcessingStatusProtocol> processingStatusProtocols;

        /**
         * @see TransmissionEnvironment
         * @param transmissionEnvironment
         * @return
         */
        public Builder withTransmissionEnvironment(TransmissionEnvironment transmissionEnvironment) {
            this.transmissionEnvironment = transmissionEnvironment;
            return this;
        }

        /**
         * Versão do Aplicativo que processou a NF-e
         * 
         * @param applicationVersion
         * @return
         */
        public Builder withApplicationVersion(String applicationVersion) {
            this.applicationVersion = applicationVersion;
            return this;
        }

        /**
         * Número do Recibo
         * 
         * @param receiptNumber
         * @return
         */
        public Builder withReceiptNumber(String receiptNumber) {
            this.receiptNumber = receiptNumber;
            return this;
        }

        /**
         * Código do status da mensagem enviada.
         * 
         * @param statusCode
         * @return
         */
        public Builder withStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        /**
         * Descrição literal do status do serviço solicitado
         * 
         * @param statusDescription
         * @return
         */
        public Builder withStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
            return this;
        }

        /**
         * Código da UF de atendimento
         * 
         * @param serviceUf
         * @return
         */
        public Builder withServiceUf(UF serviceUf) {
            this.serviceUf = serviceUf;
            return this;
        }

        /**
         * Data e hora de processamento
         * 
         * @param receptionDateTime
         * @return
         */
        public Builder withReceptionDateTime(String receptionDateTime) {
            this.receptionDateTime = receptionDateTime;
            return this;
        }

        /**
         * Código da Mensagem
         * 
         * @param messageCode
         * @return
         */
        public Builder withMessageCode(String messageCode) {
            this.messageCode = messageCode;
            return this;
        }

        /**
         * Mensagem da SEFAZ para o emissor
         * 
         * @param messageDescription
         * @return
         */
        public Builder withMessageDescription(String messageDescription) {
            this.messageDescription = messageDescription;
            return this;
        }

        /**
         * List of {@link ProcessingStatusProtocol}
         * 
         * @see ProcessingStatusProtocol
         * @param processingStatusProtocols
         * @return
         */
        public Builder withProcessingStatusProtocols(ArrayList<ProcessingStatusProtocol> processingStatusProtocols) {
            this.processingStatusProtocols = processingStatusProtocols;
            return this;
        }

        public BatchReceiptSearchResponse build() {
            final BatchReceiptSearchResponse entity = new BatchReceiptSearchResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public BatchReceiptSearchResponse() {
        this.transmissionEnvironment = null;
        this.applicationVersion = null;
        this.receiptNumber = null;
        this.statusCode = null;
        this.statusDescription = null;
        this.serviceUf = null;
        this.receptionDateTime = null;
        this.messageCode = null;
        this.messageDescription = null;
        this.processingStatusProtocols = null;
    }

    public BatchReceiptSearchResponse(Builder builder) {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.receiptNumber = builder.receiptNumber;
        this.statusCode = builder.statusCode;
        this.statusDescription = builder.statusDescription;
        this.serviceUf = builder.serviceUf;
        this.receptionDateTime = builder.receptionDateTime;
        this.messageCode = builder.messageCode;
        this.messageDescription = builder.messageDescription;
        this.processingStatusProtocols = builder.processingStatusProtocols;
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

    public String getReceiptNumber() {
        return this.receiptNumber;
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

    public String getMessageCode() {
        return this.messageCode;
    }

    public String getMessageDescription() {
        return this.messageDescription;
    }

    public ArrayList<ProcessingStatusProtocol> getProcessingStatusProtocols() {
        return this.processingStatusProtocols;
    }

}
