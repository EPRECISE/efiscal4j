
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
import javax.xml.bind.annotation.XmlTransient;

import eprecise.efiscal4j.commons.domain.FiscalDocumentVersion;
import eprecise.efiscal4j.commons.domain.adress.UF;
import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.TransmissionEnvironment;
import eprecise.efiscal4j.nfe.types.NFeAccessKey;
import eprecise.efiscal4j.nfe.types.NFeDateTimeUTC;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Tipo Retorno de Pedido de Consulta da Situação Atual da Nota Fiscal Eletrônica
 * 
 * @author Felipe Bueno
 * 
 */
@XmlRootElement(name = "retConsSitNFe")
@XmlAccessorType(XmlAccessType.FIELD)
public class NFeStatusSearchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public static String XSD = "/eprecise/efiscal4j/nfe/retConsSitNFe_v3.10.xsd";

    private @XmlAttribute(name = "versao") @NotNull final String version = FiscalDocumentVersion.NFE_VERSION;

    private @XmlAttribute(name = "xmlns") final String xmlns = "http://www.portalfiscal.inf.br/nfe";

    private @XmlElement(name = "tpAmb") @Valid @NotNull final TransmissionEnvironment transmissionEnvironment;

    private @XmlElement(name = "verAplic") @NotNull @Size(min = 1, max = 20) @NFeString final String applicationVersion;

    private @XmlElement(name = "cStat") @NotNull @Size(max = 3) @Pattern(regexp = "[0-9]{3}") final String statusCode;

    private @XmlElement(name = "xMotivo") @NotNull @Size(min = 1, max = 255) @NFeString final String statusDescription;

    private @XmlElement(name = "cUF") @NotNull final UF serviceUf;

    private @XmlElement(name = "dhRecbto") @NotNull @NFeDateTimeUTC final String receptionDateTime;

    private @XmlElement(name = "chNFe") @NotNull @NFeAccessKey final String acessKey;

    private @XmlElement(name = "protNFe") @Valid final ProcessingStatusProtocol processingStatusProtocol;

    private @XmlElement(name = "retCancNFe") @Valid final CancellationRequestResult cancellationRequestResult;

    private @XmlElement(name = "procEventoNFe") @Valid final ArrayList<EventProtocol> eventProtocols;

    private @XmlTransient String signedXml;

    public static class Builder {

        private TransmissionEnvironment transmissionEnvironment;

        private String applicationVersion;

        private String statusCode;

        private String statusDescription;

        private UF serviceUf;

        private String receptionDateTime;

        private String acessKey;

        private ProcessingStatusProtocol processingStatusProtocol;

        private CancellationRequestResult cancellationRequestResult;

        private ArrayList<EventProtocol> eventProtocols;

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
         * Código da UF responsável pelo serviço
         * 
         * @param serviceUf
         * @return
         */
        public Builder withServiceUf(UF serviceUf) {
            this.serviceUf = serviceUf;
            return this;
        }

        /**
         * Data e hora do recebimento da consulta
         * 
         * @param receptionDateTime
         * @return
         */
        public Builder withReceptionDateTime(String receptionDateTime) {
            this.receptionDateTime = receptionDateTime;
            return this;
        }

        /**
         * @see NFeAccessKey
         * @param acessKey
         * @return
         */
        public Builder withAcessKey(String acessKey) {
            this.acessKey = acessKey;
            return this;
        }

        /**
         * Protocolo de autorização de uso da NF-e
         * 
         * @see ProcessingStatusProtocol
         * @param processingStatusProtocol
         * @return
         */
        public Builder withProcessingStatusProtocol(ProcessingStatusProtocol processingStatusProtocol) {
            this.processingStatusProtocol = processingStatusProtocol;
            return this;
        }

        /**
         * Protocolo de homologação de cancelamento de uso da NF-e
         * 
         * @see CancellationRequestResult
         * @param cancellationRequestResult
         * @return
         */
        public Builder withCancellationRequestResult(CancellationRequestResult cancellationRequestResult) {
            this.cancellationRequestResult = cancellationRequestResult;
            return this;
        }

        /**
         * List of {@link EventProtocol}
         * 
         * @see EventProtocol
         * @param eventProtocols
         * @return
         */
        public Builder withEventProtocols(ArrayList<EventProtocol> eventProtocols) {
            this.eventProtocols = eventProtocols;
            return this;
        }

        public NFeStatusSearchResponse build() {
            final NFeStatusSearchResponse entity = new NFeStatusSearchResponse(this);
            ValidationBuilder.from(entity).validate().throwIfViolate();
            return entity;
        }
    }

    public NFeStatusSearchResponse() {
        this.transmissionEnvironment = null;
        this.applicationVersion = null;
        this.statusCode = null;
        this.statusDescription = null;
        this.serviceUf = null;
        this.receptionDateTime = null;
        this.acessKey = null;
        this.processingStatusProtocol = null;
        this.cancellationRequestResult = null;
        this.eventProtocols = null;
    }

    public NFeStatusSearchResponse(Builder builder) {
        this.transmissionEnvironment = builder.transmissionEnvironment;
        this.applicationVersion = builder.applicationVersion;
        this.statusCode = builder.statusCode;
        this.statusDescription = builder.statusDescription;
        this.serviceUf = builder.serviceUf;
        this.receptionDateTime = builder.receptionDateTime;
        this.acessKey = builder.acessKey;
        this.processingStatusProtocol = builder.processingStatusProtocol;
        this.cancellationRequestResult = builder.cancellationRequestResult;
        this.eventProtocols = builder.eventProtocols;
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

    public String getAcessKey() {
        return this.acessKey;
    }

    public ProcessingStatusProtocol getProcessingStatusProtocol() {
        return this.processingStatusProtocol;
    }

    public CancellationRequestResult getCancellationRequestResult() {
        return this.cancellationRequestResult;
    }

    public ArrayList<EventProtocol> getEventProtocols() {
        return this.eventProtocols;
    }
}
